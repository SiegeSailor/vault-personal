# Methods

## Methods

Common methods.

#### Json Load

Use this method to parse JSON data from POST \( Usually is from AJAX \). Here is the data in JS, it uses `JSON.stringify()` to parse the data to json type.

```javascript
data: {'data': JSON.stringify({'fruit': selected})}
```

This is in Django view.

```python
data = json.loads(request.POST.get('data', ''))
```

