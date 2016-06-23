import { Component, OnInit } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'app-sections',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor() {
    console.log('contrustor');
  }

  ngOnInit() {
  }

}
