macOS 與 Linux 中稱為 Swap Space，在記憶體使用 Paging/Segmentation 的情況發生。由於 Process 一次只會處理當下的 Page/Segment，因此，其他部分並不需要放在 Main Memory 中。Virtual Memory 會將不需要的部分 Copy、放在 Secondary Memory 中，當有需要時才取得一份到 Main Memory，並在結束後即移除。這使使用了 Virtual Memory 的系統有更多的記憶體空間能使用 (暫時不被需要的部分都被移出)，進而有更多 Process 能被執行、使效能提高。

Copy 的動作涉及 **Copy-on-Write**，COW，也與 Page 有關。首先，Page 是可分享的，如 Chrome 開啟 5 個分頁、產生 5 個 Process，那他們之間的 Page 中的 **Code Page** (在 PMT 中) 的索引會指向同一個 (OS 能辨認是否執行的 Code 相同)。而如果有多個呼叫者同時要求相同資源 (**Data Page**，如記憶體或磁碟上的資料儲存)，則他們會共同取得相同的 Reference 指向相同的資源，並且該 Page 所屬的 PMT 會增加一 Read Only 屬性、將該 Page 設為 Read Only。直到某個呼叫者試圖修改資源的內容時，系統才會真正複製一份專用副本給該呼叫者、將該副本取消設置 Read Only，而其他呼叫者所指向的 Data Page 仍然保持不變。

Virtual Memory 有以下組成：

- **Resident Set**: Process 中當下在 Main Memory 的部分
- **Working Set**: Process 使用中的部分，而真正要使 Process 能 Run Working Set 的部分，必須先移到 Resident Set 中
