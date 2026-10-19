const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;
console.log(dbUrl);

(async () => {

	let client = await MongoClient.connect(dbUrl,  { useNewUrlParser: true });
	console.log('Successfully connected to', client.s.options.dbName);
	client.close();

})();
