import { provideRouter, RouterConfig } from '@angular/router';
import { DashboardComponent } from "./dashboard";

export const routes:RouterConfig = [
    { path: '', component: DashboardComponent },
    { path: 'dash', component: DashboardComponent },
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
