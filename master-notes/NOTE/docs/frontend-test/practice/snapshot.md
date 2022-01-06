---
sidebar_position: 9
---

# Snapshot

通常而言，與 UI 耦合的 Unit 測試已經是 Integration Test 的範疇，然而 Snapshot 不同。雖然是測試 UI，但它的主要目的是比較產生的元素前後是否一致，也就是比對 HTML (意即不會真的 Render 畫面)，因此屬於 Unit Test。

例如我們有以下 Component：

```typescript title="index.tsx"
export default function TestComponent() {
  const [listOfData, setListOfData] = useState<string[]>([]);

  useEffect(() => {
    setListOfData(fetchData());
  }, []);

  return (
    <div>
      {listOfData.map((data) => {
        return <p key={data}>{data}</p>;
      })}
    </div>
  );
}
```

接著我們在旁撰寫測試檔案：

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';

import TestComponent from './index';

it('Does the component render correctly', () => {
  expect(renderer.create(<TestComponent />).toJSON()).toMatchSnapshot();
});
```

我們會發現，在 `index.tsx` 旁會產生一 `__snapshots__` 資料夾，內有 `index.test.tsx.snap`，內容是 `index.tsx` 的 Snapshot。我們可以看見其內容為一個空的 `div`、沒有任何 `p`。這是因為 `listOfData` 是在 Render 後才會拉取、而 Snapshot Test 只會比對可序列化的 HTML：

```typescript
// Jest Snapshot v1, https://goo.gl/fbAQLP

exports[`Does the component render correctly 1`] = `<div />`;
```

而一但我們更改 Snapshot Test 的 Component：

```typescript title="index.tsx"
export default function TestComponent() {
  const [listOfData, setListOfData] = useState<string[]>([]);

  useEffect(() => {
    setListOfData(fetchData());
  }, []);

  return (
    <div>
      <h1>Content</h1>
      {listOfData.map((data) => {
        return <p key={data}>{data}</p>;
      })}
    </div>
  );
}
```

Jest 便會報錯，因為前後的 Component HTML 並不一致：

![Jest Snapshot Error](/img/docs/jest-snapshot-error.png)

若要更新 Snapshots，可以在 CLI 中使用 `u`：

![Jest Snapshot Update](/img/docs/jest-snapshot-update.png)

:::caution 注意
`__snapshots__` 應進入 Git 控管，才能在每次測試時與前次比對。另外，就算對應的測試刪除、Snapshots 仍然會留存，並在下次出現同名稱的測試時提供比對。
:::

:::info 提醒
使用了 Redux React Connect 或 Hook 的元件在進行 Snapshot Test 時會出現 _Could not find react-redux context value_ 的錯誤，解決方法參考 [Redux](/docs/practice/redux)。
:::

## 價值

由上例可知，Snapshot Test 本身並不會有意義的斷言，唯一能夠提供的是告訴開發者 **這個 UI 與上次不同** (而且不會有 Render 週期)。可以預期的是，在 UI 頻繁更動時、使用到 Snapshot Test 的 Component 會大量報錯，變成無意義的測試。那什麼時候 Snapshot Test 是有價值的：

- 已定稿，該 Component 原則上不再改動
- 該 UI 相對重要 (必要元素或需提供特定介面)

根據以上概念，UI 必須盡可能單純化、拉出只根據 Props 顯示內容的 Component，避免無意義的測試 (如前段中沒有實際內容的 `TestComponent`)，像是：

```typescript title="index.tsx"
interface ITestComponent {
  listOfData: string[];
}

export default function TestComponent({ listOfData }: ITestComponent) {
  return (
    <div>
      {listOfData.map((data) => {
        return <p key={data}>{data}</p>;
      })}
    </div>
  );
}
```

如此一來，可以節省大量在 Mock Hooks 的時間，也能更清楚看見 Props 與 UI 間的關係。

:::info 提醒
若要進行視覺上像素為單位、實際 Render 出的畫面比對，可以參考 Visual Regression Testing。
:::
