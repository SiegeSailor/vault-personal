---
sidebar_position: 5
---

# Deadlock

當有 1 個以上的 Unit 在 Concurrency 的情況下使用共用資源時，並且符合以下 **所有** 4 點時，即可能會發生：

- **Mutual Exclusion**: 同時只允許 1 個 Unit 在 Critical Section 內
- **Hold and Wait**: Unit 在等待資源時，也持有資源，發生在 Unit 參與了多個 Lock 時且所有 Lock 可以同時被請求時
- **No Preemption**: 沒有上層的 OS 或 Monitor Program 控制，來中斷與分配資源
- **Circular Wait**: 系列 Unit 互相持有對方需要的資源，如 C 等待 B、B 等待 A，而 A 又等待 C (實際情況往往更複雜，並且難以判別)

:::info 提醒
時至今日，Deadlock 並沒有有效解，不是需要耗費額外資源，就是 Deadlock 會不斷發生並解決。
:::

:::info 提醒
Deadlock 可以被 **Dinning Philosophers Problem** 說明 (包含解決方法)，參考 [Wikipedia/哲學家就餐問題](https://zh.wikipedia.org/wiki/%E5%93%B2%E5%AD%A6%E5%AE%B6%E5%B0%B1%E9%A4%90%E9%97%AE%E9%A2%98)。
:::

## Resource Type

Deadlock 爭搶的資源有以下類型：

- **Reuseable**: 當任務完成，可被其他 Unit 再次使用，如 Memory、Device、Data Structure、Semaphore 等
- **Consumable**: 使用後即消失的資源，如 Interrupt、Signal、Message、I/O Buffer Data 等

## Solutions

要解決 Deadlock，可以由以下達成：

- [Prevention](/docs/6@computer/5@deadlock#prevention): 避免 Mutual Exclusion、Hold and Wait、No Preemption 和 Circular Wait 中其中一個情境
- [Avoidance](/docs/6@computer/5@deadlock#avoidance): 在每次分配資源時，檢查參與其中的 Unit 的狀態。如果該分配會造成 Deadlock，則取消或重新放入 Queue
- [Detection](/docs/6@computer/5@deadlock#detection): 偵測 Deadlock 的發生、並解決。然而 Deadlock 後發生的影響可能難以控制

### Prevention

Deadlock 的發生需要以下 4 個情境，而分別可以：

- **Mutual Exclusion**: 此點通常不可避免，因為 Mutex 是 Concurrency 的一種必要保護機制
- **Hold and Wait**: Unit 在擁有 Lock 的情況下、不可再請求另一個
- **No Preemption**: 當 Unit 請求 Lock 並被拒絕時，會釋放所有持有的資源。OS 可能可以介入強制釋放的行為
- **Circular Wait**: 編號 Unit，並且編號高者不可請求編號低者的資源，如 4 不可請求 1

### Avoidance

要實現，OS 必須在 Unit 請求資源完成前的一段時間、即被通知 (這點實現困難、導致 Avoidance 非常罕見)。當被通知時，OS 會執行 Banker's Algorithm、以提前偵測此次分配是否會導致 Deadlock。若是，則取消或重新放入 Queue

### Detection

認知到 Deadlock 會發生，並且週期性的執行 Detection Algorithm 來識別。當發生，執行以下其中之一：

- Rollback 到先前的 Unlocked State (需要 Transaction Log)
- Abort 所有 Deadlocked Unit
- Abort 其中一個 Unit，再次檢查是否有 Deadlock，重複直到沒有
- Preempt 分配資源、直到 Deadlock 不再發生

需要注意的是，除了 Rollback 到先前的 Unlocked State 外，所有動作都可能導致資料的污染或遺失。
