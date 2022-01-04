---
sidebar_position: 4
---

# Principles

比起 End-to-End Test、Integration Test 等，Unit Test 專注在避免可拆分的主功能錯誤。而前端常見的，需要瀏覽器渲染或支援 (與 UI 耦合)、或者牽涉到視覺效果的部分，已經屬於 Integration Test 的範疇，不考慮在 Unit Test 中。

## F.I.R.S.T

當撰寫 Unit Test 時，應該盡可能遵循 F.I.R.S.T 原則：

- **Fast**: 不夠快速的測試不會有開發者想跑。除此之外，Unit Test 不依賴任何外部資源如網路、資料庫等，本就應為輕巧
- **Independent**: 簡而言之，即是不會動用到受測單元外的資源，諸如網路、資料庫、其他 DOC 等
- **Repeatable**: Unit Test 應該要可以在 **任何** 環境中輸出一樣的結果。若是一個 Unit 中有 2 個以上的情境，則應將其拆分。如此一來，Unit 會變得單一、獨立，即符合 Repeatable 的原則
- **Self-validating**: 測試的結果應清晰可見。這點在使用測試框架如 Jest 都已幫我們完成，可以很容易看見失敗的測試
- **Thorough**: 與 Repeatable 相關聯。撰寫測試時應同時考慮到 Happy Path 和 Negative Scenario。若是遵循 TDD 原則的開發者，則會是 **Timely**，即要即時撰寫測試

:::info 提醒
**TDD**，Test-driven development，簡單而言，是在實作之前先寫測試，確保專案符合驗收規格。**BDD**，Behavior-driven development，則是 TDD 的延伸，除了先寫測試外，還會產出一份以自然語言撰寫的規格書，讓非技術人員能夠參與討論、減少與工程師間的溝通成本，在測試中也可以看到故事性更強。
:::

### Others

一些同樣應該盡可能遵循的原則：

- **單位**: 單元測試中，最小的單元不是 Component，而是 Function、Method、Class。引入 Unit Test 到現有專案時，最首要做的就是將可預期輸入輸出、可獨立於 UI 之外的 Unit 單獨抽出
- **單一**: 一個測試案例，應只測試一個情境
- **無邏輯**: 測試單元內不會有商業邏輯、故事性。在 Unit Test 中，最重要、也只做的事，就是保證各個單元的預期運作
- **預期情境**: Unit Test 無法對無可預期的情境做測試，只有設想了輸入與輸出的情況才可實作
- **不依賴外部**: 不會動用到受測單元外的資源，諸如網路、資料庫、其他 DOC 等
- **無耦合**: 又稱 **隔離相依性**。遇到不同功能在同一單元時，應果斷拆分、讓單元只接受可預期的輸入，而不是直接放入其他單元
