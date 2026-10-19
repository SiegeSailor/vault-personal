const Writable = require('stream').Writable;
const fs = require('fs');

// Define the custom Writable class 

class CustomWritable extends Writable {

	_write(data) {
		const lines = data.toString().split(/(?:\n|\r\n|\r)/g);
   		var line_num = 1;
   		const numbered_lines = lines.map(line => {
   			return (line_num++) + ': ' + line;
   		});
   		console.log(numbered_lines.join('\n'));
	}
} 


// Usage, same as any other Writable stream
const out = new CustomWritable();

const readStream = fs.createReadStream('./lines.txt');

readStream.pipe(out);
