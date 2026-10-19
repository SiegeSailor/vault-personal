var fs = require('fs');

// read asynchronously
fs.readFile('cs602.txt', function(err, data){
	if (err) throw err;
	console.log(data);
	console.log(data.toString());
});

