# Crontab 自動排程

## 概述

使用 Linux 的自帶 Crontab 服務來設定任務自動排程，常見的任務排程有發信件、備份，以及以 Bash 執行指定檔案等等。

## 服務相關指令

針對 Crontab 服務本身的指令。要注意的是 Linux 和 Ubuntu 的執行指令是有區別的。

### Linux

```bash
service crond status # 檢查服務運行狀態
service crond stop # 停止服務
service crond start # 開啟服務
service crond restart # 重啟服務，多用在 crontab -e 新增或編輯完任務後
```

### Ubuntu

Crontab 在 Ubuntu 與 Linux 的指令相似，但 crond 需換為 cron，並且，`/etc/init.d/cron` 可以 `service cron` 替代。我們可以輸入以下指令來查詢 Crontab 在 Ubuntu 的相關指令：

```bash
/etc/init.d/cron -l
```

輸入後即會出現以下提示：

```bash
* Usage: /etc/init.d/cron {start|stop|status|restart|reload|force-reload}
```

## 任務相關指令

```bash
crontab -l # 顯示該使用者的 crontab 指令
crontab -e # 編輯該使用者的 crontab 指令
crontab -r # 將該使用者的 crontab 指令全數清除，要注意只要去除一項，則應使用 crontab -e
crontab -u # 改變使用者執行身分
crontab -u user filename # 改變某個排程的使用者執行身份
```

### 任務

以五項時間參數接著指令檔位置的方式執行，然而若是牽涉到檔案的傳輸則不僅如此。

```bash
00 22 * * */3 /var/www/test/WebBooking2/rating/auto_fetch_sh.sh
```

{% hint style="info" %}
參考 [Cron Guru](https://crontab.guru) 了解時間排程的解釋。
{% endhint %}

