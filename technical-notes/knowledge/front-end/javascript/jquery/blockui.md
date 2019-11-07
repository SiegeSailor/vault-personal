# BlockUI

## Precondition

* Version :: 2.70.0-2014.11.23

## Trouble Shooting

### Z Index

It is not a css style in BlockUI, you have to set it as a parameter instead.

```javascript
$.blockUI({    baseZ: 210000,    css: {      width: 'auto',      left: '50%',      transform: 'translateX(-50%)',      position: 'fixed',      margin: 'auto',      border: 'none',      backgroundColor: 'transparent',      borderRadius: '0px',    },    message: $('.blockUI-loading'),  });
```

### Place Center

See examples in the article, there are different ways to reach the result depending on your CSS.

{% hint style="info" %}
[Here](https://stackoverflow.com/questions/9146520/is-there-anyway-to-have-jquery-blockui-vertically-center-on-the-screen/13688829) is the examples.
{% endhint %}

### Exception ParentNode of Undefined

This exception happens when you are using `message`, If there are more than two BlockUIs in your code, BlockUI will remove the previous **message element** once you complete a BlockUI.

One way to prevent it is append the previous element back after the BlockUI.

```javascript
$.blockUI({    baseZ: 210000,    message: $('div.growlUI'),    fadeIn: 800,    fadeOut: 800,    timeout: 4000,    showOverlay: false,    css: {        width: '280px',        left: '',        right: '24px',        border: 'none',        padding: '0px',        backgroundColor: 'transparent',        '-webkit-border-radius': '5px',        '-moz-border-radius': '5px',        color: '#fff',        textAlign: 'left',    }});$('body').append('The element which previous BlockUI used as message.');
```

{% hint style="info" %}
[Here](https://stackoverflow.com/questions/19362542/blockui-exception-parentnode-of-undefined) is the discussion.
{% endhint %}

### Safari

Safari doesn't stop BF cache so when users go history back the page doesn't refresh, if we use blockUI as the last action when leaving from the last page, users might see loading blockUI when go back. We have to force it to close.

```javascript
window.onpageshow = function(event) {    if (event.persisted) {        $.unblockUI();    }};
```

{% hint style="info" %}
[Here](https://blog.darkthread.net/blog/safari-bfcache-ignore-nocache/) is the solution.
{% endhint %}

