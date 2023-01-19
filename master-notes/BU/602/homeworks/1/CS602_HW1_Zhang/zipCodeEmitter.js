const events = require("events");

const data = require("./zips.json");

const EventEmitter = events.EventEmitter;

/**
 * I wanted to import functions from either zipCodeModule_v1 or zipCodeModule_v2,
 * but it seems not to fit to the background if we assume the data zips.json is
 * actually a database. I copied and pasted codes eventually because the PDF file
 * mentioned that the functions should be exactly the same with Part 1 or Part 2.
 */
class ZipCodeEmitter extends EventEmitter {
  lookupByZipCode(zip) {
    let output;
    for (const item of data) if (item._id === zip) output = item;
    this.emit("lookupByZipCode", output);
  }

  lookupByCityState(city, state) {
    const output = { city, state, data: [] };
    for (const item of data)
      if (item.city === city && item.state === state)
        output.data.push({ zip: item._id, pop: item.pop });
    this.emit("lookupByCityState", output);
  }

  getPopulationByState(state) {
    const total = data.reduce((accumulator, item) => {
      if (item.state === state) accumulator += item.pop;
      return accumulator;
    }, 0);
    const output = { state, pop: total };
    this.emit("getPopulationByState", output);
  }

  on(eventName, listener) {
    super.on(eventName, (value) => {
      listener(value);
      console.log();
    });
  }
}

module.exports.ZipCodeEmitter = ZipCodeEmitter;
