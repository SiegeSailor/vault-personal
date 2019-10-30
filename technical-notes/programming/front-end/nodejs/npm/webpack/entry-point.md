# Trouble Shooting

## Trouble Shooting

#### Module not found: Error: Can't resolve jQuery in

```bash
npm install --save jquery
```

#### Execute Twice

Check if you load your file outside **Entry Point** again.

#### Watch 無效

當在 Ubuntu 使用 Watch 指令監聽，卻在改動後毫無反應時，可以嘗試以下指令來增加 Watch 數量。

```bash
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```

