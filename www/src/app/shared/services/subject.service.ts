import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Subject } from '../../subjects/subject';

import { environment } from '../../environment';

@Injectable()
export class SubjectService {
  private subjectBaseUrl = '/api/subjects';
  // private cachedSubjects: Subject[] = null;
  // private cachingObservable: Observable<Subject[]> = null;

  constructor(private http: Http) {}

  /**
   * Fetches all available subjects from the server database.
   * If subjects were cached previously, reuse them.
   *
   * @return {Observable<Subject>} [Observable from type Subject-Array]
   */
  getAllSubjects(): Observable<Subject[]> {
    return this.http.get(environment.api + this.subjectBaseUrl)
      .map(this.extractData)
      .catch(this.handleError);

    //@todo implement caching mechanism
    // were subjects already cached?
    /*if (this.cachedSubjects) {
      // cached, return cached entry
      return Observable.of(this.cachedSubjects);
    }
    // is the caching-mechanism already in progress?
    else if (this.cachingObservable) {
      // in progress, return running observable
      return this.cachingObservable;
    }
    else {
      console.log('caching');
      // start caching
      this.cachingObservable = this.http
        .get(environment.api + this.subjectBaseUrl)
        .map(this.extractData)
        .do(result => {
          console.log('do');
          this.cachedSubjects = result;
          this.cachingObservable = null;
        })
        .share();
      return this.cachingObservable;
    }*/
  }

  /**
   * Fetches a subject from the database identified by the handed
   * mongodb-id.
   *
   * @param  {string}              _id [MongoDB-entry-id]
   * @return {Observable<Subject>}     [Found subject]
   */
  getSubject(_id: string): Observable<Subject> {
    return this.http.get(environment.api + this.subjectBaseUrl + '/' + _id)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Pushes a new subject to the server, where it will be saved in
   * the database.
   *
   * @param  {Subject}             _subjectModel [Subject-object with form-data]
   * @return {Observable<Subject>}               [Observable from type Subject]
   */
  postSubject(_subjectModel: Subject): Observable<Subject> {
    let body = _subjectModel;
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http
      .post(environment.api + this.subjectBaseUrl, body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Pushes an updated subject to the server, where the database entry
   * will be modified.
   *
   * @param  {Subject}             _subjectModel [Subject-object with updated form-data]
   * @return {Observable<Subject>}               [Observable from type Subject]
   */
  putSubject(_subjectModel: Subject): Observable<Subject> {
    let body = _subjectModel;
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http
      .put(environment.api + this.subjectBaseUrl, body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Helper method for extracting response-data.
   *
   * @param  {Response} res [Response from Angular-http]
   * @return {any}          [Parsed body]
   */
  private extractData(res: Response): any {
    let body = res.json();
    return body.data || { };
  }

  /**
   * Helper method for extracting and printing errors during requests.
   *
   * @param  {any}        error [Response from Angular-http]
   * @return {Observable}       [Thrown error with extracted message]
   */
  private handleError(error: any) {
    let body = error.json();
    let errMsg = (body.error.msg) ? body.error.msg :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';

    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }

}
