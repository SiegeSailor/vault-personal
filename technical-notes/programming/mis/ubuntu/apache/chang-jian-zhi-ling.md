# 常見問題

## 運作指令

以 CMD 執行 Apache 時，有時會遇到 _**Invalid Mutex directory in argument file:${APACHE\_LOCK\_DIR}**_ 的問題，這個其實與 Config 檔並無相關，而是因為使用錯誤的指令執行。

你可能使用以下指令。

```bash
apache2 start
```

當以上指令無效時，使用以下指令。

```bash
sudo service apache2 start
```

#### 常見參數

* start :: 當 apache 為 inactive 時，使用啟動
* restart :: 重新啟動 apache，這會使現有連線中斷
* graceful :: 重新啟動 apache，但會是漸進的、等待無連線時才重啟
* stop :: 停止 apache
* status :: 檢視目前狀態

## 日誌位置

### Error Log

#### 位置

* RHEL / Red Hat / CentOS / Fedora Linux :: **/var/log/httpd/error\_log**
* Debian / Ubuntu Linux :: **/var/log/apache2/error.log**
* FreeBSD :: **/var/log/httpd-error.log**

#### **取得位置**

```bash
grep ErrorLog /usr/local/etc/apache22/httpd.conf
```

```bash
grep ErrorLog /etc/apache2/apache2.conf
```

```bash
grep ErrorLog /etc/httpd/conf/httpd.conf
```

### Access Log

#### 位置

* RHEL / Red Hat / CentOS / Fedora Linux :: **/var/log/httpd/access\_log**
* Debian / Ubuntu Linux :: **/var/log/apache2/access.log**
* FreeBSD :: **/var/log/httpd-access.log**

#### **取得位置**

```bash
grep CustomLog /usr/local/etc/apache22/httpd.conf
```

```bash
grep CustomLog /etc/apache2/apache2.conf
```

```bash
grep CustomLog /etc/httpd/conf/httpd.conf
```

## 錯誤處理

### HTTP Code 400

通常伴隨以下的錯誤訊息。

> Your browser sent a request that this server could not understand.

#### 資源請求面

這有可能是因為限制了 request 的請求資源，在 Apache 的 `httpd.conf` 或 `apache2.conf` 中新增以下程式碼解決。

```text
LimitRequestLine 40940
LimitRequestFieldSize 40940
```

{% hint style="info" %}
詳細內容可以參考 51CTO博客，[Apache2 伺服器異常問題](https://blog.51cto.com/jiawu/1568298)。
{% endhint %}

#### 設定檔面

在 RewriteRule 中，若要重新導向不同的 domain 或 sub domain 都必須加上 `[R]`，沒有加上就會導致 HTTP Code 400。

#### URL 面

在 Apache 的設定檔內，任何路徑都不得使用 `_`。

### 連線緩慢

首先檢查 CPU，確認是否因為某些程式佔用了過多的處理資源。

```bash
top
```

確認 Apache 的 MPM 模式。以下三者皆可。

```bash
httpd -l
```

```bash
sudo service apache2 -l
```

```bash
apachectl -V | grep -i mpm
```

再者檢查 Httpd 進程數量。

```bash
ps -ef | grep httpd | grep -v grep | wc -l
```

檢查 Httpd 線程數量。

```bash
ps -efL | grep -v grep | grep httpd
```

{% hint style="info" %}
只有當 Apache 的 MPM 是以 worker 或 event 執行時才有線程，perfork 則只有進程。
{% endhint %}

接著，檢查伺服器的連接數。

```bash
netstat -ant | grep -E ":80|:443"
```

再查看當前伺服器的建立連接數。

```bash
netstat -ant | grep ESTABLISHED | grep -E ":80|:443"
```

{% hint style="info" %}
針對各 MPM 的設定檔以及漏洞防範，見 米撲博客，[Apache 伺服器附載低訪問慢的原因分析和優化方案](https://blog.mimvp.com/article/22790.html)。
{% endhint %}

