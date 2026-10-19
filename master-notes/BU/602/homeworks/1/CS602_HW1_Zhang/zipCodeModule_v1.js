const data = require("./zips.json");

module.exports = {
  lookupByZipCode: (zip) => {
    for (const item of data) if (item._id === zip) return item;
  },
  lookupByCityState: (city, state) => {
    const output = { city, state, data: [] };
    for (const item of data)
      if (item.city === city && item.state === state)
        output.data.push({ zip: item._id, pop: item.pop });

    return output;
  },
  getPopulationByState: (state) => {
    let total = 0;
    for (const item of data) if (item.state === state) total += item.pop;
    return { state, pop: total };
  },
};
