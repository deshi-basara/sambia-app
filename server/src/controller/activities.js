// @flow

import Activity from '../model/activity.js';

class ActivitiesController {

  static mount(app): void {
    // [GET] /api/activities
    app.get(
      '/api/activities',
      this.returnAllActivities,
    );

    // [POST] /api/activity
    /* app.post(
      '/api/activity',
      this.saveActivity,
    );*/
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

}

export default ActivitiesController;
