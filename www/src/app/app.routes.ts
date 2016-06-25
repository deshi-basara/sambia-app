import { provideRouter, RouterConfig } from '@angular/router';
import { DashboardComponent } from './dashboard';
import { SubjectsComponent, SubjectDetailComponent, SubjectAddComponent } from './subjects';

export const routes:RouterConfig = [
    { path: '', component: DashboardComponent },
    { path: 'dash', component: DashboardComponent },
    { path: 'subjects', component: SubjectsComponent },
    { path: 'subjects/add', component: SubjectAddComponent },
    { path: 'subjects/:id', component: SubjectDetailComponent },
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
