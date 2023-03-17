const express = require("express");

const application = express();

const handlebars = require("express-handlebars");
application.engine("handlebars", handlebars({ defaultLayout: "main" }));
application.set("view engine", "handlebars");

const studentModule = require("./studentModule");

application.get("/student/:id", function (request, response) {
  const result = studentModule.lookupByStudentId(request.params.id);
  response.render("studentView", result);
});

const PORT = 3000;
application.listen(PORT, function () {
  console.log(`http://localhost:${PORT}`);
});
