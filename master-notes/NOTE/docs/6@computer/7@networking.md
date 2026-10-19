---
sidebar_position: 7
---

# Networking

首先，[Internet](/docs/6@computer/8@internet) 是一種 Network 的實踐：

- **LAN**: Local Area Network
- **PAN**: Personal Area Network
- **MAN**: Metropolitan Area Network
- **WAN**: Wide Area Network，如 Internet

### Connection Link

Guided Media 有：

- **Copper Cable**: 由複數銅線纏繞絞在一起製成
- **Fiber**: 光纖。取代 Copper Cable 用，因為材質是玻璃更貴，但效率更高
- **Coax**: 同軸電纜

:::info 提醒
Guided Media 透過 TP，Twisted Pair (網路線) 來連結計算機。分有不同 Category，如 5 為 100Mbps (Ethernet 1 Gbps)，6 為 10 Gbps。
:::

Unguided Media 有 Bidirectional 等特性：

- **Wireless**: 如 WiFi 跟 MicroWave
- **Radio**: 如 WiFi
- **Satellite**: 衛星

:::info 提醒
傳輸速率 Transmission Rate 可用 **Bandwidth** (Bits per second) 做單位。
:::

在連結時，我們首要考慮的是 Bandwidth，以及該 Network 是為 Shared 或 Dedicated，Shared 會因為多人使用而導致效能降低。另外需要注意的有：

| Noun | Description                                                                                                                                                               |
| ---- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| DSL  | Digital Subscriber Line，通過銅線或者本地電話網提供數位連接的一種技術                                                                                                     |
| ADSL | Asymmetric Digital Subscriber Line，使用 DSL 達成，非對稱 (上傳與下載速度不相同) 數位使用者線路，ADSL 透過將電話線分成 Phone、Upstream、Downstream 從而避免相互之間的干擾 |

有幾種實踐的 **Physical Connections**：

- Ethernet (Enterprise Access Network)
- WiFi (802.11，由 IEEE 定義)
- ATM

### Protocol

而在連結時，我們使用 **Protocol**，來知道：

- 何時送出
- 誰送出的
- 要送給誰

而具體實踐的方式，則是透過定義：

- Format
- Order of messages sent and received
- Action taken on message transmission
- Receipt
