const fs = require('fs');

// Create readable stream
const readableStream = 
	fs.createReadStream('./cs602.txt');

const writableStream = process.stdout;

writableStream.on('pipe', function(src){
	console.log("Piping started...");
});

// Pipe it to out stdout
readableStream.pipe(process.stdout);
