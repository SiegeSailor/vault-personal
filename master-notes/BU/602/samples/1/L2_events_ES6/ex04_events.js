const EventEmitter = require('events');

const emitter = new EventEmitter();

const evtHandler =  () => {
    console.log('Event Handler called');

    // Unsubscribe
    emitter.removeListener('event1',evtHandler);
};

emitter.on('event1', evtHandler);

// Emit twice
emitter.emit('event1');
emitter.emit('event1');
