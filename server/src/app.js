import http from 'http';
import express from 'express';
import morgan from 'morgan';
import winston from 'winston';
import mongoose from 'mongoose';
import Promise from 'bluebird';

import config from './helper/config.js';

/**
 * Database
 */
Promise.promisifyAll(mongoose);
mongoose.connect(`mongodb://${config.db.host}:${config.db.port}/${config.db.name}${config.db.options}`);
mongoose.Promise = Promise;
mongoose.connection.on('error', () => {
  throw new Error(`Can not connect to database: ${config.database.db}`);
});

const app = express();
app.server = http.createServer(app);

/**
 * Middleware
 */
app.use(morgan('combined'));

/**
 * Controller
 */
config.controller.forEach(Controller => {
  Controller.mount(app);
});

/**
 * Services
 */
// ...

/**
 * Listen for connections
 */
app.server.listen(config.api.port, config.api.host, () => {
  winston.info(
      '[%s] API startet on %s:%s in \'%s\'-mode',
      config.name,
      config.api.host,
      config.api.port,
      config.mode
  );
});
