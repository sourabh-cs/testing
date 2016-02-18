var dispatcher = require('../app-dispatcher');

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
	addBrand: function(brand) {
		dispatcher.dispatch({
			type: 'add-brand',
			data: {brandName: brand}
		});
	},
	addProduct: function(brandId, productName) {
		dispatcher.dispatch({
			type: 'add-product',
			data: {brandId: brandId, productName: productName}
		});
	},
	addDevice: function(brandId, productId, deviceName) {
		dispatcher.dispatch({
			type: 'add-device',
			data: {brandId: brandId, productId: productId, deviceName: deviceName}
		});
	}
}

module.exports = Actions;