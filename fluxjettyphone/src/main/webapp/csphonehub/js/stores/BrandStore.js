var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');

var brands = [];

var SourAjax = {
	postJSON: function (url, payload, callback) {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', url);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function() {callback(xhr.responseText)};
		xhr.send(JSON.stringify(payload));
	},
	getJSON: function (url, callback) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url);
		xhr.onload = function() {callback(xhr)};
		xhr.send();
	}
};

const BASE_URL = 'http://localhost:8080/phones/';

var BrandStore = {

	addBrand: function(data){
		var brandName = data.brandName;
		var rawPayload = {'name': brandName};
		SourAjax.postJSON(BASE_URL + 'new', rawPayload, function(xhr) {
			var response = JSON.parse(xhr);
			brands.push({'id': response.id, 'name': response.name, 'products': []});
			emitter.emit('added-brand');
		});
	},
	addProduct: function(data){
		var brandId = data.brandId;
		var productName = data.productName;
		var rawPayload = {'name': productName};
		SourAjax.postJSON(BASE_URL + brandId + '/new', rawPayload, function(xhr) {
			var response = JSON.parse(xhr);
			brands[brandId].products.push({'id': brands[brandId].products.length, 'name': productName, 'devices': []});
			emitter.emit('added-product');
		});
	},
	addDevice: function(data){
		var brandId = data.brandId;
		var productId = data.productId;
		var deviceName = data.deviceName;
		var rawPayload = {'name': deviceName};
		SourAjax.postJSON(BASE_URL + brandId + '/' + productId + '/new', rawPayload, function(xhr) {
			var response = JSON.parse(xhr);
			brands[brandId].products[productId].devices.push({'id': brands[brandId].products[productId].devices.length, 'name': deviceName, 'specs': []});
			emitter.emit('added-device');
		});
	},

	getBrandNames: function() {
		newBrands = [];
		SourAjax.getJSON(BASE_URL, function(xhr){
			response = JSON.parse(xhr.responseText);
			for (var i in response) {
				newBrands.push({'id': i, 'name': response[i], 'products': []});
			}
			brands = newBrands;
			console.log('Getting all brands');
			emitter.emit('got-all-brands')
		});
	},

	getProductNames: function(data) {
		var id = data.brandId;
		SourAjax.getJSON(BASE_URL + id, function(xhr){
			response = JSON.parse(xhr.responseText);
			var products = [];
			for (var i in response) {
				products.push({'id': i, 'name': response[i], 'devices': []});
			}
			brands[id].products = products;
			console.log('Getting all products');
			emitter.emit('got-all-products', data.ctx);
		});
	},

	getDeviceNames: function(data) {
		var brandId = data.brandId;
		var productId = data.productId;
		
		SourAjax.getJSON(BASE_URL + brandId + '/' + productId, function(xhr){
			response = JSON.parse(xhr.responseText);
			var devices = [];
			for (var i in response) {
				devices.push({'id': i, 'name': response[i], 'specs': []});
			}
			brands[brandId].products[productId].devices = devices;
			console.log('Getting all devices');
			emitter.emit('got-all-devices', data.ctx);
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