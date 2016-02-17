
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
var Device = require('./Device');
	
var Product = React.createClass({
	getInitialState: function() {
		return {highlight: false, gotItems: false};
	},
	render: function() {
		var devicenodes = this.props.devices.map(function(device) {
			var newid = this.props.brand + '$' + this.props.product + '$' + device.id;
			return(
				<Device id={newid} name={device.name} key={newid} toggle={this.props.toggle} content={[]} />			
			);
		}.bind(this));
		return (
			<div className="tree product">
				<input type="checkbox" id={this.props.id} onClick={this.props.toggle.bind(null, this, this.props.id, 'product', this.props.brand, this.props.product)} />
				<label htmlFor={this.props.id} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
					{(this.props.devices.length === 0) ? <div className="tree nocontent"><em>No devices yet</em></div> : null}
				{devicenodes}
			</div>
		);
	}
});
	
module.exports = Product;