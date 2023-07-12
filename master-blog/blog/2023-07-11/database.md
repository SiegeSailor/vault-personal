ETL, ELT
也許只有 Oracle，因為 Oracle 在尾端的 Null 不寫入硬碟
Primary key columns first
Foreign key columns next.
Frequently searched columns next
Frequently updated columns later
Nullable columns last.
Least used nullable columns after more frequently used nullable columns
但在關聯式資料庫中，欄位順序不該有影響
一階邏輯
NULL 與排中論
1NF, 2NF, 3NF，通常到 3NF 就過多了
1NF: 欄位內無重複，無重複欄位，有唯一識別碼
2NF: 所有資料要和候選鍵、主鍵(聯合主見)有完全依賴 (完全相關) 關係，如果資料沒有完全依賴，則最好把這些資料存到另一張表，並且使用 forigen key 對應過去
3NF: 所有非主鍵的資料都不應該互相相關，也就是全部資料都是以主鍵為考量的
超鍵: 能唯一識別資料的單個或多個鍵組合
候選鍵: 除了符合超鍵定義外，若是一個組合，則去掉任何一個值都會使他不再單一，也就是選擇最少的欄位情況下實現了唯一識別
主鍵: 候選鍵 (組合)的其中一個，且只能有一個。不可 Null、不可改變、通常越沒意義、越短、越少越好
外來鍵: 在此表中用來關聯別張表的值，不一定要是別張表的 PK，但最好是，或至少是唯一 key (Super Key?)
ACID 保證 transaction 事務的特性:
Atomicity: 不會結束在一半，若中途錯誤，則資料會 rollback 到開始前
consistency: 資料的完整性在事務開始前與後都沒有被破壞
isolation: 資料庫如何允許多個併發事務同時對資料進行讀寫與修改的能力，分為四個層級：Read uncommited, Read committed, Repeatable read, Serializable

-   Read uncommited: A transaction 提交前 Ｂ不能更新只能讀取
-   Read commited: A transation 提交並確認前 B 不能更新只能讀取
    durability: 事務結束後資料修改就是永久的，即使機器故障也不會丟失
    distributed database 可以是一個資料中心，或透過網路散佈在四方的集合，為了效能，通常允許終端使用者在多個機器上使用 transation。
    distributed database 會使用特定軟體同步資料或指定一台機器將資料慢慢複製到其他個體上的方式 (有多種如 local autonomy, asynchrounous...) 來確保 data security, consistency, integrity。
    distributed query 指執行同時在多個 tabls/rows 上的 select/insert/update/delete
    Data Consistency: Strong type, data quliity
    Data Integrity: Usable when data is from a backup over a period of time
    RDBMS 中要 vertical scale 的話可透過升級 CPU、加大 RAM
    要 horizontal scale 的話得透過 partitioning:
-   horizontal partitioning (sharding):
    透過 hash 或特定欄位的值做 range 分隔，如 user 分為 user_age_30_50, user_age_51_60，schema 會一樣。分隔到不同的資料庫。sharding 時可以把在線的某些 shard 做 read-only。做完後，route 那邊也得作分流，可以寫在水平擴展也可以寫在 API 層。
    sharding 的邏輯是一開始就寫好的，不會動態增加資料庫和機器數量，但其他服務的機器可以動態增加，如 API。
    hotspot
-   vertical partitioning:
    將一張表分為兩張以上，較常使用的欄位放一張，不常使用的放其他，減少拿一張表的速度。PK 通常保持一樣。
-   functional partitioning
    將讀寫和僅讀的資料分為不同表
    RDBMS 的優點在於資料格式嚴謹、直接支援 ACID、可使用 SQL 執行複雜查詢
    NOSQL 的優點在於資料格式靈活、適合開發初期或需要頻繁變動時，也是和儲存大量資料，很容易做 horizontal scaling，本身就是分散式。在反正規化時，查詢速度通常比 RDBMS 快。有些有支援 SQL。ACID 可透過實作 2PC 達到
    SQL 通常是反正規化，也就是不用 id (key) 去對應而是全部存進去，但也有混合的，也就是比較常需要同步的再用 id 去對
    Idempotence: 做幾次結果都一樣 (PUT, DELETE，然而 Delete 的 stauts code 會由 200, 204 > 404, GET 也是、如果沒更動)
    2PL (2-phase locking): 保證 isolation Serializable 的層級。處理 race condition。是個概念，有關執行順序，有 shared lock (read) 跟 exclusive lock (write)，關鍵是一但釋放了持有的任何一個鎖，就不能再獲得任何鎖。
    > 可能導致 deadlock (兩方都在等待對方釋放資源以執行，但沒有人釋放，須符合 1. 不能搶資源、2. 可同時持有與等待、3. 資源只能同時給一個用、4.一系列行程互相持有對方需要的資源 共四個才會產生)。livelock 是邊釋放又邊搶佔
    > 2PC (2-phase commit): 1) 向所有參與者詢問是否參與 transaction，並將 undo 與 redo 寫入日誌。2) 根據是否全部同意或任一不同意，進行操作。執行過程中全部節點皆為阻塞狀態。
