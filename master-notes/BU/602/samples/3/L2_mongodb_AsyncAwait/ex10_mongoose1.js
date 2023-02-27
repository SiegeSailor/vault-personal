const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
	// Find all courses
	let results = await Course.find({}, 'courseName courseNumber');

	console.log("\nFind all courses");
	console.log(results);

	connection.close();	

});

























