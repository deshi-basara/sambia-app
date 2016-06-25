/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';

import { SubjectAddComponent } from './subject-add.component';

describe('Component: SubjectAdd', () => {
  it('should create an instance', () => {
    let component = new SubjectAddComponent();
    expect(component).toBeTruthy();
  });
});
