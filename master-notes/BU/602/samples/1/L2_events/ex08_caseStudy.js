var EventEmitter = require('events').EventEmitter;
var emitter = new EventEmitter();
var fs = require('fs');

emitter.on('start', function(file) {
      console.log("...Started Reading:", file);
      fs.readFile(file, 'utf8', 
        function (err, data) {
          if (err) {
            emitter.emit('error','readFile error...');
          }
          else{
              console.log("...Done Reading:", file);
              emitter.emit('print', data);
          }
        });
});

emitter.on('print', function(data) {
      console.log("...File contents\n");
      console.log(data);
      emitter.emit('done');
});

emitter.on('error', function(type) {
      console.log("Error:", type);
      emitter.emit('done');
});

emitter.on('done',function(){
      console.log("...Done");
});

// Emit start
emitter.emit('start','./ex01_events.js');

