const mongoose = require('mongoose');

// Suppress the deprecation warning
// https://mongoosejs.com/docs/deprecations.html#findandmodify

mongoose.set('useFindAndModify', false);

const credentials = require("./credentials.js");

const dbUrl = 'mongodb+srv://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + '/' + credentials.database;
console.log(dbUrl);

const {Course, Instructor, Student} = require('./models');

// Using Promise syntax
const createCourse = (data) => {
	return Course.create(data).then(doc => {
	  console.log("\n>> Created Course:\n", doc);
	  return doc;
	});
};

// Using async syntax
const createInstructor = async (data) =>  {
	return Instructor.create(data);
};

// Using async syntax
const createStudent = async (data) =>  {
	return Student.create(data);
};

// Many-to-Many associations

const addInstructorToCourse = async (courseId, instructorId) => {
	
	return Course.findByIdAndUpdate(
		courseId, 
		{ $push: { courseInstructors: instructorId} }, 
		{new: true}
	);

};

const addCourseToInstructor = async (instructorId, courseId) => {

	return Instructor.findByIdAndUpdate(
		instructorId, 
		{ $addToSet: { instructorCourses: courseId} },
		{new: true}
	);
};



(async() =>  {

	const connection = await mongoose.connect(dbUrl, {useNewUrlParser: true,useUnifiedTopology: true});

	await Course.deleteMany({});
	await Instructor.deleteMany({});

	let c1 = await createCourse({
		courseNumber: 'CS601',
		courseName: 'Web App Dev'
	});

	let c2 = await createCourse({
		courseNumber: 'CS602',
		courseName: 'Server Side Web Dev'
	});

	let c3 = await createCourse({
		courseNumber: 'CS701',
		courseName: 'RIA'
	});

	let i1 = await createInstructor({
		instructorName: 'Eric Bishop'
	});

	console.log("\n>> Created Instructor:\n", i1);

	let i2 = await createInstructor({
		instructorName: 'Suresh Kalathur'
	});

	console.log("\n>> Created Instructor:\n", i2);
	
	// Add instructors to courses
	
	console.log(`\n>> Add i1 (${i1._id}) to c1`);
	console.log(await addInstructorToCourse(c1._id, i1._id));
	
	console.log(`\n>> Add i1 (${i1._id}) to c2`);
	console.log(await addInstructorToCourse(c2._id, i1._id));
	
	console.log(`\n>> Add i2 (${i2._id}) to c2`);
	console.log(await addInstructorToCourse(c2._id, i2.id));
	
	console.log(`\n>> Add i2 (${i2._id}) to c3`);
	console.log(await addInstructorToCourse(c3._id, i2._id));

	console.log(`\n>> Add i2 (${i2._id}) to c3 - Duplicate Test (Fail)`);
	console.log(await addInstructorToCourse(c3._id, i2._id));

	// Add Courses to Instructors

	console.log(`\n>> Add c1 (${c1._id}) to i1`);
	console.log(await addCourseToInstructor(i1._id, c1._id));

	console.log(`\n>> Add c2 (${c2._id}) to i1`);
	console.log(await addCourseToInstructor(i1._id, c2._id));
	
	console.log(`\n>> Add c2 (${c2._id}) to i2`);
	console.log(await addCourseToInstructor(i2._id, c2._id));
	
	console.log(`\n>> Add c3 (${c3._id}) to i2`);
	console.log(await addCourseToInstructor(i2._id, c3._id));

	console.log(`\n>> Add c3 (${c3._id}) to i2 - Duplicate test (Pass)`);
	console.log(await addCourseToInstructor(i2._id, c3._id));
	
	
	/*
	c1.courseInstructors.push(i1);
	await c1.save();

	c2.courseInstructors.push(i1);
	c2.courseInstructors.push(i2);
	await c2.save();
	
	c3.courseInstructors.push(i2);
	await c3.save();

	i1.instructorCourses.push(c1);
	i1.instructorCourses.push(c2);
	await i1.save();

	i2.instructorCourses.push(c2);
	i2.instructorCourses.push(c3);
	await i2.save();
	*/
	


	
	process.exit();


})();

























