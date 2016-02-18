var React = require('react');
var Product = require('./Product');
	
var Brand = React.createClass({
	getInitialState: function() {
		return {highlight: false, gotItems: false};
	},
	render: function() {
		var productnodes = this.props.products.map(function(product) {
			var brandprod = this.props.id + '$' + product.id;
			return(
				<Product id={brandprod} name={product.name} key={brandprod} brand={this.props.id} product={product.id} devices={product.devices} toggle={this.props.toggle}/>			
			);
		}.bind(this));
		return (
			<div className="tree brand" tag={this.props.name}>
				<input type="checkbox" id={this.props.id} onClick={this.props.toggle.bind(null, this, 'brand', this.props.id)} />
				<label htmlFor={this.props.id} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
				{(this.state.highlight)
				? (	(this.props.products.length === 0)
					? <div className="tree nocontent"><em>No products yet</em></div>
					: productnodes)
				: null}
			</div>
		);
	}
});
	
module.exports = Brand;