# jQuery

## [Uncaught ReferenceError: Invalid left-hand side in assignment](https://stackoverflow.com/questions/1945302/uncaught-referenceerror-invalid-left-hand-side-in-assignment)

You can't assign a new value to the result of a function

```text
$('input#q').val() = urlencode($('input#q').val());
```

Use this instead:

```text
$('input#q').val(urlencode($('input#q').val()))
```





https://www.jsdelivr.com/package/npm/jquery.animate-number

