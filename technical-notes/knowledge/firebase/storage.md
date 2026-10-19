# Storage

## 概述

關於 Firebase Storage 的一些已知問題。

### CORS

若要用 XMLHttpRequest 取用 Firebase Storage 的資料，必須額外用 Gutil Command Line 設定。

{% hint style="info" %}
詳細步驟參考 Kirin Chen，[Firebase Storage 如何跨網域設定](https://medium.com/@a29243004/firebase-storage-%E5%A6%82%E4%BD%95%E8%B7%A8%E7%B6%B2%E5%9F%9F%E8%A8%AD%E5%AE%9Aaccess-control-allow-origin-cors-7dc44b46a728)。
{% endhint %}

### HTTP 連結

在使用 XMLHttpRequest 的情況下，Local 端使用 HTTPS 會被 CORS 擋住。但在 Production 端，因通常是在 SSL HTTPS 加密，只接受 HTTPS，這時則必須使用 HTTPS，而 Firebase Storage CORS 也突能允許。

