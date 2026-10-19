var EventEmitter = require('events').EventEmitter;
var inherits = require('util').inherits;

// Custom class 
function Foo(args) {
	  this.data = args;
    EventEmitter.call(this);
}
inherits(Foo, EventEmitter);

// Sample member function that raises an event
Foo.prototype.test = function () {
    this.emit('event1', 200);
}

// Usage
var foo = new Foo(100);
foo.on('event1', function (args) {
    console.log('Event raised!', 
    	args, foo.data);
});

foo.test();

