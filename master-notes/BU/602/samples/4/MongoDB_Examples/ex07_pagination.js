const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb+srv://' + credentials.username +
    ':' + credentials.password + '@' + credentials.host + '/' + credentials.database;
console.log(dbUrl);

const printZipCodes = async (collection, pageNumber, numPerPage) => {
    
    await collection.find({state: "TX"})
            .sort( { _id: 1 } )
            .skip( pageNumber > 0 ? ( ( pageNumber - 1 ) * numPerPage ) : 0 )
            .limit(numPerPage)
            .forEach(data => {
                console.log(data)
            });
}

(async() => {

	let client = await MongoClient.connect(dbUrl, {useNewUrlParser: true, useUnifiedTopology: true});

	console.log('Successfully connected');
	
	let collection = client.db(credentials.database).collection('zipcodes');

    console.log("\n**test1**");
    await printZipCodes(collection, 1, 20);

    console.log("\n**test2**");
    await printZipCodes(collection, 2, 20);
    

	client.close();

	})();
	

