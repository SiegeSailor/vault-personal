---
sidebar_position: 2
---

# Jest Config

開始撰寫測試之前，有幾個必須先設置的檔案：

```bash
├──src
# highlight-next-line
│   ├──setupTests.ts
│   └── ...
# highlight-next-line
├── jest.config.ts
# highlight-next-line
├── package.json
# highlight-next-line
├── tsconfig.test.json
└──...
```

`setupTests.ts` 會在每次測試前執行，通常會做的事是引入 Extensions 或做全域 Mock。

```typescript title="setupTests.ts"
import '@testing-library/jest-dom/extend-expect';
import 'jest-extended';
import 'jest-canvas-mock';
import 'fake-indexeddb/auto';

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

`jest.config.ts` 是對於 Jest 本身如何執行的設置。

```typescript title="jest.config.ts"
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
    /** Map 所有 `@game/utilities` 開頭的 Import Path 到 `@game/utilities` Package 下  */
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

`package.json` 也可以設置 `jest.config.ts` 內的欄位。

```json title="package.json"
{
  "jest": {
    "transformIgnorePatterns": [
      "/node_modules/(?!(@ionic/react|@ionic/react-router|@ionic/core|@stencil/core|ionicons|d3|d3-.*|internmap|delaunator|robust-predicates)/)"
    ]
  }
}
```

`tsconfig.test.json` 可以在 `jest.config.ts` 中設置使用，提供針對測試的不同 TypeScript 設置。

```json title="tsconfig.test.json"
{
  "compilerOptions": {
    "allowJs": true,
    "allowSyntheticDefaultImports": true,
    "baseUrl": "src",
    "charset": "UTF-8",
    // highlight-next-line
    "skipDefaultLibCheck": true,
    "esModuleInterop": false,
    "forceConsistentCasingInFileNames": true,
    "isolatedModules": false,
    "jsx": "react-jsx",
    "lib": ["DOM", "DOM.Iterable", "ESNEXT"],
    "locale": "zh-TW",
    "module": "ESNEXT",
    "moduleResolution": "Node",
    "noEmit": true,
    "noImplicitAny": false,
    "resolveJsonModule": true,
    "skipLibCheck": true,
    "strict": true,
    "target": "ES5",
    "noFallthroughCasesInSwitch": true,
    // highlight-next-line
    "types": ["jest"]
  },
  "include": ["src", "node_modules/@game/utilities/types", "node_modules/@game/utilities/lib/main.d.ts"]
}
```
