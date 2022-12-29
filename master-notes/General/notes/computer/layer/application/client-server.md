即一般的 Web Application，有以下特徵：

- Client 間歇性 (Intermittently) 連上 Server，並且相互間不溝通
- Server 有固定 IP，而 Client 不必有
- Server 總是 on-host，且單一 Host 上可以有不同 Process 執行 (在不同 Port，單一 Host 間的 Process 溝通由 OS 定義)
- 不同 Host 的溝通由 Message Exchanging 完成
- 連結總是由 Client 發給 Server
