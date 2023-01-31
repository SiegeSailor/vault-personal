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

app.get("/", (_, response) => {
  response.render("homeView");
});

app.get("/zip", (_, response) => {
  response.render("lookupByZipForm");
});

app.post("/zip", (request, response) => {
  try {
    const zip = request.body.id;
    if (!zip.match(/^[0-9]+$/))
      throw new Error("Zip code contains non-numeric characters.");
    const result = query.lookupByZipCode(zip);
    response.render("lookupByZipView", {
      zip,
      result,
    });
  } catch (event) {
    response.status(400);
    response.render("invalidView", {
      message: event.message,
    });
  }
});

// Implement the JSON, XML, & HTML formats

app.get("/zip/:id", (request, response) => {});

app.get("/city", (request, response) => {});

app.post("/city", (request, response) => {});

// Implement the JSON, XML, & HTML formats

app.get("/city/:city/state/:state", (request, response) => {});

app.get("/pop", (request, response) => {});

// Implement the JSON, XML, & HTML formats

app.get("/pop/:state", (request, response) => {});

app.use((request, response) => {
  response.status(404);
  response.render("404");
});

app.listen(3000, () => {
  console.log("http://localhost:3000");
});
