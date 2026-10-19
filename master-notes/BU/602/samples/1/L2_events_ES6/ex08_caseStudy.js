const EventEmitter = require('events');

const emitter = new EventEmitter();

const fs = require('fs');

emitter.on('start', (file) => {
      console.log("...Started Reading:", file);
      fs.readFile(file, 'utf8', 
         (err, data) => {
          if (err) {
            emitter.emit('error','readFile error...');
          }
          else{
              console.log("...Done Reading:", file);
              emitter.emit('print', data);
          }
        });
});

emitter.on('print', (data) => {
      console.log("...File contents\n");
      console.log(data);
      emitter.emit('done');
});

emitter.on('error', (type) => {
      console.log("Error:", type);
      emitter.emit('done');
});

emitter.on('done',() => {
      console.log("...Done");
});

// Emit start
emitter.emit('start','./ex01_events.js');

