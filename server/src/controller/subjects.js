// @flow

import Subject from '../model/subject.js';

/**
 * Controller for managing Subjects.
 */
class SubjectsController {

  static mount(app): void {
    // [GET] /api/subjects
    app.get(
      '/api/subjects',
      this.returnAllSubjects,
    );

    // [GET] /api/subjects/:id
    app.get(
      '/api/subjects/:id',
      this.returnSubject,
    );

    // [POST] /api/subjects
    app.post(
      '/api/subjects',
      this.saveSubject,
    );

    // [PUT] /api/subjects
    app.put(
      '/api/subjects',
      this.updateSubject,
    );
  }

  /**
   * Returns all available subjects from our database.
   *
   * @return {JSON}      [All available subject-objects]
   */
  static returnAllSubjects(req: any, res: any): void {
    return Subject.find({})
      .then((subjects) =>
        // send all available subjects
        res
          .status(200)
          .send({ data: subjects })
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
   * Returns a requested suspect identified by the id-parameter.
   *
   * @return {JSON}      [Found subject-object]
   */
  static returnSubject(req: any, res: any): void {
    const id = req.params.id;

    // valid request?
    if (!id) {
      return res
        .status(400)
        .send({
          error: {
            msg: 'Requested subject ID not available.',
          },
        });
    }

    // fetch database entry
    return Subject.findOne({ _id: id })
      .then((subject) =>
        // send database entry
        res
          .status(200)
          .send({ data: subject })
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
   * Saves a new subject into our database.
   *
   * @return {JSON}      [Status of database transaction]
   */
  static saveSubject(req: any, res: any): void {
    //  parse upload
    const subject = req.body;

    console.log('saveSubject');
    console.log(subject);

    // save into db
    return Subject.create(subject)
      .then((entry) =>
        // send new db-entry id as response
        res
          .status(200)
          .send({
            data: entry._id,
          })
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

  static updateSubject(req: any, res: any): void {
    const subject = req.body;

    // update in database
    return Subject.update({ _id: subject._id }, { $set: subject })
      .then(() =>
        res
          .status(200)
          .send({
            data: subject,
          })
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
}

export default SubjectsController;
