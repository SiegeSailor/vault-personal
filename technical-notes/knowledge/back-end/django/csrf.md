# CSRF

## Cross-Site Request Forgery

為防止跨網域請求，當在 Middle Ware 設置 `CsrfViewMiddleware` 後，Django 即默認會對 POST，PUT，DELETE 這幾種請求做 CSRF TOKEN 檢查。要注意的是 POST 可以將 CSRF 放入 POST 的參數中，但 Django 對 PUT 和 DELETE 只能通過 Header 來檢查。

```python
MIDDLEWARE_CLASSES = (
'django.middleware.csrf.CsrfViewMiddleware',
)
```

接著，在透過 View Render 的頁面中加入以下 Template Tag。此即將 CSRF Token 加入該 HTML 的 `document.cookie` 中。

```markup
{% csrf_token %}
```

CSRF Token 會以看似 Key Value 但為 String 的型式存在於 `document.cookie` 中，預設的 Key 為 **csrftoken**，諸多服務如 GA，Facebook Pixel 都會將 Cookie 以類似的方式儲存。可使用 Console 輸入 `document.cookie` 查看。

```javascript
"_ga=GA1.2.197490014.1558764515; _fbp=fb.1.1558764514899.1259154569; csrftoken=iLLOoXvFpZOQIBll1bcjn1IKGAjPI0xzeTdH5mIA3gBr6H9COLUw0q9J8iNuw7Eg; _gid=GA1.2.1910114080.1573457491"
```

而在前端進行 POST 或 DELETE 或 PUT 請求時，須在 Header 夾帶取得的 CSRF Token，在 Request 中 Header 的 Key 預設為 **X-CSRFToken**，但可以在 settings 中通過設置來改變。

```python
CSRF_COOKIE_NAME = "XSRF-TOKEN"
```

### 確保頁面有 CSRF

`CsrfViewMiddleware` 設置後，若為完全靜態、即若是完全沒有 Template Tag `{% csrf_token %}` 時，會導致 CSRF Token 為 Null，此時需要手動設置 `ensure_csrf_cookie`。這種設置方法必須在 View Render 出頁面的位置，若有使用 Method，則必須使用 Method Decorator 來對 Method 進行設置。

```python
from django.views.decorators.csrf import ensure_csrf_cookie
from django.utils.decorators import method_decorator

@method_decorator(ensure_csrf_cookie, name="get")
class Controller(View):
    def get(self, request):
        return render(request, 'rating/controller.html', {})
        
@ensure_csrf_cookie
def sandbox(request):
    return
```

若有設置 `CSRF_COOKIE_SECURE`，則必須為 False，否則會導致前端讀取不到該 Token。

```python
CSRF_COOKIE_SECURE = False
```

{% hint style="warning" %}
Method Decorator 的 Name 指的是 Method 的名稱，此為必要值。
{% endhint %}

使用此種方法時，邏輯是先以 GET 的 Request 向設置了 `ensure_csrf_cookie` 的 View 的 URL 發送請求、獲得 CSRF Token，再將獲得的 Token 以預設的 Key X-CSRFToken 放在 POST 或 DELETE 或 PUT 的 Header 中。

### 單獨啟用

若沒有設置 `CsrfViewMiddleware`，卻又希望特定的 View 有 CSRF 保護時，則須使用 `csrf_protect`。

```python
from django.views.decorators.csrf import csrf_protect
from django.shortcuts import render

@csrf_protect
def my_view(request):
    context = {}
    return render(request, "example.html", context)
```

### 單獨取消

當 `CsrfViewMiddleware` 設置後，Django 是對全域設置有 CSRF 保護，若要單獨對某個 POST 或 PUT 或 DELETE 做 CSRF 取消驗證，則可以使用 `csrf_exempt` 。注意若有使用 Method，則必須使用 Method Decorator 來對 Method 進行設置。

```python
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator

@method_decorator(csrf_exempt, name="post")
class Controller(View):
    def post(self, request):
        code = request.POST.get('code','')
        if settings.DEBUG == True:
            validation_code = "test000"
        else:
            validation_code = "eda82862223"
        if code == validation_code:
            return HttpResponse("pass")
        else:
            return HttpResponse("block")
            
@csrf_exempt
def my_view(request):
    return HttpResponse("Hello World")
```

### 單獨僅驗證

類似於 `csrf_protect`，一樣會進行 CSRF 驗證，但不會拒絕發送過來的請求。

```python
from django.views.decorators.csrf import requires_csrf_token
from django.shortcuts import render

@requires_csrf_token
def my_view(request):
    context = {}
    return render(request, "example.html", context)
```

## Request 接收值

一些會有 Request 端發送值的取值方法。範例 Request 的 Header 如下。

```javascript
headers= {"Content-Type": "application/json","Authorization": "JWT " + token}
```

* request.META :: 獲得 Request Header 資訊，注意內有諸多默認值
* request.body :: 獲得 Request 主體，為一 String
* request.data :: 獲得 Request 夾送的資訊，為一 Dict
* request.POST :: 獲得以 POST 方式發送的資訊
* request.GET :: 獲得以 GET 方式發送的資訊
* request.FILES :: 獲得文件

