'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _activities = require('../controller/activities.js');

var _activities2 = _interopRequireDefault(_activities);

var _subjects = require('../controller/subjects.js');

var _subjects2 = _interopRequireDefault(_subjects);

var _data = require('../controller/data.js');

var _data2 = _interopRequireDefault(_data);

var _interface = require('../controller/interface.js');

var _interface2 = _interopRequireDefault(_interface);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Development configuration
 */
exports.default = {

  name: 'Sambia Server',
  mode: 'development',

  controller: [_activities2.default, _subjects2.default, _data2.default, _interface2.default],

  models: [],

  service: [],

  api: {
    port: 8080,
    host: '192.168.1.158'
  },

  db: {
    host: 'localhost',
    port: 27017,
    name: 'sambia',
    options: ''
  }

};