# Methods

## 概述

遵循 React 特性的一些作法。

## 獲取自訂 Attribute 值

所謂自訂 Attribute 是有如下的值。

```markup
<p schoolname="schoolname" schoolid="schoolid" ></p>
```

在如 onClick 的事件觸發後，我們可以藉由 Event 來獲取這些值。

```javascript
event.target.attributes.getNamedItem("schoolname").value
```

```javascript
event.target.getAttribute("schoolid")
```



