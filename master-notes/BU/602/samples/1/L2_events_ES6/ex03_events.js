const EventEmitter = require('events');

const emitter = new EventEmitter();

emitter.on('event1',  (args) => {
    console.log('First subscriber:', args);
    args.handled = true;
});

emitter.on('event1',  (args) => {
	if (args.handled) {
    	console.log('Second subscriber:', args);
	}
});

// Emit
emitter.emit('event1', {a: 'foo', b: 'bar'});

