var data = {
		firstName: 'John',
		lastName:  'Smith'
	};

module.exports.setFirstName = 
	function(value) {
		data.firstName = value;
	};

module.exports.getFirstName = 
	function() {
		return data.firstName;
	};

exports.setLastName = 
	function(value) {
		data.lastName = value;
	};

exports.getLastName = 
	function() {
		return data.lastName;
	};

