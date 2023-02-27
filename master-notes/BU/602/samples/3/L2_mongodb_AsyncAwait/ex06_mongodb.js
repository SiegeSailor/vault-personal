const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;


(async () => {

	let client = await MongoClient.connect(dbUrl,  { useNewUrlParser: true });
	console.log('Successfully connected to', client.s.options.dbName);
	
	let collection = client.db(credentials.database).collection('people');
	
	let docs = await collection.updateOne(
											{firstName: 'Josh', lastName: 'Smith'},
											{$set: {firstName: 'James'}} );

	console.log(docs.result);

	client.close();

})();

