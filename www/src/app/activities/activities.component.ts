import { Component, OnInit } from '@angular/core';
import { Router, ROUTER_DIRECTIVES } from '@angular/router';

import { ActivityService } from '../shared/services/activity.service';

import { Group } from './group';
import { Activity } from './activity';
import { Item } from './item';

@Component({
  moduleId: module.id,
  selector: 'app-activities',
  templateUrl: 'activities.component.html',
  styleUrls: ['activities.component.css'],
  directives: [
    ROUTER_DIRECTIVES,
  ],
  providers: [
    ActivityService
  ]
})
export class ActivitiesComponent implements OnInit {
  groupArray: Group[] = [];

  constructor(
    private router: Router,
    private activityService: ActivityService
  ) {}

  ngOnInit() {
    this.activityService.getAllActivityGroups().subscribe(
      result => {
        this.groupArray = result;
        console.log(this.groupArray);
      },
      error => {
        // TODO: Error feedback
        console.log(error);
      }
    );
  }

  /**
   * Is called when a group in the table is clicked.
   * Routes to the group-detail view.
   *
   * @param  {string} _id [MongoDB-subject-id]
   */
  openGroup(_id: string) {
    this.router.navigate(['/activity', _id]);
  }

}
