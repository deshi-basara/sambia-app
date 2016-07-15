import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Group } from '../../activities/group';

import { environment } from '../../environment';

@Injectable()
export class ActivityService {
  private activityBaseUrl = '/api/activities';

  constructor(private http: Http) {
  }

  getActivityGroup(): Observable<Group> {
    return this.http
      .get(environment.api + this.activityBaseUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }


  postActivityGroup(_groupModel: Group) {
    console.log(_groupModel);

    let body = _groupModel;
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http
      .post(environment.api + this.activityBaseUrl, body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || { };
  }

  private handleError (error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';

    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}
