
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('../app-dispatcher');
var emitter = require('../emitter');
	
var Device = React.createClass({
	getInitialState: function() {
		return {highlight: false};
	},
	render: function() {
		return (
			<div className="tree device">
				<input type="checkbox" id={this.props.id} onClick={this.props.toggle.bind(null, this)} />
				<label htmlFor={this.props.id} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
				{(this.state.highlight)
				? (	(this.props.content.length === 0) 
					?  <div className="tree nocontent"><em>No content yet</em></div> 
					: null) 
				: null}
			</div>
		);
	}
});
	
module.exports = Device;