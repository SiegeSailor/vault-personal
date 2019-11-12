# Process

## 

## 概述

解釋 Process 以及與其相關的名詞。

## 摘要

* CPU :: 處理單元，一個 CPU Core 同時只能執行一個 Process
* Program :: 執行的程式檔，通常是 Compile 過的 EXE 檔、還未 Load 進記憶體的 Code。具有唯一性
  * Process :: 即執行中的 Program，一個 Program 可以同時有很多 Process，Process 間不可互相存取 Memory，但仍然有如 IPC 一類互相溝通資料的方法。一個 Process 是指 Thread 們的容器，默認時，Java 是單一 Thread，而 Python 是 Multi-Thread
    * Process 的構成 ::
      * Program Code \( Code Section \) :: 程式碼
      * Process Counter :: 下一個要執行的 Process 的位置
      * Processor Register \( Register Set \) :: 暫存器中的資料。暫存器為小又能快速取用 CPU 資源的元件
      * Stack Space :: 儲存暫時性資料
      * 變數 :: Dynamically Allocated Variables 和 Non-Thread-Local Global Variables
      * Data Section \( Thread 間的 Shared Data \) :: Program 和 Process 的相關資料
      * Memory Cache \( Heap \) :: 執行時產生的動態記憶體空間
        * Thread :: 可以稱為輕量級的 Process，一樣有自己的 Stack Space 與 Memory Cache \( Heap \)、Program Counter、Processor Register \( Register Set \)，但同 Process 間的 Thread 能共享變數與 Shared Data \( Data Section \)、Program Code \( Code Section \)、OS Resources \( 如開啟檔案等 \)，一旦 Thread 讀取到 Shared Data \( Data Section \)，便會將其儲存在自己的 Memory Cache \( Heap \)
  * Python :: 直譯式程式語言
    * Interpreter :: Python 直譯器，即時編譯 Python 程式碼讓其可以作為程式執行。CPython 是預設的 Interpreter。CPython 本身沒有多核心的指令並且使用 GIL，因此最終 Threading Library 只能使用單核。對 OS 而言，Python Interpreter 即是一個 Program
      * Green Thread :: 即在執行時，程式是由 Run-Time Library 或 VM 運算的概念。Python 是由 Python Interpreter 而非 OS 運算，屬於 Green Thread
        * Threading Library ::
          * Multi-Threading :: 若不同 Thread 時存取或改變 Global Variable，可能發生 Synchronization。而若不同 Thread 互搶資源，則可能發生 Deadlock。GIL 可以確保 Thread 執行時不會因為變數的變動時機不同而產生錯誤，List 的使用方法也可以確保按照順序執行
            * Concurrency :: 在一個核心內給不同的 Thread 分配時間與資源，讓使用者感覺同時處理、而實際上是由於運算資源有做時間分配，讓真正有需要用到的 Thread 在需要時才佔用核心
              * GIL :: Global Interpreter Lock，Python 用於協調及分配運算資源給 Thread 的方式
                * Thread Safe :: GIL 管理 Thread 的方式，是一個 Lock 而非 Mutex，一次只有一個 Thread 能拿到 Lock，而只有拿到 Lock 的 Thread 能獲得運算資源並運作
        * 其餘多核用 Library :: OpenMP、PThreads、GCD
          * Fork :: 可以視為 Multi-Processing 的原始版本，需要 Import OS 來使用
            * Parallelism :: Fork 應用的概念
          * Multi-Processing :: 
            * Parallelism :: 產生新的 Process，使他們被系統分配、同時在不同的 CPU Core 中執行。Multi-Processing 與原先 Process 分隔的不同的部份是，變數是共用的、但由於 Process 是新的，Memory 也是新的，因此 Global 變數的改變並不會影響到不同 Process。Multi-Processing 在 Parent Process 和 Child Process 中有 Shared Memory。
  * uWSGI :: 接口，似乎可以在 Parallelism 的多核下對單核進行 Concurrency 應用
  * JavaScript :: 直譯或編譯式程式語言
    * Interpreter :: 即 V8，普遍存在於瀏覽器以及 NodeJS 中，即時編譯 JavaScript 程式碼使其可以作為程式執行
    * Compiler :: 如 Gulp 或 Webpack，編譯 JavaScript 後使其可以在特定環境下運行

## 來源

整理摘要時的資料來源，備查。

#### Thread 的定義

> 章節 :: Definition

{% embed url="https://en.wikipedia.org/wiki/Thread\_\(computing\)" %}

> 章節 :: Process vs Thread?

{% embed url="https://www.geeksforgeeks.org/thread-in-operating-system/" %}

> 章節 :: Process vs threads

{% embed url="https://www.vogella.com/tutorials/JavaConcurrency/article.html" %}

#### Green Thread 的定義

> 章節 :: Definition

{% embed url="https://en.wikipedia.org/wiki/Green\_threads" %}

#### Program 與 Process 與 Thread 的差異

{% embed url="https://medium.com/@totoroLiu/program-process-thread-%E5%B7%AE%E7%95%B0-4a360c7345e5" %}

## 爭議

一些在來源上互有不同或與其他官方文件有差異的文章。

#### Threading 的 Concurrency 限制

解釋因為 CPython 使用 GIL 從而導致 Threading Library 只能應用 Concurrency

> 章節 :: Concurrency in Python

{% embed url="https://www.quantstart.com/articles/parallelising-python-with-threading-and-multiprocessing" %}

解釋因為 Threading 的應用原理，因此 Threading Library 實際上應為 Concurrency

{% embed url="https://medium.com/@alan81920/python-%E7%9A%84-concurrency-%E5%92%8C-parallelization-efeddcb30c4c" %}

