var React = require('react');
var ReactDOM = require('react-dom');
var PhoneRoot = require('./components/PhoneRoot');

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