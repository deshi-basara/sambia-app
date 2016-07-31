'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _path = require('path');

var _path2 = _interopRequireDefault(_path);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Controller for managing the web-interface.
 */

var InterfaceController = function () {
  function InterfaceController() {
    _classCallCheck(this, InterfaceController);
  }

  _createClass(InterfaceController, null, [{
    key: 'mount',
    value: function mount(app) {
      // [GET] /
      app.get('/', this.renderInterface);
    }

    /**
     * Renders the index.html
     */

  }, {
    key: 'renderInterface',
    value: function renderInterface(req, res) {
      var indexHtml = _path2.default.join(__dirname, '../public/index.html');
      console.log(indexHtml);
      return res.sendFile(indexHtml);
    }
  }]);

  return InterfaceController;
}();

exports.default = InterfaceController;