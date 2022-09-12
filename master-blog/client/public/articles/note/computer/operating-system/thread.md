可以稱為輕量級的 Process，一樣有自己的 Stack Space 與 Memory Cache、Program Counter、Processor Register 等。同 Process 間的 Thread 能共享 Variables 和 Data Section、Program Code、OS Resources ( 如開啟檔案等 )，一旦 Thread 讀取到 Data Section，便會將其儲存在自己的 Memory Cache。

為什麼需要 Multithreading？有以下原因：

- **Foreground/Background**: 如 Microsoft Word，將文法檢查、輸入文字、讀取檔案分在不同 Thread
- **Asynchronous/Synchronous Processing**: 當不可為 Asynchronous Thread 時，分成多個 Synchronous Thread 可以避免一個 Blocked 導致全部等待
- **Infrequent Tasks**: 如每 10 分鐘自動儲存檔案
- **Speed Reading**: 在一個 Thread 讀取檔案同時，另一個 Thread 已開始解析讀入的部分
- **Modular Program Structure**: 將龐大的程式分為多個小任務的 Thread

### State

與 Process 的 States 比較，會發現 Thread 只有 **Ready**、**Running** 和 **Blocked** 3 種 State，這是因爲當 Thread 建立起 Thread Control Block、TCB 時，Code 已經 Load 好了、不具有 **New**。而當 Thread 結束時，只需要被標注結束、而不需要向 Process 回傳值 (除非是最後一個 Thread)，因此不需要 **Exit**。

:::info 提醒
**Suspension** 屬於 Process Level。當一個 Process Suspended，所有 Thread 都會停止。
:::
