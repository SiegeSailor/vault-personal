const fs = require('fs');
const gzip = require('zlib').createGzip();

const inp = fs.createReadStream('./cs602.txt');
const out = fs.createWriteStream('./cs602.txt.gz');

// Pipe chain
inp.pipe(gzip).pipe(out);

