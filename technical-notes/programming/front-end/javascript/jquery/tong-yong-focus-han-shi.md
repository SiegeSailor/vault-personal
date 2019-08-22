# 通用 Focus 函式

## 概述

使用 jQuery 時，我們經常使用 `focus()` 方法來聚焦 DOM 元素，然而由於各瀏覽器的差異，經常發生無法作用的情況。這裏是一個能夠應對 FireFox 甚至 IE 等瀏覽器問題的 focus 函式，能夠避免絕大部分的失效情況。

### 函式

```javascript
(function($)
{
    jQuery.fn.setfocus = function()
    {
        return this.each(function()
        {
            var dom = this;
            setTimeout(function()
            {
                try { dom.focus(); } catch (e) { } 
            }, 0);
        });
    };
})(jQuery);
```

{% hint style="info" %}
原文來自 The Will Will Web，[利用 jQuery 將 DOM 元素聚焦 focus\(\) 的六個版本](https://blog.miniasp.com/post/2009/10/29/The-six-versions-that-let-DOM-focus-using-jQuery)。
{% endhint %}

