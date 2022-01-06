---
sidebar_position: 1
---

# System Hardware

對於電腦硬體的基礎介紹與定義。

## Definition

所有 Computer 都適用以下定義與大部分配件：

```mermaid
flowchart LR
   A[Input] --> B[Computer] --> C[Output]
```

- **CPU**: 全部的計算
- **Mother Board**: Information Bus。在 I/O Devices 與 CPU、Main Storage 中來回運送資料，I/O Devices 的優先權較低
- **Main Storage**: DDR (SDRAM、RAM)。當下 Data 與 Code 存在的地方，關機即消失
- **Secondary Storage**: 硬碟。唯一電腦中可永久儲存的地方
- **Tertiary Storage**: Offline 硬碟，像是 USB、Flash Drive、Tape Drive，通常不需馬上存取
- **GPU/Video Card**: 圖形計算

:::info 提醒
Main Storage 等同 Main Memory。
:::

電腦大致可以分為以下類型：

- **Mainframe**: 獨立的大型電腦，常見於學校中
- **Server**: 常見於 Data Center，有完全不同等級的處理力
- **End User Devices**: Desktop、Laptop、Table 與 Portable Phone 等

## CPU

Computer's Brain。

- Silicon Chip
- Computer 唯一執行 Code 的地方
- 只執行 Machine Language (OpCode、Instruction)，平常我們寫的 Higher Level Language 會轉譯過來
- 每種 CPU 有自己的 ISA，Instruction Set Architecture，如 ARM、x86-64 等，供他自己閱讀與執行 OpCode
- 每個 CPU 有微量的 Memory，稱為 Registers (Instruction Register)，暫時儲存結果來進行計算
- 只能執行 Basic Command 像是 Move、Add、Subtract、Multiply、Compare、Jump 等，並且執行這些 Command (Instruction) 的能力是物理上被寫在 Silicon Chip 中的
- Operates on a Fetch-Decode-Execute Cycle
- 每個 CPU 有 Cache Memory，提供比 Main Memory 更快的存取速度，但軟體工程師一般無法對此進行操作

```bash
| Instruction | OpCode (Numeric Value) |
| ----------- | ---------------------- |
| ADD         | 0x00                   |
| AND         | 0x20                   |
| CMP         | 0x38                   |
| INT         | 0xCD                   |
| JMP         | 0xE9                   |
| ----------- | ---------------------- |
```

### Fetch-Decode-Execute Cycle

不斷重複直到完成指令，CPU 每秒可執行數百萬次 Cycle：

```mermaid
flowchart LR
   A[Main Main Memory] --> |OpCode and their parameter data| B{Fetch};
   B --> C[CPU's Register];
   C --> D{Decode};
   D --> E{Execute};
```

## Memory Hierarchy

當 CPU 要執行 Code，Code 必須在 Register 內。為什麼不直接把所有東西都存在 Register 內？這樣就不必在每次計算前傳遞資料了。因為，太貴。所以有個 Memory Hierarchy，越高層越貴但越快，反之亦然：

```mermaid
flowchart TB
   A[Register] --> |100x Bigger| B[Cache L1];
   B --> |4x Faster| A;
   B --> |300x Bigger| C[Cache L2];
   C --> |1.5x Faster| B;
   C --> |20,000x Bigger| D[DDR/Main Memory];
   D --> |2x Faster| C;
```

### RAM

DDR (SDRAM、RAM)。當下 Data 與 Code 存在的地方，關機即消失。

- Random Access Memory
- 組成單位為 Bytes
- 取得每個 Byte 的時間一樣
- 當執行程式，該程式的 Instructions 會全被放入 RAM 中，並且一個接一個以 Fetch-Decode-Execute Cycle 拉進 CPU
- 當關機，資料全部消失

### Secondary Storage

硬碟。唯一電腦中可永久儲存的地方。

#### HDD

Hard Disk Drive。

- Also known as **Spinning Drives**
- Contain multiple magnetic material discs which rotate together at a constant velocity
- Contain read heads which move to different radii on the disk
- Allow the system to access any position via it’s three dimensional polar coordinates
- Accessing first the innermost radius then the outermost radius takes significantly longer than two adjacent radii
- Size is usually measured in **Terabytes**

#### SSD

Solid State Disks。

- Contain a number of chips like USB flash drives
- Data is stored, electrically, in these chips
- All data can be access in the **Same Amount Of Time**
- Due to cost, these drives a smaller than HDDs but perform much faster

## Networking

Internet 是一種 Network。

有幾種連結方式：

- **Copper Cable**: 由複數銅線纏繞絞在一起製成
- **Fiber**: 光纖。取代 Copper Cable 用，因為材質是玻璃更貴，但效率更高
- **Wireless**: 如 WIFI 跟 MicroWave

而在連結時，我們有 Protocol，來知道：

- 何時送出
- 誰送出的
- 要送給誰

有幾種 **Physical Connections**：

- Ethernet
- Wifi (802.11，由 IEEE 定義)
- ATM

### Packets

封包。透過網路傳送的資料單位，每經過一層就會加上 Header，這樣在 Data 到達目的時才知道從哪來、要去哪、什麼時候送的。

### Layers

舉例而言，在看影片時，我們收到的資料內會有 HTTP Header、TCP Header、IP Header 與 Ethernet Header。

Application Level：

- **HTTP**: Hyper Text Transport Protocol
- **SMTP**: Simple Mail Transport Protocol
- **IMAP**: Internet Mail Access Protocol

:::info 提醒
SMTP 與 IMAP 經常一起使用，如下圖：

```mermaid
flowchart LR
   A[Sender] --> |SMTP| B[Server];
   B --> |SMTP| C[Server];
   C --> |IMAP| D[Receiver];
```

:::

Logical Level (Network)：

- Global delivery of packets
- **TCP**: Connection oriented
- **UDP**: Connection less deals with the ordering and guarantee of delivery of packets
- **IP**: Internet Protocol。每台機器在網路上的唯一一組識別碼

| 比較項目   | TCP                                                      | UDP                                            |
| ---------- | -------------------------------------------------------- | ---------------------------------------------- |
| 可靠性     | 可靠                                                     | 不可靠                                         |
| 速度       | 慢                                                       | 快                                             |
| 傳輸方式   | 封包按順序傳輸                                           | 封包以串流方式傳輸                             |
| 檢查與修正 | 有                                                       | 無                                             |
| 壅塞控制   | 有                                                       | 無                                             |
| 確認       | 有                                                       | 只有檢查碼                                     |
| 適用服務   | 要求可靠傳輸的服務，例如電子郵件、網頁瀏覽、檔案傳輸等等 | 即時服務，例如串流媒體、網路電話、網路遊戲等等 |

Physical Level：

- Often adds a header and footer (to indicate the end)
- Mostly concerned with local addressing and how to deal with eccentricities of the physical medium
- Ethernet
- 802.11
