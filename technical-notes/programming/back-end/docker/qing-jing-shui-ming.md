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

在 `docker-compose.yaml` 中，會對每個 service 中指定 image，即建立起該環境的檔案。在這當中，Nginx 直接指定，代表 Docker 環境建立後會自動在 Docker Hub 上尋找該 service 的 image 並下載。由於 Nginx 是相當知名的 service，Docker Hub 上通常會有該 image。雖然這裡沒有使用，但這是能夠指定版本的。

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

{% hint style="info" %}
當伺服器重啟時，若 Docker 內的 MySQL 正在寫入，則很有可能因為中斷而導致 Table 毀損。建議伺服器重啟前先將該 Docker 暫停。
{% endhint %}

