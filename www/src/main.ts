import { bootstrap } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { HTTP_PROVIDERS } from '@angular/http';
import { disableDeprecatedForms, provideForms } from '@angular/forms';

// Override the router `LocationStrategy` to hashbangs
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppComponent, environment } from './app/';
import { APP_ROUTER_PROVIDERS } from './app/app.routes';

if (environment.production) {
  enableProdMode();
}

bootstrap(AppComponent, [
    // angular http
    HTTP_PROVIDERS,

    // angular router
    APP_ROUTER_PROVIDERS,
    { provide: LocationStrategy, useClass: HashLocationStrategy },

    // angular forms (new api)
    disableDeprecatedForms(),
    provideForms()
])
.catch(err => console.error(err));
