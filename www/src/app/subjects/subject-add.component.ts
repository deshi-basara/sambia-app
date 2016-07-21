import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/common';

import { SubjectService } from '../shared/services/subject.service';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-subject-add',
  templateUrl: 'subject-add.component.html',
  styleUrls: ['subject-add.component.css'],
  providers: [SubjectService]
})
export class SubjectAddComponent implements OnInit {
  subjectModel = new Subject();
  educationArray = ['Student', 'Farmer'];

  constructor(
    private router: Router,
    private subjectService: SubjectService
  ) {}

  onSubmit() {
    this.subjectService.postSubject(this.subjectModel)
      .subscribe(
        result => {
          this.router.navigate(['/subjects']);
        },
        error => {
          console.log(error);
        }
      )
  }

  /**
   * Initialize component after Angular initializes the data-bound input
   * properties.
   */
  ngOnInit() {
  }

}
