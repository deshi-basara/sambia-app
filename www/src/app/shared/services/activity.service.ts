import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Group } from '../../activities/group';
import { Activity } from '../../activities/activity';
import { Item } from '../../activities/item';

import { environment } from '../../environment';

@Injectable()
export class ActivityService {
  private activityBaseUrl = '/api/activities';

  constructor(
    private http: Http
  ) {}

  /**
   * Fetches all available activity-groups from server.
   *
   * @return {Observable<Group[]>} [Observable of type group-array]
   */
  getAllActivityGroups(): Observable<Group[]> {
    return this.http
      .get(environment.api + this.activityBaseUrl)
      .map(this.extractGroups)
      .catch(this.handleError);
  }

  getActivityGroup(): Observable<Group> {
    return this.http
      .get(environment.api + this.activityBaseUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  /**
   * Pushes a new activity group to the server, where it will be saved
   * into the database.
   *
   * @param  {Group}  _groupModel [description]
   * @return {[type]}             [description]
   */
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

  /**
   * Helper-method for parsing the JSON-response from server into their corresponding
   * typescript object-structure: Group-Object <- Activity-Object <- Item-Object.
   *
   * @param  {Response}  res [Angular-http response]
   * @return {Group[]}       [Array of all extracted Groups]
   */
  private extractGroups(res: Response): Group[] {
    let body = res.json();
    let groupArray: Group[] = [];

    for (let i = 0; i < body.data.length; i++) {
      let currentGroupData = body.data[i];

      // parse group activity and extract activities
      let activityArray: Activity[] = [];
      currentGroupData.activities = JSON.parse(currentGroupData.activities);
      for (let i = 0; i < currentGroupData.activities.length; i++) {
        let currentActivityData = currentGroupData.activities[i];

        // parse activity items and extract items
        let itemArray: Item[] = [];
        for (let i = 0; i < currentActivityData.items.length; i++) {
          let currentItemData = currentActivityData.items[0];

          let currentItem = new Item(
            '',
            currentItemData.name,
            '',
            '',
            false
          );
          itemArray.push(currentItem);
        }

        // build activity object
        let currentActivity = new Activity(
          '',
          currentActivityData.name,
          '',
          '',
          itemArray,
          currentActivityData.enabled
        );
        activityArray.push(currentActivity);
      }

      // build group object
      let currentGroup = new Group(
        currentGroupData._id,
        currentGroupData.name,
        currentGroupData.translation,
        currentGroupData.image,
        activityArray,
        currentGroupData.enabled
      );
      groupArray.push(currentGroup);
    }

    return groupArray;
  }

  /**
   * Helper method for extracting and printing errors during requests.
   *
   * @param  {any}        error [Response from Angular-http]
   * @return {Observable}       [Thrown error with extracted message]
   */
  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';

    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}
