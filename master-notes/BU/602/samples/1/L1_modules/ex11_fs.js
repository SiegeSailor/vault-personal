var fs = require('fs');
  
fs.unlink('./cs701.txt', function(err){
	if (err) throw err;
	console.log('cs701.txt successfully deleted');
});
  
