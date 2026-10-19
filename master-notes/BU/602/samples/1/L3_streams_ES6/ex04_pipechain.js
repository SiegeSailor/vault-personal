const fs = require('fs');
const gunzip = require('zlib').createGunzip();

const inp = fs.createReadStream('./cs602.txt.gz');

// Pipe chain
inp.pipe(gunzip).pipe(process.stdout);

