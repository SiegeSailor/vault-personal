# 開始

## 概述

Supervisor 使用上常遇見的需求。

## 通常路徑

Supervisor 的 config 檔存放位置。

```bash
/etc/supervisor
```

Supervisor 本身的 config 檔。通常只有一個。

```bash
/etc/supervisor/supervisord.d
```

```bash
[unix_http_server]
file=/var/run/supervisor.sock   ; (the path to the socket file)
chmod=0700                       ; sockef file mode (default 0700)

[supervisord]
logfile=/var/log/supervisor/supervisord.log ; (main log file;default $CWD/supervisord.log)
pidfile=/var/run/supervisord.pid ; (supervisord pidfile;default supervisord.pid)
childlogdir=/var/log/supervisor            ; ('AUTO' child log dir, default $TEMP)

; the below section must remain in the config file for RPC
; (supervisorctl/web interface) to work, additional interfaces may be
; added by defining them in separate rpcinterface: sections
[rpcinterface:supervisor]
supervisor.rpcinterface_factory = supervisor.rpcinterface:make_main_rpcinterface

[supervisorctl]
serverurl=unix:///var/run/supervisor.sock ; use a unix:// URL  for a unix socket

; The [include] section can just contain the "files" setting.  This
; setting can list multiple files (separated by whitespace or
; newlines).  It can also contain wildcards.  The filenames are
; interpreted as relative to this file.  Included files *cannot*
; include files themselves.

[include]
files = /etc/supervisor/conf.d/*.conf


[inet_http_server]
port = 8001
```

Supervisor 下轄的針對個別 app 的 config 檔位置。通常不只一個。

```bash
/etc/supervisor/conf.d
```

Supervisor 的錯誤紀錄位置。

```bash
/var/log
```

## 常用指令

當新增或修改完 config 檔後，需使用以下指令載入設定。

```bash
sudo systemctl restart supervisor.service
```

## 錯誤處理

當使用 `supervisorctl status` 後，若見到程序狀態為 _**FATAL Exited too quickly \(process log may have details\)**_，可以嘗試將該程序 Config 檔內的 `user` 設為 **root**。

{% hint style="info" %}
相關操作可見 李林克斯，[使用 Supervisor 管理進程](http://liyangliang.me/posts/2015/06/using-supervisor/)。
{% endhint %}

