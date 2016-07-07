import { Component, OnInit } from '@angular/core';

import { Group } from './group';

@Component({
  moduleId: module.id,
  selector: 'app-activity-add',
  templateUrl: 'activity-add.component.html',
  styleUrls: ['activity-add.component.css']
})
export class ActivityAddComponent implements OnInit {

  /**
   * Attributes
   */
  showModal = true;
  groupModel = new Group(
    null,
    '',
    '',
    '',
    [],
    false
  );
  educationArray = ['Student', 'Farmer'];

  constructor() {}

  onSubmit() {
    console.log(this.groupModel);
  }

  toggleModal() {
    this.showModal = this.showModal ? false : true;
  }

  /**
   * Initialize component after Angular initializes the data-bound input
   * properties.
   */
  ngOnInit() {
  }

}
