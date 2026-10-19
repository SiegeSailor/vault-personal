# Front-End Interview

## 面試題庫

實際面試而來的 Front End 相關面試題目。

## 演算法

嘿，前端工程師也得會資料結構和演算法。

### 陣列比較

```javascript
// 找出 next 與 prev 間不同的值。

let prev = ["a", "b", "c", "d", "a"];
let next = ["a", "b","c"];
```

### 取中間值

```javascript
// 輸入為 String，回傳該 String 中間的字串。奇數為 1，偶數為 2。

let inputEven = "text"; // 應回傳 "ex"。
let inputOdd = "texting"; // 應回傳 "t"。
```

### 牌組勝負

```javascript
// 輸入為一個 String 的 Condition，以及兩個數字 Array 牌組。
// Andrea 與 Marria 兩人各有一套牌組，牌組數量一樣。在條件確認後，兩人應就每張牌做比對，
// 最後將每個以自己的牌扣除對方的牌的分數加總做比較。

let inputCondiction = "Even";
// Even 為雙方拿掉牌組的第二張牌，Odd 為雙方拿掉牌組的第一張牌。

let andreaDeck = [1, 10, 5, 2];
let marriaDeck = [3, 10, 2, 3];

// Andrea 分數：(1-3) + (10-10) + (5-2) + (2-3) = 0;
// Marria 分數：(3-1) + (10-10) + (2-5) + (3-2) = 0;

let answer = "Tie"; // 或 Andrea 或 Marria。
```

### 經過的最多階層

```javascript
// 輸入為一個 Number 的階層數和走的路徑的 Array。如階層數為 10，那麼代表有 1-10 層。

let inputLevels = 10;
// 即有 1 2 3 4 5 6 7 8 9 10 層。

let inputArray = [1, 5, 10, 3];
// 代表由 1 走到 5，再由 5 走到 10，以此類推。

// 表格化類似於此：
// 　　層數 1 2 3 4 5 6 7 8 9 10
// 順序
//   1　　 - - - - -
//   2　　         - - - - - -
//   3　　     - - - - - - - -

 // - 代表有經過過的階層，而從這可以得知，5 經過最多次的經層，答案即為 5。 
```

## SQL 語法

試想有兩張 Table，範例僅列出前三項，實則有數千。根據此兩張 Table 的 Id，印出同時有 Id, Name, Surname 的資料。

#### Name

| Id | Name |
| :--- | :--- |
| 1 | Zhang |
| 2 | Raymond |
| 3 | Wong |

#### Surname

| Id | Name |
| :--- | :--- |
| 1 | Ken |
| 3 | Adam |
| 4 | Adam |

## Browser 輸入網址後的行為

當使用者在瀏覽器網址列輸入網址並搜尋後，具體會發生的事。

### 瀏覽器相關

當使用者在瀏覽器網址列輸入網址並送出後，Browser Process 的 UI Thread 接收到使用者輸入的資料，UI Thread 會要求發出網路請求，此時 Network Thread 開始執行 DNS lookup、建立連線，而當連線成功、資料傳輸完畢後，Network Thread 通知 UI Thread 資料已準備好，Browser Process 利用 IPC Inter Process Communication 將資料傳遞給 Render Process 以進行頁面渲染，渲染完畢即可讓使用者看到畫面。

{% hint style="info" %}
每個 Render Process 有配置其獨立的記憶體空間，因此除了必要的資源外並不共享，安全性較高。
{% endhint %}

{% hint style="info" %}
目前 Chrome 可做 Site Isolation，意即每個 Iframe 也是由獨立運作的 Render Process 來負責執行，而這些 Render Process 又有分配獨立的記憶體空間，對於安全性來說，隔離不同網站的記憶體使用的資訊，可說是同源策略 Same-Origin Policy 的第二層保護機制。
{% endhint %}

### 解析 URL

當使用者在瀏覽器網址列輸入網址並送出後，Browser Process 的 UI Thread 接收到使用者輸入的資料，UI Thread 會要求發出網路請求，此時 Network Thread 便開始將這網址字串進行解析以便下載資源，而這個網址字串會解析成以下部份。

* 協定類型 :: HTTP、HTTPS、FTP 等
* 伺服器位址 :: Domain Name \( 需要 DNS 解析 \)、IP Address
* 埠號 :: 預設為 80，若為預設則不須寫出
* 資源層級 UNIX 檔案路徑 :: Path，如 `/main/page`
* 檔案名 :: 如 `index.html`
* 查詢 Query String :: 以 ? 開始，並以 & 分隔，如 `?keyword=happy&sad`
* Fragment :: 常用於定位，如 `#head`

### 請求優化

關於以上步驟，可以往以下方向做優化。

#### 減少 Request 數量

* 將文字、圖片、程式碼等資源利用 Webpack 打包後再配合瀏覽器 Cache 機制
* 圖片資源可以使用 Base64、SVG 等應用再配合瀏覽器 Cache 機制
* 內聯推送，如 CSS in JS、Inline JavaScript 等

#### 最佳化載入方式

