# Common Issues

## Methods

Common methods.

### Json Load

Use this method to parse JSON data from POST \( Usually is from AJAX \). Here is the data in JS, it uses `JSON.stringify()` to parse the data to json type.

```javascript
data: {'data': JSON.stringify({'fruit': selected})}
```

This is one of the attribute in AJAX.

```javascript
dataType: "json",
```

This is in Django view.

```python
data = json.loads(request.POST.get('data', ''))
```

## Trouble Shooting

Common issues.

### Dict Object Has no Attribute

_**dict object has no attribute status\_code error in Django project**_, Probably you are returning a dictionary or list from Django with HttpResponse rather than JsonResponse.

### Failed to Establish New Connection

This is usually the server side issue, but it might be caused if you send **None** value request, check if the value you are sending out is not **None**.

And the reason why your request is sending None might be your AJAX, remove this.

```javascript
contentType: "json",
```

### ModuleNotFoundError

_**ModuleNotFoundError: No module named 'mysite'**_ might caused because you are returning a **HttpResponse** or **JsonResponse** with functions which is not from a **View**.

