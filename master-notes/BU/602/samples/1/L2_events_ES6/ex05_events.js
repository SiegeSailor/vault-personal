const EventEmitter = require('events');

const emitter = new EventEmitter();

emitter.once('event1',  () => {
    console.log('Event Handler called');
});

// Emit twice
emitter.emit('event1');
emitter.emit('event1');

