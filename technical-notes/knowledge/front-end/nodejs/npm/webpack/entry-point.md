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

#### 處理時超出預設內存

在Node 中通過JavaScript 使用內存時只能使用部分內存（ 64 位系統：1.4 GB，32 位系統：0.7 GB ），這個時候，如果前端項目非常的龐大，Webpack 編譯時就會佔用很多的系統資源，如果超出了V8 引擎對Node 默認的內存限制大小時，就會產生內存洩露 _**FATAL ERROR: CALL\_AND\_RETRY\_LAST Allocation failed - JavaScript heap out of memory**_ 的錯誤。

首先，安裝 increase-memory-limit 套件，這是一個專門解決 Webpack 或 TypeScript 編譯時內存洩漏問題的套件。

```bash
sudo npm -g install increase-memory-limit --unsafe-perm
```

接著在指定專案的根目錄下使用以下指令。

```bash
increase-memory-limit
```

使用後可能導致 Webpack 編譯無反應，此時須重新安裝 NPM Packages。

