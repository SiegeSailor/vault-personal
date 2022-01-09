---
sidebar_position: 1
---

# Setup

建置測試環境，以下列出關鍵 Packages：

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

JavaScript 測試框架，不限於 React、Vue、Angular，也可用於後端。是 [React 官方推薦工具](https://zh-hant.reactjs.org/docs/testing.html#tools)，在 Jest 官方網站上也有 [針對 React 的文件](https://jestjs.io/docs/tutorial-react)。實務上，Jest 會需要一些針對專案的設置，詳見 [Config](/docs/test/practice/config)：

```json
{
  "jest": "26.6.0"
}
```

### NodeJS

Jest 運行於 Node 而非 Browser 上，最好保持一致的版本：

```json
{
  "engine": {
    "node": "13.10.1"
  }
}
```

### TypeScript

提供與 Unit Test 不同層級的檢查，可以在編譯時或在編輯器上拋出潛在的邏輯與型別錯誤：

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

注意若使用 TypeScript 並設置 Type Check，則一定要放入相關的 `@types/**` Packages。

### Redux

經常與 React 配合使用的狀態管理 Package，一些仰賴 Redux 的 Unit 會需要先對 Redux 進行配置才能測試：

```json
{
  "redux-mock-store": "1.5.4"
}
```

### React DOM

提供在 Node 環境對於 DOM 的操作，若沒有針對這些功能測試，則不需要使用：

```json
{
  "@testing-library/jest-dom": "5.11.9",
  "@testing-library/react": "11.2.5",
  "@testing-library/user-event": "12.6.3",
  "react-test-renderer": "17.0.2"
}
```

:::info 提醒
`react-dom` 內也有提供 [針對 React 測試的 Utilities](https://zh-hant.reactjs.org/docs/test-utils.html)。

```javascript
import ReactTestUtils from 'react-dom/test-utils';
```

:::
