var util = require('util');

util.log('Welcome to CS602!');

var name = 'CS602';
var credits = 4;

console.log(util.format(
	'%s is worth %d credits', 
	name, credits));

