'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _subject = require('../model/subject.js');

var _subject2 = _interopRequireDefault(_subject);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Controller for managing Subjects.
 */

var SubjectsController = function () {
  function SubjectsController() {
    _classCallCheck(this, SubjectsController);
  }

  _createClass(SubjectsController, null, [{
    key: 'mount',
    value: function mount(app) {
      // [GET] /api/subjects
      app.get('/api/subjects', this.returnAllSubjects);

      // [GET] /api/subjects/:id
      app.get('/api/subjects/:id', this.returnSubject);

      // [POST] /api/subjects
      app.post('/api/subjects', this.saveSubject);

      // [PUT] /api/subjects
      app.put('/api/subjects', this.updateSubject);
    }

    /**
     * Returns all available subjects from our database.
     *
     * @return {JSON}      [All available subject-objects]
     */

  }, {
    key: 'returnAllSubjects',
    value: function returnAllSubjects(req, res) {
      return _subject2.default.find({}).then(function (subjects) {
        return(
          // send all available subjects
          res.status(200).send({ data: subjects })
        );
      }).error(function (error) {
        return(
          // send error
          res.status(500).send({
            error: {
              msg: error
            }
          })
        );
      });
    }

    /**
     * Returns a requested suspect identified by the id-parameter.
     *
     * @return {JSON}      [Found subject-object]
     */

  }, {
    key: 'returnSubject',
    value: function returnSubject(req, res) {
      var id = req.params.id;

      // valid request?
      if (!id) {
        return res.status(400).send({
          error: {
            msg: 'Requested subject ID not available.'
          }
        });
      }

      // fetch database entry
      return _subject2.default.findOne({ _id: id }).then(function (subject) {
        return(
          // send database entry
          res.status(200).send({ data: subject })
        );
      }).error(function (error) {
        return(
          // send error
          res.status(500).send({
            error: {
              msg: error
            }
          })
        );
      });
    }

    /**
     * Saves a new subject into our database.
     *
     * @return {JSON}      [Status of database transaction]
     */

  }, {
    key: 'saveSubject',
    value: function saveSubject(req, res) {
      //  parse upload
      var subject = req.body;

      console.log('saveSubject');
      console.log(subject);

      // save into db
      return _subject2.default.create(subject).then(function (entry) {
        return(
          // send new db-entry id as response
          res.status(200).send({
            data: entry._id
          })
        );
      }).error(function (error) {
        return(
          // send error
          res.status(500).send({
            error: {
              msg: error
            }
          })
        );
      });
    }
  }, {
    key: 'updateSubject',
    value: function updateSubject(req, res) {
      var subject = req.body;

      // update in database
      return _subject2.default.update({ _id: subject._id }, { $set: subject }).then(function () {
        return res.status(200).send({
          data: subject
        });
      }).error(function (error) {
        return(
          // send error
          res.status(500).send({
            error: {
              msg: error
            }
          })
        );
      });
    }
  }]);

  return SubjectsController;
}();

exports.default = SubjectsController;