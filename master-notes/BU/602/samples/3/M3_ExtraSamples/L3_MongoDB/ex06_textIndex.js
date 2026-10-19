const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;
console.log(dbUrl);

let newData = [
	 { _id: 1, name: "Java Hut", description: "Coffee and cakes" },
   { _id: 2, name: "Burger Buns", description: "Gourmet hamburgers" },
   { _id: 3, name: "Coffee Shop", description: "Just coffee" },
   { _id: 4, name: "Clothes Clothes Clothes", description: "Discount clothing" },
   { _id: 5, name: "Java Shopping", description: "Indonesian goods" }
 ];

MongoClient.connect(dbUrl, {useNewUrlParser: true}, (err, client) => {
	if (err) throw err;
	console.log('Successfully connected');
	
	let collection = client.db(credentials.database).collection('stores');
	
	collection.deleteMany({});
	
	collection.insertMany(newData,  (err, docs) => {
		console.log('Inserted Count:', docs.insertedCount);
		console.log('Inserted Ids:', docs.insertedIds);
		
		// create text index to allow text search
		// allow text search over the name and description fields

		collection.createIndex({ name: "text", description: "text" });


		test1(collection, client);
	});
});

// SELECT * FROM inventory

const test1 =  (collection, client) => {
	console.log("\n**test1** - find all stores containing any terms from the list “coffee”, “shop”, and “java”");
	collection.find( { $text: { $search: "java coffee shop" } } ).toArray( (err, docs) => {
		console.log(docs);
		test2(collection, client);
		
	});
}

const test2 =  (collection, client) => {
	console.log("\n**test2** - exact phrase - find all documents containing “coffee shop”");
	collection.find( { $text: { $search: "\"coffee shop\"" } } ).toArray( (err, docs) => {
		console.log(docs);
		test3(collection, client);
		
	});
}

const test3 =  (collection, client) => {
	console.log("\n**test3** - term exclusion - find all stores containing “java” or “shop” but not “coffee”");
	collection.find( { $text: { $search: "java shop -coffee"  } } ).toArray( (err, docs) => {
		console.log(docs);
		client.close();
		
	});
}

