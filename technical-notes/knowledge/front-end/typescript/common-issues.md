# Common Issues

## 概述

Common issues.

#### Object is possibly undefined

在確認必有值，而且前方已有判斷式但仍出現此警告時，可使用 `!` 省略檢查。

```typescript
let x = some.expr.thing; // 錯誤
let y = some.expr!.thing; // 正確
```

#### Argument of type 'void' is not assignable to parameter of type 'Function'. ts\(2345\)

```typescript
someFunction('key', someCallback(variable)) // 錯誤
someFunction('key', () => someCallback(variable)) // 正確
```

### 關於宣告檔

當使用 `*.d.ts` 檔做全域類型宣告檔時，不可用 ES6 Modules 的方法去匯入，必須在 `ts.config.json` 內做宣告檔的路徑管理，或者用以下方法在 Entry Point TS File 匯入。

```typescript
/// <reference path="" />
```

