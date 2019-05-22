# CSRF



* 首先，最基本的原则是：GET 请求不要用有副作用。也就是说任何处理 GET 请求的代码对资源的访问都一定要是“只读“的。
* 要启用 django.middleware.csrf.CsrfViewMiddleware 这个中间件
* 再次，在所有的 POST 表单元素时，需要加上一个 {% csrf\_token %} tag
* 在渲染模块时，使用 RequestContext。RequestContext 会处理 csrf\_token 这个 tag,  从而自动为表单添加一个名为 csrfmiddlewaretoken 的 input



## ajax connection example

$\('.pull-right'\).click\(function \(\) { if \(accountCreateNumber == 1\) { swal\({ // title: 'Automatically Log In', title: 'Account Has Been Created', html: 'Account information have been sent to your email.', type: 'info', }\).then\(function \(result\) { $.ajax\({ url: "/class/landing/guest/", type: "GET", headers: { "X-CSRFToken": getCookie\('csrftoken'\), 'Authorization': 'Bearer ' + token, }, data: { "customer\_id": response.data.customerInfo.customerId, "token": response.data.customerInfo.token, }, }\); }\); } }\)

!! should be no data:{}

