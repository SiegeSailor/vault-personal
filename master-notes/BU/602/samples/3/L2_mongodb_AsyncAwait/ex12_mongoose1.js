const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
	  // Delete a document
	let result = await Course.deleteOne({courseNumber: 'cs602'});
	
	console.log("\nDelete...");
	console.log("Affected docs:", result);
	
	connection.close();

});















































