/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';

import { SubjectDetailComponent } from './subject-detail.component';

describe('Component: SubjectDetail', () => {
  it('should create an instance', () => {
    let component = new SubjectDetailComponent();
    expect(component).toBeTruthy();
  });
});
