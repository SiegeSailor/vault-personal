var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

module.exports = {
	setFirstName: (value) => {
		data.firstName = value;
	},
	getFirstName: () => {
		return data.firstName;
	},
	setLastName: (value) => {
		data.lastName = value;
	},
	getLastName: () => {
		return data.lastName;
	}
};

