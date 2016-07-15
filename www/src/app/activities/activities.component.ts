import { Component, OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

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
    private activityService: ActivityService
  ) {}

  ngOnInit() {
    this.activityService.getAllActivityGroups().subscribe(
      result => {
        this.groupArray = result;
      },
      error => {
        console.log(error);
      }
    );
  }

}
