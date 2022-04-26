---
sidebar_position: 2
---

# Application

在 Internet 中，主要分為兩種 Architecture，Client-Server 與 P2P，Peer-Peer。

## Client-Server

即一般的 Web Application，有以下特徵：

- Client 間歇性 (Intermittently) 連上 Server，並且相互間不溝通
- Server 有固定 IP，而 Client 不必有
- Server 總是 on-host，且單一 Host 上可以有不同 Process 執行 (在不同 Port，單一 Host 間的 Process 溝通由 OS 定義)
- 不同 Host 的溝通由 Message Exchanging 完成
- 連結總是由 Client 發給 Server

## Peer-Peer

如 File Sharing、Movie Sharing，有以下特徵：

- End System 直接來回間歇溝通
- 具有 Self-Capacity，New peer brings new service capacity, as well as service demand
- End System 上同時具有 Client Process 與 Server Process

## Protocol

Application Layer 有自己的 Protocol，主要用於訊息間的交換，如 Request、Response，以及訊息各個欄位的存在、意義，和何時與如何做 Request 與 Response。Open Protocol 被記錄在 RFC 中，有：

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

也有如 Skype 一般自行定義 (Proprietary)、專給 Skype 關聯的應用使用的 Protocol。

### HTTP

**Stateless**，接收 Request 端 (Server) 不會有過去的 Request 資訊。HTTP 仰賴 TCP，關於 TCP Connection 的建立，參考 [/Computer/Layer/Transport#Handshake](/docs/6@computer/10@layer/3@transport#handshake)。
