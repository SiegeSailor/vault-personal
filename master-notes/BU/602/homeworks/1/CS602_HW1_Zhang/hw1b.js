/**
 * I renamed cities to queries because cities doesn't sound like a
 * collection of functions to get data.
 */
const queries = require("./zipCodeModule_v2");
const helpers = require("./helpers");

helpers.callQueries(queries);
