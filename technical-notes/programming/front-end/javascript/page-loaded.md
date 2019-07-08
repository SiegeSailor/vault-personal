# Page Loaded

## When Page Loaded

 There are three ways to get this status.

```javascript
<body onload="script();">
```

```javascript
document.onload = function () {}
```

The last one is the better way to go.

```javascript
window.onload = function () {}
```

#### jQuery

```javascript
$(document).ready(function () {
    window.onload = function () {
    }
}
```



