var Writable = require('stream').Writable;
var util = require('util');
var fs = require('fs');

// Define the custom Writable class 
function CustomWritable() {
    Writable.call(this);
}
util.inherits(CustomWritable, Writable);

CustomWritable.prototype._write = function (data) {
   var lines = data.toString().split(/(?:\n|\r\n|\r)/g);
   var line_num = 1;
   var numbered_lines = lines.map(function(line) {
   	return (line_num++) + ': ' + line;
   });
   console.log(numbered_lines.join('\n'));
};

// Usage, same as any other Writable stream
var out = new CustomWritable();

var readStream = fs.createReadStream('./lines.txt');

readStream.pipe(out);
