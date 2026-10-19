const employeeDB = require("../employeeDB.js");
const Employee = employeeDB.getModel();

module.exports = async (req, res, next) => {
  const id = req.params.id;

  const employee = await Employee.findById(id);

  if (!employee) {
    res.render("404");
  } else {
    res.render("deleteEmployeeView", {
      title: "Delete an Employee",
      data: {
        id: employee._id,
        firstName: employee.firstName,
        lastName: employee.lastName,
      },
    });
  }
};
