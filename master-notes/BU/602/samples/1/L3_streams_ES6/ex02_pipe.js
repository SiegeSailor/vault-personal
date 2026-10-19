const fs = require('fs');

// Create readable stream
const readableStream = 
	fs.createReadStream('./cs602.txt');

// Pipe it to out stdout
readableStream.pipe(process.stdout);

