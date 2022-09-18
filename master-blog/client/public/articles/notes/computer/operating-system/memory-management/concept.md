Memory 是永遠不可能足夠的。OS 必須藉由在 Main Memory、Secondary 中移動資料、分配記憶體來達到 Program 的需求。達成 Memory Management 需要以下：

- **Relocation**: Process 應能被 Load 在 Main Memory 的任意位置，且能在該 Process 的 Lifetime 後被移除
- **Protection**: Process 只能訪問自己被分配到的記憶體，而該檢查為 Dynamic (使用當下才分配並檢查)，原因是 OS 無法提前訪問記憶體
- **Sharing**: 有些 Process 可能使用共通的 Code 或者 Data。
- **Logical Organization**: 使用共通的 Modules、Objects、Dynamic Linked Liberties，若多個 Process 使用相同物件，能夠只 Load 一次即可
- **Physical Organization**: 將 Logical Memory Map 到 Physical Memory。此段由 Hardware 負責，最簡單的方法是在每個 Progress 的 Logical Memory 前加上 Offset。Process 只知道分配到的 Logical Memory，因爲 Process 並不知道 Physical Memory 的分配情況

Memory management determines what is in memory and when Optimizing CPU utilization and computer response to users

Memory management activities:

- Keeping track of which parts of memory are currently being used and by whom
- Deciding which processes (or parts thereof) and data to move into and out of memory
- Allocating and deallocating memory space as needed
