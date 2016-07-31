'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _production = require('../config/production');

var _production2 = _interopRequireDefault(_production);

var _development = require('../config/development');

var _development2 = _interopRequireDefault(_development);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var config = void 0;
switch (process.env.NODE_ENV) {
  case 'production':
    config = _production2.default;
    break;
  case 'development':
  default:
    config = _development2.default;
    break;
}

exports.default = config;