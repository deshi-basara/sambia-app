import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { SubjectService } from '../shared/services/subject.service';

import { Subject } from './subject';

@Component({
  moduleId: module.id,
  selector: 'app-subject-detail',
  templateUrl: 'subject-detail.component.html',
  styleUrls: ['subject-detail.component.css'],
  providers: [
    SubjectService
  ]
})
export class SubjectDetailComponent implements OnInit {
  private subscribedRoute: any;
  educationArray = ['Student', 'Farmer'];
  subjectModel: Subject = new Subject();

  constructor(
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
            //@todo print error
            console.log(error);
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
        //@todo implement feedback
        console.log(result);
      },
      error => {
        //@todo print error
        console.log(error);
      }
    )
  }

}
