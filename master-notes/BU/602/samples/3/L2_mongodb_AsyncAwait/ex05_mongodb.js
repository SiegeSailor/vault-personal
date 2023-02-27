const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;


(async () => {

	let client = await MongoClient.connect(dbUrl,  { useNewUrlParser: true });
	console.log('Successfully connected to', client.s.options.dbName);
	
	let collection = client.db(credentials.database).collection('people');
	
	let docs1 = await collection.find({lastName: 'Smith'}).toArray();
	console.log("Search by lastName:\n", docs1);

	let docs2 = await collection.find({firstName: 'John'}).toArray();
	console.log("Search by firstName:\n", docs2);

	let docs3 = await collection.find({firstName: 'John', lastName: 'Smith'}).toArray();
	console.log("Search by first and last Name:\n", docs3);

	client.close();

})();

