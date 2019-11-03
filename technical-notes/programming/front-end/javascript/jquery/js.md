# jQuery 與 JavaScript

## Overview

JS here means **JavaScript** and **jQuery,** here I compare them and list the differences of these two.

## DOM and Class

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

## Regex and Replace

You might use this when you want to replace some text from a `string`.

```javascript
var regExp = new RegExp('Your Regular Expression without //, and what you want to replace', 'g')
var newValue = oldValue.replace(regExp, ('What you want to insert'));
```

{% hint style="info" %}
See [This](http://jsnwork.kiiuo.com/archives/264/jquery-javascript-%E4%BD%BF%E7%94%A8replace%E8%88%87regexp%E5%8F%96%E4%BB%A3%E6%96%87%E5%AD%97/) to get more.
{% endhint %}

## Email Validation

The following code uses `jQuery`, but the logic should work with native JavaScript.

#### Validate Callback Function

First, announce a callback function to validate email with `Regex`, and return boolean with `test()` method.

```javascript
function validateEmail(email) {
      var regex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return regex.test(email);
}
```

#### Operation Function

Then create a function to suit to your situation.

```javascript
function validate() {
  var result = $("#validation-result");
  var email = $("#email").val();
  result.text("");
  if (validateEmail(email)) {
    result.text('you are validated.');
  } else {
    result.html('<i class="fas fa-exclamation-circle"></i> ' + email + "is not valid.");
    return;
  }
}
```

## Phone Number Input Limitation

The following code uses `jQuery`, but the logic should work with native JavaScript.

```javascript
(function ($) {
    $.fn.inputFilter = function (inputFilter) {
      return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function () {
        if (inputFilter(this.value)) {
          this.oldValue = this.value;
          this.oldSelectionStart = this.selectionStart;
          this.oldSelectionEnd = this.selectionEnd;
        } else if (this.hasOwnProperty("oldValue")) {
          this.value = this.oldValue;
          this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
        }
      });
    };
  }(jQuery));
  $("#phone").inputFilter(function (value) {
    return /^\d*$/.test(value);
  });
```

## Input Prohibition

Simply prohibit user from typing.

```javascript
  $('#input').keypress(function (e) {
    e.preventDefault();
  });
```

## Check If A Key Exists

If you meet _**Uncaught TypeError: Cannot use 'in' operator to search for 'length'**_  which means you are not running this check with **Object**.

```javascript
if ('error' in response) {
    console.log('Error!')
}
```

## Href With OnClick

Function first then href.

```markup
<a target="_blank" onclick="document.location.href="https://www.edallianz.com/tw/";return false;" href="https://www.edallianz.com/tw/">Visit Us</a>
```

## Text Length Validation

This is usually used when you want to check if your input text length is long enough and it contains Chinese and English **both**, this offers you a way to calculate them with **Byte**.

```javascript
var commentComment = $('.comment').val();
var commentLength = encodeURIComponent(commentComment);
commentLength = commentLength.replace(/%[A-Fd]{2}/g, 'U').length;
    if (commentLength < 140) {
        return;
    } else {
    }
```

{% hint style="info" %}
See [This](https://blog.caesarchi.com/2011/07/14/-e5-88-86-e4-ba-ab-javascript--e8-a8-88-e7-ae-97-e5-ad-97-e5-85-83-e9-95-b7-e5-ba-a6-count-string-length-by-javascript/).
{% endhint %}

## URL 404 Not Found

It is probably caused by your URL context, you can not use `#` in your URL.

