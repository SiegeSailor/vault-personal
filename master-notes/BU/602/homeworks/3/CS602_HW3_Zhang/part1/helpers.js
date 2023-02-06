const query = require("./mongo_zipCodeModule_v2");

const verification = {
  numeric: function (input) {
    return input.match(/^[0-9]+$/);
  },
  zip: function (input) {
    if (!input)
      throw new Error(
        "Zip Code is necessary to lookup for the corresponding data."
      );
    if (!verification.numeric(input))
      throw new Error("Zip Code contains non-numeric characters.");
  },
  cityState: function (city, state) {
    if (!city || !state)
      throw new Error(
        "City and State is necessary to lookup for the corresponding data."
      );
    if (verification.numeric(city) || verification.numeric(state))
      throw new Error("City or State contains numeric characters.");
  },
  state: function (state) {
    if (!state)
      throw new Error(
        "State is necessary to lookup for the corresponding data."
      );
    if (verification.numeric(state))
      throw new Error("State contains numeric characters.");
  },
};

const render = {
  badRequest: function (response, message) {
    response.status(400);
    response.render("invalidView", {
      message,
    });
  },
  zipLookup: async function (zip, response) {
    try {
      verification.zip(zip);
      const result = await query.lookupByZipCode(zip);

      response.format({
        html: function () {
          response.render("lookupByZipView", {
            zip,
            result,
          });
        },
        json: function () {
          response.json(!!result ? result : { message: "Not found." });
        },
        xml: function () {
          const content = !!result
            ? `<city>${result.city}</city><state>${result.state}</state><pop>${result.pop}</pop>`
            : "No result found.";
          response.send(
            `<?xml version="1.0"?><zipCode id="${zip}">${content}</zipCode> `
          );
        },
        default: function () {
          throw new Error("Invalid required document type.");
        },
      });
    } catch (event) {
      console.error(event);
      render.badRequest(response, event.message);
    }
  },
  cityStateLookup: async function (city, state, response) {
    try {
      verification.cityState(city, state);
      city = city.toUpperCase();
      state = state.toUpperCase();
      const result = await query.lookupByCityState(city, state);

      response.format({
        html: function () {
          response.render("lookupByCityStateView", {
            result,
          });
        },
        json: function () {
          response.json(!!result ? result : { message: "Not found." });
        },
        xml: function () {
          const content = !!result.data.length
            ? result.data.map(function (item) {
                return `<entry zip="${item.zip}" pop="${item.pop}" />`;
              })
            : "No result found.";
          response.send(
            `<?xml version="1.0"?><city-state city="${result.city}" state="${result.state}">${content}</city-state> `
          );
        },
        default: function () {
          throw new Error("Invalid required document type.");
        },
      });
    } catch (event) {
      console.error(event);
      render.badRequest(response, event.message);
    }
  },
  population: async function (state, response) {
    try {
      verification.state(state);
      state = state.toUpperCase();
      const result = await query.getPopulationByState(state);

      response.format({
        html: function () {
          response.render("populationView", {
            result,
          });
        },
        json: function () {
          response.json(!!result ? result : { message: "Not found." });
        },
        xml: function () {
          const content = !!result.pop
            ? `<pop>${result.pop}</pop>`
            : "No result found.";
          response.send(
            `<?xml version="1.0"?><state-pop state="${result.state}">${content}</state-pop> `
          );
        },
        default: function () {
          throw new Error("Invalid required document type.");
        },
      });
    } catch (event) {
      console.error(event);
      render.badRequest(response, event.message);
    }
  },
};

module.exports = {
  verification,
  render,
};
