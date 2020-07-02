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

#### Module Path

若是使用 TypeScript，無論是使用 `tsc` 編譯後使用或直接用 `ts-node`，都必須使用 **絕對路徑**。

```typescript
import { Change, Event } from './constants/index'; 
import { Names } from './constants/common'; 
```

但若是使用 `module-alias`，則可以使用 `tsconfig` 的 Path 設定。

```bash
npm install --save module-alias
npm install --save-dev @types/module-alias
```

在任意地方創建 alias.ts \( 通常與 index.ts 同層 \)。以下 Path 需與 `tsconfig` 內的 Path 同步。並在 index.ts 的最上層匯入。

```typescript
// alias.ts
import ModuleAlias from 'module-alias';

ModuleAlias.addAliases({
  '@routes': __dirname + '/routes',
  '@constants': __dirname + '/constants',
  '@types': __dirname + '/types',
  '@utils': __dirname + '/utils',
  '@triggers': __dirname + '/triggers',
});

// tsconfig.ts
{
  "compilerOptions": {
    "paths": {
      "@routes/*": ["./routes/*"],
      "@constants/*": ["./constants/*"],
      "@types/*": ["./types/*"],
      "@utils/*": ["./utils/*"],
      "@triggers/*": ["./triggers/*"]
    },
  }
}

// index.ts
import './alias';
import common from '@routes/common/index';
import handchopping from '@routes/handchopping/index';

module.exports = {
  common,
  handchopping,
  handchoppingProd: handchopping,
};
```

注意，若是裡面有使用到不需匯入即可使用的、全域宣告的 `*.d.ts` 檔，在先 `tsc` 編譯後再使用的情況下不會有錯，但若是使用 `ts-node`，則必須在其中一個被引用的檔案最上方使用 **類型宣告引用** \( 只需要在其中一個檔案內引用一次 \)。

```typescript
/// <reference path="./../../types/webSocket.d.ts" />

export const EventNames: { [key in WebSocket.EventNames]: WebSocket.EventNames } = {
  Join: 'Join',
  Sys: 'Sys',
  Leave: 'Leave',
};
```

### 關於宣告檔

當使用 `*.d.ts` 檔做全域類型宣告檔時，不可用 ES6 Modules 的方法去匯入，必須在 `ts.config.json` 內做宣告檔的 **include** 路徑管理，或者用以下方法在 Entry Point TS File 匯入。

```typescript
/// <reference path="" />
```

