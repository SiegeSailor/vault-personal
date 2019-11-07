# 常見結構

## 排程

相當常見的用於任務排程與訊息傳送的例子。

```bash
Celery > RadditMQ or Redis > Django > Nginx
```

Celery 內有 config 檔，裡面與 RadditMQ or Redis 有關。之後在 Django 內引入 Celery 做 function 上的 decorator，最後再以 Nginx 做對外連結。

