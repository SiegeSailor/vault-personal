var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

module.exports.setFirstName = 
	(value) => {
		data.firstName = value;
	};

module.exports.getFirstName = 
	() => {
		return data.firstName;
	};

exports.setLastName = 
	(value) => {
		data.lastName = value;
	};

exports.getLastName = 
	() => {
		return data.lastName;
	};

