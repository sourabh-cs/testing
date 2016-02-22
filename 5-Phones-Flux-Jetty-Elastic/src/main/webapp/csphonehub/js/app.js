var React = require('react');
var ReactDOM = require('react-dom');
var PhoneRoot = require('./components/PhoneRoot');

function init() {
    window.addEventListener('scroll', function(e){
        var distanceY = window.pageYOffset || document.documentElement.scrollTop;
        var shrinkOn = 40;
        if (distanceY > shrinkOn) {
            document.querySelector('header').classList.add('smaller');
        } else {
            if (document.querySelector('header').classList.contains('smaller')) {
                document.querySelector('header').classList.remove('smaller');
            }
        }
    });
}

window.onload = init();

ReactDOM.render(
	<PhoneRoot />,
	document.getElementById('tree-container'),
	function() {
		var items = document.getElementsByClassName('tree');
		for (var i = 0; i < items.length; i++) {
			//items[i].style.display = 'none';
		}
		document.getElementById('tree-root').style.display = '';
	}
);