// SystemJS configuration file, see links for more information
// https://github.com/systemjs/systemjs
// https://github.com/systemjs/systemjs/blob/master/docs/config-api.md

/***********************************************************************************************
 * User Configuration.
 **********************************************************************************************/
/** Map relative paths to URLs. */
const map: any = {
  '@angular2-material': 'vendor/@angular2-material',
  'chartist': 'vendor/chartist/dist/chartist.js',
  'angular2-chartist': 'vendor/angular2-chartist/dist/angular2-chartist.js'
};

/** User packages configuration. */
const packages: any = {
  'chartist': {
    format: 'cjs'
  },
  'angular2-chartist': {
    format: 'cjs'
  }
};

// material packages
const materialPkgs:string[] = [
  'core',
  'button',
  'card',
  'checkbox',
  'icon',
  'input',
  'list',
  'progress-bar',
  'progress-circle',
  'radio',
  'sidenav',
  'slide-toggle',
  'tabs',
  'toolbar',
];
materialPkgs.forEach((pkg) => {
  packages[`@angular2-material/${pkg}`] = {main: `${pkg}.js`};
});

// Forms not on rc yet
packages['@angular/forms'] = { main: 'index.js', defaultExtension: 'js' };

////////////////////////////////////////////////////////////////////////////////////////////////
/***********************************************************************************************
 * Everything underneath this line is managed by the CLI.
 **********************************************************************************************/
const barrels: string[] = [
  // Angular specific barrels.
  '@angular/core',
  '@angular/common',
  '@angular/compiler',
  '@angular/http',
  '@angular/router',
  '@angular/platform-browser',
  '@angular/platform-browser-dynamic',

  // Thirdparty barrels.
  'rxjs',

  // App specific barrels.
  'app',
  'app/shared',
  'app/test',
  'app/dashboard',
  'app/subjects',
  'app/subjects/subject-detail',
  'app/subjects/subject-add',
  'app/activities',
  'app/activity-add',
  /** @cli-barrel */
];

const cliSystemConfigPackages: any = {};
barrels.forEach((barrelName: string) => {
  cliSystemConfigPackages[barrelName] = { main: 'index' };
});

/** Type declaration for ambient System. */
declare var System: any;

// Apply the CLI SystemJS configuration.
System.config({
  map: {
    '@angular': 'vendor/@angular',
    'rxjs': 'vendor/rxjs',
    'main': 'main.js'
  },
  packages: cliSystemConfigPackages
});

// Apply the user's configuration.
System.config({ map, packages });
