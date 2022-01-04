---
sidebar_position: 3
---

# 3A Pattern

Unit Test 中，無論對何種 Unit 做哪個 Test Case，都有必須遵循的三個步驟，分別是：

1. **Arrange**: 設置測試所需，諸如初始化物件、準備預期輸入與輸出等
2. **Act**: 執行與運作 Unit
3. **Assert**: 驗證是否實際輸出符合預期輸出

我們以下列的 `flatten` 做示範。此 Function 將輸入的 Object 變為只有一層的結構 (無論原本有多少層)：

```typescript
function flatten(
  object: object,
  parent?: string,
  result: { [key in string | number]: object } = {}
) {
  for (const key in object) {
    const parentName = parent ? parent + "." + key : key;
    if (typeof object[key] == "object") {
      handleCalculate.flatten(object[key], parentName, result);
    } else {
      result[parentName] = object[key];
    }
  }
  return result;
}
```

以 Jest 示範 3A Pattern：

```typescript
describe("flatten", () => {
  it("Flatting a single value object to a key-value with commas", () => {
    /** Arrange: 準備預期輸入 `input` 與預期輸出 `output` */
    const input = { first: { second: { third: { value: 1 } } } };
    const output = { "first.second.third.value": 1 };
    /** Act: 執行 Function */
    const result = handleCalculate.flatten(input);
    /** Assert: 驗證 `result` 符合預期輸出 `output` */
    expect(result).toEqual(output);
  });
});
```

## Test Cases

以下為其他測試。實務上並不是所有工程師都會對經手的程式碼寫文件，這時若有已經存在的測試檔案，可以更快地理解這些程式碼的作用：

```typescript
describe("flatten", () => {
  it("Flatting a single value object to a key-value with commas", () => {
    expect(
      handleCalculate.flatten({ first: { second: { third: { value: 1 } } } })
    ).toEqual({ "first.second.third.value": 1 });
  });

  it("Flatting an object with multiple keys to key-values with commas", () => {
    expect(
      handleCalculate.flatten({
        first: { second: { third: { value: 1 } } },
        one: { two: { three: { value: 2 } } },
      })
    ).toEqual({
      "first.second.third.value": 1,
      "one.two.three.value": 2,
    });
  });

  it("Flatting a single value array to a key-value with commas", () => {
    expect(
      handleCalculate.flatten([{ first: { second: { third: { value: 1 } } } }])
    ).toEqual({ "0.first.second.third.value": 1 });
  });

  it("Flatting an array with multiple keys to key-values with commas", () => {
    expect(
      handleCalculate.flatten([
        { first: { second: { third: { value: 1 } } } },
        { one: { two: { three: { value: 2 } } } },
      ])
    ).toEqual({
      "0.first.second.third.value": 1,
      "1.one.two.three.value": 2,
    });
  });

  it("Does the result only have one layer", () => {
    expect(
      Object.values(
        handleCalculate.flatten({
          object: {
            father: { age: 18, weight: 75, height: 180 },
            mother: { age: 18, weight: 45, height: 160 },
          },
          array: [{ kid: { pet: null } }],
        })
      ).some((value) => typeof value === "object")
    ).toBeFalsy();
  });
});
```
