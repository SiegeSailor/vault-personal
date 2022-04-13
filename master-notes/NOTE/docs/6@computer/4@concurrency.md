---
sidebar_position: 4
---

# Concurrency

首先要注意的是，Concurrency 的概念可以用於 Process、Thread 等，並且有多種具體實現方法。簡單而言，Concurrency 是多個 Unit Queue 對上單一 Resource。要注意的是，Concurrency 是同時處理、而非同時執行多個任務，是透過在非常相近的時間執行不同任務的方式，讓人感覺在同時執行。

Concurrency 有以下問題：

- **Lost Updates**
- **Uncommitted Data**
- **Inconsistent Retrievals**

:::info 提醒
**Parallelism** 是多個 Unit Queue 對上多個 Resource，像是多個人完成多個相同的事，同樣的，Parallelism 也有多種實現方法如 Load Balancing。
:::

:::info 提醒
JavaScript 透過 Event Loop 實現 Concurrency Model，然而 JavaScript 本身並不是一個 Concurrent Programming Language，而是 Single Thread。
:::

## Asynchronous

又稱異步或非同步，一種任務型態，指 2 個以上的 Thread 在幾乎相同的時間同時執行，可以是 Program 主動、或發生於 Program 遭到暫停後、自動產生。與另一種任務型態 **Synchronous** 最大的差異是，**Asynchronous 在執行當下即回傳，並在結束後再通知結果，而 Synchronous 是在執行完成後才回傳結果**。也可以說，會 Blocking 的是 Synchronous，而會馬上返回、不造成 Blocking 的是 Asynchronous。

首先我們必須認知到，1 個 Thread 是由一系列有序 Instruction 組成的。先後順序對於單一 Thread 有重要性，但對 Processor 而言，Thread 的順序只是 State 的變化：

![Single Thread](/img/single-thread.png)

> 圖片來源：[Brave Clojure](https://www.braveclojure.com/concurrency/)

:::info 提醒
Thread 的 State 分為 **Ready**、**Running** 和 **Blocked**，可參考 [Computer/Operating System#State](/docs/6@computer/3@operating-system#state)。
:::

1 Processor 的情況。要注意的是，當中 Thread 的執行順序 (`A1, B1, B2, ...`) 是 **Nondeterministic**，也就是不可確定：

![Two Threads, One Processor](/img/two-threads-one-processor.png)

> 圖片來源：[Brave Clojure](https://www.braveclojure.com/concurrency/)

2 Processor 的情況。可以看見在圖中將 2 個 Thread 分配給了不同的 Processor，並同時執行。注意即使我們預期每個 Processor 只會執行 1 個 Thread，且 Processor 數量遠大於總 Thread 數，執行順序仍然是 **Nondeterministic**：

![Two Threads, Two Processor](/img/two-threads-two-processors.png)

> 圖片來源：[Brave Clojure](https://www.braveclojure.com/concurrency/)

## Critical Section

Concurrency 會導致資料被污染、不同步的一大原因，是來自於執行與回傳分為 2 個以上的步，也就是 **Double Update Problem** (或 **Missing Update Problem**)，若到執行後 Processor 移到另一 Thread 執行、又跳回，那麼原先的 Thread 會在並未收到第二個 Thread 的更新的情況下、使用舊的資料去回傳。要解決這一問題，其中一個辦法就是確保執行的動作是 **Atomic**，也就是將檢查與更新放在同一個 Instruction 內。

而需要確保 Atomic 的 Code，就是所謂的 Critical Section。

### Mutual Exclusion Rule

解決 Double Update Problem 的方法，主要有：

- 同時只允許 1 個 Thread 在 Critical Section 內
- 當下沒有 Thread 在 Critical Section 內時，應當無 Delay 直接進入
- Thread 佔用 Critical Section 的時間越短越好

:::info 提醒
**System Bus** (見 [/Computer/System Hardware#Definition](/docs/6@computer/1@system-hardware#definition)) 也在底層使用了 Mutual Exclusion Rule。同一個時間點，只能有 1 個 Processor 進入 Memory 並訪問 System Bus，其他 Processor 必須等待直到 Next Cycle。
:::

### Software Solution

Peterson's Algorithm 使用了 `flag` 的 Boolean Array 和 `turn` 的 Int 變數來在 Software 的層面上解決這個問題，並且可以套用在除了 System Bus 以外的所有地方。假設有 P0 與 P1，當 `flag[1]` 為 False 或 `turn` 為 0 時，則 P0 得到進入 Critical Section 的權限。

單只使用 `flag` 的 Boolean Array 是為 LockOne Algorithm，其中的盲區是當 2 個 Thread 同時開始尋找是否有其他 Thread 想進入 Critical Section 時，會發現 `flag[0]` 與 `flag[1]` 都為 `true`，導致無從辨識：

```cpp
bool flag[2] = {false, false};
int turn;

P0:      flag[0] = true;
P0_gate: turn = 1;
         while (flag[1] == true && turn == 1)
         {
            /** busy wait */
         }
         /** critical section */
         ...
         /** end of critical section */
         flag[0] = false;

P1:      flag[1] = true;
P1_gate: turn = 0;
         while (flag[0] == true && turn == 0)
         {
            /** busy wait */
         }
         /** critical section */
         ...
         /** end of critical section */
         flag[1] = false;
```

> 程式碼來源：[Wikipedia](https://en.wikipedia.org/wiki/Peterson%27s_algorithm)

### Hardware Solution

在硬體層面上，針對 CPU 有以下解決 Concurrency Double Update Problem 的方法：

- **Disable Interrupt**: 直接禁止 Interrupt，進而避免 Asynchronous。然而，當目前 Process Crash 時，只能全部重來 (因為無法 Interrupt 掉)
- **Test and Set**: Atomic Instruction。以 1 個 Boolean Flag 確認 Memory Location 並設為 1 (啟用中)。結果會返回 Success or Failure
- **Exchange**: 將 1 個 Boolean Flag 設置在 Register 中，並將其與 Main Memory **Swap**。其中一個益處是，即使 Interrupt 立即發生在 Exchange 後，也能回過頭去檢查設置好的 Boolean
