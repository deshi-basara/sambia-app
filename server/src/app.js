import http from 'http';
import express from 'express';
import morgan from 'morgan';
import winston from 'winston';

import config from './helper/config.js';

const app = express();
app.server = http.createServer(app);

/**
 * Middleware
 */
app.use(morgan('combined'));

/**
 * Controller
 */
// ...

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
