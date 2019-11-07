# Apply Important

## Overall

jQuery has a method called `css()`, which is used to add css style to a selector. When we want to apply **!important**, this line of code should come first in our mind.

```javascript
$("#elem").css("width", "100px !important");
```

But this is not working at all, here are a few ways that you can apply !important through jQuery.

#### Adding Class

```javascript
.importantRule { width: 100px !important; }$('#elem').addClass('importantRule');
```

#### Modify Attribute

```javascript
$('#elem').attr('style', 'width: 100px !important');
```

{% hint style="warning" %}
This way might override the previous argument if you already applied style to the same element, so use this carefully.
{% endhint %}

#### Attribute with Function

```javascript
$('#elem').attr('style', function(i,s) { return s + 'width: 100px !important;' });
```

