const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", () => {
	
 // Update
	Course.updateOne({courseNumber: 'cs701'}, 
			{courseName: 'Rich Internet App Development'}, 
		(err, result) => {
			connection.close();
			if (err) throw err;
			console.log("\nUpdate...");
			console.log("Affected docs:", result);
	});

		
});

























