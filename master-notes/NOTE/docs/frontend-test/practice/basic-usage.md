---
sidebar_position: 4
---

# Basic Usage

測試時主要使用 Jest，詳情見 [Jest](https://jestjs.io/docs/getting-started)。這邊以 `handleTime`，一個對時間格式化的 Function 進行說明：

```typescript title="format.ts"
export const handleTime = {
  timeZoneOffset: new Date().getTimezoneOffset() * 60000,
  mileSecondsOneDay: 86400000,
  now: function () {
    return Date.now();
  },
  date: function (timestamp?: number) {
    return new Date((timestamp || this.now()) - this.timeZoneOffset)
      .toISOString()
      .slice(0, 10);
  },
  time: function (timestamp?: number) {
    return new Date((timestamp || this.now()) - this.timeZoneOffset)
      .toISOString()
      .slice(11, 16);
  },
  full: function (timestamp?: number) {
    return `${this.date(timestamp)} ${this.time(timestamp)}`;
  },
};
```

## Describe and Test

- `describe`: 將相關的 `test` 放在一區，便於組織與報告
- `test`: 即 Test Case，在裡面使用 Matcher 驗證執行結果是否符合預期

我們將設想的 Test Cases 以 `describe` 和 `test` 組織起來：

```typescript title="format.test.ts"
import { handleTime } from "./format";

describe("handleTime", () => {
  describe("now", () => {
    it("Does now generates timestamp in milliseconds", () => {});
    it("Timestamp now should be greater than yesterday", () => {});
  });
});
```

## Matcher

- `expect`: 即一個 Assertion。Jest 會追蹤所有 `expect` 的結果
- `toBe`: 斷言式，從語意可知為使用 === 做判斷。其他有 `toEqual`、`toBeFalsy`、`toMatch` 等

接著使用以上 Matcher 來檢驗 `handleTime.now`：

```typescript title="format.test.ts"
import { handleTime } from "./format";

describe("handleTime", () => {
  describe("now", () => {
    it("Does now generates timestamp in milliseconds", () => {
      expect(String(handleTime.now()).length).toBe(
        String(new Date().getTime()).length
      );
    });

    it("Timestamp now should be greater than yesterday", () => {
      expect(handleTime.now()).toBeGreaterThan(
        new Date().getTime() - handleTime.mileSecondsOneDay
      );
    });
  });
});
```

## Command Line

只要有 Matcher 和 Test Cases 即可設置基本的測試。我們在此執行 `npm run test` (如何設置請見 [Config](/docs/practice/config)) 來檢驗結果：

![Test Result](/img/docs/jest-result.png)

### Coverage

加上 `--coverage` 查看覆蓋率，可以發現目前僅有 40%：

- **Stmts (Statement)**: 語法單位，也就是一段 Code
- **Branch**: 情境，例如一個 `if else` 中會有 2 個情境
- **Funcs (Function)**: 即指 Function
- **Lines**: 以一行為單位，基本上執行時 Lines >= Statement
- **Uncovered Lines**: 與 Lines 相反，表示未被測試到的行數

![Test Coverage](/img/docs/jest-coverage.png)

將其他的 Function 也加上 Test：

```typescript title="format.test.ts"
import { handleTime } from "./format";

describe("handleTime", () => {
  describe("now", () => {
    it("Does now generates timestamp in milliseconds", () => {
      expect(String(handleTime.now()).length).toBe(
        String(new Date().getTime()).length
      );
    });

    it("Timestamp now should be greater than yesterday", () => {
      expect(handleTime.now()).toBeGreaterThan(
        new Date().getTime() - handleTime.mileSecondsOneDay
      );
    });
  });

  describe("date", () => {
    it("Does date format as YYYY-MM-DD", () => {
      expect(handleTime.date()).toMatch(
        /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/
      );
    });

    it("Formatting timestamp to 2021-12-12", () => {
      expect(handleTime.date(1639267200000)).toBe("2021-12-12");
    });

    it("Formatting timestamp to 2021-12-20", () => {
      expect(handleTime.date(1640163198853)).toBe("2021-12-22");
    });
  });

  describe("time", () => {
    it("Does time format as HH:MM", () => {
      expect(handleTime.time()).toMatch(/^\d{2}:\d{2}$/);
    });

    it("Formatting timestamp to 16:53", () => {
      expect(handleTime.time(1640163198853)).toBe("16:53");
    });
  });

  describe("full", () => {
    it("Does full format as YYYY-MM-DD HH:MM", () => {
      expect(handleTime.full()).toMatch(
        /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])\s\d{2}:\d{2}$/
      );
    });

    it("Formatting timestamp to 2021-12-22 16:53", () => {
      expect(handleTime.full(1640163198853)).toBe("2021-12-22 16:53");
    });
  });
});
```

由於 `format.ts ` 中只有 `handleTime`，可以看見在加上所有 Function 的測試後覆蓋率達到了 100%：

![Test Coverage Full](/img/docs/jest-coverage-full.png)
