var EventEmitter = require('events').EventEmitter;
var emitter = new EventEmitter();

emitter.once('event1', function () {
    console.log('Event Handler called');
});

// Emit twice
emitter.emit('event1');
emitter.emit('event1');

