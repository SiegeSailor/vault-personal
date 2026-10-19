var _ = require('underscore');

console.log(_.min([10,20,30]));
console.log(_.max([10,20,30]));

var data = [
	{name : 'John', age : 41},
	{name : 'Jane', age : 38},
	{name : 'Joe',  age : 20}
];

console.log(_.pluck(data, 'name'));


