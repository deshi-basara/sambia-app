import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SnackbarComponent } from '../snackbar';

import { SubjectService } from '../shared/services/subject.service';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-subject-detail',
  templateUrl: 'subject-detail.component.html',
  styleUrls: ['subject-detail.component.css'],
  providers: [
    SubjectService
  ],
  directives: [SnackbarComponent]
})
export class SubjectDetailComponent implements OnInit {
  @ViewChild(SnackbarComponent) snackbar: SnackbarComponent;

  private subscribedRoute: any;
  educationArray = ['Student', 'Farmer'];
  subjectModel: Subject = new Subject();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private subjectService: SubjectService
  ) {}

  /**
   * Is called when the angular-component is initied.
   */
  ngOnInit() {
    // retrieve route-parameters by subscribing to the activeRoute-service
    this.subscribedRoute = this.route.params.subscribe(
      params => {
        let id = params['id'];
        this.subjectService.getSubject(id).subscribe(
          result => {
            this.subjectModel = result;
          },
          error => {
            this.snackbar.showSnackbar(error, true);
          }
        );
      }
    );
  }

  /**
   * Is called when the angular-component is destroyed.
   */
  ngOnDestroy() {
    // unsubscribe from activeRoute-service
    this.subscribedRoute.unsubscribe();
  }

  /**
   * Is called when the update-form is submitted.
   */
  onSubmit() {
    // make an update-request
    this.subjectService.putSubject(this.subjectModel).subscribe(
      result => {
        this.snackbar.showSnackbar('Subject was successfully updated.', false);

        setTimeout(() => {
          this.router.navigate(['/subjects']);
        }, 2000);
      },
      error => {
        this.snackbar.showSnackbar(error, true);
      }
    )
  }

}
