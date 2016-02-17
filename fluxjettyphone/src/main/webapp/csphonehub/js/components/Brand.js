
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
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
				<input type="checkbox" id={this.props.id} onClick={this.props.toggle.bind(null, this, this.props.id, 'brand', this.props.id)} />
				<label htmlFor={this.props.id} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
					{(this.props.products.length === 0) ? <div className="tree nocontent"><em>No products yet</em></div> : null}
				{productnodes}
			</div>
		);
	}
});
	
module.exports = Brand;