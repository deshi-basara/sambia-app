'use strict';

var _http = require('http');

var _http2 = _interopRequireDefault(_http);

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _ejs = require('ejs');

var _ejs2 = _interopRequireDefault(_ejs);

var _bodyParser = require('body-parser');

var _bodyParser2 = _interopRequireDefault(_bodyParser);

var _morgan = require('morgan');

var _morgan2 = _interopRequireDefault(_morgan);

var _winston = require('winston');

var _winston2 = _interopRequireDefault(_winston);

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

var _bluebird = require('bluebird');

var _bluebird2 = _interopRequireDefault(_bluebird);

var _config = require('./helper/config.js');

var _config2 = _interopRequireDefault(_config);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

/**
 * Database
 */
_bluebird2.default.promisifyAll(_mongoose2.default);
_mongoose2.default.connect('mongodb://' + _config2.default.db.host + ':' + _config2.default.db.port + '/' + _config2.default.db.name + _config2.default.db.options);
_mongoose2.default.Promise = _bluebird2.default;
_mongoose2.default.connection.on('error', function (error) {
  throw new Error('MongoDB: ' + error.message);
});

var app = (0, _express2.default)();
app.server = _http2.default.createServer(app);

/**
 * Views
 */
/* app.set('views', `${__dirname}/public`);
app.set('view engine', 'ejs');
app.engine('ejs', ejs);*/
app.use(_express2.default.static(__dirname + '/public'));

/**
 * Middleware
 */
app.use((0, _morgan2.default)('combined'));
app.use(_bodyParser2.default.json({ limit: '25mb' }));

/**
 * Controller
 */
_config2.default.controller.forEach(function (Controller) {
  Controller.mount(app);
});

/**
 * Services
 */
// ...

/**
 * Listen for connections
 */
app.server.listen(_config2.default.api.port, _config2.default.api.host, function () {
  _winston2.default.info('[%s] API startet on %s:%s in \'%s\'-mode', _config2.default.name, _config2.default.api.host, _config2.default.api.port, _config2.default.mode);
});