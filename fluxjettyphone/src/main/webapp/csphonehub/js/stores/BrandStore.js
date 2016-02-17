var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
var ajax = require('ajax-promise');

var brands = [];

//var brands{"Apple": {products: {}}, "Samsung": {products: {}}};

var searchBrand = function (brandName){
    for (var i=0; i < brands.length; i++) {
        if (brands[i].name === brandName) {
            return brands[i];
        }
    }
	return null;
}

var searchProduct = function (brandName, productName){
	console.log('Searching: ' + brandName + ':' + productName);
	var brand = searchBrand(brandName);
	if (!brand)
		return brand;
	var products = brand.products;
    for (var i=0; i < products.length; i++) {
        if (products[i].name === productName) {
			console.log('Product: ' + products[i]);
            return products[i];
        }
    }
	return null;
}

var BASE_URL = 'http://localhost:8080/fluxjettyphone/phones/';

var BrandStore = {

	addBrand: function(data){
		var brandId = data.brandName;
		ajax.post(BASE_URL + 'new', brandId)
		.then(function (response) {
			console.log(response);
			brands.push({'id': brands.length, 'name': brandId, 'products': []});
			console.log(brands);
			emitter.emit('added-brand');
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},
	addProduct: function(data){
		var brandId = data.brandId;
		var productName = data.productName;
		ajax.post(BASE_URL + brandId + '/new', productName)
		.then(function (response) {
			console.log(response);
			brands[brandId].products.push({'id': brands[brandId].products.length, 'name': productName, 'devices': []});
			console.log(brands);
			emitter.emit('added-product');
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},
	addDevice: function(data){
		var brandId = data.brandId;
		var productId = data.productId;
		var deviceName = data.deviceName;
		ajax.post(BASE_URL + brandId + '/' + productId + '/new', deviceName)
		.then(function (response) {
			console.log(response);
			brands[brandId].products[productId].devices.push({'id': brands[brandId].products[productId].devices.length, 'name': deviceName, 'specs': []});
			console.log(brands);
			emitter.emit('added-device');
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},

	getBrandNames: function() {
		brands = [];
		ajax.get(BASE_URL)
		.then(function(response){
			for (var i in response) {
				brands.push({'id': i, 'name': response[i], 'products': []});
			}
			console.log('Getting all brands');
			emitter.emit('got-all-brands')
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},

	getProductNames: function(data) {
		var id = data.brandId;
		ajax.get(BASE_URL + id)
		.then(function(response){
			var products = [];
			for (var i in response) {
				console.log(response[i]);
				products.push({'id': i, 'name': response[i], 'devices': []});
			}
			brands[id].products = products;
			console.log('Getting all products');
			emitter.emit('got-all-products', data.ctx);
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},

	getDeviceNames: function(data) {
		var brandId = data.brandId;
		var productId = data.productId;
		ajax.get('http://localhost:8080/fluxjettyphone/phones/' + brandId + '/' + productId)
		.then(function(response){
			var devices = [];
			for (var i in response) {
				devices.push({'id': i, 'name': response[i], 'specs': []});
			}
			brands[brandId].products[productId].devices = devices;
			console.log('Getting all devices');
			emitter.emit('got-all-devices', data.ctx);
		})
		.catch(function (err) {
			console.log('errors in response', err);
		});
	},
	
	getAllBrands: function() {
		return brands;
	}, 
	
	getAllBrands: function() {
		return brands;
	}, 
	
	dispIndex: dispatcher.register(function(payload) {
		switch(payload.type) {
			case 'get-brand-names':
				BrandStore.getBrandNames();
				break;
			case 'get-product-names':
				BrandStore.getProductNames(payload.data);
				break;
			case 'get-device-names':
				BrandStore.getDeviceNames(payload.data);
				break;
			case 'add-brand':
				BrandStore.addBrand(payload.data);
				break;
			case 'add-product':
				BrandStore.addProduct(payload.data);
				break;
			case 'add-device':
				BrandStore.addDevice(payload.data);
				break;
		}
	})
};

module.exports = BrandStore;