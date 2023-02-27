var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

module.exports = {
	setFirstName: function(value) {
		data.firstName = value;
	},
	getFirstName: function() {
		return data.firstName;
	},
	setLastName: function(value) {
		data.lastName = value;
	},
	getLastName: function() {
		return data.lastName;
	}
};

