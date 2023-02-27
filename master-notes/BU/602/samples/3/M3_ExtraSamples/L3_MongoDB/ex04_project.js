const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;
console.log(dbUrl);

let newData = [
  { "_id": 1, "quizzes": [ 10, 6, 7 ], "labs": [ 5, 8 ], "final": 80, "midterm": 75 },
  { "_id": 2, "quizzes": [ 9, 10 ], "labs": [ 8, 8 ], "final": 95, "midterm": 80 },
  { "_id": 3, "quizzes": [ 4, 5, 5 ], "labs": [ 6, 5 ], "final": 78, "midterm": 70 }
];

MongoClient.connect(dbUrl, { useNewUrlParser: true},  (err, client) => {
	if (err) throw err;
	console.log('Successfully connected');
	
	let collection = client.db(credentials.database).collection('students');
	
	collection.deleteMany({});
	
	collection.insertMany(newData,  (err, docs) => {
		console.log('Inserted Count:', docs.insertedCount);
		
		test1(collection, client);
	});
});

// SELECT * FROM inventory

const test1 =  (collection, client) => {
	console.log("\n**test1** - Select All Documents in a Collection");
	collection.aggregate([
   {
     $project: {
       quizMax: { $max: "$quizzes"},
       quizMin: { $min: "$quizzes"},
       labMax: { $max: "$labs" },
       labMin: { $min: "$labs" },
       examMax: { $max: [ "$final", "$midterm" ] },
       examMin: { $min: [ "$final", "$midterm" ] },
       quizTotal: { $sum: "$quizzes"},
       labTotal: { $sum: "$labs" },
       examTotal: { $sum: [ "$final", "$midterm" ] }
     }
   }
]).toArray( (err, docs) => {
		console.log(docs);
		client.close();
	});


}

