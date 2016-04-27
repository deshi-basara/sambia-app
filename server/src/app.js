import http from 'http';
import express from 'express';

const app = express();
app.server = http.createServer(app);

/**
 * Insert Express middleware
 */

/**
 * [listen description]
 * @param  {[type]} config.api.port [description]
 * @param  {[type]} config.api.host [description]
 * @param  {[type]} (               [description]
 * @return {[type]}                 [description]
 */
app.server.listen(config.api.port, config.api.host, () => {
  console.log('[%s] API startet on %s:%s', config.name, config.api.host, config.api.port);
});
