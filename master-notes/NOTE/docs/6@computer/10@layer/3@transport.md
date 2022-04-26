---
sidebar_position: 3
---

# Transport

管控 Packet 的傳輸方法，即 End-to-End 間的各式 Socket (API，用來接受與傳送資訊)。有些服務需要 Low-Delay 來讓該服務有效，如網路電話，也有些服務需要維持一定量的 Throughput，如多媒體。常見的有：

| Application      | Data Loss   | Throughput     | Time Sensitive   |
| ---------------- | ----------- | -------------- | ---------------- |
| File Transfer    | Not allowed | Elastic        | No               |
| Web Document     | Not allowed | Elastic        | No               |
| Email            | Not allowed | Elastic        | No               |
| Audio            | Allowed     | 5kbps - 1Mbps  | Yes, 100msec     |
| Video            | Allowed     | 10kbps - 5Mbps | Yes, 100msec     |
| Interactive Game | Allowed     | Few kbps up    | Yes, few seconds |
| Text Messaging   | Not allowed | Elastic        | Yes, 100msec     |

### Protocol

常見的協議有：

- **TCP**: Connection oriented
- **UDP**: Connection less deals with the ordering and guarantee of delivery of packets

| Field    | TCP                                      | UDP                                      |
| -------- | ---------------------------------------- | ---------------------------------------- |
| 可靠性   | 可靠                                     | 不可靠                                   |
| 速度     | 慢                                       | 快                                       |
| 傳輸方式 | 封包按順序傳輸                           | 封包以串流方式傳輸                       |
| 檢查修正 | 有                                       | 無                                       |
| 壅塞控制 | 有                                       | 無                                       |
| 確認     | 有                                       | 只有檢查碼                               |
| 適用服務 | 要求可靠，如電子郵件、網頁瀏覽、檔案傳輸 | 要求即時，如串流媒體、網路電話、網路遊戲 |

:::info 提醒
若要在同樣的條件下以相同速度傳送，UDP 只要求 40% 的 Bandwidth 即可達到。
:::

而不同的服務，也要求不同的 Protocol：

| Application            | Application Layer Protocol    | Transport Layer Protocol |
| ---------------------- | ----------------------------- | ------------------------ |
| Email                  | SMTP, RFC 2821                | TCP                      |
| Remote Terminal Access | Telnet, RFC 854               | TCP                      |
| Web                    | HTTP, RFC 2616                | TCP                      |
| File Transfer          | FTP, RFC 959                  | TCP                      |
| Streaming Multimedia   | HTTP, RFC 2616. RTP, RFC 1889 | TCP or UDP               |
| Internet Telephony     | SIP. RTP. Proprietary         | TCP or UDP               |

## Handshake

建立和關閉 TCP 連結的動作。

- **SYN**: Synchronize Sequence Numbers。如果有設置，才發出連線請求，用來同步序列號
- **SEQ**: Segment Length
- **ACK**: Acknowledgment Field Significant。如果有設置，使確認號欄位有效

:::info 提醒
通常會透過 Socket 完成，如 Socket.io 等。
:::

### Three Way Handshake

建立 TCP 連結的動作。

1. 傳送端發出 SYN，與 SEQ
2. 接收端發出 SYN 與 ACK，與 SEQ
3. 傳送端回傳 ACK，與 SEQ

ACK 為對方的 SEQ 加上 Segment Length，然而在建立 TCP 連線時，通常還沒有 Segment，因此才常見為 SEQ 加 1。若是有繼續用同樣的連結請求，則必須加上 Segment Length。

### Four Way Handshake

用於關閉 TCP 連結的動作。

1. 欲關閉端發出 FIN 為 1 的包，表示沒有數據可以發送，但仍可以接收。進入 `FIN_WAIT_1`
2. 另一端回發 ACK，確認收到關閉請求，但還沒準備關閉，進入 `CLOSE_WAIT`。欲關閉端收到後進入 `FIN_WAIT_2`
3. 另一端準備好關閉連結後，向欲關閉端發送 FIN 為 1 的包，並進入 `LAST_ACK`，等待欲關閉端的最後一個 ACK
4. 欲關閉端發送一個確認包，並進入 `TIME_WAIT`，等待可能出現要求重傳的 ACK

另一端接收到最後的確認包後即會斷開連結，進入 `CLOSED`，而欲關閉端會在等待 2 Maximum Segment Lifetime 後跟著斷開連結。
