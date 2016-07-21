import { Component, OnInit } from '@angular/core';
import { ChartistComponent, ChartType } from 'angular2-chartist';

@Component({
  moduleId: module.id,
  selector: 'app-sections',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css'],
  directives: [ChartistComponent]
})
export class DashboardComponent implements OnInit {
  userCount: Number = 0;
  apiCount: Number = 0;
  dataCount: Number = 0;
  requestCount: Number = 0;

  chartData: any;
  chartType: String = 'Line';
  chartOptions: Object = { low: 0, high: 200, showArea: true, height: '320px' };

  constructor() {

  }

  ngOnInit() {

    setTimeout(() => {
      this.userCount = 22;
      this.apiCount = 42;
      this.dataCount = 50;
      this.requestCount = 10;
    }, 1000);

    this.chartData = {
      "labels": [
        18,
        19,
        20,
        21,
        22,
        23,
        24
      ],
      "series": [
        [
          10,
          29,
          56,
          70,
          91
        ]
      ]
    };
  }

}
