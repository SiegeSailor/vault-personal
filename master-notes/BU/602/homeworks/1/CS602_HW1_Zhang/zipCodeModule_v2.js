const data = require("./zips.json");

module.exports.lookupByZipCode = (zip) => {
  return data.find((item) => item._id === zip);
};

module.exports.lookupByCityState = (city, state) => {
  return {
    city,
    state,
    data: data
      .filter((item) => {
        return item.city === city && item.state === state;
      })
      .map((item) => {
        return { zip: item._id, pop: item.pop };
      }),
  };
};

module.exports.getPopulationByState = (state) => {
  const total = data.reduce((accumulator, item) => {
    if (item.state === state) accumulator += item.pop;
    return accumulator;
  }, 0);
  return { state, pop: total };
};
