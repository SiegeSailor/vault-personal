const express = require("express");
const app = express();

const bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// setup handlebars view engine
const handlebars = require("express-handlebars");

app.engine("handlebars", handlebars({ defaultLayout: "main" }));

app.set("view engine", "handlebars");

// static resources
app.use(express.static(__dirname + "/public"));

// Use the employee module
const query = require("./mongo_zipCodeModule_v2");
const helpers = require("./helpers");

// GET request to the homepage

app.get("/", function (_, response) {
  response.render("homeView");
});

app.get("/zip", async function (request, response) {
  if (!!request.query.id)
    await helpers.render.zipLookup(request.query.id, response);
  else response.render("lookupByZipForm");
});

app.post("/zip", async function (request, response) {
  try {
    const zip = request.body.id;
    helpers.verification.zip(zip);
    const result = await query.lookupByZipCode(zip);

    response.render("lookupByZipView", {
      zip,
      result,
    });
  } catch (event) {
    helpers.render.badRequest(response, event.message);
  }
});

app.get("/zip/:id", async function (request, response) {
  await helpers.render.zipLookup(request.params.id, response);
});

// Complete the code for the following

app.get("/city", async function (request, response) {
  const { city, state } = request.query;
  if (city && state)
    await helpers.render.cityStateLookup(city, state, response);
  else response.render("lookupByCityStateForm");
});

app.post("/city", async function (request, response) {
  try {
    let { city, state } = request.body;
    helpers.verification.cityState(city, state);
    city = city.toUpperCase();
    state = state.toUpperCase();
    const result = await query.lookupByCityState(city, state);

    response.render("lookupByCityStateView", {
      result,
    });
  } catch (event) {
    helpers.render.badRequest(response, event.message);
  }
});

app.get("/city/:city/state/:state", async function (request, response) {
  const { city, state } = request.params;
  await helpers.render.cityStateLookup(city, state, response);
});

app.get("/pop", async function (request, response) {
  if (!!request.query.state)
    await helpers.render.population(request.query.state, response);
  else response.render("populationForm");
});

app.get("/pop/:state", async function (request, response) {
  await helpers.render.population(request.params.state, response);
});

app.use(function (_, response) {
  response.status(404);
  response.render("404");
});

app.listen(3000, function () {
  console.log("http://localhost:3000");
});
