var EventEmitter = require('events').EventEmitter;

var emitter = new EventEmitter();

// Subscribe
emitter.on('event1', function (args) {
    console.log('event1 raised, Args:', args);
});

// Emit
emitter.emit('event1', {a: 'foo', b: 'bar'});
