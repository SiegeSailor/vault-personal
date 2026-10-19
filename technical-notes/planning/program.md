# Programming

## 筆記

紀錄曾經接觸與使用過的技術。

### Design Pattern

* MVC，Model View Controller
* SWF，Static Website Framework
* SPA，Single Page Application

### Front End

#### 總覽

* Advanced JavaScript
  * ES6+
    * Promise
    * Async
  * There.JS
  * D3.JS
  * Canvas
  * Chart.JS
  * Go.JS
  * WebGL
  * Web Worker
    * 概念是利用創建一個新 Instance 的分離的 JavaScript 檔案，做完全於主 Thread 分開的工作。可以透過監聽來互相溝通。沒有獲取 DOM 的權限。
  * Service Worker
    * 同樣是一個分離的 JavaScript 檔案。啟動並安裝後，在使用者發出特定請求時，會將該請求的資源 cache，並在離線時當使用者發出同樣請求，能用先前 cache 的資源，在啟用、接收 request、轉存 cache、判別 response 來源時都需要注意。只有 localhost 和 https 可用，只有同時間所有舊的 service worker 關閉時，新的才會運作。沒有獲取 DOM 的權限。
  * WorkLet
    * 同樣是一個分離的 JavaScript 檔案，能夠對各個渲染層做鉤子。如 CSS 渲染層。
  * Assert\(\)
* React
  * React DOM
  * React Router
  * Redux
  * React Hooks
  * Context API
  * React Native
  * Universal
  * Flux
  * CORS
  * React Axios
  * Web API
* CSS
  * Semantic UI
  * LESS
  * BEM
  * SASS
  * ReCharts
  * Functional CSS
* Auto Tool
  * Webpack
    * Hot Module Replacement
  * Yarn
  * Gulp
  * Bower
  * LavavelMix
  * Live Server
    * Ngrok

### Back End

#### 總覽

* Language
  * Python
    * Django
      * SMTP
      * CSRF
      * CORS
      * Login
      * CGI
      * WSGI
      * uwsgi
      * uWSGI
  * Node.JS
    * Express
* API
  * RESTful API
    * Swagger
  * GraphQL
* Apache vs Gunicorn vs Nginx

### MIS

#### 總覽

* Database
  * MangoDB
  * SQL
  * MySQL
* Docker
* K8S
* Elastic Search
* Nginx
  * HTTP/2 Server Push
    * One request multi responses
* Apache
  * HTTP/2 Server Push
    * One request multi responses
* Gunicorn
* Knowledge
  * Socket
  * Socket.io
  * SOAP
  * Web Socket
  * Web Services
  * HTTP Keep Alive
  * Amazon AWS
    * EC2
  * Google GCP
    * Firebase
    * VM
* Task Manager
  * Celery
    * Redis
    * RabbitMQ
    * Cron
* Debugger
  * Sentry
  * ELK

### Develop

#### 總覽

* CI/CD
  * Jenkins
* Tool
  * GitLab
  * Postman
  * Supervisor
  * CronTab

## Develop Notes

1. [x] 在 _**src**_ 內使用 `TypeScript` 與 `Express` 建立 Mock Server，並將 Webpack Config 分為 **Common**、**Develop**、**Production**，在對應 Config 忽略 Mock Server
2. [x] 重新命名各資料夾以備之後的資料庫、後端等分類，並且在各部使用不同的 `package.json`
3. [x] Browserlist 設定，`AutoPreFixer`、`StyleLint`
   * [x] Postcss Webpack 內設定，[參考](https://github.com/postcss/postcss-loader)
   * [x] Postcss Config 設定，[參考](https://cythilya.github.io/2018/08/10/postcss/)
   * [x] Browserlist 設定，[參考](https://juejin.im/post/5b8cff326fb9a019fd1474d6)
   * [x] 弄清楚 AutoPrefixer 以及其他 Postcss 的 Plugins
4. [x] License 設定
5. [x] VS Code 安裝 Eslint 與 Stylelint
6. [x] 弄清楚為何 Eslint 出現這麼多錯誤，fix 後又無法運作
7. [x] 設置 Eslint，導入 Webpack。Stylelint 已導入 Webpack 的 Postcss 內
8. [x] 設置 Prettier，Config 在最外層
9. [x] 將 Prettier 整合 Eslint 與 Stylelint
10. [x] 設置 Prettier 的 Script，以及 ESlint 與 Stylelint 的 Script
11. [x] 設置 Babel
12. [x] 重新確認各 Tsconfig
    * [x] 如何才能使用共用 Types
    * [x] paths 的字串是否可以和 node\_modules 的 package 一樣。不可
13. [x] 在最外層設定共用格式化套件
14. [x] 在最外層使用 Husky 與 Lint-Staged，`git add .` 時實行 Prettier 後並做 Lint 檢查
15. [x] Npm Aduit Vulnerability
16. [x] Typescript 研讀後決定共用 Type 方式
17. [x] Webpack dev server 有現有網頁時不重開。目前無解，但 `vue-cli` 與 `create-react-app` 都以自己的方式實做了
18. [x] 使用到 Firebase 的部分，都在前端完成
19. [x] Cloud Function 實作在 Firebase 有安全性疑慮時
20. [x] 將 `Live Server` 轉換成 `Webpack Dev Server`
21. [x] 使用 `React Hooks` 開發，視情況需要使用 `Context API`。要用
    * [x] Functional Components 用 `useState` 與 `useContext`
    * [x] Class Components 用 `Context API`
22. [x] 將 `UglifyJS` 轉換成 `Terser Webpack Plugin`
23. [ ] 前端使用 Firebase Hosting。前端建置後將 Documents 資料拉入，照計畫建立後端，並在建立後一併移至 Server Side
24. [ ] Gitlab 內建立 Dynamic Trello Card
25. [x] 參考 **React Wheather** 配置 `Eslint`、`Prettier`、`Webpack`
26. [ ] 學習精進 `TypeScript`、`NodeJS`、`Express`、`Firebase`
27. [ ] 參酌使用 `Service Worker` 與 `Web Worker`
28. [ ] 逐步使用其他套件如 `React Color`、Excel 相關、Less 相關
29. [x] 將 Prettier、Tsconfig 拉到最外層，並在各部份做客製
    * [x] Prettier
    * [x] Tsconfig
30. [ ] 一定規模後，對特定常改 Function 利用 JEST 做測試



