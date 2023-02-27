var EventEmitter = require('events').EventEmitter;
var emitter = new EventEmitter();

var evtHandler = function () {
    console.log('Event Handler called');

    // Unsubscribe
    emitter.removeListener('event1',evtHandler);
};

emitter.on('event1', evtHandler);

// Emit twice
emitter.emit('event1');
emitter.emit('event1');
