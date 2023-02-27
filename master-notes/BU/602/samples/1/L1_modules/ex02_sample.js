var foo1 = require('./ex02_foo');

console.log(foo1.firstName, foo1.lastName);

foo1.lastName = 'Doe';
console.log(foo1.firstName, foo1.lastName);

var foo2 = require('./ex02_foo');
console.log(foo2.firstName, foo2.lastName);

