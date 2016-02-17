

var now = require('./now');


document.getElementById('when').innerHTML = now.day + "/" + now.month + "/" + now.year;

now.isTime();
now.isTime('baaaa');