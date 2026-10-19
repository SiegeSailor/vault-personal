---
sidebar_position: 8
---

# Internet

一種 Network 的應用實踐，而 **WWW**，World Wide Web 透過 Internet 連結與存取。Internet Structure 由 **Host**、[Connection Link](/docs/6@computer/7@networking#connection-link) 及 **Packet Switch** 組成並
跟隨由如 IETF，Internet Engineering Task Force 等組織制定的標準 (RHC，Request For Comment)：

- **Host**: 執行 Network Application 的 End System，可以是 Client 或 Server
- **Packet Switch**: Router 與 Switch，負責轉送 Packet。Internet

Internet 的主要目的，是作為一個 Infrastructure 提供如 Web、VoIP、Game 之類的 Service。也作為 Application 間的 Programming Interface，來讓 Application 間可以互相 Send/Receive Information。

### Network Edge

Internet 的一個封閉部分，如 Home Network、Residential Access Net 等，由以下組成：

- **Host**: Client 或 Server
- **Server**: 通常在 Data Center
- **Physical Media**: Wired or Wireless
- **Network Core**: Interconnected Router or Network of Networks

### ISP

Internet Service Provider，網絡服務供應商，即指提供網際網路存取服務如 DSL 的公司，如 Residential、University、Company。Network 連向 ISP，而 ISP 間互相以 **IXP**、**Peering Link** 連接。注意的是，Access Net 的活躍通常與 Commercial 或 National Policy 有關：

```mermaid
flowchart TB
Networks --> ISP[ISP 1...ISP N]
ISP --> X[Reginal Net 1...Reginal Net N]
X --> 1[Access Net 1...Access Net N]
```

:::caution 注意
Access Net 即為最底層的 Network，而 Reginal Net 同時可以視作下游 ISP。Google、Microsoft、Sprint、Facebook 等在為上游 ISP 的同時，也是 Content Provider Network，儘管定義上是 Internet 中其中一個 Network，但他們提供了靠近使用者端的服務，使他們遍及整個 Networks。
:::

## Packet

封包。透過 Internet 傳送的資料單位 (Chunk of data)，由 Application Layer Message 拆分而來，內有目的地 Address，並且每經過一層就會加上 Header，這樣在 Data 到達目的時才知道從哪來、要去哪、什麼時候送的。

```mermaid
stateDiagram-v2
direction LR
    A: Source A
    B: Source B
    T: Transmission
    P: Propagation
    Q: Queueing
    N: Nodal Processing

    A --> Router : Bits
    B --> Router : Bits
    state Router {
        direction LR
        [*] --> N
        N --> Q
    }

    Q --> T
    T --> P
    P --> [*]
```

## Delay

傳輸 Packet 時，會在各個階段遇到各種不同的 Delay。

### Nodal Processing

Nodal Processing Delay，發生在檢查 Router 內的 Bit Error 時、Queueing 之前，通常小於 Millisecond。可以想成在一個隊伍在進去大會會場前、自行先報數確認人數順序的情境。

### Transmission

Transmission Delay，傳送封包時，採用 **Store-and-Forward** 政策。當封包開始從 Source 傳送到 Router 時，會是以 Bit 為單位，只有當 Router 端有 Source 來的 Packet 的全部 Bit 時，才會將 Packet 再次以 Bit 為單位傳送到下個目的地 (**Fragmentation and Reassembly**)。舉例而言，要傳送的封包 L 有 7.5Mbits，而傳送速率 R 為 1.5Mbps，那麼此封包需要 5 秒來到達 Router。而通常而言，我們假設 End-to-End Delay (Transmission Delay) 為 2L/R (假設沒有 Propagation Delay)。

:::info 提醒
Each packet transmitted at full link capacity.
:::

:::info 提醒
**Fragmentation and Reassembly** 是由於 Packet/Datagram 過大而採取的一種切斷傳送方法，並在 Packet 當中透過 `fragflag` 的 0 或 1 指明是否有下一個 Fragment、並用 `offset` 表示要到下一個 Fragment 要經過多少個 Bits。
:::

### Queueing

Queueing Delay，當等待被傳送的 Packet Bit 超過傳送速度，則會發生 Queueing，而若 Queue 內的 Bit 超過 Router 的 **Memory Buffer**，就會發生 **Queueing Loss**，導致在 Queue 後端已達到的封包在還未被傳送時即消失。設 L 為欲傳送的封包、R 為傳送速度 Bandwidth、A 為 Average Packet Arrival Rate，他們與 Average Queueing Delay 間的關係為：

- $LA/R \sim 0$: Small
- $LA/R \sim 1$: Large
- $LR/R >1$: More work arriving than serving, the delay is **infinite**

:::info 提醒
**Queueing Loss**，又稱 Head-Of-Line Blocking。當 Queueing Loss 發生時，TCP 會透過重新傳送的方式進行修正，可參考 [Computer/Layer/Transport#Protocol](/docs/6@computer/10@layer/3@transport#protocol)。
:::

### Propagation

Propagation Delay，假設 D 為 Physical Link 的長度，而 S 為介質的 Propagation Speed (Approximate $2x108m/sec$)，那麼 Propagation Delay 為 D/S。

:::info 提醒
**Throughput**，吞吐量，即 Bits/Time Unit，可以用來在較長的時間中解釋 Packet 的平均傳輸量。
:::

## Encapsulation

舉例而言，在看影片時，我們收到的資料內會有 HTTP Header、TCP Header、IP Header 與 Ethernet Header (見 [Packet](/docs/6@computer/8@internet#packet))，這些事實上是來自不同 Layer 的 Header，可以稱作為 **Internet Protocol Stack**，表示每一層的資訊。從起點傳輸到終點，會逐步加上每層的資訊、並在到達對應的層時逐步識別並移除：

```mermaid
stateDiagram-v2
direction LR
    SourceApplication : Application
    SourceTransport : Transport
    SourceNetwork : Network
    SourcePhysical : Physical
    SwitchLink : Link
    SwitchPhysical : Physical
    RouterPhysical : Physical
    RouterLink : Link
    RouterNetwork : Network
    DestinationApplication : Application
    DestinationTransport : Transport
    DestinationNetwork : Network
    DestinationPhysical : Physical

    Source --> Switch
    Switch --> Router
    Router --> Destination

    state Source {
        SourceApplication --> SourceTransport : H0
        SourceTransport -->SourceNetwork : H0 H1
        SourceNetwork --> SourceLink : H0 H1 H2
        SourceLink --> SourcePhysical : H0 H1 H2 H3
    }

    state Switch {
        SwitchLink --> SwitchPhysical : H0 H1 H2 H3
    }

    state Router {
        RouterPhysical --> RouterLink : H0 H1 H2 H3
        RouterLink --> RouterNetwork : H0 H1 H2
    }

    state Destination {
        DestinationPhysical --> DestinationLink : H0 H1 H2 H3
        DestinationLink --> DestinationNetwork : H0 H1 H2
        DestinationNetwork -->DestinationTransport : H0 H1
        DestinationTransport --> DestinationApplication : H0
    }
```

## Security

Internet 被設計之初，並沒有 Security 的概念，它的原意是讓一群互相信任的使用者、在一個透明的網路上連結，因此，多年來 Internet Protocol Designers 是以 Catch-Up 的方式去防範更新型的攻擊。攻擊可以分為：

- **Malware Virus**: Self-replicating infection by receiving in executing an object
- **Malware Worm**: Self-replicating infection by passively receiving an object that gets itself executed
- **Spyware**: Record keystrokes, websites visited, or upload information into different rogue sites
- **Infected Host**: Be enrolled in a botnet which are used for spam which then can generate
  something called [DDoS Attack](/docs/6@computer/8@internet#ddos-attack), Distributed Denial-of-Service attacks
- **Packet Sniffing**: Promiscuous network interface (Sniff packets through the wired network which is usually much harder than through the wireless) to read or record all packets, such as password, passing by
- **IP Spoofing**: Send packets with false source address

### DDoS Attack

Distributed Denial-of-Service Attack. The attackers make resources such as servers or bandwidth unavailable for legitimate traffic by overwhelming our hosts with bogus traffic, number of different requests. Imagine if a group of people is asking you questions all the time before you get a chance to answer, you get overwhelmed with information. That's exactly what the distributed denial of service attack is.

:::info 提醒
可以使用 [Kali Linux](https://www.kali.org/) 來進行攻擊測試。
:::
