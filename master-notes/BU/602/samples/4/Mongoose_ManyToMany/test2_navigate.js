const mongoose = require('mongoose');
const colors = require('colors');

const credentials = require("./credentials.js");

const dbUrl = 'mongodb+srv://' + credentials.username +
    ':' + credentials.password + '@' + credentials.host + '/' + credentials.database;
console.log(dbUrl);

const {Course, Instructor, Student} = require('./models');

(async() =>  {

	const connection = await mongoose.connect(dbUrl, {useNewUrlParser: true,useUnifiedTopology: true});

    console.log("\n>> Course Find CS602".red);
    let course1 = await Course.findOne({courseNumber: 'CS602'});
    console.log(course1);

    console.log("\n>> Course Find CS602 with populate".red);
    
    let course2 = await Course.findOne({courseNumber: 'CS602'})
                            .populate("courseInstructors");
    console.log(JSON.stringify(course2, null, 2));


    console.log("\n>> Course Find CS602 with populate - include only some fields".red);
    let course3 = await Course.findOne({courseNumber: 'CS602'})
                            .populate("courseInstructors", "_id instructorName");
    console.log(JSON.stringify(course3, null, 2));

    console.log("\n>> Course Find CS602 with populate - include only some fields (Pretty Print)".red);
    
    let course;

    course = course3;

    console.log(`${course.courseNumber} - ${course.courseName}`);
    course.courseInstructors.map(instructor => {
        console.log(` Developed by ${instructor.instructorName}`);
    })

    
    console.log("\n>> Course Find CS602 with populate multiple levels".red);
    let course4 = await Course.findOne({courseNumber: 'CS602'})
                            .populate({
                                path: "courseInstructors",
                                populate: {path: "instructorCourses", select: "courseNumber courseName -_id"}
                            });
    console.log(JSON.stringify(course4, null, 2));


    console.log("\n>> Course CS602 with populate multiple levels (Pretty Output)".red);
    
    course = course4;

    console.log(`${course.courseNumber} - ${course.courseName}`);
    course.courseInstructors.map(instructor => {
        console.log(` Developed by ${instructor.instructorName}`);
        instructor.instructorCourses.map(c => {
            console.log(`   Developer for ${c.courseNumber} - ${c.courseName}`);
        })
    })


	process.exit();


})();

























