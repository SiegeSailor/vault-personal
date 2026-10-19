/**
 * I renamed cities to queries because cities doesn't sound like a
 * collection of functions to get data.
 */
const queries = require("./zipCodeModule_v1");
const helpers = require("./helpers");

helpers.callQueries(queries);
