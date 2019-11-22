# Back-End Interview

## 面試題庫

搜集而來的 Back End 相關面試題目。

## Binary Search

```python
class Solution:
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        return -1
```

或。

```python
class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        N = len(nums)
        left, right = 0, N
        # [0, N)
        while left < right:
            mid = left + (right - left) / 2
            if nums[mid] == target:
                return mid
            elif nums[mid] > target:
                right = mid
            else:
                left = mid + 1
        return -1
```

## Process 在 Memory 內怎麼存放

可參考以下筆記。

{% page-ref page="../knowledge/back-end/process.md" %}

### Process 的構成

* Program Code \( Code Section \) :: 程式碼
* Process Counter :: 下一個要執行的 Process 的位置
* Processor Register \( Register Set \) :: 暫存器中的資料。暫存器為小又能快速取用 CPU 資源的元件
* Stack Space :: 儲存暫時性資料
* 變數 :: Dynamically Allocated Variables 和 Non-Thread-Local Global Variables
* Data Section \( Thread 間的 Shared Data \) :: Program 和 Process 的相關資料
* Memory Cache \( Heap \) :: 執行時產生的動態記憶體空間

### Process 的關係

* Process :: 即執行中的 Program，一個 Program 可以同時有很多 Process，Process 間不可互相存取 Memory，但仍然有如 IPC 一類互相溝通資料的方法。一個 Process 是指 Thread 們的容器，默認時，Java 是單一 Thread，而 Python 是 Multi-Thread
* Thread :: 可以稱為輕量級的 Process，一樣有自己的 Stack Space 與 Memory Cache \( Heap \)、Program Counter、Processor Register \( Register Set \)，但同 Process 間的 Thread 能共享變數與 Shared Data \( Data Section \)、Program Code \( Code Section \)、OS Resources \( 如開啟檔案等 \)，一旦 Thread 讀取到 Shared Data \( Data Section \)，便會將其儲存在自己的 Memory Cache \( Heap \)

## Parallel 與 Concurrent 的差別

* Concurrency :: 在一個核心內給不同的 Thread 分配時間與資源，讓使用者感覺同時處理、而實際上是由於運算資源有做時間分配，讓真正有需要用到的 Thread 在需要時才佔用核心。
* Parallelism :: 產生新的 Process，使他們被系統分配、同時在不同的 CPU Core 中執行。Multi-Processing 與原先 Process 分隔的不同的部份是，變數是共用的、但由於 Process 是新的，Memory 也是新的，因此 Global 變數的改變並不會影響到不同 Process。Multi-Processing 在 Parent Process 和 Child Process 中有 Shared Memory。

## TCP/HTTP Keep-Alive

在建立起 TCP 連結後，同時產生一組計時器，當中有一些是負責 Keep-Alive 的，而每當歸零時，便會向對端發送一個無數據的探測包 Probe Packet，同時打開 ACK 標誌。如果我們沒有收到對端的 Keep-Alive Probe Packet ACK 的話，便能判定為連結已斷開，進而採取行動。

Keep-Alive 只會產生一些額外的探測包，由於裡面無數據，對連結並無顯著負擔。

### 使用目的

在已經通過 Three-Way Handshake 建立的 TCP 連線中，若果其中一端因為網路或電源問題忽然關閉，TCP 連結不會自動斷開。若無 Keep-Alive，則只會在斷掉端重啟後、繼續傳送封包時，因為原先的 Socket 關閉，接收端回應 RST 要求傳送端關閉對端並不需要回覆 ACK 之後，傳送端才會自動關閉連結。

若有 Keep-Alive，則能更正確並且更即時的斷開連結。

另一個情況是因為在使用 NAT 或防火牆時，因為物理限制連線數是有限制的，而此時會自動關閉舊的或不活動的連結、以優先使用當前連結。Keep-Alive 可以避免被判定為不活動連結、進而避免被自動關閉。

另外，Keep-Alive 可以將短連結變為長連結，即當建立起 TCP 連結後，在 Keep-Alive 的持續時間內，都可以透過此 TCP 傳輸資料、而不需要不斷地斷開再建立連結。然而，若只是啟用 Keep-Alive，則每次請求仍會帶有 Header `Connection: Keep-Alive`、佔用頻寬。

