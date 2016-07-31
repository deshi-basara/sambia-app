'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Group Schema.
 *
 * Database schema for a group of activities.
 */
var GroupSchema = new _mongoose2.default.Schema({

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
    required: false,
    default: ''
  },
  activities: {
    type: [_mongoose2.default.Schema.Types.ObjectId],
    ref: 'Activity',
    required: true
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
 * @typedef Group
 */
exports.default = _mongoose2.default.model('Group', GroupSchema);