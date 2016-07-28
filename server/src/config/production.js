import ActivitiesController from '../controller/activities.js';
import SubjectsController from '../controller/subjects.js';

/**
 * Development configuration
 */
export default {

  name: 'Sambia Server',
  mode: 'producation',

  controller: [
    ActivitiesController,
    SubjectsController,
  ],

  models: [],

  service: [],

  api: {
    port: 8088,
    host: '127.0.0.1',
  },

  db: {
    host: 'localhost',
    port: 27017,
    name: 'sambia',
    options: '',
  },

};
