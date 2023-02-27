const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
	// Delete existing data

	await Course.deleteMany({});

	// create and save document objects
	let course;

	course = new Course({
		courseNumber: 'cs601',
		courseName: 'Web Application Development',
		courseDevelopers: [
			{ firstName: 'Eric',lastName: 'Bishop'}
		]
	}); 
	await course.save();

	course = new Course({
		courseNumber: 'cs602',
		courseName: 'Server Side Web Development',
		courseDevelopers: [
			{ firstName: 'Eric', lastName: 'Bishop'},
			{ firstName: 'Suresh',lastName: 'Kalathur'}
		]
	}); 
	await course.save();
  

	course = new Course({
		courseNumber: 'cs701',
		courseName: 'RIA',
		courseDevelopers: [
			{ firstName: 'Suresh',lastName: 'Kalathur'}
		]
	}); 
	await course.save();

	connection.close();
	console.log("Success!");
	
});










