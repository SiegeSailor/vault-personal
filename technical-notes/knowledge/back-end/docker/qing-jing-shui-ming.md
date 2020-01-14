# 情境說明

## 概述

實務上常用到的指令與情境。

### 專案結構

假設情境，不一定與其他 Dokcer 專案一樣。注意 env 資料夾內的 `social_d.env` 和 `.env` 兩個檔案存放著環境變數，並未進入 Git 管控。

* app :: Django 專案
  * log
  * social :: Django App
* app.dockerfile :: 建立 app 這個 Django 專案的 Docker config，會在 `docker-compose.yaml` 中被指定
* conf.d
  * nginx.conf :: 對上 `uwsgi.ini`，主要做 `runserver` 用
  * uwsgi.ini
* docker-compose.yaml :: Docker 的 port 對應，以及主要的 config 檔
* env
  * social\_d.env :: 主要環境變量
* requirements :: PIP 套件用
  * requirements.txt
* .env :: 指定整個專案的 port 以及要使用的環境變量檔 `social_d.env`

## 環境變量

一些不進入 Git 管控的變量，通常用於指定個別伺服器需要的溝通變量、帳號密碼以及正式與測試間的差異指定。

#### .env

指定整個專案的 port，以及指定另一個 .env 檔，通常該檔是為測試或正式環境的變量

```bash
PORT=10004
ENV_PATH=env/social_d.env
```

#### social\_d.env

環境變量。

```bash
ALLOWED_HOSTS=18.138.107.163 # IP
APP_ENV=develop # 無實際作用
APP_SECRET_KEY=tw5Qko8bwhTWY4rfcMG2iqt7LRO6kRdC3bFWd0tsLKTtbKWWDn8jcjn3dmSz # Django 用
# Webhook 用的驗證 token，可任意指定，只是要與 Facebook Developer 上的一樣
FB_VERIFY_TOKEN=6PJSELhVpzDtxzaK7e2ZEHrSdQ3WxDeebkZhDpgcTpvW858AEartv3y64SEjcYj8
APP_DEBUG=True # Django 用

MYSQL_HOST=ec2-3-1-156-249.ap-southeast-1.compute.amazonaws.com
MYSQL_PORT=3307
MYSQL_DATABASE=service_social
MYSQL_USER=service_social
MYSQL_PASSWORD=86QPSV6dJawNNcBdgjdCjVPhwk47JfRweC53hvcN

APK_UPLOAD_TMP_PATH=/app/tmp/
GCP_APK_UPLOAD_KEY_PATH=/app/key/micro-service-test-storage-9c6b31271da3.json
GCP_APK_UPLOAD_BUCKET=micro-service-test
GCP_APK_UPLOAD_PATH=APK/
ALLOWED_HOSTS=*
# 應用程式的密鑰，Facebook Developer > 選擇應用 > 設定 > 基本資訊 > 應用程式密鑰
FB_APP_SECRET=84f62951fa27364cf3132aff45caad63

# 此為 Facebook Chat Bot 專案
# Page ID 為將使用此應用的粉絲專頁 ID
# Access Token 為該粉絲專頁的權杖
FB_PAGE_ID_TAIWAN=624937491350766
FB_PAGE_ACCESS_TOKEN_TAIWAN=EAADZCdnh9oHUBANm8E8ZBBpR89jUeGBH4kJJxpcLnA1xjKUfR82iqwaPnorvl1k28nQdZCuaqYLZAHZBi5bQtZAxVVeYGQ9XqP8xCGAn5cgFn5FVIJXxyfvU4wBUybYNYzmDd8Eg6bbtoZAJ2cIf7KcuiYOMjZCncWU4y3zhSggdRDj5CrHaAZCiGA5ZCJxiy17roJmD5JIZBYaVQZDZD
FB_PAGE_ID_SINGAPORE=111208616917708
FB_PAGE_ACCESS_TOKEN_SINGAPORE=EAADZCdnh9oHUBAIcZC3P7P83P81y4d5olIpcyAxPbSD1sMitfZAO2gitdVIiCSNozqhF0PET52wEqFFEfsUiVZC7jOMtZAwS4RauwNFlTNvcMHEmbmVB0IcyhtNfTaOEcD8EVAxizR7gtrMKeayokz2yLbLEFpqrsQooy1Ir7BTVWMhBT6R4x2VGQTkkW53AZD
```

## 設定檔

#### docker-compose.yaml

