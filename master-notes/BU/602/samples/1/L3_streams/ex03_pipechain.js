var fs = require('fs');
var gzip = require('zlib').createGzip();

var inp = fs.createReadStream('./cs602.txt');
var out = fs.createWriteStream('./cs602.txt.gz');

// Pipe chain
inp.pipe(gzip).pipe(out);

