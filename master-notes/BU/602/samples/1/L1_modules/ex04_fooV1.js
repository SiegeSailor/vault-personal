var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

var f1 = function(value) {
	data.firstName = value;
};
var f2 = function() {
	return data.firstName;
};
var f3 = function(value) {
	data.lastName = value;
};
var f4 = function() {
	return data.lastName;
};

module.exports = {
	setFirstName: f1,
	getFirstName: f2,
	setLastName:  f3,
	getLastName:  f4
};

