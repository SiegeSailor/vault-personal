require("colors");

const queries = require("./zipCodeModule_v1");

const questions = [
  [queries.lookupByZipCode, "Lookup by zip code", "02215"],
  [queries.lookupByZipCode, "Lookup by zip code", "99999"],
  [queries.lookupByCityState, "Lookup by city", "BOSTON", "MA"],
  [queries.lookupByCityState, "Lookup by city", "BOSTON", "TX"],
  [queries.lookupByCityState, "Lookup by city", "BOSTON", "AK"],
  [queries.getPopulationByState, "Get population by state", "MA"],
  [queries.getPopulationByState, "Get population by state", "TX"],
  [queries.getPopulationByState, "Get population by state", "AA"],
];
for (let index = 0; index < questions.length; index++) {
  const [query, description, ...condition] = questions[index];
  console.log(
    description.bold.bgBlue,
    `(${condition})`.bold.blue,
    "\n",
    query(...condition),
    "\n"
  );
}
