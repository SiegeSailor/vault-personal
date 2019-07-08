# Gulp

## Precondition

Here is which versions I use when I was practicing Gulp.

* Gulp :: 3.9.1
* Graceful-fs :: 4.1.15
* Minimatch :: 3.0.2
* Operating System :: Ubuntu 18.04.2 LTS

Use this code to install Gulp.

```bash
npm install --save-dev gulp@3.9.1 --no-optional
```

Then you need to install other packages to enable the true power of Gulp, use `npm install packageName --save-dev -no-optional` to install them.

* gulp-jade :: HTML template language
* gulp-sass :: CSS preprocessor
* gulp-plumber :: Make Gulp nonstop even meet errors
* gulp-postcss :: CSS postprocessor
* autoprefixer :: Apply prefixes based on the current browser popularity.
* gulp-load-plugins :: Simplify how Gulp works
* gulp-babel :: ES6 compiler
* @babel/core :: Dependence for gulp-babel
* @babel/preset-env :: Dependence for gulp-babel
* babel-preset-es2015 :: ES6 compiler
* gulp-sourcemaps :: Source mark
* gulp-concat :: Concatenate codes
* browser-sync :: Web server, including live load
* main-bower-files :: Browser tool
* gulp-minify-css :: Minify CSS
* gulp-uglify :: Minify JavaScript
* minimist :: Import scripts into the process of Gulp
* gulp-if :: Add logic in Gulp
* gulp-watch :: Watch files even when deleting or adding
* gulp-order :: Give the order you want to the files you are handling
* gulp-clean :: Simply clean files
* gulp-sequence :: Activate your tasks with order
* gulp-imagemin :: Compress images

This is how the packages look like in `package.json`.

```javascript
"devDependencies": {
    "@babel/core": "^7.4.5",
    "@babel/preset-env": "^7.4.5",
    "autoprefixer": "^9.6.0",
    "babel-preset-es2015": "^6.24.1",
    "browser-sync": "^2.26.7",
    "gulp": "^3.9.1",
    "gulp-babel": "^8.0.0",
    "gulp-clean": "^0.4.0",
    "gulp-clean-css": "^4.2.0",
    "gulp-concat": "^2.6.1",
    "gulp-if": "^2.0.2",
    "gulp-imagemin": "^5.0.0",
    "gulp-jade": "^1.1.0",
    "gulp-minify-css": "^1.2.4",
    "gulp-order": "^1.2.0",
    "gulp-plumber": "^1.2.1",
    "gulp-postcss": "^8.0.0",
    "gulp-sass": "^4.0.2",
    "gulp-sequence": "^1.0.0",
    "gulp-sourcemaps": "^2.6.5",
    "gulp-uglify": "^3.0.2",
    "gulp-watch": "^5.0.1",
    "main-bower-files": "^2.13.1",
    "minimist": "^1.2.0",
    "yargs": "^13.2.4"
  },
```

If you get errors such as _**Task “default” is not in your gulpfile**_ or _**Task never defined: default**_, when running `npm run gulp taskName`, that might be you didn't install the right versions for the dependencies for Gulp. You should get the information after  `npm install --save-dev gulp@3.9.1 --no-optional`. Here is an example, I updated **minimatch@3.0.2** and **graceful-fs@4.1.15** for **gulp@3.9.1**.

```javascript
"devDependencies": {
    "gulp": "^3.9.1"
  },
  "dependencies": {
    "graceful-fs": "^4.1.15",
    "minimatch": "^3.0.2"
  }
```

## Activate Script

After installation, you should insert a script in `package.json` to activate Gulp.

```javascript
"scripts": {
    "gulp": "gulp"
  },
```

Then you can use this command line to activate Gulp.

```bash
npm run gulp taskName
```

When your NPM version is above 5.2, you can use `npx` to run npm globally.

```bash
npx gulp taskName
```

## Load Module

As a NodeJS package, Gulp follows the same way to call module functions.

```javascript
var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var plumber = require('gulp-plumber');
```