{% hint style="info" %}
請參考或許未必不過，[TCP/HTTP Keep-Alive 詳解](https://www.zhuxiaodong.net/2018/tcp-http-keepalive/)。
{% endhint %}

## HTTPS 原理

HTTPS 和 HTTP 協議相比提供了。

* 資料完整性 :: 內容傳輸經過完整性校驗
* 資料隱私性 :: 內容經過對稱加密，每個連線生成一個唯一的加密金鑰
* 身份認證 :: 第三方無法偽造服務端 \( 客戶端 \) 身份

HTTPS 即是使用了 SSL 的 HTTP。SSL 目前版本是 3.0，之後升級為了 `TLS Transport Layer Security` 協議，TLS 目前為 1.2 版本。如未特別說明，SSL 與 TLS 均指同一協議。

HTTPS 要保證客戶端與服務器端的通信安全，必須使用對稱加密算法，但是協商對稱加密算法的過程 Handshake，需要使用非對稱加密算法來保證安全，然而直接使用非對稱加密的過程本身也不安全，會有中間人篡改公鑰的可能性，所以客戶端與服務器不直接使用公鑰，而是使用數字證書簽發機構頒發的證書來保證非對稱加密過程本身的安全。這樣通過這些機制協商出一個對稱加密算法，就此雙方使用該算法進行加密解密。從而解決了客戶端與服務器端之間的通信安全問題。

### HTTPS 使用的演算法

* 非對稱加密演算法 :: RSA，DSA / DSS
* 對稱加密演算法 :: AES，RC4，3DES
* HASH 演算法 :: MD5，SHA1，SHA256

其中非對稱加密演算法用於在 Handshake 過程中加密生成的密碼，對稱加密演算法用於對真正傳輸的資料進行加密，而 HASH 演算法用於驗證資料的完整性。由於瀏覽器生成的密碼是整個資料加密的關鍵，因此在傳輸的時候使用了非對稱加密演算法對其加密。非對稱加密演算法會生成公鑰和私鑰，公鑰只能用於加密資料，因此可以隨意傳輸，而網站的私鑰用於對資料進行解密，所以網站都會非常小心的保管自己的私鑰，防止洩漏。TLS Handshake 過程中如果有任何錯誤，都會使加密連線斷開，從而阻止了隱私資訊的傳輸。

## Three-Way Handshake

建立 TCP 連結的動作。TCP/IP 的第四層傳輸層。

1. 傳送端發出 SYN，與 SEQ
2. 接收端發出 SYN 與 ACK，與 SEQ
3. 傳送端回傳 ACK，與 SEQ

ACK 為對方的 SEQ 加上 Segment Length，然而在建立 TCP 連線時，通常還沒有 Segment，因此才常見為 SEQ 加 1。若是有繼續用同樣的連結請求，則必須加上 Segment Length。

* SYN :: 如果有設置，才發出連線請求，用來同步序列號。即 Synchronize Sequence Numbers
* ACK :: 如果有設置，使確認號欄位有效。即 Acknowledgment Field Significant

## Four-Way Handshake

用於關閉 TCP 連結的動作。TCP/IP 的第四層傳輸層。

1. 欲關閉端發出 FIN 為 1 的包，表示沒有數據可以發送，但仍可以接收。進入 `FIN_WAIT_1` 狀態
2. 另一端回發 ACK，確認收到關閉請求，但還沒準備關閉，進入 `CLOSE_WAIT` 狀態。欲關閉端收到後進入 `FIN_WAIT_2` 狀態
3. 另一端準備好關閉連結後，向欲關閉端發送 FIN 為 1 的包，並進入 `LAST_ACK` 狀態，等待欲關閉端的最後一個 ACK
4. 欲關閉端發送一個確認包，並進入 TIME\_WAIT 狀態，等待可能出現要求重傳的 ACK

另一端接收到最後的確認包後即會斷開連結，進入 `CLOSED` 狀態，而欲關閉端會在等待 2Maximum Segment Lifetime 後跟著斷開連結。

## HTTP 與 WebSocket 與 Socket

TCP/IP 的第七層應用層。三者差異主要表現在實時通訊上。

### HTTP

不斷發送 Request，但這種方式會因為 Header 而浪費頻寬，更會因為要維持通訊不斷發送無資訊的 Request、造成 Server CPU 佔用。即使啟用 Keep-Alive，每次請求仍會帶有 Header `Connection: Keep-Alive`、佔用頻寬。

### WebSocket

在透過 Three-Way Handshake 後，則可以直接透過 TCP 傳輸、不透過 HTTP，因此不會因為 Header 造成頻寬浪費。在 JavaScript 中，可以透過建立 WebSocket 物件並選擇性地指定 Protocol 如 SOAP 或 WAMP來建立 WebSocket。

### Socket

Socket 其實位於第五層會話層。是一個完整的應用層協議，代表一個實例主機上的一個應用程序的唯一通信線路。

## HTTP 2

2015 年發布。在 HTTP 1.1 時，因為每個 Request 都必須佔用一個 TCP Connection，因此連線速度有限，而且同時最高六個。

而在 HTTP 2，一個 TCP Connection 即可連結，節省 HTTP 1.1 不斷建立耗費的時間，並且因為 Multiplexing，可以併發處理 Request 和 Response，將它們都拆為 Frame 來處理，因此不會有 TCP 佔用的情形。

同時，可以決定哪些檔案要優先被下載，以及使用 HPACK 壓縮 Header、單一使用 BInary 封包結構節省解析時間，更重要的是可以使用 Server Push，達到不用 Request 由 Server 主動發送訊息、甚至可以達到 1 Request Mulit Response。

Keep-Alive 是默認開啟的。在 HTTP 1.0 中，需要在 Request 的 Header 中指定 `Connection: Keep-Alive`。HTTP 1.1 也默認帶有 Keep-Alive 的 Header。

## PostgreSQL vs MySQL

PostgreSQL 能處理更多類型、更穩定、但少人用，MySQL 多人用但不穩定、免費版本功能陽春、但社群廣。兩者效能沒有明顯差距。

## Google Protobuf

一種檔案格式。優勢是一但定義檔案結構，就能在多種語言上使用，而不用像 JSON 一樣每次轉換時都需要重新定義。

## TCP 該如何做流量控制

接收端有接收緩衝區 Receiving Buffers，將來不及處理的資料先做緩存。而發送端有發送緩衝區 Sending Buffers，當接收端回覆確認收到時才將 Sending Buffers 內的資料清除。

TCP Flow Control 由接收端控制，最主要是由接收端告訴發送端，目前能接收多少資料，每次 Feedback 都會告知傳送端目前的 RWND 接收視窗有多少、即 Sliding Window。若接收緩衝區 Receiving Buffers 滿載 \( 即 RWND = 0 \)，則只能棄置資料，這也是為甚麼有 Retransmission 的機制。

## Ping 某個 Protocol 或是 Port

Ping 使用 ICMP Internet Control Message Protocol，位於第三層，然而 TCP 的 Port 位於上一層，兩者階層不同，有 Ping 的階層內沒有 Port 的定義，因此不能直接使用。

但可以用以下方法。

#### PaPing

```text
paping www.google.com -p 80 -c 4
```

#### NetCat

```text
nc -vz google.com 80
```

#### HPing

```text
hping google.com -S -V -p 80
```

#### NMAP

```text
nmap -p 80 google.com
```

Teinet

```text
benteki-air:~ ben$ telnet www.google.com 23
```

## 防火牆擋住 Telnet

Telnet 是一能遠端登入主機的 CLI 文字介面軟體，使用者首先在電腦執行 Telnet 程式，連線至目的地伺服器，然後輸入帳號和密碼以驗證身分。使用者可以在本地主機輸入命令，然後讓已連接的遠端主機執行，就像直接在對方的控制台上輸入一樣。傳統 Telnet 會話所傳輸的資料並未加密，帳號和密碼等敏感資料容易會被竊聽，因此很多伺服器都會封鎖 Telnet 服務，改用更安全的 SSH。

若防火牆擋住 Telnet，則可以。

* 開啟專用 Port
* 允許 Telnet
* 關閉防火牆

