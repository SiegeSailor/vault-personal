---
sidebar_position: 1
---

# 設置環境

建置測試環境，以下列出關鍵 Packages。

```json title="package.json"
{
  "dependencies": {
    "@reduxjs/toolkit": "1.5.1",
    "@testing-library/jest-dom": "5.11.9",
    "@testing-library/react": "11.2.5",
    "@testing-library/user-event": "12.6.3",
    "@types/jest": "26.0.20",
    "@types/node": "12.19.15",
    "@types/react": "16.14.3",
    "@types/react-dom": "16.9.10",
    "@types/react-redux": "7.1.16",
    "@types/react-router": "5.1.11",
    "@types/react-router-dom": "5.1.7",
    "@types/react-test-renderer": "17.0.2",
    "connected-react-router": "6.9.1",
    "fake-indexeddb": "3.1.7",
    "jest": "26.6.0",
    "react": "17.0.2",
    "react-dom": "17.0.2",
    "react-redux": "7.2.4",
    "react-router": "5.2.0",
    "react-router-dom": "5.2.0",
    "react-scripts": "4.0.3",
    "react-test-renderer": "17.0.2",
    "redux": "4.1.0",
    "redux-mock-store": "1.5.4",
    "ts-jest": "27.0.3",
    "ts-node": "10.0.0",
    "typescript": "4.5.4"
  },
  "engine": {
    "node": "13.10.1"
  }
}
```

## 套件一覽

所有 Package 皆為可選，根據專案內容選擇即可。例如只有引入 TypeScript 的專案需要 `@types/**`、`ts-jest`、`ts-node` 與 `typescript`。

### Jest

JavaScript 測試框架，不限於 React、Vue、Angular，也可用於後端。是 [React 官方推薦工具](https://zh-hant.reactjs.org/docs/testing.html#tools)，在 Jest 官方網站上也有 [針對 React 的文件](https://jestjs.io/docs/tutorial-react)。實務上，Jest 會需要一些針對專案的設置，詳見 [Jest Config](/docs/react-test/jest-config)。

```json
{
  "jest": "26.6.0"
}
```

### Redux

經常與 React 配合使用的狀態管理 Package，一些仰賴 Redux 的 Unit 會需要先對 Redux 進行配置才能測試。

```json
{
  "redux-mock-store": "1.5.4"
}
```

### TypeScript

提供與 Unit Test 不同層級的檢查，可以在編譯時或在編輯器上拋出潛在的邏輯與型別錯誤。

```json
{
  "@types/jest": "26.0.20",
  "@types/node": "12.19.15",
  "@types/react": "16.14.3",
  "@types/react-dom": "16.9.10",
  "@types/react-redux": "7.1.16",
  "@types/react-router": "5.1.11",
  "@types/react-router-dom": "5.1.7",
  "@types/react-test-renderer": "17.0.2",
  "typescript": "4.5.4",
  "ts-jest": "27.0.3",
  "ts-node": "10.0.0"
}
```

### NodeJS

Jest 運行於 Node 而非 Browser 上，最好保持一致的版本。

```json
{
  "engine": {
    "node": "13.10.1"
  }
}
```

### React DOM

提供在 Node 環境對於 DOM 的操作。若沒有針對這些功能測試，則不需要使用。

```json
{
  "@testing-library/jest-dom": "5.11.9",
  "@testing-library/react": "11.2.5",
  "@testing-library/user-event": "12.6.3",
  "react-test-renderer": "17.0.2"
}
```

:::note
`react-dom` 內也有提供 [針對 React 測試的 Utilities](https://zh-hant.reactjs.org/docs/test-utils.html)。

```javascript
import ReactTestUtils from 'react-dom/test-utils';
```

:::

## 專案結構

以下示例引入 Unit Test 的 React 專案結構。

```bash
├── build
├── coverage
├── node_modules
├── public
│   ├──index.html
│   └── ...
├── src
│   ├── component
│   ├── constant
│   ├── helper
│   ├── page
│   ├── source
│   ├── state
│   ├── theme
│   ├── index.tsx
│   ├── Main.tsx
│   ├── react-env-app.d.ts
# highlight-next-line
│   ├── setupTests.ts
│   └── ...
├── .env
├── package.json
├── gitlab-ci.yml
├── .npmrc
├── babel.config.js
├── deploy.sh
# highlight-next-line
├── jest.config.ts
├── tsconfig.json
├── tsconfig.test.json
└──...
```

`setupTest.ts` 可以設想為每次測試時會預先執行、載入的 Module，而 `jest.config.ts` 則是對於 Jest 本身如何執行的設置。`.ts` 在非 TypeScript 專案時應換為 `.js`。詳細設置參考 [Configuring Jest](https://jestjs.io/docs/configuration)。

### 繼承

若引入測試的專案採用 Monorepo 架構或在 Multi-repo 配置了他處的 Config，可以使用 `module.exports` 繼承。

```bash
├── Admin
│   ├── src
# highlight-next-line
│   ├── jest.config.ts
│   └── ...
├── API
# highlight-next-line
│   ├── jest.config.ts
│   └── ...
# highlight-next-line
├── jest.config.ts
└──...
```

```javascript title="Admin/jest.config.ts"
module.exports = require('./../.jest.config.ts');
```
