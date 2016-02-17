
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
var Brand = require('./Brand');
var Actions = require('../Actions');
var BrandStore = require('../stores/BrandStore');
	
var PhoneRoot = React.createClass({
	getInitialState: function() {
		return {brands: [], highlight: false, gotItems: false};
	},
	componentDidMount: function() {
		emitter.on("got-all-brands", function(){
				this.setState({brands: BrandStore.getAllBrands(), gotItems: true});
		}.bind(this));
		emitter.on("got-all-products", function(ctx){
				this.setState({brands: BrandStore.getAllBrands()});
				ctx.setState({gotItems: true});
		}.bind(this));
		emitter.on("got-all-devices", function(ctx){
				this.setState({brands: BrandStore.getAllBrands()});
				ctx.setState({gotItems: true});
		}.bind(this));
		emitter.on("added-brand", function(){
				this.setState({brands: BrandStore.getAllBrands()});				
		}.bind(this));
		emitter.on("added-product", function(brandName){
				this.setState({brands: BrandStore.getAllBrands()});				
		}.bind(this));
		emitter.on("added-device", function(productName){
				this.setState({brands: BrandStore.getAllBrands()});				
		}.bind(this));
	},
	addBrand: function() {
		var brand = prompt('Enter brand name');
		if (!brand)
			return;
		Actions.addBrand(brand);
	},
	addProduct: function(brandId) {
		var product = prompt('Enter product name');
		if (!product)
			return;
		Actions.addProduct(brandId, product);
	},
	addDevice: function(brand, product) {
		var device = prompt('Enter device name');
		if (!device)
			return;
		Actions.addDevice(brand, product, device);
	},
	addNew: function() {
		if (document.getElementsByClassName('highlight').length === 0)
			return;
		var highlighted = document.getElementsByClassName('highlight')[0];
		var parentClasses = highlighted.parentNode.classList;
		if (parentClasses.contains('root')) {
			console.log('Adding brand');
			this.addBrand();
		}
		else if (parentClasses.contains('brand')) {
			console.log('Adding product');
			this.addProduct(highlighted.htmlFor);
		}
		else if (parentClasses.contains('product')) {
			console.log('Adding device');
			var htmlfor = highlighted.htmlFor.split('$');
			this.addDevice(htmlfor[0], htmlfor[1]);
		}
	},
	checkToggle: function(ctx, type, brandId, productId) {
		var previous = document.getElementsByClassName('highlight');
		if (previous.length > 0)
			previous[0].className = '';
		ctx.setState({highlight: !ctx.state.highlight});
		
		if (ctx.state.gotItems)
			return;
		switch(type) {
			case 'root':
				Actions.getBrandNames();
				console.log("ROOOOOOT");
				break;
			case 'brand':
				Actions.getProductNames(brandId, ctx);
				console.log("BRAAAAND");
				break;
			case 'product':
				Actions.getDeviceNames(brandId, productId, ctx);
				console.log("PRODUUUUCT");
				break;
		}
	},
	render: function() {
		var brandnodes = this.state.brands.map(function(brand) {
			return(
				<Brand toggle={this.checkToggle} id={brand.id} name={brand.name} key={brand.id} products={brand.products} />			
			);
		}.bind(this));
		return (
			<div id='phone-tree'>
				<div className="add-button" onClick={this.addNew}>+</div>
				<div className="tree root" id="tree-root" key="0">
					<input type="checkbox" id="root" key="a" onClick={this.checkToggle.bind(null, this, 'root')} />
					<label htmlFor="root" key="b" className={this.state.highlight ? "highlight" : ""}>Phones</label>
					{(this.state.highlight)
					?(	(this.state.brands.length === 0)
						? <div className="tree nocontent"><em>No brands yet</em></div>
						: brandnodes)
					: null}
				</div>
			</div>
		);
	}
});
	
module.exports = PhoneRoot;