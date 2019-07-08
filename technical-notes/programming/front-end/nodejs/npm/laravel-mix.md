# Laravel Mix

## Precondition

Versions that used on LeJourney Web.

* Node.js :: 6.14.2
* NPM :: 3.10.1
* Laravel-Mix :: 2.1.14
* PHP :: 7.0.28
* Composer :: 1.5.2
* Webpack :: 3.12.0
* Laravel :: None

#### What is Laravel Mix

a package for npm, a API for defining basic [Webpack](http://github.com/webpack/webpack) build steps for your Laravel application.

#### Laravel and Laravel Mix

Laravel 5.5 above automatically contain Laravel Mix, and the Webpack version should depend on the dependency of Laravel Mix, which is 3.4.0. Use `webpack.mix.js` to config.

## Trouble Shooting

#### sh: cross-env: command not found

Just delete all `cross-env` in your `package.json`.

#### npm failed to run script dev

Follow the steps to solve.

1. rm -r node\_modules
2. rm package.lock.json
3. npm install

