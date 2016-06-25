import { Component, OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import { Subject } from './subject';
import { SubjectsService } from './subjects.service';

@Component({
  moduleId: module.id,
  selector: 'app-sections',
  templateUrl: 'subjects.component.html',
  styleUrls: ['subjects.component.css'],
  directives: [
    ROUTER_DIRECTIVES,
  ],
})
export class SubjectsComponent implements OnInit {
  subjects: Subject[];

  constructor(
    private _subjectsService: SubjectsService
  ) {}

  getSubjects() {
    this._subjectsService.fetchSubjects().then((subjects) => {
      this.subjects = subjects;
      console.log(this.subjects);
    });
  }

  ngOnInit() {
    this.getSubjects();
  }

}
