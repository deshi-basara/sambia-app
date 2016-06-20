import { Component } from '@angular/core';
import {MD_SIDENAV_DIRECTIVES} from '@angular2-material/sidenav';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  directives: [
    MD_SIDENAV_DIRECTIVES,
  ],
})
export class AppComponent {
  title = 'app works!';
}
