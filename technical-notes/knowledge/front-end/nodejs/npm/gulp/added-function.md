# 傳遞參數

## 概述

當在 Terminal 中使用 Gulp 時，可以藉由在指令後傳遞參數的方式，向 GulpFile 傳遞一值，使 GulpFile 在執行時可以以此參數作為各類任務執行的判斷。

### 傳遞參數

有兩種方式可以使 GulpFile 執行時獲得 `npm run gulp` 指令後的值。

#### Yargs

```javascript
var argv = require('yargs').argv;
var isProduction = (argv.production === undefined) ? false : true;
console.log(isProduction); 
```

#### Minimist

```javascript
const minimist = require('minimist');
var envOptions = {
  string: 'env',
  default: {
    env: 'develop'
  },
};
var options = minimist(process.argv.slice(2), envOptions);
console.log(options)
```

首先藉由  `envOptions` 定義字串 Key 以及 Default 值，接著用 `minimist()` 代入指令後的參數以及先定義好的值，最後，再將產出的 Config 宣告成 `option`。在這之後的 Gulp Task 通常會使用 **Gulp-If** 來判斷 `option` 內的值做不同期望下的處理。

注意在取得指令後的參數時，其中的 `process.argv` 是 Node 自有、用於處理指令的內存。Slice 默認取至設定的最後一位的前一位，因此 2 是指 0 與 1，0 是 Gulp 本身，1 即是我們代入的第一個參數，若有需要兩個以上，則會出現在 2 以後的位置，此時再調整 Slice 的值即可。

若需要傳遞 2 個以上的參數，可以使用 `process.argv.slice(3)` 。而指令會像如下。

```bash
npx gulp initial --env develop --env emergency
```

