---
sidebar_position: 2
---

# File Structure

以下示例引入 Jest 的 React 專案結構。

```bash
├── build/
├── coverage/
├── node_modules
├── public/
│   ├──index.html
│   └── ...
├── src/
│   ├── component/
│   ├── constant/
│   ├── helper/
│   ├── page/
│   ├── source/
│   ├── state/
│   ├── theme/
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

若引入測試的專案採用 Monorepo 架構，可以使用 `module.exports` 繼承。

```bash
├── Admin/
│   ├── src/
# highlight-next-line
│   ├── jest.config.ts
│   └── ...
├── API/
# highlight-next-line
│   ├── jest.config.ts
│   └── ...
# highlight-next-line
├── jest.config.ts
└──...
```

```javascript title="Admin/jest.config.ts"
module.exports = require("./../.jest.config.ts");
```
