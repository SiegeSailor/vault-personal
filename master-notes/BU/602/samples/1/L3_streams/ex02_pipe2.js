var fs = require('fs');

// Create readable stream
var readableStream = 
	fs.createReadStream('./cs602.txt');

var writableStream = process.stdout;

writableStream.on('pipe', function(src){
	console.log("Piping started...");
});

// Pipe it to out stdout
readableStream.pipe(process.stdout);
