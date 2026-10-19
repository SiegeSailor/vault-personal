const express = require("express");

const app = express();

const bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const handlebars = require("express-handlebars");

app.engine("handlebars", handlebars({ defaultLayout: "main" }));
app.set("view engine", "handlebars");

app.use(express.static(__dirname + "/public"));

const query = require("./zipCodeModule_v2");
const helpers = require("./helpers");

app.get("/", (_, response) => {
  response.render("homeView");
});

app.get("/zip", (request, response) => {
  if (!!request.query.id) helpers.render.zipLookup(request.query.id, response);
  else response.render("lookupByZipForm");
});

app.post("/zip", (request, response) => {
  try {
    const zip = request.body.id;
    helpers.verification.zip(zip);
    const result = query.lookupByZipCode(zip);

    response.render("lookupByZipView", {
      zip,
      result,
    });
  } catch (event) {
    helpers.render.badRequest(response, event.message);
  }
});

app.get("/zip/:id", (request, response) => {
  helpers.render.zipLookup(request.params.id, response);
});

app.get("/city", (request, response) => {
  const { city, state } = request.query;
  if (city && state) helpers.render.cityStateLookup(city, state, response);
  else response.render("lookupByCityStateForm");
});

app.post("/city", (request, response) => {
  try {
    let { city, state } = request.body;
    helpers.verification.cityState(city, state);
    city = city.toUpperCase();
    state = state.toUpperCase();
    const result = query.lookupByCityState(city, state);

    response.render("lookupByCityStateView", {
      result,
    });
  } catch (event) {
    helpers.render.badRequest(response, event.message);
  }
});

app.get("/city/:city/state/:state", (request, response) => {
  const { city, state } = request.params;
  helpers.render.cityStateLookup(city, state, response);
});

app.get("/pop", (request, response) => {
  if (!!request.query.state)
    helpers.render.population(request.query.state, response);
  else response.render("populationForm");
});

app.get("/pop/:state", (request, response) => {
  helpers.render.population(request.params.state, response);
});

app.use((request, response) => {
  response.status(404);
  response.render("404");
});

app.listen(3000, () => {
  console.log("http://localhost:3000");
});
