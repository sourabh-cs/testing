
var React = require('react');
var ReactDOM = require('react-dom');
	
var Comment = React.createClass({
	rawMarkup: function() {
	var rawMarkup = marked(this.props.children.toString(), {sanitize: true});
	return { __html: rawMarkup };
	},
	render: function() {
		return(
			<div className="comment">
				<h4 className="commentAuthor">
					{this.props.author}
				</h4>
				<span dangerouslySetInnerHTML={this.rawMarkup()} />
			</div>
		);
	}
});
	
module.exports = Comment;