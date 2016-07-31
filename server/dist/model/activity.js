'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Activity Schema.
 *
 * Database schema for on activity. An activity has to be in a group and
 * can have a items describing the activity.
 */
var ActivitySchema = new _mongoose2.default.Schema({

  name: {
    type: String,
    required: true
  },
  translation: {
    type: String,
    required: false,
    default: ''
  },
  image: {
    type: String,
    required: false
  },
  items: {
    type: [_mongoose2.default.Schema.Types.ObjectId],
    ref: 'Item',
    required: false,
    default: []
  },
  enabled: {
    type: Boolean,
    required: true,
    default: false
  },
  createdAt: {
    type: Date,
    default: Date.now
  }

});

/**
 * @typedef Activity
 */
exports.default = _mongoose2.default.model('Activity', ActivitySchema);