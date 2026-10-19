const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;
console.log(dbUrl);


(async() => {

	let client = await MongoClient.connect(dbUrl, {useNewUrlParser: true});

	console.log('Successfully connected');
	
	let collection = client.db(credentials.database).collection('zipcodes');

	var docs;

// https://docs.mongodb.com/manual/tutorial/aggregation-zip-code-data-set/

	console.log("\n**test1**");

	docs = await collection.find({}).toArray();

	console.log("#Docs=", docs.length);
	console.log("First Doc", docs[0]);
	

// Return States with Populations above 10 Million
/*
  SELECT state, SUM(pop) AS totalPop
   FROM zipcodes
  GROUP BY state
   HAVING totalPop >= (10*1000*1000)
*/

	console.log("\n**test2** - Return States with Populations above 10 Million");

	docs = await collection.aggregate( [
	   { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
	   { $match: { totalPop: { $gte: 10*1000*1000 } } }
	] ).toArray();

	console.log(docs);

	console.log("\n**test3** - Return Average City Population by State");
		
	docs = await collection.aggregate( [
	   { $group: { _id: { state: "$state", city: "$city" }, pop: { $sum: "$pop" } } },
	   { $group: { _id: "$_id.state", avgCityPop: { $avg: "$pop" } } }
	] ).toArray();

	console.log(docs);
	
	console.log("\n**test4** - Return Largest and Smallest Cities by State");

	docs = await collection.aggregate( [
			   { $group:
			      {
			        _id: { state: "$state", city: "$city" },
			        pop: { $sum: "$pop" }
			      }
			   },
			   { $sort: { pop: 1 } },
			   { $group:
			      {
			        _id : "$_id.state",
			        biggestCity:  { $last: "$_id.city" },
			        biggestPop:   { $last: "$pop" },
			        smallestCity: { $first: "$_id.city" },
			        smallestPop:  { $first: "$pop" }
			      }
			   },

			  // the following $project is optional, and
			  // modifies the output format.

			  { $project:
			    { _id: 0,
			      state: "$_id",
			      biggestCity:  { name: "$biggestCity",  pop: "$biggestPop" },
			      smallestCity: { name: "$smallestCity", pop: "$smallestPop" }
			    }
			  }, 
			  { $sort: { state: 1 } }
			] ).toArray();

		console.log(docs);
	
		client.close();

	})();
	

