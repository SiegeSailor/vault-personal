# Trouble Shooting

## Trouble Shooting

After `bower install`, you need to run `npm install` in order to use gulp, in this stage you might meet such error.

```bash
npm install

> semantic-ui@2.4.1 install /Users/apple/Desktop/Edallianz/bower_components/semantic
> gulp install

/Users/apple/Desktop/Edallianz/bower_components/semantic/node_modules/gulp-help/index.js:81
    task = gulp.tasks[name];
                     ^

TypeError: Cannot read property 'help' of undefined
    at Gulp.gulp.task (/Users/apple/Desktop/Edallianz/bower_components/semantic/node_modules/gulp-help/index.js:81:22)
    at module.exports (/Users/apple/Desktop/Edallianz/bower_components/semantic/node_modules/gulp-help/index.js:97:8)
    at Object.<anonymous> (/Users/apple/Desktop/Edallianz/bower_components/semantic/gulpfile.js:6:38)
    at Module._compile (internal/modules/cjs/loader.js:701:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:712:10)
    at Module.load (internal/modules/cjs/loader.js:600:32)
    at tryModuleLoad (internal/modules/cjs/loader.js:539:12)
    at Function.Module._load (internal/modules/cjs/loader.js:531:3)
    at Module.require (internal/modules/cjs/loader.js:637:17)
    at require (internal/modules/cjs/helpers.js:22:18)
npm ERR! code ELIFECYCLE
npm ERR! errno 1
npm ERR! semantic-ui@2.4.1 install: `gulp install`
npm ERR! Exit status 1
npm ERR!
npm ERR! Failed at the semantic-ui@2.4.1 install script.
npm ERR! This is probably not a problem with npm. There is likely additional logging output above.
```

This is because of that your Gulp version is too high \( Usually is `4.0.2` \), you need to change the version to `3.9.1` in your **bower\_componentes/semantic/package.json** and **package-lock.json**. Then you have to run this again.

```bash
npm install
```

