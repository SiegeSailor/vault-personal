# Swal

## Precondition

* Version :: sweetalert2 v7.28.11

## Example

A structure for a Swal.

```javascript
var sorryTrans = gettext('Sorry!');
var goToSignInTrans = gettext('Go to Sign In');
var cancelTrans = gettext("Cancel");
var response = '';
goToRegister = "<a href='/account/signup/' >" + goToRegisterTrans + "</a>";
swal({
    title: sorryTrans,
    html: response,
    type: 'info',
    showCancelButton: true,
    confirmButtonText: goToSignInTrans,
    cancelButtonText: cancelTrans,
    footer: goToRegister,
    allowOutsideClick: false
}).then(function (result) {
    if (result.value) {
        window.location.href = "/account/login/";
    } else {}
})
```



