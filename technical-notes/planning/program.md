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



