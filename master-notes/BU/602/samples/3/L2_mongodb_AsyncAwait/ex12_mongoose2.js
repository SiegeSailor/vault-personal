const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", async () => {
	
	let result = await Course.deleteMany(
								{courseName: { "$regex": "Web", "$options": "i" }});
	
	console.log("\nDelete...");
	console.log("Affected docs:", result);

	connection.close();
	
});















































