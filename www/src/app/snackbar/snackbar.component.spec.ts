/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import {
  beforeEach, beforeEachProviders,
  describe, xdescribe,
  expect, it, xit,
  async, inject
} from '@angular/core/testing';

import { SnackbarComponent } from './snackbar.component';

describe('Component: Snackbar', () => {
  it('should create an instance', () => {
    let component = new SnackbarComponent();
    expect(component).toBeTruthy();
  });
});
