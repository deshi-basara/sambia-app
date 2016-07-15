import { Component, OnInit } from '@angular/core';
import { Router, ROUTER_DIRECTIVES } from '@angular/router';

import { SubjectService } from '../shared/services/subject.service';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-sections',
  templateUrl: 'subjects.component.html',
  styleUrls: ['subjects.component.css'],
  directives: [
    ROUTER_DIRECTIVES,
  ],
  providers: [
    SubjectService
  ]
})
export class SubjectsComponent implements OnInit {
  subjects: Subject[];

  constructor(
    private router: Router,
    private subjectService: SubjectService) {}

  ngOnInit() {
    // get all subjects
    this.subjectService.getAllSubjects().subscribe(
      result => {
        this.subjects = result;
      },
      error => {
        console.log(error);
      }
    );
  }

  openSubject(_id: string) {
    this.router.navigate(['/subject', _id]);
  }



}
