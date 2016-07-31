import { provideRouter, RouterConfig } from '@angular/router';
import { DashboardComponent } from './dashboard';
import { SubjectsComponent, SubjectDetailComponent, SubjectAddComponent } from './subjects';
import { ActivitiesComponent, ActivityDetailComponent, ActivityAddComponent } from './activities';

export const routes:RouterConfig = [
    // dashboard
    { path: '', redirectTo: 'dash' },
    { path: 'dash', component: DashboardComponent },

    // activities
    { path: 'activities', component: ActivitiesComponent },
    { path: 'activity/add', component: ActivityAddComponent },
    { path: 'activity/:id', component: ActivityDetailComponent },

    // subjects
    { path: 'subjects', component: SubjectsComponent },
    { path: 'subject/add', component: SubjectAddComponent },
    { path: 'subject/:id', component: SubjectDetailComponent },
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
