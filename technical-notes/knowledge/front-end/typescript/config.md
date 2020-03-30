# Config

## 概述

與 `tsconfig.json` 有關的設定。

### Import

當 `target` 設定為 **ES5** 或 **ES3** 時，`module` 應設定為 **CommonJS**，否則會出現 _SyntaxError: Cannot use import statement outside a module_。這是因為 ES5 之前並沒有 `import` 的語法，而 target 的目的是將 TypeScript 編譯成指定版本的 JS。

官方說明可將副檔名改為 **.mjs**，強制使用 **module** \( **.cjs** 為強制使用 **require** \)，或者在指令後加上 `--experimental-modules`，然而實測此兩種方法無效。另外，在 package.json 內設置 `type` 為 **module**，以及在 tsconfig.json 內設置 `module` 為 **esnext** 這兩種方法在使用 _ts-node_ 時都會報錯。這也許會在新的 _ts-node_ 中改進。

### Merge TSconfig

若需要在不同場合使用不同 `tsconfig.json`，例如平常編寫時使用一套標準，在編譯時使用另一套標準，可以 NPM 使用以下指令。

```javascript
"scripts" : {
    "mergeTsconfig": "cross-env TS_NODE_PROJECT=\"tsconfig.extended.json\" npm run $1"
}
```

`tsconfig.extended.json` 為要合併入 `tsconfig.json` 的檔案，格式可能如下。

```javascript
{
  "compilerOptions": {
    "module": "CommonJS",
    "target": "ES5",
    "esModuleInterop": true
  }
}
```

$1 為第二個參數的意思，因此，實際執行時指令應為如下。

```bash
npm run mergeTsconfig devBuild
```

