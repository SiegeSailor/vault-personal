require("colors");

const ZipCodeEmitter = require("./zipCodeEmitter").ZipCodeEmitter;

/**
 * I renamed cities to zipCodeEmitter because cities doesn't sound like a
 * collection of functions to get data.
 */
const zipCodeEmitter = new ZipCodeEmitter();

zipCodeEmitter.on("lookupByZipCode", (output) => {
  console.log("Event lookupByZipCode raised!".blue);
  console.log(output);
});

zipCodeEmitter.on("lookupByCityState", (output) => {
  console.log("Event lookupByCityState raised! (Handler1)".blue);
  console.log(output);
});

zipCodeEmitter.on("lookupByCityState", (output) => {
  console.log("Event lookupByCityState raised! (Handler2)".blue);
  console.log(output);
});

zipCodeEmitter.on("getPopulationByState", (output) => {
  console.log("Event getPopulationByState raised!".blue);
  console.log(output);
});

const zip = "02215";
console.log(`Lookup by zip code (${zip})`.red);
zipCodeEmitter.lookupByZipCode(zip);

const city = "BOSTON",
  stateLookup = "MA";
console.log(`Lookup by city (${city}, ${stateLookup})`.red);
zipCodeEmitter.lookupByCityState(city, stateLookup);

const statePopulation = "MA";
console.log(`Get Population by State (${statePopulation})`.red);
zipCodeEmitter.getPopulationByState(statePopulation);