* preload :: 使用 rel 設定，並且可以用 as 標籤指定類型、加速解析。當遇見使用 preload 的時候會直接開始下載，繪製不會停下。若是 JavaScript 不會馬上執行，只有到有遇見 Script 標籤並且加載同樣檔名的 JavaScript 時才會將預先加載的 JavaScript 執行。
* prefetch :: 使用 rel 設定。向瀏覽器提示要預先下載的資源，並在繪製完成後、瀏覽器空閒時將其拉取存在瀏覽器緩存中，當用戶訪問其中一個預取文檔時，便可以快速的從瀏覽器緩存中得到。每個頁面都可以有多個預取提示，因為預取多個文檔是合理的。
  * dns-prefetch :: 對象是一個網域，能夠預先解析 DNS Lookup，因為瀏覽頁面或下載資源時得先將 DNS 轉為 IP Address，但若數量多，等到要使用時才下載會阻塞，因此可以預先解析備用。
* async :: 只能使用於以 src 取得的資源，即 Script。繪製不會停下，Script 在背景下載。待下載完畢後，Script 即馬上執行，此時繪製會停下，待 Script 執行完後才繼續。
* defer :: 只能使用於以 src 取得的資源，即 Script。繪製不會停下，Script 在背景下載。文檔解析後、在 DOMContentLoaded 前執行。

#### Cache

* 記憶體 Cache ::
* Service Worker Cache ::
* HTTP Cache ::
* Push Cache ::

#### DNS 解析優化

* DNS Cache ::
* DNS 負載平衡 ::
* DNS Prefetch ::

## 事件的傳遞

DOM 的事件傳遞機制，與其相關的有 `addEventListener` 與 `preventDefault` 與 `stopPropagation`。會考慮到傳遞時，是因為有不同的元素堆疊在同一個區域上，那麼當觸發時，需要清楚傳遞的機制才能了解執行的先後順序。

#### AddEventListener

事件有三個階段 `event.eventphase`。

1. CAPTURING\_PHASE :: 捕獲階段，通常指的是在點擊一個元素時，點擊這件事的事件由 Window 一直傳到該元素為止的階段。
2. AT\_TARGET :: 事件到該元素的階段。
3. BUBBLING\_PHASE :: 冒泡階段，點擊事件由元素傳回到 Window 的階段。

{% hint style="info" %}
因此有一句口訣。先捕獲，再冒泡。
{% endhint %}

顧名思義，AddEventListener 的意思就是對事件進行監聽，當事件產生時進行相應的動作，而這事件可以是 Focus、Click 等。因此，`addEventListener` 的第三個 True or False 的參數，預設 False，代表的是預設在 BUBBLING\_PHASE 時進行監聽，True 則是在 CAPTURING\_PHASE 就進行監聽。

即當 False 時，當元素重疊且都有事件時，是由內而外進行，因為是在冒泡階段才監聽。而 True 則是由外而內，因為在捕獲階段就監聽了。

而另外要注意的是，監聽在同一個元素的事件，無論是在捕獲或冒泡階段監聽，在傳遞到目標對象時都會變為 AT\_TARGET 的階段，因此執行順序是由 `addEventListener` 的監聽順序而定。

#### StopPropagation

阻止事件傳遞，若 AddEventListener 第三個參數設定為 True，則事件到捕獲階段就會停止傳遞。此方式只會阻止該 Listener，如果該元素有其他 EventListener，則會繼續監聽。

#### PreventDefault

取消瀏覽器的預設行為。與事件傳遞無關，加上 `event.preventDefault()` 後，事件仍然會繼續傳遞。然而，此取消的行為會在之後傳遞的事件內一直延續，也就是如果在父元素設定取消預設行為，子元素點擊時仍然不會有預設行為。

### 具體應用

* 事件代理 :: 在父節點上掛載 Listener，使其下的子元素都有同樣的 Listener。
* Window :: 在 Window 上掛載 Listener，當 `event.preventDefault()` 和 `event.stopProgagation()` 同時使用，可以停止一切頁面元素。或者，當在 Window 上使用第三個參數為 True、在捕獲階段進行監聽的方法，可以確保是第一個被執行的事件，可以在該 Function 中偵測每個元素的點擊、做數據分析與統計。

{% hint style="info" %}
請參考 TechBridge，[DOM 的事件傳遞機制：捕獲與冒泡](https://blog.techbridge.cc/2017/07/15/javascript-event-propagation/)。
{% endhint %}

## 重點觀念

* Higher Order Function :: 至少有一個函式輸入，並且輸出一個函式。forEach、Filter 等也都是 Higher Order Function。
* Closure :: 能夠存取在不同 Scope 間被使用的變數。特別是 HOF 中回傳了一次函式、第一次輸入時被保留的變數。
* Pure Function :: 給相同的輸入、會有相同的輸出。沒有 Side Effect。不依賴任何外部狀態與變數。
* Side Effect :: 除了主要作用外，會產生其他作用。如 `splice()`、`setTimeout()`、I/O 相關、AJAX、資料庫相關等。
* Shallow Clone :: 之所以 Shallow Clone 是指物件類型如 Object、Array、Map 等，是因為物件類型是 Mutable 可以變動的，因為他們只是 Reference，會更動到原本變數的方法如 Splice、Sort 也屬於 Mutable。
* Deep Clone :: Deep Clone 是與 Number、String 等有關，因為他們是 Immutable，指向時是直接創造在新的記憶體內，直接創造新的陣列或不會影響舊的陣列的方法如 Map、Concat 則也屬於 Immutable。
* JavaScript ::
  * 單執行緒
  * 有 Async，能夠實現 Concurrency

