import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import './rxjs-operators';

import { SubjectsService } from './subjects/subjects.service';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives: [
    ROUTER_DIRECTIVES,
  ],
  providers: [
    SubjectsService,
  ]
})
export class AppComponent {
  title = 'app works!';
}
