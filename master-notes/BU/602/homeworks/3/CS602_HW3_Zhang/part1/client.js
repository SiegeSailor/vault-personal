require("colors");

const query = require("./mongo_zipCodeModule_v2");

(async () => {
  console.log("\nLookup by zip code (02215)".red);
  const result1 = await query.lookupByZipCode("02215");
  console.log(result1);

  console.log("\nLookup by zip code (99999)".red);
  const result2 = await query.lookupByZipCode("99999");
  console.log(result2);

  console.log("\nLookup by city (BOSTON, MA)".red);
  const result3 = await query.lookupByCityState("BOSTON", "MA");
  console.log(result3);

  console.log("\nGet Population by State (MA)".red);
  const result4 = await query.getPopulationByState("MA");
  console.log(result4);

  process.exit();
})();
