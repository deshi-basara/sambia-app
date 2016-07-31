'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Item Schema.
 *
 * Database schema for on activity-item.
 * An item has to belong to an activity and.
 */
var ItemSchema = new _mongoose2.default.Schema({

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
  enabled: {
    type: Boolean,
    required: false,
    default: true
  },
  createdAt: {
    type: Date,
    default: Date.now
  }

});

/**
 * @typedef Activity
 */
exports.default = _mongoose2.default.model('Item', ItemSchema);