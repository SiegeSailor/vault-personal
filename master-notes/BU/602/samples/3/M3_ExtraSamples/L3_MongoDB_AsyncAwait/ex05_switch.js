const MongoClient = require('mongodb').MongoClient;
const credentials = require("./credentials.js");

const dbUrl = 'mongodb://' + credentials.username +
	':' + credentials.password + '@' + credentials.host + ':' + credentials.port + '/' + credentials.database;
console.log(dbUrl);

let newData = [
	{ "_id" : 1, "name" : "Susan Wilkes",   "scores" : [ 87, 86, 78 ] },
  { "_id" : 2, "name" : "Bob Hanna",      "scores" : [ 71, 64, 81 ] },
  { "_id" : 3, "name" : "James Torrelio", "scores" : [ 91, 84, 97 ] }
];

(async() => {

  let client = await MongoClient.connect(dbUrl, {useNewUrlParser: true});

  console.log('Successfully connected');
  
  let collection = client.db(credentials.database).collection('grades');

  var docs;
	
	await collection.deleteMany({});
	
	docs = collection.insertMany(newData);
	
  console.log('Inserted Count:', docs.insertedCount);
		
	console.log("\n**test1** - Select All Documents in a Collection");
	
  docs = await collection.aggregate( [
          {
            $project:
              {
                "name" : 1,
                "summary" :
                {
                  $switch:
                    {
                      branches: [
                        {
                          case: { $gte : [ { $avg : "$scores" }, 90 ] },
                          then: "Doing great!"
                        },
                        {
                          case: { $and : [ { $gte : [ { $avg : "$scores" }, 80 ] },
                                           { $lt : [ { $avg : "$scores" }, 90 ] } ] },
                          then: "Doing pretty well."
                        },
                        {
                          case: { $lt : [ { $avg : "$scores" }, 80 ] },
                          then: "Needs improvement."
                        }
                      ],
                      default: "No scores found."
                    }
                 }
              }
           }
        ] ).toArray();

  console.log(docs);
	
  client.close();

})();

