const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;

let newData = [
	{ firstName: 'Jane', lastName: 'Doe' },
	{ firstName: 'John', lastName: 'Smith' },
	{ firstName: 'Josh', lastName: 'Smith' }];


(async () => {

	let client = await MongoClient.connect(dbUrl,  { useNewUrlParser: true });
	console.log('Successfully connected to', client.s.options.dbName);
	
	let collection = client.db(credentials.database).collection('people');
	
	let docs = await collection.insertMany(newData);

	console.log('Inserted Count:', docs.insertedCount);
	console.log('Inserted Ids:', docs.insertedIds);
	console.log('Data IDs:', newData);

	client.close();

})();


