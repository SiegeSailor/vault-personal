# Common Issues

## 概述

Common issues.

### 常見問題

一些編寫 TypeScript 時頻繁遇見的問題。

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

#### Arrow Function Overload

Arrow Function 沒辦法像一般 Function 一樣重複宣告來使用 Overload，需要另外宣告 Type。

```typescript
type TypeReverseParameter = {
  (parameter: number): number;
  (parameter: string): string;
};
export const reverseParameter: TypeReverseParameter = (parameter: any): any => {
  if (typeof parameter === 'string') {
    return parameter.split('').reverse().join('');
  } else if (typeof parameter === 'number') {
    return parseInt(String(parameter).split('').reverse().join(''));
  }
};
```

#### Enum Constant

當需要規定特定字串時。

```typescript
// 首先宣告一個 Type 在任意 *.d.ts 檔案中。
type EventNames = 'Join' | 'Message' | 'Info' | 'Msg';

// 在 *.d.ts 檔案中，可以使用該 Type 規範某一個 Key 下的輸入值。
interface Input {
  action: EventNames;
  data?: {
    [key: string]: any;
  };
}

//而在 Constants 中，可用該 Type 定義一個 Object，使此 Object 的值可以被填入該 Key 中。
export const EventNames: { [key in WebSocket.EventNames]: WebSocket.EventNames } = {
  Join: 'Join',
  Message: 'Message',
  Info: 'Info',
  Msg: 'Msg',
};
```

#### Exported Variable

雖然 `let` 宣告的值在 JavaScript 中可以被 Export 並在別的 Module 中修改，但在 TypeScript 中需使用改變 Object 內某個 Key 的值的方法。

```typescript
// export.ts

const exportedObject = {
    a: 1,
    b: 2,
};

let exportedLet = 0;

export {
    exportedObject,
    exportedLet
}
```

```typescript
// import.ts
import { exportedObject, exportedLet} from 'export'

exportedLet = 2; // 錯誤
exportedObject.a = 2; // 正確
```

### 關於宣告檔

當使用 `*.d.ts` 檔做全域類型宣告檔時，不可用 ES6 Modules 的方法去匯入，必須在 `ts.config.json` 內做宣告檔的路徑管理，或者用以下方法在 Entry Point TS File 匯入。

```typescript
/// <reference path="" />
```

