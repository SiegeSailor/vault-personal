const EventEmitter = require('events');

const emitter = new EventEmitter();

emitter.on('event1',  (args) => {
    console.log('First subscriber:', args);
});

emitter.on('event1',  (args) => {
    console.log('Second subscriber:', args);
});

// Emit
emitter.emit('event1', {a: 'foo', b: 'bar'});

