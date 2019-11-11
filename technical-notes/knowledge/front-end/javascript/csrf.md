# CSRF

## 概述

在使用 Django 作為 Server Side 時，若有開啟 CSRF 機制，則 POST 和 PUT 和 DELETE 請求都必須在 Header 中夾帶 CSRF Token，驗證通過請求始可執行。

### Universal Cookie

首先，利用 NPM 安裝 Universal Cookie。

```bash
npm install universal-cookie --save
```

接著在要取得 Cookie 的 JavaScript 檔案中引用。

```javascript
import Cookies from 'universal-cookie';
```

以 `cookies.get('csrftoken')` 的方式獲取 CSRF Token。若有使用 Axios，則設置方法會如下。

```javascript
Axios.get(`/edadviser/reviews/list/reviews/${reviewNumber}/`, {
    headers: {
        "X-CSRFToken": cookies.get('csrftoken'),
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
    }
}).then();
```

若是使用 jQuery AJAX，則設置方法會如下。

```javascript
$.ajax({
url: "/edadviser/reviews/list/cram/",
type: "POST",
header: {
    "X-CSRFToken": getCookie('csrftoken')
},
data: {
    "listInit": JSON.stringify(listInit),
    "country": schoolCountry,
    "name": schoolName,
},
dataType: "json",
success: function (response) {}
})
```

{% hint style="info" %}
若是使用 React，則應使用 **react-cookie**。
{% endhint %}

### Document Cookie

藉由解析 document.cookie 的方式來獲得 Cookie。

```javascript
const cookies = document.cookie
.split(';')
.reduce((res, c) => {
  const [key, val] = c.trim().split('=').map(decodeURIComponent)
  try {
    return Object.assign(res, { [key]: JSON.parse(val) })
  } catch (e) {
    return Object.assign(res, { [key]: val })
  }
}, {});
```

若有使用 Axios，則設置方法會如下。

```javascript
Axios.post(`/edadviser/reviews/controller/`, 
QueryString.stringify({code: this.state.loginInputValue}), {
    headers: {
        "X-CSRFToken": cookies.csrftoken,
        "Content-Type": "application/x-www-form-urlencoded"
    }
}).then(
```

