# Regex

## Regular Expression

`(?P<category_slug>)` creates a match group named `category_slug`.

The regex itself matches a string starting with `category/` and then a mix of alphanumeric characters, the dash `-` and the underscore `_`, followed by a trailing slash. Here is example URLs accepted by the regex:

* category/foo/
* category/foo\_bar-baz/
* category/12345/

