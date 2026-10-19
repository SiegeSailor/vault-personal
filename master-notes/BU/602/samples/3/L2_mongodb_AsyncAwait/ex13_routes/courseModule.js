const courseDB = require("./courseDB.js");
const Course = courseDB.getModel();

module.exports.addCourse = (req, res, next) => {
  res.render("addCourseView", { title: "Add a Course" });
};

module.exports.deleteCourse = async (req, res, next) => {
  let id = req.params.id;

  let course = await Course.findById(id);

  if (!course) {
    res.render("404");
  } else {
    await course.remove();
    res.redirect("/courses");
  }
};

module.exports.displayCourses = async (req, res, next) => {
  let courses = await Course.find({});

  let results = courses.map((course) => {
    return {
      id: course._id,
      courseName: course.courseName,
      courseNumber: course.courseNumber,
      developers: course.getDeveloperNames(),
    };
  });

  res.render("displayCoursesView", { title: "List of Courses", data: results });
};

module.exports.editCourse = async (req, res, next) => {
  let id = req.params.id;

  let course = await Course.findById(id);

  if (!course) {
    res.render("404");
  } else {
    res.render("editCourseView", {
      title: "Edit Course",
      data: {
        id: course._id,
        courseNumber: course.courseNumber,
        courseName: course.courseName,
        developers: course.getDeveloperNames(),
      },
    });
  }
};

module.exports.saveCourse = async (req, res, next) => {
  let developers = req.body.cdev;
  // create an array of objects
  if (developers.length > 0) {
    developers = developers.split(",").map((elem) => {
      let names = elem.trim().split(" ");
      return { firstName: names[0], lastName: names[1] };
    });
  } else developers = [];

  let course = new Course({
    courseNumber: req.body.cnumber,
    courseName: req.body.cname,
    courseDevelopers: developers,
  });

  await course.save();

  res.redirect("/courses");
};

module.exports.saveAfterEdit = async (req, res, next) => {
  let id = req.params.id;

  let course = await Course.findById(id);

  if (!course) {
    res.render("404");
  } else {
    course.courseNumber = req.body.cnumber;
    course.courseName = req.body.cname;
    let developers = req.body.cdev;
    if (developers.length > 0) {
      developers = developers.split(",").map((elem) => {
        let names = elem.trim().split(" ");
        return { firstName: names[0], lastName: names[1] };
      });
      course.courseDevelopers = developers;
    }

    await course.save();
    res.redirect("/courses");
  }
};
