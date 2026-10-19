const MongoClient = require("mongodb").MongoClient;
const credentials = require("./credentials.js");

const dbUrl =
  "mongodb+srv://" +
  credentials.username +
  ":" +
  credentials.password +
  "@" +
  credentials.host +
  "/" +
  credentials.database;

let client = null;

const getConnection = async () => {
  if (client == null)
    client = await MongoClient.connect(dbUrl, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
  return client;
};

module.exports.lookupByZipCode = async (zip) => {
  const client = await getConnection();
  const collection = client.db(credentials.database).collection("zipcodes");

  const result = await collection.find({ _id: zip }).toArray();

  if (result.length > 0) return result[0];
  else return undefined;
};

module.exports.lookupByCityState = async (city, state) => {
  const client = await getConnection();
  const collection = client.db(credentials.database).collection("zipcodes");

  const result = await collection.find({ city, state }).toArray();
  return {
    city,
    state,
    data: result.map((item) => {
      return { zip: item._id, pop: item.pop };
    }),
  };
};

module.exports.getPopulationByState = async (state) => {
  const client = await getConnection();
  const collection = client.db(credentials.database).collection("zipcodes");

  const result = await collection.find({ state }).toArray();
  const total = result.reduce((accumulator, item) => {
    if (item.state === state) accumulator += item.pop;
    return accumulator;
  }, 0);
  return { state, pop: total };
};
