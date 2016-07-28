import beep from 'beepbeep';
import del from 'del';

import gulp from 'gulp';
import babel from 'gulp-babel';
import flow from 'gulp-flowtype';
import eslint from 'gulp-eslint';
import nodemon from 'gulp-nodemon';
import Cache from 'gulp-file-cache';

import notifier from 'node-notifier';
import runSequence from 'run-sequence';

/**
 * Global conf
 */
const config = {
  beep: true, // make beep-sound on errors
  cache: new Cache(),
  path: {
    src: 'src',
    dist: 'dist',
    tmp: '.tmp',
    test: 'test',
  },
};

/**
 * Run flow type-checking and print errors.
 */
gulp.task('types', () =>
  gulp.src(`${config.path.src}/**/*.js`)
    .pipe(flow({
      all: false,
      weak: false,
      killFlow: false,
      beep: config.beep,
      abort: false,
    }))
);

/**
 * Run javascript linter and print errors.
 */
gulp.task('linter', () =>
  gulp.src(`${config.path.src}/**/*.js`)
    .pipe(eslint())
    .pipe(eslint.formatEach())
    .pipe(eslint.result((results) => {
      // beep, if errors were found
      if (results.errorCount > 0 && config.beep) {
        beep();
      }
    }))
);

/**
 * Compile ES2015 code with babel and cache compiled files.
 */
gulp.task('compile:tmp', () =>
  gulp.src(`${config.path.src}/**/*.js`)
    .pipe(config.cache.filter()) // remember files
    .pipe(babel()) // compile
    .pipe(config.cache.cache()) // cache compiled files
    .pipe(gulp.dest(config.path.tmp))
);

/**
 * Compile ES2015 code with babel
 */
gulp.task('compile:dist', () =>
  gulp.src(`${config.path.src}/**/*.js`)
    .pipe(babel())
    .pipe(gulp.dest(config.path.dist))
);

/**
 * Runs all tasks synchronously.
 */
gulp.task('runSync', done => {
  // execute synchronously on changes
  runSequence(
    'linter',
    'types',
    'compile:tmp',
    done
  );
});

/**
 * Restarts the node-app after changes were applied.
 */
gulp.task('serve', ['compile'], () => {
  nodemon({
    script: `${config.path.tmp}/app.js`,
    watch: config.path.src,
    tasks: ['runSync'],
  })
  .on('restart', () => {
    notifier.notify({ title: 'Serve', message: 'Server restarted' });
  });
});

/**
* Runs all tasks synchronously.
*/
gulp.task('build', done =>
  // execute synchronously
  runSequence(
    'linter',
//    'types',
    'clean',
    'compile:dist',
    done
  )
);

// empty dist folder, before creating a new build
gulp.task('clean', () => del([config.path.dist], { dot: true }));
