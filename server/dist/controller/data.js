'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Controller for logged data.
 */

var DataController = function () {
  function DataController() {
    _classCallCheck(this, DataController);
  }

  _createClass(DataController, null, [{
    key: 'mount',
    value: function mount(app) {
      // [POST] /api/data
      app.post('/api/data', this.saveNewData);
    }
  }, {
    key: 'saveNewData',
    value: function saveNewData(req, res) {
      console.log(req.body);
    }
  }]);

  return DataController;
}();

exports.default = DataController;