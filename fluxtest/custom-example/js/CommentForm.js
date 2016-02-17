
var React = require('react');
var ReactDOM = require('react-dom');

var CommentForm = React.createClass({
	getInitialState: function() {
		return {author: '', text: ''};
	},
	handleAuthorChange: function(e) {
		this.setState({author: e.target.value});
	},
	handleTextChange: function(e) {
		this.setState({text: e.target.value});
	},
	handleSubmit: function(e) {
		e.preventDefault();
		var author = this.state.author.trim();
		var text = this.state.text.trim();
		if (!text || !author) {
			return;
		}
		this.props.onCommentSubmit({author: author, text: text});
		this.setState({author: '', text: ''});
	},
	render: function() {
		return (
			<form className="commentForm" onSubmit={this.handleSubmit}>
				<input
					type="text" 
					placeholder="Your name"
					value={this.state.author}
					onChange={this.handleAuthorChange}
				/>
				<textarea
					placeholder="Say something..."
					rows="4"
					value={this.state.text}
					onChange={this.handleTextChange}>
				</textarea>
				<input type="submit" value="Post" />
			</form>
		);
	}
});
	
module.exports = CommentForm;