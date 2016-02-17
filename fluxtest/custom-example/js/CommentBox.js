
var React = require('react');
var ReactDOM = require('react-dom');
var dispatcher = require('./app-dispatcher');
require('./CommentStore.js');
var emitter = require('./emitter');
var CommentForm = require('./CommentForm');
var CommentList = require('./CommentList');
	
var CommentBox = React.createClass({
	handleCommentSubmit: function(comment) {
		var comments = this.state.data;
		comment.id = Date.now();
		var newComments = comments.concat([comment]);
		dispatcher.dispatch({
			type: 'add-comment',
			data: comment
		});
	},
	getInitialState: function() {
		return {data: []};
	},
	componentDidMount: function() {
		emitter.on("got-all-comments", function(data){
				this.setState({data: data});				
		}.bind(this));
		emitter.on("added-comment", function(data){
				this.setState({data: data});				
		}.bind(this));
		dispatcher.dispatch({
			type: 'get-all-comments',
			data: {}
		});
	},
	render: function() {
		return (
		  <div className="commentBox">
			<h2>Comments</h2>
			<CommentList data={this.state.data} />
			<CommentForm onCommentSubmit={this.handleCommentSubmit} />
		  </div>
		);
	}
});
	
module.exports = CommentBox;