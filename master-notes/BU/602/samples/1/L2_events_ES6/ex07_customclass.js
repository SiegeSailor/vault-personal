const EventEmitter = require('events').EventEmitter;

// Custom class 
class Foo  extends EventEmitter {
	constructor(args) {
		super();
		this.data = args;
	}

	// Sample member function that raises an event
	test() {
		this.emit('event1', 200)
	}
}

Foo.prototype.test2 = function (args) {
    this.emit('event1', args);
}

// Usage
const foo = new Foo(100);

foo.on('event1', function (args) {
    console.log('Event raised!', 
    	args, foo.data);
});

foo.test();
foo.test2(500);
