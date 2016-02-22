
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
var Device = require('./Device');
	
var Product = React.createClass({
	getInitialState: function() {
		return {highlight: false};
	},
	render: function() {
		var devicenodes = this.props.devices.map(function(device) {
			return(
				<Device id={device.id} name={device.name} key={device.id} toggle={this.props.toggle} content={[]} />			
			);
		}.bind(this));
		return (
			<div className="tree product">
				<input type="checkbox" id={this.props.id} onClick={this.props.toggle.bind(null, this, this.props.id)} />
				<label htmlFor={this.props.id} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
					{(this.props.devices.length === 0) ? <div className="tree nocontent"><em>No devices yet</em></div> : null}
				{devicenodes}
			</div>
		);
	}
});
	
module.exports = Product;