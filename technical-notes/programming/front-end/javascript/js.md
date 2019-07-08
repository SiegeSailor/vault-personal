# JS

## Overview

JS here means **JavaScript** and **jQuery,** here I compare them and list the differences of these two.

### DOM and Class

#### JavaScript

You can just use `addClass` to add a class to an DOM in an array, you have to use `className` to reassign the class for the DOM.

Wrong way.

```javascript
target[i].addClass('navBar');
```

Right way.

```javascript
target[i].className += 'navBar-hover';
```

#### jQuery

You can use a simple way to add a class to an DOM in an array.

```javascript
$(target[i]).addClass('navBar-hover');
```

