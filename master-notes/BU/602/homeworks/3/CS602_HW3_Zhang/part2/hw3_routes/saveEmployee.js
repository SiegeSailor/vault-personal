const employeeDB = require("../employeeDB.js");
const Employee = employeeDB.getModel();

module.exports = async (req, res, next) => {
  const { fname, lname } = req.body;
  await new Employee({ firstName: fname, lastName: lname }).save();

  res.redirect("/employees");
};
