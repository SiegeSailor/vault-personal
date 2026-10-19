const EventEmitter = require('events').EventEmitter;

// Custom class 
class ClockEmitter  extends EventEmitter {
	
	constructor() {
		super();
		this._started = false;
	}

	start() {
		var self = this;

	  if (self._started) return;

	  var tic = true;

	  this._started = true;

	  self._interval = setInterval(() => {
	    var event = tic ? 'tic' : 'toc';
	    self.emit(event, self.getTime());
	    tic = ! tic;
	  }, 1000);
	}

	stop() {

		if (!this._started) return;

		clearInterval(this._interval);

  	this._started = false;
	}

	getTime() {
		return this._started && new Date();
	}
}

module.exports = ClockEmitter;

