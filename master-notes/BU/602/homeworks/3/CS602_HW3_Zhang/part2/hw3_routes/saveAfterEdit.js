const employeeDB = require("../employeeDB.js");
const Employee = employeeDB.getModel();

module.exports = async (req, res, next) => {
  const id = req.body.id;

  const employee = await Employee.findById(id);

  if (!employee) {
    res.render("404");
  } else {
    employee.firstName = req.body.fname;
    employee.lastName = req.body.lname;
    await employee.save();
    res.redirect("/employees");
  }
};
