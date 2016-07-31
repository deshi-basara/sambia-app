'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Subject Schema.
 *
 * Database schema for a subject.
 */
var SubjectSchema = new _mongoose2.default.Schema({

  name: {
    type: String,
    required: true
  },
  image: {
    type: String,
    required: false
  },
  age: {
    type: Number,
    required: true
  },
  gender: {
    type: String,
    required: true
  },
  education: {
    type: String,
    required: true
  },
  tribe: {
    type: String,
    required: true
  },
  household: {
    type: Number,
    required: true
  },
  size: {
    type: Number,
    required: true
  },
  weight: {
    type: Number,
    required: true
  },
  createdAt: {
    type: Date,
    default: Date.now
  }

});

/**
 * @typedef Subject
 */
exports.default = _mongoose2.default.model('Subject', SubjectSchema);