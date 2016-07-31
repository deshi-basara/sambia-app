'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _bluebird = require('bluebird');

var _bluebird2 = _interopRequireDefault(_bluebird);

var _underscore = require('underscore');

var _underscore2 = _interopRequireDefault(_underscore);

var _group = require('../model/group.js');

var _group2 = _interopRequireDefault(_group);

var _activity = require('../model/activity.js');

var _activity2 = _interopRequireDefault(_activity);

var _item = require('../model/item.js');

var _item2 = _interopRequireDefault(_item);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

/**
 * Controller for managing activities.
 */

var ActivitiesController = function () {
  function ActivitiesController() {
    _classCallCheck(this, ActivitiesController);
  }

  _createClass(ActivitiesController, null, [{
    key: 'mount',
    value: function mount(app) {
      // [GET] /api/activities
      app.get('/api/activities', this.returnAllActivityGroups);

      // [GET] /api/activities/app
      app.get('/api/activities/app', this.returnAllActivityGroupsApp);

      // [GET] /api/activities/:id
      app.get('/api/activities/:id', this.returnActivityGroup);

      // [POST] /api/activities
      app.post('/api/activities', this.saveActivityGroup);

      // [PUT] /api/activitties
      app.put('/api/activities', this.updateActivityGroup);
    }

    /**
     * Returns all available activities from our database.
     *
     * @return {JSON}      [All available activity objects]
     */

  }, {
    key: 'returnAllActivityGroups',
    value: function returnAllActivityGroups(req, res) {
      return _group2.default.find({}).populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item'
        }
      }).then(function (groups) {
        // send all available activities
        res.status(200).send({ data: groups });
      }).catch(function (error) {
        return(
          // send error
          res.status(500).send({
            error: error
          })
        );
      });
    }

    /**
     * Returns all available activities from our database and parses them
     * into app-friendly format.
     *
     * @return {JSON}      [All available activity objects]
     */

  }, {
    key: 'returnAllActivityGroupsApp',
    value: function returnAllActivityGroupsApp(req, res) {
      return _group2.default.find({}).populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item'
        }
      }).then(function (groups) {
        // parse into app-friendly json
        var appResult = [];
        for (var i = 0; i < groups.length; i++) {
          var currentGroup = groups[i];

          if (currentGroup.enabled) {
            var jsonParent = {
              group_activity: currentGroup.name,
              sub_activity: '',
              item: '',
              title: '',
              _id: '',
              imageName: 'graphic_seed.png'
            };

            // fetch all activities of group
            for (var j = 0; j < currentGroup.activities.length; j++) {
              var currentActivity = currentGroup.activities[j];

              // is activity enabled?
              if (!currentActivity.enabled) {
                break;
              }

              // copy parent & manipulate
              var jsonChild = JSON.parse(JSON.stringify(jsonParent));
              jsonChild.sub_activity = currentActivity.name;
              jsonChild.title = currentActivity.name;
              jsonChild._id = currentActivity._id;

              // fetch all items of activity
              for (var k = 0; k < currentActivity.items.length; k++) {
                var currentItem = currentActivity.items[k];

                // is item enabled?
                if (!currentItem.enabled) {
                  break;
                }

                // overwrite parent & manipulate
                jsonChild = JSON.parse(JSON.stringify(jsonChild));
                jsonChild.item = currentItem.name;
                jsonChild.title = currentItem.name;
                jsonChild._id = currentItem._id;

                appResult.push(jsonChild);
              }

              // if the activity has no items, push activity
              if (currentActivity.items.length === 0) {
                appResult.push(jsonChild);
              }
            }
          }
        }

        return appResult;
      }).then(function (result) {
        // send all available activities
        res.status(200).send({ activitys: result });
      }).catch(function (error) {
        return(
          // send error
          res.status(500).send({
            error: error
          })
        );
      });
    }

    /**
     * Returns a certain activity identified by id from our database.
     *
     * @return {JSON}      [An activity objects]
     */

  }, {
    key: 'returnActivityGroup',
    value: function returnActivityGroup(req, res) {
      var id = req.params.id;

      // valid request?
      if (!id) {
        return res.status(400).send({
          error: {
            msg: 'Requested group ID not available.'
          }
        });
      }

      return _group2.default.find({ _id: id }).populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item'
        }
      }).then(function (group) {
        return(
          // send available activity
          res.status(200).send({ data: group })
        );
      }).catch(function (error) {
        return(
          // send error
          res.status(500).send({
            error: error
          })
        );
      });
    }

    /**
     * Saves a new activity group into our database.
     *
     * @return {JSON}      [Status of database transaction]
     */

  }, {
    key: 'saveActivityGroup',
    value: function saveActivityGroup(req, res) {
      //  parse upload
      var group = req.body;

      // loop through activities
      var activityIds = [];
      return _bluebird2.default.each(group.activities, function (activity) {
        var currentActivity = activity;

        // if we have sub-items, create database entries
        return _item2.default.create(currentActivity.items).then(function (itemResults) {
          // extract itemIds
          var itemIds = [];

          // does the activity have items?
          if (itemResults) {
            itemResults.forEach(function (item) {
              itemIds.push(item._id);
            });
          }

          return itemIds;
        }).then(function (itemIds) {
          // add items to activity
          currentActivity.items = itemIds;

          // create activity
          return _activity2.default.create(currentActivity);
        }).then(function (activityResult) {
          // extract activityId
          activityIds.push(activityResult._id);
        });
      }).then(function () {
        // add activities to group
        group.activities = activityIds;

        // create group
        return _group2.default.create(group);
      }).then(function () {
        return res.send({ data: 'Insert successfull.' });
      }).catch(function (error) {
        return(
          // send error
          res.status(500).send({
            error: error
          })
        );
      });
    }

    /**
     * Update an activity group identified by the handed id.
     *
     * @return {JSON}      [Status of database transaction]
     */

  }, {
    key: 'updateActivityGroup',
    value: function updateActivityGroup(req, res) {
      //  parse upload
      var group = req.body;

      var groupEntry = null;
      var newActivityIds = [];
      return _group2.default.findOne({ _id: group.id }).populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item'
        }
      }).then(function (result) {
        groupEntry = result;

        // loop through all database activities and decide if we have to update or
        // delete it
        return _bluebird2.default.each(result.activities, function (activity) {

          for (var i = 0; i < group.activities.length; i++) {
            var currentActivity = group.activities[i];

            if (currentActivity.id === activity._id.toString()) {
              // remove from array
              group.activities.splice(i, 1);

              // activity already exists, do we need to update items?
              var newItemIds = [];
              for (var j = 0; j < currentActivity.items.length; j++) {
                var currentItem = currentActivity.items[j];

                // if (activity.item)
              }
              // TODO: Also update items

              // activity already exists, try to update it
              return activity.update({ $set: currentActivity }).then(function (result) {
                newActivityIds.push(activity._id);
              });
            }
          }

          // activity doesn't exist anymore, try to delete it
          return activity.delete();
        }).then(function () {
          // all activities left have to be new activities, create them
          return _bluebird2.default.each(group.activities, function (newActivity) {
            // create new entry in database
            return _activity2.default.create(newActivity).then(function (activityResult) {
              newActivityIds.push(activityResult._id);
            });
          });
        });
      }).then(function () {
        // re-add activities to group
        group.activities = newActivityIds;

        return groupEntry.update({ $set: group });
      }).then(function () {
        res.send({ data: 'Update successfull.' });
      }).catch(function (error) {
        console.log(error);

        // send error
        res.status(500).send({
          error: error
        });
      });

      /* return Group.update({ _id: group.id }, { $set: group })
        .then(() => {
          res.send({ data: 'Update successfull.' });
        })
        .catch((error) =>
          // send error
          res
            .status(500)
            .send({
              error,
            })
        );*/
    }
  }]);

  return ActivitiesController;
}();

exports.default = ActivitiesController;