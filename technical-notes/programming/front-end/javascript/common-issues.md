# Common Issues

## Trouble Shooting

Common issues.

### Parent Selector

Use this way to select an element from its parent node.

```javascript
var imageBoxLi = document.querySelector(".imageBox").querySelector('ul').querySelectorAll('li');
```

```javascript
for (var i = 0; i < imageBoxLi.length; i = i + 1) {
  var imageBoxH3_textContent = [];
  imageBoxH3_textContent = imageBoxLi[i].childNodes[1].textContent;
};
```

```javascript
var listBoxTotal = document.querySelectorAll('.listBox_Content');
for (var i = 0; i < ListBoxTotal.length; i = i + 1) {
  var assignedListBox = listBoxTotal[i].id;
  assignedListBox.style.display = "none";
  };
```

### JSON

Here are the three methods related to JSON.

* toString :: convert a **number** to a **string**
* JSON.parse :: is used to convert a **string** containing JSON notation into a Javascript **object**
* JSON.stringify :: is used to convert **JavaScript value** to a **JSON string**

Depending on the above methods, you don't need to use **toString** or **JSON.parse** on a receiving value which was sent as a JSON. Like Django **JsonResponse**. It might cause error if you use.

