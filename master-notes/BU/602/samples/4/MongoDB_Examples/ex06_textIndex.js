const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb+srv://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + '/' + credentials.database;
console.log(dbUrl);

let newData = [
	 { _id: 1, name: "Java Hut", description: "Coffee and cakes" },
   	{ _id: 2, name: "Burger Buns", description: "Gourmet hamburgers" },
   	{ _id: 3, name: "Coffee Shop", description: "Just coffee" },
   	{ _id: 4, name: "Clothes Clothes Clothes", description: "Discount clothing" },
   	{ _id: 5, name: "Java Shopping", description: "Indonesian goods" }
 ];

(async() => {

  let client = await MongoClient.connect(dbUrl, {useNewUrlParser: true, useUnifiedTopology: true});

  console.log('Successfully connected');
  
  let collection = client.db(credentials.database).collection('stores');

  let docs;

	await collection.deleteMany({});
	
	docs = await collection.insertMany(newData);

	console.log('Inserted Count:', docs.insertedCount);
	console.log('Inserted Ids:', docs.insertedIds);
		
		// create text index to allow text search
		// allow text search over the name and description fields

	await collection.createIndex({ name: "text", description: "text" });

	console.log("\n**test1** - find all stores containing any terms from the list “coffee”, “shop”, and “java”");

	docs = await collection.find( { $text: { $search: "java coffee shop" } } ).toArray();

	console.log(docs);
	

	console.log("\n**test2** - exact phrase - find all documents containing “coffee shop”");

	docs = await collection.find( { $text: { $search: "\"coffee shop\"" } } ).toArray();

	console.log(docs);
	
	console.log("\n**test3** - term exclusion - find all stores containing “java” or “shop” but not “coffee”");
	
	docs = await collection.find( { $text: { $search: "java shop -coffee"  } } ).toArray();
		
	console.log(docs);
	
	client.close();
	
})();

