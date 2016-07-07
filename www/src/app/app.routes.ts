import { provideRouter, RouterConfig } from '@angular/router';
import { DashboardComponent } from './dashboard';
import { SubjectsComponent, SubjectDetailComponent, SubjectAddComponent } from './subjects';
import { ActivitiesComponent, ActivityAddComponent } from './activities';

export const routes:RouterConfig = [
    { path: 'dash', component: DashboardComponent },

    // activities
    { path: 'activities', component: ActivitiesComponent },
    { path: 'activities/add', component: ActivityAddComponent },

    // subjects
    { path: 'subjects', component: SubjectsComponent },
    { path: 'subjects/add', component: SubjectAddComponent },
    { path: 'subjects/:id', component: SubjectDetailComponent },
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
