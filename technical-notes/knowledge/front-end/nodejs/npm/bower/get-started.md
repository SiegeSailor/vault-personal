# Get Started

## 概述

Bower 是在 NPM 套件管理工具下的一個針對 CSS 與 JS 的 Library 和 Framework 和 Package 的套件管理工具，有屬於自己的部署指令。因此，在建構一個專案時，常見的指令如下。

```bash
sudo npm install --unsafe-perm=true
```

```bash
bower install
```

通常使用到 Bower 的專案會有以下檔案供建構。

* source :: 主 JavaScript 與 CSS 類原碼，在同一目錄下供 Gulp 或 Webpack 編譯
* gulpfile.js :: Gulp 編譯的 Config 檔，通常與 Webpack 二擇一
* webpack.config.js :: Webpack 編譯的 Config 檔，通常與 Gulp 二擇一
* package.json :: NPM 建置的 Config 檔
* bower.json :: Bower 建置的 Config 檔
* .bowerrc :: Bower 配置的 Config 檔

#### bower.json

常見的建置檔描述。

```javascript
{  "name": "bower-practice",  "description": "A Practice Project for Bower.",  "main": "index.js",  "authors": [    "Zhang Jin Yu"  ],  "license": "ISC",  "keywords": [    "Bower"  ],  "homepage": "https://github.com/SiegeSailor/GulpPractice",  "private": true,  "ignore": [    "**/.*",    "node_modules",    "bower_components",    "test",    "tests"  ],  "dependencies": {    "jQuery": "^3.4.1",    "bootstrap": "^4.3.1",    "semantic": "semantic-ui#^2.4.1"  }}
```

#### .bowerrc

與 `bower.json` 描述的與 `package.json` 類似的安裝套件及專案訊息不同，.bowerrc 主要描述 Bower 的路徑和參數等等。以下為最常見的對於 Bower 的安裝路徑的指定。

```javascript
{    "directory": "bower/components"}
```

#### .gitignore

常見列入 `.gitignore` 的檔案。

```text
node_modules/bower/bower_components/public.tmppackage-lock.json
```

