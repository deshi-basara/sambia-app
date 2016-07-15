/* tslint:disable:no-unused-variable */

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';
import { SubjectService } from './subject.service';

describe('Subject Service', () => {
  beforeEachProviders(() => [SubjectService]);

  it('should ...',
      inject([SubjectService], (service: SubjectService) => {
    expect(service).toBeTruthy();
  }));
});
