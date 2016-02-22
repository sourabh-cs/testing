var dispatcher = require('../core/app-dispatcher');

var Actions = {
	getBrandNames: function() {
		dispatcher.dispatch({
			type: 'get-brand-names',
			data: {}
		});
	},
	getProductNames: function(brandId, ctx) {
		dispatcher.dispatch({
			type: 'get-product-names',
			data: {brandId: brandId, ctx: ctx}
		});
	},
	getDeviceNames: function(brandId, productId, ctx) {
		dispatcher.dispatch({
			type: 'get-device-names',
			data: {brandId: brandId, productId: productId, ctx: ctx}
		});
	},
	addBrand: function() {
		var brand = prompt('Enter brand name');
		if (!brand)
			return;
		dispatcher.dispatch({
			type: 'add-brand',
			data: {brandName: brand}
		});
	},
	addProduct: function(brandId) {
		var productName = prompt('Enter product name');
		if (!productName)
			return;
		dispatcher.dispatch({
			type: 'add-product',
			data: {brandId: brandId, productName: productName}
		});
	},
	addDevice: function(brandId, productId) {
		var deviceName = prompt('Enter device name');
		if (!deviceName)
			return;
		dispatcher.dispatch({
			type: 'add-device',
			data: {brandId: brandId, productId: productId, deviceName: deviceName}
		});
	}
}

module.exports = Actions;