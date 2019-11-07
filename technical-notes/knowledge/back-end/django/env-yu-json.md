# ENV 與 JSON

## 概述

在 Django 專案中，我們經常使用一個 `env` 資料夾裝載 `.env` 的環境變量，以根據環境變化因素。下列是一個常見的範例。

* project
  * log
  * outcome
    * urls.py
    * views.py
    * service.py
  * project
    * settings
      * base.py
      * source.py
    * settings.py
    * urls.py
* env
  * project.env

在結構確定後，我們會通常會在 settings.py 內以 `os.getenv` 的方式取得 ENV 資料，並在他處以由 Settings 匯入的方式來統一匯入這些環境變量。

#### project.env

```text
MAIL_ACCOUNT_DEFAULT={"EMAIL_USE_SSL" : true,"EMAIL_USE_TLS" : false,"EMAIL_HOST" : "smtp.zoho.com","EMAIL_PORT" : "465","EMAIL_HOST_USER" : "membersvc@cs.lejourneyapp.com","EMAIL_HOST_PASSWORD" : "OlgvG~TwCq#8"}
```

#### settings.py

```python
import osMAIL_ACCOUNT_DEFAULT= os.getenv('MAIL_ACCOUNT_DEFAULT', {})
```

#### views.py

```python
from django.conf import settingsimport jsonsender_email = settings.MAIL_ACCOUNT_DEFAULT
```

其中，要注意的是在匯入環境變量的過程中須注意幾點。

* 使用單一個 ENV 檔案 :: `os.getenv` 只會獲取第一個傳值的檔案內的值
* 不可斷行 :: ENV 檔案內為 String 的 Key Value 類型，分行即代表不在同一個 Key 內，會導致只取得該 Key 內的第一行的字串
* 在正確的時機使用正確的 JSON 解析 :: 若未遵從，會產生不同的解析錯誤。而若在 Setting 內獲取環境變量的同時解析錯誤，如 `MAIL_ACCOUNT_DEFAULT = json.dump(os.getenv('MAIL_ACCOUNT_DEFAULT', {}))` 則會在 Docker Terminal 內出現 _**no python application found, check your startup logs for errors**_ 的訊息。

在 `import json` 後，可以使用四種解析方式。

* json.dumps :: 在內存內的儲存處理。
* json.loads :: 在內存內的讀取處理，接受 String。
* json.dump :: 對檔案的儲存處理。
* json.load :: 對檔案的讀取處理，接受 File Name。

在上述環境變數匯入的範例中，由於使用 `os.getenv` 匯入到 Django 內，已存在內存中，因此應使用 json.loads 來將該 Key 的 String 轉為 JSON、Dictionary 使用。

