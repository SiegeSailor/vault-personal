# Trouble Shooting

## Trouble Shooting

#### Module not found: Error: Can't resolve jQuery in

```bash
npm install --save jquery
```

#### Execute Twice

Check if you load your file outside **Entry Point** again.

#### Watch 無效

當在 Ubuntu 使用 Watch 指令監聽，卻在改動後毫無反應時，可以嘗試以下指令來增加 Watch 數量。

```bash
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```

#### Package exports for '.../@babel/helper-compilation-targets' do not define a '.' subpath

此為 Babel 的錯誤，若發生請將 NodeJS 升級至 &gt;= **13.2.0**。

#### 處理時超出預設內存

在Node 中通過JavaScript 使用內存時只能使用部分內存（ 64 位系統：1.4 GB，32 位系統：0.7 GB ），這個時候，如果前端項目非常的龐大，Webpack 編譯時就會佔用很多的系統資源，如果超出了V8 引擎對Node 默認的內存限制大小時，就會產生內存洩露 _**FATAL ERROR: CALL\_AND\_RETRY\_LAST Allocation failed - JavaScript heap out of memory**_ 的錯誤。

首先，安裝 increase-memory-limit 套件，這是一個專門解決 Webpack 或 TypeScript 編譯時內存洩漏問題的套件。

```bash
sudo npm -g install increase-memory-limit --unsafe-perm
```

接著在指定專案的根目錄下使用以下指令。

```bash
increase-memory-limit
```

使用後可能導致 Webpack 編譯無反應，此時須重新安裝 NPM Packages。

#### Webpack Dev Server Invalid Host Header

Most development-oriented application servers, such as `ng`, **webpack-dev-server**, or `WAMPP` default to only binding to localhost, or in some cases blocking traffic directly. Essentially, only traffic directed to your machine from itself is allowed to pass through, meaning that traffic passing through the tunnel will not work the same as traffic coming from your machine directly. Even though it works on your machine, the way the server is set up makes it so that even other local machines cannot connect to it.

In most cases, the fix is to tell the server to restart and allow connections from outside localhost. Kill the server and restart it, adding `--host 0.0.0.0` to the command. `--disable-host-check` is sometimes needed here as well. Your **package.json** configuration script might be the following.

```javascript
"scripts": {
    "start": "webpack-dev-server --config webpack/base.js --open --host 0.0.0.0 --disable-host-check",
},
```

You need to set `disableHostCheck` as `true` while using **Vue**.

```javascript
const port = process.env.PORT !== undefined ? { port: process.env.PORT } : {};
const hot = process.env.DEV_HOT && process.env.DEV_HOT.match(/^false$/i) ? { hot: false } : {};

module.exports = {
  devServer: {
    ...port,
    ...hot,
    disableHostCheck: true,
  },
};
```

