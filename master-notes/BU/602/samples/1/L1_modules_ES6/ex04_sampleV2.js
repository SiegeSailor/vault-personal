var foo = require('./ex04_fooV2');

console.log(foo.getFirstName(), 
					  foo.getLastName());

foo.setLastName('Doe');

console.log(foo.getFirstName(), 
					  foo.getLastName());

