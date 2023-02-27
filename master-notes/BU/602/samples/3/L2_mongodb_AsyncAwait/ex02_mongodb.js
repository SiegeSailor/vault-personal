const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

let newData = { firstName: 'John', lastName: 'Doe' };

(async () => {

	let client = await MongoClient.connect(dbUrl,  { useNewUrlParser: true });
	console.log('Successfully connected to', client.s.options.dbName);
	
	let collection = client.db(credentials.database).collection('people');
	
	let docs = await collection.insertOne(newData);

	console.log('Inserted Count:', docs.insertedCount);
	console.log('Inserted Id:', docs.insertedId);
	console.log('Data ID:', newData);

	client.close();

})();

