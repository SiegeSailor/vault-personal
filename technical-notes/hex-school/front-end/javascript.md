# JavaScript

`JSON.parse()` is used to convert a **string** containing JSON notation into a Javascript object.

Your code turns the object into a string \(by calling `.toString()`\) in order to try to parse it as JSON text.  
The default `.toString()` returns `"[object Object]"`, which is not valid JSON; hence the error.





* is it not an Array? Why is it an object. Objects start with { and arrays start with \[? or am i false here
* 3Arrays are objects; that's what `.toString()` returns \(as per the spec\). – [SLaks](https://stackoverflow.com/users/34397/slaks) [Sep 4 '16 at 18:01](https://stackoverflow.com/questions/14432165/uncaught-syntaxerror-unexpected-token-with-json-parse#comment65971880_14432182)
* 1Is the solution to stringify the object first? – [Mohammed Noureldin](https://stackoverflow.com/users/1988508/mohammed-noureldin) [Feb 3 '18 at 23:58](https://stackoverflow.com/questions/14432165/uncaught-syntaxerror-unexpected-token-with-json-parse#comment84203353_14432182)
* 4@MohammedNoureldin: No; the solution is to do nothing and use your object. – [SLaks](https://stackoverflow.com/users/34397/slaks) [Feb 4 '18 at 0:19](https://stackoverflow.com/questions/14432165/uncaught-syntaxerror-unexpected-token-with-json-parse#comment84203576_14432182)
* 1What if I get my data from a remote service using Ajax, which gives me Json response back? And I want that response to be saved in JavaScript array object? – [Mohammed Noureldin](https://stackoverflow.com/users/1988508/mohammed-noureldin) [Feb 4 '18 at 0:54](https://stackoverflow.com/questions/14432165/uncaught-syntaxerror-unexpected-token-with-json-parse#comment84203948_14432182) 
* 1@MohammedNoureldin: If you have an object, you don't need to parse it.. If you have string, you won't get an error. – [SLaks](https://stackoverflow.com/users/34397/slaks) [Feb 4 '18 at 0:55](https://stackoverflow.com/questions/14432165/uncaught-syntaxerror-unexpected-token-with-json-parse#comment84203963_14432182)

