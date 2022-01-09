---
sidebar_position: 3
---

# Config

開始撰寫測試之前，有幾個必須先設置的檔案：

```bash
├── src/
# highlight-next-line
│   ├── setupTests.ts
│   └── ...
# highlight-next-line
├── jest.config.ts
# highlight-next-line
├── package.json
# highlight-next-line
├── tsconfig.test.json
└── ...
```

:::info 提醒
目前大多 Framework 或 Scaffold 都有將測試納入配置、不需手寫，如 [Ionic](https://ionicframework.com/docs/react/testing) 和 [CRA](https://create-react-app.dev/docs/running-tests/) 都使用了 Jest。
:::

### 如何執行

`jest.config.ts` 是對於 Jest 本身如何執行的設置：

```tsx title="jest.config.ts"
import type { Config } from '@jest/types';

const config: Config.InitialOptions = {
  verbose: true,
  preset: 'ts-jest/presets/default-esm',
  timers: 'fake',
  testEnvironment: 'jsdom',

  /**
   * Source code 的 Root，通常是 `/src`
   * `<rootDir>` 是 Jest 可設置的 path
   */
  roots: ['<rootDir>/src'],
  /** 使用 `ts-jest` 來支援 TypeScript */
  transform: {
    '^.+\\.ts?$': 'ts-jest',
    '^.+\\.jsx?$': 'babel-jest',
    'node_modules/@game/utilities/^.+\\.ts?$': 'ts-jest',
  },
  /**
   * `@game/utilities` 為一未被編譯的 TypeScript Package
   * 直接使用在測試會導致 Syntax Error，又由於 Jest 在編譯時通常忽略 `node_modules`
   * 這邊設置 Jest 編譯忽略除了 `@game/utilities` 外的所有 `node_modules`
   */
  transformIgnorePatterns: ['node_modules/(?!(@game/utilities)/)^.+\\.ts?$'],
  moduleNameMapper: {
    /** Jest 直接使用 CSS 檔案會導致 Syntax Error，使用 `identity-obj-proxy` 來 Mock  */
    '\\.(css|less|scss|sass)$': 'identity-obj-proxy',
    /** Map 所有 `@game/utilities` 開頭的 Import Path 到 `@game/utilities` 下  */
    '^@game/utilities$': '@game/utilities',
    /** 使用 `@` 作為 `baseUrl` 的情況  */
    '^@/(.*)$': '<rootDir>/$1',
  },
  extensionsToTreatAsEsm: ['.ts', '.tsx'],
  globals: {
    'ts-jest': {
      useESM: true,
      tsconfig: 'tsconfig.test.json',
      babel: 'babel.config.js',
    },
  },

  /**
   * 在 Envs 注入前執行的邏輯，像是 Cleaning up components
   * 當使用 React Testing Library 時需加入擴增的 Jest 斷言
   */
  setupFilesAfterEnv: ['@testing-library/jest-dom/extend-expect', 'jest-extended'],
  /**
   * 測試檔案的命名規則，這邊需父層資料夾為 `__tests__
   * 或者名稱有 `test` 或 `spec`
   */
  testRegex: '(/test/.*|(\\.|/)(test|spec))\\.tsx?$',

  moduleFileExtensions: ['ts', 'tsx', 'js', 'jsx', 'json', 'node'],
  moduleDirectories: ['node_modules', 'src'],
};

export default config;
```

注意 `transformIgnorePatterns` 的設置，未被編譯的 Library 若直接在測試使用會出現以下錯誤：

```bash
FAIL  src/component/InputPagination/index.test.tsx
● Test suite failed to run

  Jest encountered an unexpected token

  This usually means that you are trying to import a file which Jest cannot parse, e.g. it's not plain JavaScript.

  By default, if Jest sees a Babel config, it will use that to transform your files, ignoring "node_modules".

  Here's what you can do:
    • If you are trying to use ECMAScript Modules, see https://jestjs.io/docs/en/ecmascript-modules for how to enable it.
    • To have some of your "node_modules" files transformed, you can specify a custom "transformIgnorePatterns" in your config.
    • If you need a custom transformation specify a "transform" option in your config.
    • If you simply want to mock your non-JS modules (e.g. binary assets) you can stub them out with the "moduleNameMapper" config option.

  You'll find more details and examples of these config options in the docs:
  https://jestjs.io/docs/en/configuration.html

  Details:

  /Users/ken.zhang/Desktop/Servicetech-Monorepo/PANEL/node_modules/@ionic/core/components/index.js:4
  export { setAssetPath, setPlatformOptions } from '@stencil/core/internal/client';
  ^^^^^^

  SyntaxError: Unexpected token 'export'

    at Runtime.createScriptFromCode (node_modules/jest-runtime/build/index.js:1350:14)
    at Object.<anonymous> (node_modules/@ionic/react/dist/index.js:6:20)
```

### 執行指令

`package.json` 也可以設置 `jest.config.ts` 內的欄位。同時在此設置 NPM Scripts：

```json title="package.json"
{
  "jest": {
    "transformIgnorePatterns": [
      "/node_modules/(?!(@ionic/react|@ionic/react-router|@ionic/core|@stencil/core|ionicons|d3|d3-.*|internmap|delaunator|robust-predicates)/)"
    ]
  },
  "scripts": {
    "test": "jest"
  }
}
```

:::info 提醒
使用 **CRA**，Create React App 與 TypeScript 的話，可以使用 `tsc && react-scripts test` 來在執行測試前檢查型別。
:::

### 執行前

`setupTests.ts` 會在每次測試前執行，通常會做的事是引入 Extensions 或做全域 Mock：

```tsx title="setupTests.ts"
import 'jest-extended';
import 'fake-indexeddb/auto';
import '@testing-library/jest-dom/extend-expect';
import 'jest-canvas-mock';

window.matchMedia =
  window.matchMedia ||
  function () {
    return {
      matches: false,
      addListener: function () {},
      removeListener: function () {},
    };
  };
```

### 專用配置

`tsconfig.test.json` 可以在 `jest.config.ts` 中設置使用，提供針對測試的不同 TypeScript 設置：

```json title="tsconfig.test.json"
{
  "compilerOptions": {
    "allowJs": true,
    "allowSyntheticDefaultImports": true,
    "baseUrl": "src",
    "charset": "UTF-8",
    /** 略過引入 Packages 的型別檢查 */
    "skipDefaultLibCheck": true,
    "esModuleInterop": false,
    "forceConsistentCasingInFileNames": true,
    "isolatedModules": false,
    "jsx": "react-jsx",
    /** 若出現 TypeError，優先檢查此列 */
    "lib": ["DOM", "DOM.Iterable", "ESNEXT"],
    "locale": "zh-TW",
    "module": "ESNEXT",
    "moduleResolution": "Node",
    "noEmit": true,
    "noImplicitAny": false,
    "resolveJsonModule": true,
    /** 建議開啟，略過檢查 Libraries */
    "skipDefaultLibCheck": true,
    "skipLibCheck": true,
    "strict": true,
    "target": "ES5",
    "noFallthroughCasesInSwitch": true,
    /** 讀取 Jest Types */
    "types": ["jest"]
  },
  "include": ["src", "node_modules/@game/utilities/types"]
}
```

注意 `compilerOption.lib` 的設置，特別是發現來自原生函式的 Type Error 時：

```bash
src/component/ButtonSubmit/index.test.tsx:16:29 - error TS2812: Property 'parentElement' does not exist on type 'HTMLElement'. Try changing the 'lib' compiler option to include 'dom'.

16     expect(listOfButton[0]?.parentElement).not.toBeNull();
                               ~~~~~~~~~~~~~
```
