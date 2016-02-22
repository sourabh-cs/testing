var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');

var brands = [{id: 0, name: "Apple", products: []}, {id: 1, name: "Samsung", products: []}];

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

var BrandStore = {

	addBrand: function(brand){
		brand.id = brands.length;
		brand.products = [];
		brands.push(brand);
		console.log(brand.id);
		emitter.emit('added-brand');
	},
	addProduct: function(data){
		var brandName = data.brand;
		var productName = data.product;
		var brand = searchBrand(brandName);
		var product = {};
		product.id = brand.products.length;
		product.name = productName;
		product.devices = [];
		brand.products.push(product);
		emitter.emit('added-product', brandName);
	},
	addDevice: function(data){
		var brandName = data.brand;
		var productName = data.product;
		var deviceName = data.device;
		var product = searchProduct(brandName, productName);
		var device = {};
		device.id = product.devices.length;
		device.name = deviceName;
		device.stats = [];
		product.devices.push(device);
		emitter.emit('added-device', brandName);
	},

	getAll: function() {
		console.log('Getting all comments');
		emitter.emit('got-all-brands')
	},
	
	getAllBrands: function() {
		return brands;
	}, 
	
	getAllBrands: function() {
		return brands;
	}, 
	
	dispIndex: dispatcher.register(function(payload) {
		switch(payload.type) {
			case 'request-all-brands':
				BrandStore.getAll();
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