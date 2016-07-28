// @flow
import Promise from 'bluebird';
import _ from 'underscore';

import Group from '../model/group.js';
import Activity from '../model/activity.js';
import Item from '../model/item.js';

/**
 * Controller for managing activities.
 */
class ActivitiesController {

  static mount(app): void {
    // [GET] /api/activities
    app.get(
      '/api/activities',
      this.returnAllActivityGroups,
    );

    // [GET] /api/activities/:id
    app.get(
      '/api/activities/:id',
      this.returnActivityGroup,
    );

    // [POST] /api/activities
    app.post(
      '/api/activities',
      this.saveActivityGroup,
    );

    // [PUT] /api/activitties
    app.put(
      '/api/activities',
      this.updateActivityGroup,
    );
  }

  /**
   * Returns all available activities from our database.
   *
   * @return {JSON}      [All available activity objects]
   */
  static returnAllActivityGroups(req: any, res: any): void {
    return Group.find({})
      .populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item',
        },
      })
      .then((groups) => {
        // send all available activities
        res
          .status(200)
          .send({ data: groups })
      })
      .error((error) =>
        // send error
        res
          .status(500)
          .send({
            error: {
              msg: error,
            },
          })
      );
  }

  /**
   * Returns a certain activity identified by id from our database.
   *
   * @return {JSON}      [An activity objects]
   */
  static returnActivityGroup(req: any, res: any): void {
    const id = req.params.id;

    // valid request?
    if (!id) {
      return res
        .status(400)
        .send({
          error: {
            msg: 'Requested group ID not available.',
          },
        });
    }

    return Group.find({ _id: id })
      .populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item',
        },
      })
      .then((group) => {
        console.log(group);

        // send available activity
        res
          .status(200)
          .send({ data: group })
      })
      .error((error) =>
        // send error
        res
          .status(500)
          .send({
            error: {
              msg: error,
            },
          })
      );
  }

  /**
   * Saves a new activity group into our database.
   *
   * @return {JSON}      [Status of database transaction]
   */
  static saveActivityGroup(req: any, res: any): void {
    //  parse upload
    const group = req.body;

    // loop through activities
    const activityIds = [];
    return Promise.each(group.activities, (activity) => {
      const currentActivity = activity;

      // we have sub-items, create database entry
      return Item.create(currentActivity.items)
        .then((itemResults) => {
          // extract itemIds
          const itemIds = [];
          itemResults.forEach((item) => {
            itemIds.push(item._id);
          });

          return itemIds;
        })
        .then((itemIds) => {
          // add items to activity
          currentActivity.items = itemIds;

          // create activity
          return Activity.create(currentActivity);
        })
        .then((activityResult) => {
          // extract activityId
          activityIds.push(activityResult._id);
        });
    })
    .then(() => {
      // add activities to group
      group.activities = activityIds;

      // create group
      return Group.create(group);
    })
    .then(() =>
      res.send({ data: 'Insert successfull.' })
    )
    .error((error) =>
      // send error
      res
        .status(500)
        .send({
          error: {
            msg: error,
          },
        })
    );
  }

  /**
   * Update an activity group identified by the handed id.
   *
   * @return {JSON}      [Status of database transaction]
   */
  static updateActivityGroup(req: any, res: any): void {
    //  parse upload
    const group = req.body;
    group.activities = JSON.stringify(group.activities);

    return Group.update({ _id: group.id }, { $set: group })
      .then(() => {
        res.send({ data: 'Update successfull.' });
      })
      .error((error) =>
        // send error
        res
          .status(500)
          .send({
            error: {
              msg: error,
            },
          })
      );
  }
}

export default ActivitiesController;
