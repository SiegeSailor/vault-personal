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

### Regex and Replace

You might use this when you want to replace some text from a `string`.

```javascript
var regExp = new RegExp('Your Regular Expression without //, What you want to replace', 'g')
var newValue = oldValue.replace(regExp, ('What you want to insert'));
```

{% hint style="info" %}
See [This](http://jsnwork.kiiuo.com/archives/264/jquery-javascript-%E4%BD%BF%E7%94%A8replace%E8%88%87regexp%E5%8F%96%E4%BB%A3%E6%96%87%E5%AD%97/) to get more.
{% endhint %}

