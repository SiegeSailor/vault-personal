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

For some reason, `window.onload` might not fire anyway, in that case you should go this way.

```javascript
window.addEventListener('load', extendCommentsArea, false);

function extendCommentsArea() {
    if (window.innerHeight > schoolInfo.offsetHeight) {
        commentsArea.style.height = (window.innerHeight - schoolInfo.offsetHeight) + "px";
    }
}
```

### jQuery

```javascript
$(document).ready(function () {
    window.onload = function () {
    }
}
```





