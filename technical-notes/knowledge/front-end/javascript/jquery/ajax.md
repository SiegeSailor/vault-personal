# AJAX

## Overall

List common issues related to AJAX method.

### Confusing Parameter

* datatype :: the data type that server returns
* contentType :: the data type that you send to the server, default is `application/x-www-form-urlencoded` 

### With Django

Need to use `datatype: 'json'` if Django already uses `JsonResponse` to send data.

### GET Method with Data

Usually, we send data with `GET` method like this.

```javascript
$.ajax({
   url: "htttp://api.com/entity/list($body)",
   type: "GET",
   data: "id1-id2-id3",
   contentType: "text/plain",
   dataType: "json",
   processData: false,
   success: onSuccess,
   error: onError
});
```

Above is a regular structure for an AJAX call, assuming that we want to send data in body with GET, here is the conclusion.

* There is no way to do it, unless the browser doesn't respect the specification.
* Try to use POST method first, then turn it into GET in your End Point
* Use POST with data
* Use GET with query string

```javascript
url: 'somesite.com/models/thing?ids=1,2,3'
```

If you still want to send the data within the body, try this way.

```javascript
$.ajax({
   url: "htttp://api.com/entity/list($body)",
   type: "GET",
   headers: ['id1':1, 'id2':2, 'id3':3],
   data: "",
   contentType: "text/plain",
   dataType: "json",
   success: onSuccess,
   error: onError
});
```

{% hint style="info" %}
Question is [Here](https://stackoverflow.com/questions/10298899/how-to-send-data-in-request-body-with-a-get-when-using-jquery-ajax).
{% endhint %}

