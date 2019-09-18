# Methods

## Overview

Common methods.

## Check If A Key Exists

Use this to check if there is the key.

```python
if result['apiURL'] is None:
    return
```

Or check if a key is in a dictionary.

```python
if "test" in wordFreqDic:
    return
```

```python
if "key" not in request.session:
    return
```

## Appending A Value

```python
d['stupdid'].append(foo)
```

## Wipe Empty Character

This method allows you to wipe empty character around a string.

```python
a = " abec "
len(a)
>>> 6
len(a.strip())
>>> 3
```

## Round Alternative

Usually we use `round()` to get point numbers, but the method is not always rendering the right number we want, so here is another solution.

```python
real_time_score = int('0.555')
real_time_score = '%.1f' % real_time_score
```

`%.1f` can be `%.2f` or `%.3f`, depends on how many point numbers you want to have.

{% hint style="info" %}
See [Here](https://blog.csdn.net/chenmozhe22/article/details/81666831).
{% endhint %}

## Get Related Value with a Key

To compare a key's value to a key's value from a dict with `next`, then get or replace the value from the dict under the key.

```python
for indexOuter in range(len(response['data'])):
        for indexInner in range(len(response['data'][indexOuter]['replies'])):
            response['data'][indexOuter]['replies'][indexInner]['question'] = (next(
                item for item in request.session['questions']['data'] if item["id"] == response['data'][indexOuter]['replies'][indexInner]['question_id']))['question']
            response['data'][indexOuter]['replies'][indexInner]['question_des'] = (next(
                item for item in request.session['questions']['data'] if item["id"] == response['data'][indexOuter]['replies'][indexInner]['question_id']))['question_des']
```

## 類 Switch Case 表達式

與 Jave 或 C , C++ 等語言不同，Python 原生語法中並不提供 switch 的語法，因此，我們必須使用另一種方式來實現。

首先假設我們有個 view，而這個 view 要求使用者輸入一個值 `type`：

```python
class Survey(View):
    def get(self, request, type='goodclass'):
```

接著我們創建一個 dict，每個 key 的值都是使用者可能輸入在 `type` 中的值：

```python
number = {
    "goodclass": '0',
    "experience": '1',
    "participate": '2',
    'parent': '3',
    'classforchild': '4',
}
```

而在另一個 dict，或者任何要傳出值的語句中，我們使用 `dict.get()` 的方法來實現 switch 的語法：

```python
output = {
    "banner": number.get(type, None),
}
```

這麼一來，當使用者輸入 type 的值為 experience 時，output 這個 dict 裡的 banner 這個 key 的值就會是 1。由於有加上 default 值，若 type 裡的值不合所有 number dict 的 key 的話會輸出 None。

### 默認值

若是希望使用者在 type 輸入非預期時仍能對應到現有 number 的 key，可以使用 list 與 `if value in list:` 的語句來指定。

```python
types = ['goodclass', 'experience','participate','parent','classforchild','educator']
        if type not in types:
            type = 'goodclass'
```

這樣即使使用者輸入如 parant、classforadult 之類的值，仍會自動對應到 goodclass 這個值。

{% hint style="info" %}
原文來自 CSDN，[Python switch/case 語句實現方法](https://blog.csdn.net/l460133921/article/details/74892476)。
{% endhint %}

## 單獨與引用的執行

Python 直譯器執行時，有一些內建、隱含的變數，`__name__` 就是其中之一，其意義是**模組名稱**。如果該檔案是被引用，其值會是模組名稱；但若該檔案是 \( 透過命令列 \) 直接執行，其值會是 `__main__` 。

在實務上之所以常看見這樣的寫法，是因為該程式可能有單獨執行、如單檔案的單元測試，或是被引用的情況，用以下的方法就能區分這兩種情況。

```python
if __name__ == '__main__':
    main()  # 或是任何你想執行的函式
```

