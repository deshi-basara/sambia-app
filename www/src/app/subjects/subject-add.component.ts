import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/common';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-subject-add',
  templateUrl: 'subject-add.component.html',
  styleUrls: ['subject-add.component.css']
})
export class SubjectAddComponent implements OnInit {
  subjectModel = new Subject(
    null,
    '',
    null,
    '',
    null,
    '',
    '',
    null,
    null,
    null,
    null
  );
  educationArray = ['Student', 'Farmer'];

  constructor() {}

  onSubmit() {
    console.log(this.subjectModel);
  }

  /**
   * Initialize component after Angular initializes the data-bound input
   * properties.
   */
  ngOnInit() {
  }

}
