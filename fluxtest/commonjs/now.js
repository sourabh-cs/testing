var isTime = function(a) {
	console.log("The time is now " + a);
}


exports.hour = new Date().getHours();
exports.minute = new Date().getMinutes();
exports.second = new Date().getSeconds();


exports.day = new Date().getDate();
exports.month = new Date().getMonth() + 1;
exports.year = new Date().getFullYear();

exports.isTime = isTime;