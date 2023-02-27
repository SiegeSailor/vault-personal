const EventEmitter = require('events');

const emitter = new EventEmitter();

// Subscribe
emitter.on('event1',  (args) => {
    console.log('event1 raised, Args:', args);
});

// Emit
emitter.emit('event1', {a: 'foo', b: 'bar'});
