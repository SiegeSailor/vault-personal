const EventEmitter = require('events');

const emitter = new EventEmitter();

// Listener removal notification
emitter.on('removeListener', 
	 (eventName, listenerFunction) => {
    console.log('Listener removed:', 
    	eventName, listenerFunction.name);
});

// Listener addition notification
emitter.on('newListener', 
	 (eventName, listenerFunction) => {
    console.log('Listener added:', 
    	eventName, listenerFunction.name);
});

function fa(args) {
	console.log("Function a called:", args);
 }

function fb(args) {
	console.log("Function b called:", args);
 }

// Add 
emitter.on('event1', fa);
emitter.on('event2', fb);

// Emit
emitter.emit('event1', 100);
emitter.emit('event2', 200);

// Remove 
emitter.removeListener('event1', fa);
emitter.removeListener('event2', fb);



