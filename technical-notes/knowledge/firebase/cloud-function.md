# Cloud Function

## 概述

關於 Firebase Cloud Function 的一些已知問題。

### Serve

使用預設指令 Serve 檔案到 Local 端做測試時，指定的 Port 是 5000。當該 Port 有佔用時會直接導致失敗，Firebase Cloud Function 並不會自動尋找可用 Port。

### Get

取資料時，即使已設定 CORS，在本地端一律會被 CORS 擋住。

