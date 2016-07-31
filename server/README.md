# Server

## Requirements

* [Nodejs](https://nodejs.org/en/) (~v6.2.2)
* [MongoDB](https://www.mongodb.com), a high-performance, schema-free document-oriented database

## Install

Clone this repo and execute:
```
$ > npm install
```

## Development

There are three different ways of starting the server:
```
$ > npm run debug     # for the debug env with auto-restarts
$ > npm run start     # for the production env
```

A code-base for production can be generated with `build`. All tasks (linter, types, transpiler and tests) are
executed beforehand. If one task fails, the build will be stopped. (Caution: Your `dist`-folder will be deleted):

```
$ > npm run build
```

## Deployment

For deployment you have to edit `src/config/production.js` and finish
the following tasks
```
$ > npm run build && npm run start

info: [Sambia Server] API startet on X:X:X:X:8080 in 'production'-mode
```
