if (!String.prototype.format) {
	String.prototype.format = function() {
		var args = arguments;
		return this.replace(/{(\d+)}/g, function(match, number) {
			return typeof args[number] != 'undefined' ? args[number] : match;
		});
	};
}


function Phone(brand, name, imageurl, specs) {
	this.brand = brand;
	this.name = name;
	this.imageurl = imageurl;
	this.specs = specs;
	this.getName = function() {
		return this.brand + " " + this.name;
	}
}

var parseJson = function(json) {
	return $.parseJSON(json)
}


$(document).ready(function() {
	var brandstart = '<div class="tree brand">\n\t{0}';
	var brandend = '</div>';
	var productstart = '<div class="tree product">\n\t{0}';
	var productend = '</div>';
	var devicestart = '<div class="tree device" id={1}>\n\t{0}';
	var deviceend = '</div>';



	var row = '<tr><td>{0}</td><td>{1}</td></tr>';
	//$.getJSON( "phones.json", function( data ) {});
	var htmlgen = "";
	var data = parseJson(json);
	var phones = [];
	var count = 0;

	$('table').hide();

	
	$.each(data.brands, function(i, brand) {
		console.log(brand.name);
		htmlgen += brandstart.format(brand.name);
		$.each(brand.products, function(i, product) {
			console.log("   {0}".format(product.name));
			htmlgen += productstart.format(product.name);
			$.each(product.devices, function(i, device) {
				console.log("      {0}".format(device.name));
				phones.push(new Phone(brand.name, device.name, device.imageurl, device.specs));
				htmlgen += devicestart.format(device.name, count);
				count += 1;
				htmlgen += deviceend;
			});
			htmlgen += productend;
		});
		htmlgen += brandend;
	});
	
	
	console.log(htmlgen);
	$('#list-phones').html(htmlgen);
	$('.tree').hide();
	$('.brand').show();

	
	$('.tree').click(function(e) {
		$(this).children('div').toggle(500);
		e.stopPropagation();
	});
	
	
	$('.device').click(function() {
		var index = $(this).attr('id');
		console.log(phones[index].specs);
		$('table').html('');
		$('table').show();
		$('#device-name').html(phones[index].getName());
		$('#device-image').attr('src', phones[index].imageurl);
		var c = 1;
		
		$.each(phones[index].specs, function(key, val) {
			console.log(key + " : " + val);
			var rowelement = $(row.format(key, val)).appendTo('table');
			rowelement.hide();
			rowelement.show(200 * c++);

		});
		
	});
	
});