Docker 的主要 config 檔。

```bash
version: '3'

services:  
  web:
    image: nginx
    volumes:
    # 這裡的路徑是外對內，外:內
      - ./app:/app
      - ./conf.d/nginx.conf:/etc/nginx/conf.d/nginx.conf
    ports:
      - "$PORT:8888"        
    links:
      - app
  app:
    build:
      context: ./
      dockerfile: app.dockerfile
    env_file: "${ENV_PATH}"
    volumes:
      - ./app:/app
```

在 `docker-compose.yaml` 中，會對每個 service 中指定 image，即建立起該環境的檔案。在這當中，Nginx 直接指定，代表 Docker 環境建立後會自動在 Docker Hub 上尋找該 service 的 image 並下載。由於 Nginx 是相當知名的 service，Docker Hub 上通常會有該 image。雖然這裡沒有使用，但這是能夠指定版本的。

而 Docker Hub 上沒有的專案，通常是自己的專案，則會使用 dockerfile 來建立起 image，如這裡的 app 這個 Django 專案就是指定 `app.dockerfile` 來建立。

#### app.dockerfile

在 `docker-compose.yaml` 中被指定，用來建立 Django App 的 config 檔。

```bash
FROM python:3.7

COPY ./requirements /requirements
COPY ./app /app
COPY ./conf.d/uwsgi.ini /uwsgi/uwsgi.ini

RUN pip install -r /requirements/requirements.txt

CMD uwsgi --ini /uwsgi/uwsgi.ini
```

### Docker Log

Docker 本身每個 Container 的 Log 皆存放於 `/var/lib/docker/containers`，在該資料夾內可以看見由 Docker Container ID 命名的各個資料夾。在每個資料夾中的 JSON 檔即為 Log。JSON 為預設格式，可以變更。

