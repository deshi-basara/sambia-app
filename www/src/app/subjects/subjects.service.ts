import { Injectable } from '@angular/core';

import { Subject } from './subject';
import { SUBJECTS } from './mock-subjects';


@Injectable()
export class SubjectsService {

  fetchSubjects() {
    return Promise.resolve(SUBJECTS);
  }

}
