const mongoose = require('mongoose');
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

const connection = mongoose.createConnection(dbUrl, { useNewUrlParser: true } );

const CourseDb = require('./coursesDb.js');
const Course = CourseDb.getModel(connection);

connection.on("open", () => {
	
	// Find all courses
	Course.find({}, 'courseName courseNumber', 
		(err, results) => {
			if (err) throw err;
			console.log("\nFind all courses");
			console.log(results);

			connection.close();
		});

});

