{% hint style="info" %}
完整使用過程請參考 史帝芬心得筆記，[Docker Logs](https://stevenitlife.blogspot.com/2018/02/docker-logs.html)。
{% endhint %}

## 常用指令

列出正在運行中的 Docker。通常能不在 CMD 運作而能保持運行，是因為 Supervisor 監管中。

```bash
docker ps
```

運行 Docker。若是第一次會額外 build image，接著 exec。

```text
docker-compose up
```

若 `docker-compose.yaml` 本身有更改，則需重新 build image。

```bash
docker-compose up --build
```

指定要執行的 config 檔。一個 Docker 內可以有多個 `docker-compose.yaml` 。

```bash
docker-compose -f docker-compose-release.yaml up
```

關閉 Docker。

```bash
docker-compose down
```

進入指定的 Docker。

```bash
docker exec -it containerID bash
```

{% hint style="warning" %}
若遇到 _**OCI runtime exec failed: exec failed: container\_linux.go:346: starting container process caused "exec: \"bash\": executable file not found in $PATH": unknown**_，應將 bash 換為 /bin/sh。
{% endhint %}

{% hint style="info" %}
當伺服器重啟時，若 Docker 內的 MySQL 正在寫入，則很有可能因為中斷而導致 Table 毀損。建議伺服器重啟前先將該 Docker 暫停。
{% endhint %}

## 錯誤處理

常見問題。

### App 建置失敗

這不是一個能夠直接看見 Log 的錯誤。情境是，`docker-compose up` 一個專案後，利用 PostMan 使用 API 卻總是得到 500 Internal Server Error，而在閱讀 Docker 在 Terminal 上開始運行時跑出的 Log 時，發現有 _**No app loaded. going in full dynamic mode**_，這代表其中一個 App 並未建置成功。

```bash
Step 4/5 : RUN pip install -r /requirement/requirements.txt
 ---> Using cache
 ---> 41649b7b6efe
Step 5/5 : CMD uwsgi --ini /uwsgi/uwsgi.ini
 ---> Using cache
 ---> cf7079bc5df7
Successfully built cf7079bc5df7
Successfully tagged developmicroservicerating_api:latest
Starting developmicroservicerating_rabbitmq_1 ... 
Starting developmicroservicerating_rabbitmq_1 ... done
Starting developmicroservicerating_migration_and_run_worker_1 ... 
Starting developmicroservicerating_migration_and_run_worker_1
Starting developmicroservicerating_api_1 ... 
Starting developmicroservicerating_api_1 ... done
Starting developmicroservicerating_nginx_1 ... 
Starting developmicroservicerating_nginx_1 ... done
Attaching to developmicroservicerating_rabbitmq_1, developmicroservicerating_api_1, developmicroservicerating_migration_and_run_worker_1, developmicroservicerating_nginx_1
api_1                       | [uWSGI] getting INI configuration from /uwsgi/uwsgi.ini
api_1                       | *** Starting uWSGI 2.0.18 (64bit) on [Tue Oct 22 09:01:08 2019] ***
api_1                       | compiled with version: 4.9.2 on 21 October 2019 15:25:27
api_1                       | os: Linux-5.0.0-31-generic #33~18.04.1-Ubuntu SMP Tue Oct 1 10:20:39 UTC 2019
api_1                       | nodename: 2998c1df761d
api_1                       | machine: x86_64
api_1                       | clock source: unix
api_1                       | pcre jit disabled
api_1                       | detected number of CPU cores: 12
api_1                       | current working directory: /project
api_1                       | detected binary path: /usr/local/bin/uwsgi
api_1                       | uWSGI running as root, you can use --uid/--gid/--chroot options
api_1                       | *** WARNING: you are running uWSGI as root !!! (use the --uid flag) *** 
api_1                       | your memory page size is 4096 bytes
api_1                       | detected max file descriptor number: 1048576
api_1                       | lock engine: pthread robust mutexes
api_1                       | thunder lock: disabled (you can enable it with --thunder-lock)
api_1                       | uwsgi socket 0 bound to UNIX address /project/rating.sock fd 3
api_1                       | uWSGI running as root, you can use --uid/--gid/--chroot options
api_1                       | *** WARNING: you are running uWSGI as root !!! (use the --uid flag) *** 
api_1                       | Python version: 3.6.0 (default, Feb 28 2017, 22:20:46)  [GCC 4.9.2]
api_1                       | *** Python threads support is disabled. You can enable it with --enable-threads ***
api_1                       | Python main interpreter initialized at 0x1d9b310
api_1                       | uWSGI running as root, you can use --uid/--gid/--chroot options
api_1                       | *** WARNING: you are running uWSGI as root !!! (use the --uid flag) *** 
api_1                       | your server socket listen backlog is limited to 100 connections
api_1                       | your mercy for graceful operations on workers is 60 seconds
api_1                       | mapped 802120 bytes (783 KB) for 10 cores
api_1                       | *** Operational MODE: preforking ***
api_1                       | Traceback (most recent call last):
api_1                       |   File "./rating/__init__.py", line 5, in <module>
api_1                       |     from .celery import app as celery_app
api_1                       |   File "./rating/celery.py", line 3, in <module>
api_1                       |     from celery import Celery
api_1                       | ModuleNotFoundError: No module named 'celery'
api_1                       | unable to load app 0 (mountpoint='') (callable not found or import error)
api_1                       | *** no app loaded. going in full dynamic mode ***
                            ## 注意以上此行。
api_1                       | uWSGI running as root, you can use --uid/--gid/--chroot options
api_1                       | *** WARNING: you are running uWSGI as root !!! (use the --uid flag) *** 
api_1                       | *** uWSGI is running in multiple interpreter mode ***
api_1                       | spawned uWSGI master process (pid: 6)
api_1                       | spawned uWSGI worker 1 (pid: 7, cores: 1)
api_1                       | spawned uWSGI worker 2 (pid: 8, cores: 1)
api_1                       | spawned uWSGI worker 3 (pid: 9, cores: 1)
api_1                       | spawned uWSGI worker 4 (pid: 10, cores: 1)
api_1                       | spawned uWSGI worker 5 (pid: 11, cores: 1)
api_1                       | spawned uWSGI worker 6 (pid: 12, cores: 1)
api_1                       | spawned uWSGI worker 7 (pid: 13, cores: 1)
api_1                       | spawned uWSGI worker 8 (pid: 14, cores: 1)
api_1                       | spawned uWSGI worker 9 (pid: 15, cores: 1)
api_1                       | spawned uWSGI worker 10 (pid: 16, cores: 1)
migration_and_run_worker_1  | 2019-10-22 09:01:09,322 DEBUG    django.db.backends (0.058) None; args=None

```

比對後該專案的 `docker-compose.yaml` 後，發現 `api_1` 為一 Python Django 專案，判斷為 **requirements.txt** 未完善，才導致依循此檔案建構的 Python Django 專案錯誤。若有 API 專案無法運行時，應由 Docker 是否完善建置開始檢查。

