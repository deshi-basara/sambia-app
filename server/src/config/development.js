import ActivitiesController from '../controller/activities.js';
import SubjectsController from '../controller/subjects.js';
import InterfaceController from '../controller/interface.js';

/**
 * Development configuration
 */
export default {

  name: 'Sambia Server',
  mode: 'development',

  controller: [
    ActivitiesController,
    SubjectsController,
    InterfaceController,
  ],

  models: [],

  service: [],

  api: {
    port: 8080,
    host: 'localhost',
  },

  db: {
    host: 'localhost',
    port: 27017,
    name: 'sambia',
    options: '',
  },

};
