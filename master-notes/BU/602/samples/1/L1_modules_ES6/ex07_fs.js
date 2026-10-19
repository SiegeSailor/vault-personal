var fs = require('fs');

// write 
fs.writeFileSync('cs602.txt', 
	'Welcome to CS602!');

// read
var data1 = fs.readFileSync('cs602.txt');
console.log(data1);
console.log(data1.toString());

var data2 = fs.readFileSync('cs602.txt', 
														'utf8');
console.log(data2);

