
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
				<input type="checkbox" id={this.props.name} onClick={this.props.toggle.bind(null, this, this.props.name)} />
				<label htmlFor={this.props.name} className={this.state.highlight ? "highlight" : ""}>{this.props.name}</label>
					{(this.props.content.length === 0) ? <div className="tree nocontent"><em>No content yet</em></div> : null}
			</div>
		);
	}
});
	
module.exports = Device;