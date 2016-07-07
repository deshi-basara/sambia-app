import ActivitiesController from '../controller/activities.js';

/**
 * Development configuration
 */
export default {

  name: 'Sambia Server',
  mode: 'development',

  controller: [
    ActivitiesController,
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
