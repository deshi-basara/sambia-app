// SystemJS configuration file, see links for more information
// https://github.com/systemjs/systemjs
// https://github.com/systemjs/systemjs/blob/master/docs/config-api.md
/***********************************************************************************************
 * User Configuration.
 **********************************************************************************************/
/** Map relative paths to URLs. */
var map = {
    '@angular2-material': 'vendor/@angular2-material',
    'chartist': 'vendor/chartist/dist/chartist.js',
    'angular2-chartist': 'vendor/angular2-chartist/dist/angular2-chartist.js'
};
/** User packages configuration. */
var packages = {
    'chartist': {
        format: 'cjs'
    },
    'angular2-chartist': {
        format: 'cjs'
    }
};
// material packages
var materialPkgs = [
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
materialPkgs.forEach(function (pkg) {
    packages[("@angular2-material/" + pkg)] = { main: pkg + ".js" };
});
// Forms not on rc yet
packages['@angular/forms'] = { main: 'index.js', defaultExtension: 'js' };
////////////////////////////////////////////////////////////////////////////////////////////////
/***********************************************************************************************
 * Everything underneath this line is managed by the CLI.
 **********************************************************************************************/
var barrels = [
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
    'app/snackbar',
];
var cliSystemConfigPackages = {};
barrels.forEach(function (barrelName) {
    cliSystemConfigPackages[barrelName] = { main: 'index' };
});
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
System.config({ map: map, packages: packages });
//# sourceMappingURL=system-config.js.map