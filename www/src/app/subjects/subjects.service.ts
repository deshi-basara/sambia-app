import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Subject } from './subject';
import { SUBJECTS } from './mock-subjects';


@Injectable()
export class SubjectsService {

  constructor(private http: Http) { }

  fetchSubjects(): Promise<Subject[]> {
    return Promise.resolve(SUBJECTS);
  }

  submitSubject(subject: Subject) {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });

  }

}
