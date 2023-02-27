var colors = require('colors/safe');

var data = 'Welcome to CS602!';

console.log(colors.green(data)); 
console.log(colors.red.underline(data));
console.log(colors.red.bold(data));
console.log(colors.inverse(data)); 
console.log(colors.rainbow(data)); 

