const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
 
	// Update multiple documents
	
	let result = await Course.updateMany(
												{}, 
												{courseName: 'Web Development Course'});	
			
	console.log("\nUpdate Many...");
	console.log("Affected docs:", result);
		
	connection.close();
		
});

























