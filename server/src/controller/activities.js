// @flow

import Activity from '../model/activity.js';
import Group from '../model/group.js';

class ActivitiesController {

  static mount(app): void {
    // [GET] /api/activities
    app.get(
      '/api/activities',
      this.returnAllActivities,
    );

    // [POST] /api/activity
    app.post(
      '/api/activities',
      this.saveActivityGroup,
    );
  }

  /**
   * Returns all available activities from our database.
   *
   * @return {JSON}      [All available activity objects]
   */
  static returnAllActivities(req: any, res: any): void {
    return Activity.find({})
      .then((activities) =>
        // send all available activities
        res.send({ activities })
      )
      .error((error) =>
        // send error
        res.send(500, error)
      );
  }

  /**
   * Saves a new activity group into our database.
   *
   * @return {JSON}      [Status of database transaction]
   */
  static saveActivityGroup(req: any, res: any): void {
    console.log(req.body);

    const data = req.body;

    return Group.create(data)
      .then(() => {
        res.send({ ok: 200 });
      })
      .error((error) =>
        // send error
        res.send(500, error)
      );
  }
}

export default ActivitiesController;
