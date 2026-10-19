const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
	// Find all courses
	let results1 = await Course.find({}, 'courseName courseNumber');
	console.log("\nFind all courses");
	console.log(results1);
		
	let results2 = await Course.find({courseNumber: 'cs602'}); 
	console.log("\nFind cs602");
	console.log(results2);
	
	let results3 = await Course.find({courseDevelopers: {$not: {$size: 1}}});
		
	console.log("\nCourses with multiple develeopers");
	console.log(results3);
	console.log(JSON.stringify(results3, null, 2));
	
	connection.close();
	
});

























