# Return Multi Values

No, but you could return an array containing your values:

```text
var newCodes = function() {
    var dCodes = fg.codecsCodes.rs;
    var dCodes2 = fg.codecsCodes2.rs;
    return [dCodes, dCodes2];
};
```

Then you can access them like so:

```text
var codes = newCodes();
var dCodes = codes[0];
var dCodes2 = codes[1];
```

If you want to put "labels" on each of the returned values \(easier to maintain\), you can return an object:

```text
var newCodes = function() {
    var dCodes = fg.codecsCodes.rs;
    var dCodes2 = fg.codecsCodes2.rs;
    return {
        dCodes: dCodes,
        dCodes2: dCodes2
    };
};
```

And to access them:

```text
var codes = newCodes();
var dCodes = codes.dCodes;
var dCodes2 = codes.dCodes2;
```

