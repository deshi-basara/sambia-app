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

    // [GET] /api/activities/app
    app.get(
      '/api/activities/app',
      this.returnAllActivityGroupsApp,
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
          .send({ data: groups });
      })
      .catch((error) =>
        // send error
        res
          .status(500)
          .send({
            error,
          })
      );
  }

  /**
   * Returns all available activities from our database and parses them
   * into app-friendly format.
   *
   * @return {JSON}      [All available activity objects]
   */
  static returnAllActivityGroupsApp(req: any, res: any): void {
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
        // parse into app-friendly json
        const appResult = [];
        for (let i = 0; i < groups.length; i++) {
          const currentGroup = groups[i];

          if (currentGroup.enabled) {
            const jsonParent = {
              group_activity: currentGroup.name,
              sub_activity: '',
              item: '',
              title: '',
              _id: '',
              imageName: 'graphic_seed.png',
            };

            // fetch all activities of group
            for (let j = 0; j < currentGroup.activities.length; j++) {
              const currentActivity = currentGroup.activities[j];

              // is activity enabled?
              if (!currentActivity.enabled) {
                break;
              }

              // copy parent & manipulate
              let jsonChild = JSON.parse(JSON.stringify(jsonParent));
              jsonChild.sub_activity = currentActivity.name;
              jsonChild.title = currentActivity.name;
              jsonChild._id = currentActivity._id;

              // fetch all items of activity
              for (let k = 0; k < currentActivity.items.length; k++) {
                const currentItem = currentActivity.items[k];

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
      })
      .then((result) => {
        // send all available activities
        res
          .status(200)
          .send({ activitys: result });
      })
      .catch((error) =>
        // send error
        res
          .status(500)
          .send({
            error,
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
      .then((group) =>
        // send available activity
        res
          .status(200)
          .send({ data: group })
      )
      .catch((error) =>
        // send error
        res
          .status(500)
          .send({
            error,
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

      // if we have sub-items, create database entries
      return Item.create(currentActivity.items)
        .then((itemResults) => {
          // extract itemIds
          const itemIds = [];

          // does the activity have items?
          if (itemResults) {
            itemResults.forEach((item) => {
              itemIds.push(item._id);
            });
          }

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
    .catch((error) =>
      // send error
      res
        .status(500)
        .send({
          error,
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

    let groupEntry = null;
    let newActivityIds = [];
    return Group.findOne({ _id: group.id })
      .populate({
        path: 'activities',
        model: 'Activity',
        populate: {
          path: 'items',
          model: 'Item',
        },
      })
      .then((result) => {
        groupEntry = result;

        // loop through all database activities and decide if we have to update or
        // delete it
        return Promise.each(result.activities, (activity) => {

          for (var i = 0; i < group.activities.length; i++) {
            let currentActivity = group.activities[i];

            if (currentActivity.id === activity._id.toString()) {
              // remove from array
              group.activities.splice(i, 1);

              // activity already exists, do we need to update items?
              let newItemIds = [];
              for (var j = 0; j < currentActivity.items.length; j++) {
                let currentItem = currentActivity.items[j];

                // if (activity.item)
              }
              // TODO: Also update items

              // activity already exists, try to update it
              return activity.update({ $set: currentActivity })
                .then((result) => {
                  newActivityIds.push(activity._id);
                });
            }
          }

          // activity doesn't exist anymore, try to delete it
          return activity.delete();
        })
        .then(() => {
          // all activities left have to be new activities, create them
          return Promise.each(group.activities, (newActivity) => {
            // create new entry in database
            return Activity.create(newActivity)
              .then((activityResult) => {
                newActivityIds.push(activityResult._id);
              });
          })
        });
      })
      .then(() => {
        // re-add activities to group
        group.activities = newActivityIds;

        return groupEntry.update({ $set: group });
      })
      .then(() => {
        res.send({ data: 'Update successfull.' });
      })
      .catch((error) => {
        console.log(error);

        // send error
        res
          .status(500)
          .send({
            error,
          })
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
}

export default ActivitiesController;
