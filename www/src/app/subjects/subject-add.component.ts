import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/common';

import { SnackbarComponent } from '../snackbar';
import { SubjectService } from '../shared/services/subject.service';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-subject-add',
  templateUrl: 'subject-add.component.html',
  styleUrls: ['subject-add.component.css'],
  providers: [SubjectService],
  directives: [SnackbarComponent]
})
export class SubjectAddComponent implements OnInit {
  @ViewChild(SnackbarComponent) snackbar: SnackbarComponent;

  subjectModel = new Subject();
  educationArray = ['Student', 'Farmer'];

  constructor(
    private router: Router,
    private subjectService: SubjectService
  ) {}

  /**
   * Executed when the subject-form is submitted.
   */
  onSubmit() {
    this.subjectService.postSubject(this.subjectModel)
      .subscribe(
        result => {
          this.snackbar.showSnackbar('Subject was successfully created.', false);

          setTimeout(() => {
            this.router.navigate(['/subjects']);
          }, 2000);
        },
        error => {
          this.snackbar.showSnackbar(error, true);
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
