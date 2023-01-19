require("colors");

module.exports.callQueries = function (queries) {
  const questions = [
    [queries.lookupByZipCode, "Lookup by zip code", "02215"],
    [queries.lookupByZipCode, "Lookup by zip code", "99999"],
    [queries.lookupByCityState, "Lookup by city", "BOSTON", "MA"],
    [queries.lookupByCityState, "Lookup by city", "BOSTON", "TX"],
    [queries.lookupByCityState, "Lookup by city", "BOSTON", "AK"],
    [queries.getPopulationByState, "Get Population by State", "MA"],
    [queries.getPopulationByState, "Get Population by State", "TX"],
    [queries.getPopulationByState, "Get Population by State", "AA"],
  ];
  for (let index = 0; index < questions.length; index++) {
    const [query, description, ...conditions] = questions[index];
    console.log(
      `${index + 1}.`,
      description.bold.bgBlue,
      `(${conditions.join(", ")})`.bold.blue,
      "\n",
      query(...conditions),
      "\n"
    );
  }
};
