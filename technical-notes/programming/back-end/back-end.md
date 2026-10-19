# Back-End

node\_modules/laravel-mix/src/builder



node &gt; npm &gt; laravel

node 6.14.2

npm: 3.10.1

laravel-mix : 2.1.14 \(a package for npm, a API for defining basic [webpack](http://github.com/webpack/webpack) build steps for your Laravel application. \)

php: 7.0.28

Composer 1.5.2

webpack: 3.12.0 \(npm list webpack\)

沒有 laravel

沒有composer





查看laravel的文档得知laravel 5.5（现在后端项目普遍用的版本）已经内嵌laravel-mix

使用的webpack版本受限于laravel-mix的依赖，不能自由升级（当前laravel-mix用的webpack版本是3.4.0）





```text
// 執行所有 Mix 任務...
npm run dev

// 執行所有 Mix 任務和壓縮輸出...
npm run production
```

npm run dev &gt; laravel compile

follow webpack.mix.js

