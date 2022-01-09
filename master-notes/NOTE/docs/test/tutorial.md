---
sidebar_position: 1
---

# Tutorial

測試可以分成單元測試 (Unit Test)、整合測試 (Integration Test) 和 端對端測試 (End-to-End Test)。這三個的區隔壁壘分明，但無論是哪一種，目的都是透過 **預期結果** 去和 **真實結果** 做比對。因此，在著手撰寫測試之前，要先能預期自己的程式是如何運作的。

這三類測試的關係可以參考由 Testing Pyramid 來說明：

![Testing Pyramid](/img/testing-pyramid.png)

> 圖片來源：[Method Poet](https://methodpoet.com/testing-pyramid/)

在理解這段關係前，我們要先懂得圖中 **White Box** 和 **Black Box** 的意思。首先，有兩個相似的詞：**Black Box Users** 和 **White Box Users**。大多使用者都是 Black Box User，也就是在購買電腦、冰箱等電子產品時，只在乎它夠不夠冰、怎麼開機，至於機器是如何達到這些功能的，一點都不在乎；White Box User 則相反，除了對內部結構感到興趣外，甚至還會主動拆解零件。而顧名思義：

- **Black Box Testing**: 注重受測元件 (SUT) 的介面與功能規格，以及怎麼樣的 Input 會得到怎麼樣的 Out，但不在乎如何完成。會使用到 Test Double 作為預期輸入。就測試案例設計 (Test Case Design) 而言，可重複使用性很高，並且可以預先設計 Test Case
- **White Box Testing**: 注重故事性與設計意圖 (Design Intention)，比起過程更在乎結果，並且會主動操控 Input 來更有效率的得到 Output 或狀態

:::info 提醒
**SUT**，System Under Test/Software Under Test，待測程式。**DOC**，Depended-on-Component，相依元件。SUT 執行時會使用到的元件，即在功能上兩個元件有因果關係。但仍是分隔成不同 Unit 測試。

**Test Double**，Dummy, Fake, Stub, Mock, Spy 等。可以理解為假的 DOC。
:::

#### 層級

- **Unit Test**: 最底層，White Box。通常實現在 Functions、Methods 和 Classes，專注單一事件。不仰賴任何外部資源
- **Integration Test**: 中間層，Black Box。測試不同的產品、服務整合的結果，而不是程式碼本身，如使用 Mock Data 假設 API 回傳的資料並呈現在 DOM 上等
- **End-to-End Test**: 最高層，Black Box，模擬真實使用者的操作，可以被定義為多層的 Integration Test

通常而言，高層的測試應該比底層的測試少。

#### 比例

1. **距離**: 理想上，測試應要在越靠近 Root Cause 的地方捕捉到錯誤，而 **Unit Test** 是第一道防線。錯誤像是 Formatting Errors、Calculation Blunders 和 Null Pointers 能夠輕易在 Unit Test 中被發現但很難在 Integration Test 和 End-to-End Test 中找到
2. **時間**: Unit Test 非常快，而 End-to-End Test 非常慢。試想一個 Unit Test 花費 ~1 Millisecond、一個 Integration Test 花費 ~1 Second，而一個 End-to-End Test 花費 ~1 Minute。若是有上百上千個測試，會耗費過量的時間在 Test 上，這在 **Continuos Integration** 中是不允許的
3. **成本**: 撰寫越高層的測試會需要越高的成本，除了需要覆蓋的範圍更廣外，也會需要額外的依賴與工具

在 Testing Pyramid 中，測試的層級每上升一層，風險就更上一級。毫無疑問降低風險的做法是將層級下降，然而更高層級的測試的回報更高。

:::caution 注意
Testing Pyramid 應為一個 Guideline 而不是規則，而且永遠不該設下在每次層級應該撰寫的測試數，否則團隊可能會略過有價值的 End-to-End Test 或是寫了多個無用的 Unit Test。
:::
