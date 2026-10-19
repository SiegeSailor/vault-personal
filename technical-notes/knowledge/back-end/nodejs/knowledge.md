# Knowledge

## 概述

各式關於 NodeJS 的觀念說明與基本指令。

### 觀念

Node 的 `global` 跟 Browser 的 `window` 一樣，掛載所有全域物件。

```javascript
console.log(global);
```

Node 與 Browser 不一樣在於，`var` 提昇到全域後不會自動掛載在 `global` 下，必須用物件下的 key 的方式掛載。如果沒有 Export，那麼所謂全域只存在於該 JS 檔案，或是該 Module 內。

```javascript
var data = 1;
console.log(global); ​// ​此時 ​data 不​在 ​global ​內

global.data = 1;
console.log(global); ​// ​此時可以看見 ​data ​在 ​global ​內
```

#### Commands

Execute files.

```bash
node file.js
```

#### VS Code

點擊左側來下 Break Point，程式會在該行停止，並且此時 Debug 模式能看到變數、Callback、Watch 等值。

## Modules

每個 JS 檔案為一個 Module，當一個 Module 內有多個 Export 方法時，最下的會覆蓋上方的。

### Require

用於取得來自別的 JS 檔案的 Export 的值。

```javascript
var content = require("./ModuleExportValue.js"); // .js 不一定要寫上
```

### Export

宣告該 JS 檔案可以被別的 JS 檔案 `require` 或 `import` 的值。

#### Module Exports Single Value

```javascript
var data = 2;

module.exports = data;
```

#### Module Export Object

```javascript
var dataOne = "data 1";
var dataTwo = "data 2";

module.exports = {
  dataOne: dataOne,
  dataTwo
};
```

#### Exports

此時是將 exports 當作空物件掛載，因此 `require` 或 `import` 後取到的一定是物件。

```javascript
exports.fire = "fire";
```

