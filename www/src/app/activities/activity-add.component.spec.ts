/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';

import { ActivityAddComponent } from './activity-add.component';

describe('Component: ActivityAdd', () => {
  it('should create an instance', () => {
    let component = new ActivityAddComponent();
    expect(component).toBeTruthy();
  });
});
