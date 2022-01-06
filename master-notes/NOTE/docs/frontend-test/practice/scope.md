---
sidebar_position: 5
---

# Scope

介紹一些 Jest 關於 Scope 的相關函式，詳情見 [Jest](https://jestjs.io/docs/getting-started)。這邊以 `handleStorage`，一個對操作 LocalStorage 進行封裝的 Function 進行說明：

```typescript title="react-app-env.d.ts"
declare namespace NodeJS {
  interface ProcessEnv {
    readonly REACT_APP_VERSION: string;
  }
}
```

```typescript title="device.ts"
export const handleLocalStorage = {
  _verify: function () {
    if (!process.env.REACT_APP_VERSION) throw Error('出現異常，請回報');
    return process.env.REACT_APP_VERSION;
  },
  set: function (key: string, value: object) {
    const prefix = this._verify();
    localStorage.setItem(`${prefix}_${key}`, JSON.stringify(value));
  },
  update: function (key: string, value: { [key: string]: object }) {
    const data = this.get(key) || {};
    this.set(key, { ...data, ...value });
  },
  get: function <T = object>(key: string): T | null {
    const prefix = this._verify();
    const dataStringified = localStorage.getItem(`${prefix}_${key}`);
    return dataStringified ? JSON.parse(dataStringified) : null;
  },
};
```

## Setup and Tear Down

- `beforeEach`: 另有 `afterEach`。會在該 Scope 的每個測試前執行
- `beforeAll`: 另有 `afterAll`。會在該 Scope 的開頭執行

每個 Tes Case 都必須是獨立的，不能相互影響，而解決方法是在每個測試執行前重設或結束後清除痕跡。Jest 透過以上函式在不同 Scope 作用，可以避免測試案例之間互相污染。

### 環境變數

首先，我們注意到 `process.env.REACT_APP_VERSION` 是作為 LocalStorage Key 的組成之一來使用的，那麼為了維持測試的一致性，我們應該要使用一樣的值，然而我們不可能開個 Parameter 每次執行把 `process.env.REACT_APP_VERSION` 傳入或在執行前設置 `process.env` (雖然可以，但很麻煩)。這時候可以使用到 `beforeAll`：

```typescript title="device.test.ts"
import { handleLocalStorage } from './device';

beforeAll(() => {
  process.env = { REACT_APP_VERSION: 'v1.0.0' };
});
```

:::info 提醒
這邊寫在檔案的 Scope，也就是會在這個測試檔案的最前面執行一次。若是寫在某個 `describe` 內，則是會在該 `describe` 執行前執行一次。
:::

#### 所有測試之前

環境變數是不會變的，因此我們可以考慮將指派變數這件事指定在一個在所有測試執行之前、且只執行一次的地方：

```typescript title="setupTests.ts"
import 'jest-extended';
import 'fake-indexeddb/auto';
import '@testing-library/jest-dom/extend-expect';
import 'jest-canvas-mock';

process.env = { REACT_APP_VERSION: 'v1.0.0' };
```

或者也可以寫在 `jest.config.ts` 內：

```typescript title="jest.config.ts"
process.env = { REACT_APP_VERSION: 'v1.0.0' };

const config: Config.InitialOptions = {};
export default config;
```

:::info 提醒
Jest 同樣支援來自 `.env` 的值。
:::

然而若是需要測試例外情況，還是需要手動設置與清除：

```typescript title="device.test.ts"
import { handleLocalStorage } from './device';

describe('handleLocalStorage', () => {
  describe('set', () => {
    it('Does the function throw error when there is no UID', () => {
      const version = process.env.REACT_APP_VERSION;
      process.env = { REACT_APP_VERSION: '' };
      /** 測試 `toThrow` 或 `toThrowError` 時，Parameter 為 Function */
      expect(() => {
        handleLocalStorage.set('emptyObject', {});
      }).toThrowError();
      process.env = { REACT_APP_VERSION: version };
    });
  });
});
```

### 設置與清除

回到 `handleLocalStorage`，在已經設好 `process.env` 的情況下，假設我們有以下測試：

```typescript title="device.test.ts"
import { handleLocalStorage } from './device';

describe('handleLocalStorage', () => {
  describe('set', () => {
    it('Set animal numbers', () => {
      expect(handleLocalStorage.set('entryOfAnimal', { dog: 1, elephant: 5 })).toBeUndefined;
    });

    it('Check stored values', () => {
      handleLocalStorage.set('entryOfTool', { ruler: 1 });
      handleLocalStorage.set('entryOfFood', { hotdog: 1, cabbage: 2 });
      /** 這邊會少了一組 { entryOfAnimal: { dog: 1, elephant: 5 } } */
      // highlight-start
      expect(handleLocalStorage.getAll()).toEqual({
        entryOfTool: { ruler: 1 },
        entryOfFood: { hotdog: 1, cabbage: 2 },
      });
      // highlight-end
    });
  });
});
```

那麼在進行 `Check stored values` 測試時就會失敗，原因是在 `Set animal numbers` 時已經在 LocalStorage 存了一個值，導致 `Check stored values` 測試存入的值這件事不合預期 (多了 1 個在該 Test Case 沒有出現的值)。這時，我們應該在每個測試前對 LocalStorage 進行清除：

```typescript title="device.test.ts"
import { handleLocalStorage } from "./device";

describe("handleLocalStorage", () => {
  beforeEach(() => {
    localStorage.clear();
  });
  ...
});
```

加上的是 `beforeEach`，也就是 `localStorage.clear` 這件事會在 `handleLocalStorage` 這組測試的每個 `test` 前執行，讓每個 `test` 間不會相互污染。而若此測試檔案內有其他組 `describe`，由於限制在 `handleLocalStorage` 這個 Scope，也不會執行多餘的清除、浪費效能。
