import { Component, OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-activities',
  templateUrl: 'activities.component.html',
  styleUrls: ['activities.component.css'],
  directives: [
    ROUTER_DIRECTIVES,
  ],
})
export class ActivitiesComponent implements OnInit {

  constructor() {}

  ngOnInit() {
  }

}
