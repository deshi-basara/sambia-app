// @flow


/**
 * Controller for logged data.
 */
class DataController {

  static mount(app): void {
    // [POST] /api/data
    app.post(
      '/api/data',
      this.saveNewData,
    );
  }

  static saveNewData(req: any, res: any): void {
    console.log(req.body);
  }
}

export default DataController;
