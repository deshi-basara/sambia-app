import gulp from 'gulp';
import babel from 'gulp-babel';
import eslint from 'gulp-esling';
import Cache from 'gulp-file-cache';

/**
 * Gulp configuration
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
 * Compile ES2015 code with babel and cache compiled files.
 */
gulp.task('compile', () => {
  return gulp.src(`${config.path.src}/**/*.js`)
    .pipe(config.cache.filter()) // remember files
    .pipe(babel()) // compile
    .pipe(config.cache.cache()) // cache compiled files
    .pipe(gulp.dest(config.path.tmp));
});
