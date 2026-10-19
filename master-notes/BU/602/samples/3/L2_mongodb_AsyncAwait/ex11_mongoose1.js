const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
 // Update
	let result = await Course.updateOne(
											{courseNumber: 'cs701'}, 
											{courseName: 'Rich Internet App Development'});
		
	console.log("\nUpdate...");
	console.log("Affected docs:", result);
	
	connection.close();
		
});

























