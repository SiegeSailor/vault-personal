---
sidebar_position: 2
---

# Do Not

在進入 Unit Test 之前，有幾件事是不該做的：

### Test Library

常發生在使用到 `react-router-dom` 或 `redux` 的專案。通常而言，成熟的套件會有自己的測試，如果是使用上有需要驗證的地方如 Redux Action，也有對應的 `redux-mock-store` 來測試傳入的 Action 是否合預期 (而且並非必要)，不必花費時間在這上面。

我們要做的事，是把這些 Library 當作可信任、通過測試的 Method 來使用。例如在改變 Route 後是否對應的 Component 有 Render，或是當下 Store 內的使用者 State 在登入後有符合預期的資料。

### Full Test Coverage

除非產品有要求，否則應該以覆蓋重要功能為主要訴求。若是想覆蓋全部專案，最常見的情況是寫到追求數字、具體測試的內容完全沒意義，徒增測試時間與開發成本。有價值的測試，哪怕只有一個，都能大幅提高產品良率。
