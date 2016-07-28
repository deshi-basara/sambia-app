import ActivitiesController from '../controller/activities.js';
import SubjectsController from '../controller/subjects.js';

/**
 * Development configuration
 */
export default {

  name: 'Sambia Server',
  mode: 'development',

  controller: [
    ActivitiesController,
    SubjectsController,
  ],

  models: [],

  service: [],

  api: {
    port: 8080,
    host: '141.62.39.48',
  },

  db: {
    host: 'localhost',
    port: 27017,
    name: 'sambia',
    options: '',
  },

};
