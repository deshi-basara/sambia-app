/* tslint:disable:no-unused-variable */

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';
import { SubjectsService } from './subjects.service';

describe('Subjects Service', () => {
  beforeEachProviders(() => [SubjectsService]);

  it('should ...',
      inject([SubjectsService], (service: SubjectsService) => {
    expect(service).toBeTruthy();
  }));
});
