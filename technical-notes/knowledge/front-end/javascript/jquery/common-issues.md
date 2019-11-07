# Common Issues

## Methods

Common methods.

### Assign Value

Use `val()` to assign a value to an element or to get a value of an element.

```javascript
$('input#q').val(urlencode($('input#q').val()))
```

Do not use JavaScript logic for this method.

```javascript
$('input#q').val() = urlencode($('input#q').val());
```

You will get _**Uncaught ReferenceError: Invalid left-hand side in assignment**_ if you use the above way.

### Check Value

Use `prop("check")` to get the checked status, use `prop("checked", true)` to set the status. You can use this method on **CheckBox** or **Radio**.

```javascript
var accountCreate = $('#apply-account-id').prop("checked");var accountCreateNumber = '';if (accountCreate == false) {  accountCreateNumber = 0;} else if (accountCreate == true) {  accountCreateNumber = 1;};
```

## Trouble Shooting

Common issues.

### Swal and BlockUI

If you can't click Swal on mobile, it might caused by BlockUI if you didn't `$.unblockUI()` before call Swal.

## Things to Avoid

Things that don't cause error but the outcome is really bad, as bad as you should avoid.

### Slide and Transition

If you are using `slide` method, don't add `transition` to any related element CSS style.

