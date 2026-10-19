# Proxy and Account

只要在想要吃這個設定的專案下面跑一行設定：  
`git config http.proxy http://myusername:mypassword@proxy.server:port`

沒有使用者認證的話就把小老鼠\(at\)前面的那一串拿掉就好了：  
`git config http.proxy http://proxy.server:port`

如果你的所有專案都是在 GitHub 上的可以畫乾脆設為使用者的全域設定：  
`git config --global http.proxy http://proxy.server:port`

