var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

var f1 = (value) => {
	data.firstName = value;
};
var f2 = () => {
	return data.firstName;
};
var f3 = (value) => {
	data.lastName = value;
};
var f4 = () => {
	return data.lastName;
};

module.exports = {
	setFirstName: f1,
	getFirstName: f2,
	setLastName:  f3,
	getLastName:  f4
};

