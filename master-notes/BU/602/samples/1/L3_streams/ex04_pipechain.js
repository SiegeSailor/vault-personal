var fs = require('fs');
var gunzip = require('zlib').createGunzip();

var inp = fs.createReadStream('./cs602.txt.gz');

// Pipe chain
inp.pipe(gunzip).pipe(process.stdout);

