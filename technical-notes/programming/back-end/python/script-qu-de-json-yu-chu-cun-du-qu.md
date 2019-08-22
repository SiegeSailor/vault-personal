# Script 取得資料與儲存讀取

## 概述

假設我們有些 API，透過提供參數後能夠取得 JSON 資料，接著，我們希望能夠根據情況不同使用不同的 API 來取得對應的資料以 JSON 格式存到本地，並在之後以 JSON 的格式將資料讀取出來。

### 專案結構

這是一個 Django 的專案，但有無使用 Django 並不影響這個主題。

* assets
  * js
    * pages
      * school\_list.json
* mysite
* rating
  * auto\_fetch.py
  * auto\_fetch\_execute.py
  * services.py
* venv
  * bin
    * activate

## 指令碼

以下程式碼皆在 `auto_fetch_execute.sh` 內。

### 宣告

這是一個 Shell Script 需注意的地方，在每個 .sh 檔案內的第一行註解並非註解，而是宣告要用何種 Shell 來執行該 .sh 檔案：

```bash
#!/bin/bash
```

{% hint style="info" %}
常見的 Shell 有 SH、BASH、ZSH 和 FISH。
{% endhint %}

### 對 Python 的指令

通常而言，Python 執行都會在 Virtual Environment 中指名要執行的版本以及相關套件，同時在執行 .py 檔前要先宣告使用的 Python。

#### 啟動虛擬環境

因此在執行 Python 之前首先要啟動 Virtual Environment：

```bash
. venv/bin/activate
```

{% hint style="warning" %}
注意此處以 . 取代 source，這是因為執行環境時有些 bash 並不支援 source。
{% endhint %}

#### 執行檔與帶入參數

```bash
python rating/auto_fetch.py $1
```

{% hint style="info" %}
$1 會是你在執行 auto\_fetch\_execute.sh 時後面接的第一個參數，若有需要則能接著 $2、$3。$0 會是接取參數並執行的檔案，也就是 auto\_fetch.py。
{% endhint %}

### 一般指令

同時， Shell Script 裡也能使用一般在 Command Line 中使用的指令，這裡假設在 Linux 上：

```bash
cp rating/school_list.json assets/js/pages/school_list.json
```

### 完整指令碼

```bash
#!/bin/bash
. venv/bin/activate
python rating/auto_fetch_school_list.py $1
cp rating/school_list.json assets/js/pages/school_list.json
```

#### 執行指令

```bash
sh rating/auto_fetch_sh.sh test
```

## 寫入用腳本

以下程式碼皆在 `auto_fetch.py` 內。

### 應用模組

首先，我們匯入必須的模組：

```python
import requests # 向 API 要求資料用
import sys # 使 Shell Script 能在執行 auto_fetch.py 時帶入參數
from json.decoder import JSONDecodeError # 資料要求錯誤時的 fallback
import json # 資料解析
```

### 接取參數

接著，使用 `sys` 來接取 Shell Script 在執行 auto\_fetch.py 帶入的資數。這邊可以在與 auto\_fetch\_execute.sh 對照後了解如何運作：

```python
file_name = sys.argv[0] # 第一個參數會是執行檔案的名稱，即 auto_fetch.py
mode = sys.argv[1] # 接取第二個參數
```

### 定義函式

再來定義用來接取與存取資料的函式：

```python
def auto_fetch_request(mode):
    
    # mode 會是 Shell Script 在執行時帶入的第二個參數，這邊以該值判斷 API 的主 URL，
    # 接著與子 URL 組合成 API 字串
    if mode == "test":
        base_url = "https://apipro0.edallianz.net"
    elif mode == "release":
        base_url = 'https://apipro.edallianz.net'
    api_url = base_url + "/rating/list/cram/?page=1&page_length=10&preload=true"
    
    # 宣告參數，並使用 request 對 API 取得資料。這邊會因 API 而有所不同
    params = {'name': None, 'country': "Taiwan"}
    response = requests.request("GET", api_url, data=params)
    
    result = {} # 宣告要存取資料的變數

    # 這裡以 try 與 json() 來做資料取得錯誤的 Fallback
    try:
        result = response.json()
    except JSONDecodeError:
        return
    
    # 在 with 內以 w 寫入、不存在時則建立的方式開啟要存放資料的 school_list.json，
    # 當檔案使用結束，會自動關閉以節省效能
    with open("rating/school_list.json", "w+") as target:
        # 使用 json.dump() 將 JSON 儲存在指定檔案內，
        # 而由於 json.dump() 默認將中文作為 ascii 字元儲存，如若資料內有中國，儲存的將
        # 會是 "\u4e2d\u56fd" 而非 "中國"，因此設 ensure_ascii=False 確保儲存中文
        json.dump(result, target, ensure_ascii=False)

    return
    
auto_fetch_request(mode)
```

## 讀取用腳本

以下程式碼皆在 `services.py` 內。

### 應用模組

首先，我們匯入必須的模組：

```python
import json
```

### 定義函式

再來定義用來讀取資料的函式：

```python
def get_data_from_local(request):
    school_list = set({}) # 以 set() 建立空的 dict
    
    # 使用 encoding='UTF-8' 因為資料內含有中文
    with open("rating/school_list.json", "r", encoding='UTF-8') as data_from_local:
        school_list = data_from_local
        
        # 在 with 內以 read() 開啟資料，再以 json.load() 解析 JSON
        school_list = json.loads(school_list.read())
        return school_list
```

