
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('./app-dispatcher');

var Actions = {
	requestAllBrands: function() {
		dispatcher.dispatch({
			type: 'request-all-brands',
			data: {}
		});
	},
	addBrand: function(brand) {
		dispatcher.dispatch({
			type: 'add-brand',
			data: {name: brand}
		});
	},
	addProduct: function(brand, product) {
		dispatcher.dispatch({
			type: 'add-product',
			data: {brand: brand, product: product}
		});
	},
	addDevice: function(brand, product, device) {
		dispatcher.dispatch({
			type: 'add-device',
			data: {brand: brand, product: product, device: device}
		});
	}
}

module.exports = Actions;