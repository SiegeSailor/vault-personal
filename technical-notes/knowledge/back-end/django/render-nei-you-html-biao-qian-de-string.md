# Render 內有 HTML 標籤的 String

## 概述

假設在一個 view 中，我們有這樣一個 dict：

```python
label = {
    "one": "One is <em>Good</em>",
    "two": "Two<br>is Bad",
}
```

我們預期要用 `render()` 的方式將這個 dict 渲染到 HTML 上：

```python
return render(request, 'example.html', {"label":label})
```

這樣，我們會需要使用 Django 的 template tag 在 HTML 中指名要渲染的位置和值，如下：

```markup
<div class="example">{{ label.one }}</div>
```

我們預期得出的結果會是 _One is **Good**_，然而在預設情況下，Django 內輸出的 string 內的 HTML tag 是不會自動生效的，裡面的標籤只會以文本方式存在值中，也就是 _One is Good_。若要讓 string 內的 HTML tag 生效，我們需要在 Django template tag 中加入 `safe` 標籤。

```markup
<div class="example">{{ label.one | safe }}</div>
```

如此一來，string 內的 HTML tag 就會生效了。

