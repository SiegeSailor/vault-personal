var EventEmitter = require('events').EventEmitter;
var emitter = new EventEmitter();

emitter.on('event1', function (args) {
    console.log('First subscriber:', args);
    args.handled = true;
});

emitter.on('event1', function (args) {
	if (args.handled) {
    console.log('Second subscriber:', args);
	}
});

// Emit
emitter.emit('event1', {a: 'foo', b: 'bar'});

