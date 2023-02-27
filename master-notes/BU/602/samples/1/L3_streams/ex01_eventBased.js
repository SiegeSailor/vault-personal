var stream = require('stream');
var EventEmitter = require('events').EventEmitter;

console.log(new stream.Stream() instanceof 
	EventEmitter);

console.log(new stream.Readable({}) instanceof 
	stream.Stream);
console.log(new stream.Writable({}) instanceof 
	stream.Stream); 
console.log(new stream.Duplex({}) instanceof 
	stream.Stream); 
console.log(new stream.Transform({}) instanceof 
	stream.Stream); 

