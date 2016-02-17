(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){


var now = require('./now');


document.getElementById('when').innerHTML = now.day + "/" + now.month + "/" + now.year;

now.isTime();
now.isTime('baaaa');
},{"./now":2}],2:[function(require,module,exports){
var isTime = function(a) {
	console.log("The time is now " + a);
}


exports.hour = new Date().getHours();
exports.minute = new Date().getMinutes();
exports.second = new Date().getSeconds();


exports.day = new Date().getDate();
exports.month = new Date().getMonth() + 1;
exports.year = new Date().getFullYear();

exports.isTime = isTime;
},{}]},{},[1]);
