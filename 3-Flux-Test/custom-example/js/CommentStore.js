var dispatcher = require('./app-dispatcher');
var emitter = require('./emitter');


this.comments = [{id: 2, author: 'Not Sourabh', text: 'Test comment 2'}, {id: 1, author: 'Sourabh', text: 'This is a test comment'}];

dispatcher.register(function(payload) {
	switch(payload.type) {
		case 'add-comment':
			this.add(payload.data);
			break;
		case 'get-all-comments':
			this.getAll();
			break;
	}
}.bind(this));

this.add = function(comment){
	comment.id = this.comments.length + 1;
	this.comments.unshift(comment);
	console.log(comment.id);
	//console.log(this.comments);
	emitter.emit('added-comment', this.comments);
};

this.getAll = function() {
	console.log('Getting all comments');
	emitter.emit('got-all-comments', this.comments)
}
	