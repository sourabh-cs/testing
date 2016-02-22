var React = require('react');
var emitter = require('../core/emitter');
var Brand = require('./Brand');
var Actions = require('../actions/Actions');
var BrandStore = require('../stores/BrandStore');
	
var PhoneRoot = React.createClass({
	getInitialState: function() {
		return {brands: [], highlight: false, gotItems: false, loading: false};
	},
	getAllFromStore: function() {
		this.setState({loading: false});
		return BrandStore.getAllBrands();
	},
	componentDidMount: function() {
		emitter.on("error", function(){
				this.setState({loading: false});
		}.bind(this));
		emitter.on("got-all-brands", function(){
				this.setState({brands: this.getAllFromStore(), gotItems: true});
		}.bind(this));
		emitter.on("got-all-products", function(ctx){
				this.setState({brands: this.getAllFromStore()});
				ctx.setState({gotItems: true});
		}.bind(this));
		emitter.on("got-all-devices", function(ctx){
				this.setState({brands: this.getAllFromStore()});
				ctx.setState({gotItems: true});
		}.bind(this));
		emitter.on("added-brand", function(){
				this.setState({brands: this.getAllFromStore()});				
		}.bind(this));
		emitter.on("added-product", function(brandName){
				this.setState({brands: this.getAllFromStore()});				
		}.bind(this));
		emitter.on("added-device", function(productName){
				this.setState({brands: this.getAllFromStore()});				
		}.bind(this));
	},
	addBrand: function() {
	},
	addProduct: function(brandId) {
	},
	addDevice: function(brand, product) {
		Actions.addDevice(brand, product);
	},
	getHighlighted: function() {
		var obj = {'type': '', 'brandId': '', 'productId': '', 'deviceId': ''};
		if (document.getElementsByClassName('highlight').length === 0)
			return;
		var highlighted = document.getElementsByClassName('highlight')[0];
		var parentClasses = highlighted.parentNode.classList;
		if (parentClasses.contains('root')) {
			obj.type = 'root';
		}
		else if (parentClasses.contains('brand')) {
			obj.type = 'brand';
			obj.brandId = highlighted.htmlFor;
		}
		else if (parentClasses.contains('product')) {
			obj.type = 'product';
			var htmlfor = highlighted.htmlFor.split('$');
			obj.brandId = htmlfor[0];
			obj.productId = htmlfor[1];
		}
		return obj;

	},
	addNew: function() {
		var highlighted = this.getHighlighted();
		switch(highlighted.type) {
			case 'root':
				console.log('Adding brand');
				Actions.addBrand();
				break;
			case 'brand':
				console.log('Adding product');
				Actions.addProduct(highlighted.brandId);
				break;
			case 'product':
				console.log('Adding device');
				this.addDevice(highlighted.brandId, highlighted.productId);
				break;
			default: 
				console.log('Invalid');
		}
	},
	deleteEntity: function() {

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
				this.setState({loading: true});
				Actions.getBrandNames();
				console.log("ROOOOOOT");
				break;
			case 'brand':
				this.setState({loading: true});
				Actions.getProductNames(brandId, ctx);
				console.log("BRAAAAND");
				break;
			case 'product':
				this.setState({loading: true});
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
				<div className="fobs">
					<div className={this.state.loading ? "fob add-button loading" : "fob add-button"} onClick={this.addNew}>
						<span>+</span>
					</div>
					<div className={this.state.loading ? "fob del-button loading" : "fob del-button"} onClick={this.deleteEntity}>
						<span>-</span>
					</div>
				</div>
				<div className="tree root" id="tree-root" key="0">
					<input type="checkbox" id="root" key="a" onClick={this.checkToggle.bind(null, this, 'root')} />
					<label htmlFor="root" key="b" className={this.state.highlight ? "highlight" : ""}>Phones</label>
					{(this.state.highlight)
					?	( (this.state.brands.length === 0)
						? <div className="tree nocontent"><em>No brands yet</em></div>
						: brandnodes)
					: null}
				</div>
			</div>
		);
	}
});
	
module.exports = PhoneRoot;