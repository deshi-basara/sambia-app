'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Activities Schema.
 *
 * Database schema for one activity. An activity has to be in a group and
 * can have a items describing the activity.
 */
var ActivitiesSchema = new _mongoose2.default.Schema({

  group_activity: {
    type: String,
    required: true
  },
  sub_activity: {
    type: String,
    required: true
  },
  item: {
    type: String,
    required: false
  },
  title: {
    type: String,
    required: true
  },
  translation: {
    type: String,
    required: false,
    default: ''
  },
  imageName: {
    type: String,
    required: false
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
 * @typedef Activities
 */
exports.default = _mongoose2.default.model('Activities', ActivitiesSchema);