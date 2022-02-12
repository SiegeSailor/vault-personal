---
sidebar_position: 1
---

# Outline

參考 AppSheet、Data Studio，整合不同來源的資料、透過可複用的 Logic (Controller) 與 Component，展示可執行寫入與讀取的 UI (View)。操作者只需了解 Controller 與 View 的對應關係即可針對需求組合頁面。

以下為 Server、Module Configuration 與 Module Runtime 的對應關係：

```mermaid
stateDiagram-v2
    direction TB
    [*] --> Server
    state Server {
      direction TB
      [*] --> Background
      state Background {
        direction TB
        [*] --> K8SJob/CronJob
        [*] --> Celery
        [*] --> Airflow
        [*] --> CloudPub/Sub
      }
      Background --> StatefulSet/Storage
      state StatefulSet/Storage {
        direction TB
        [*] --> MongoDB
        [*] --> Firestore
        [*] --> MySQL
        [*] --> Redis
        [*] --> Postgres
        [*] --> RedditMQ
        [*] --> GCS
      }
      StatefulSet/Storage --> Foreground
      state Foreground {
        direction TB
        [*] --> FastAPI/Flask/Django
        FastAPI/Flask/Django --> API1...APIN
        FastAPI/Flask/Django --> OAI1...OAIN
      }
    }
    OAI1...OAIN --> ModuleConfiguration
    API1...APIN --> ModuleRuntime
    state ModuleConfiguration {
      direction TB
      [*] --> ModelList
      state ModelList {
        [*] --> Model1...ModelN
      }
      ModelList --> ConfigurableController
      state ConfigurableController {
        [*] --> Logic1...LogicN
      }
      ModelList --> CustomizedController
      state CustomizedController {
        [*] --> LogicA...LogicN
      }
      ConfigurableController --> Component
      CustomizedController --> Component
      state Component {
        [*] --> Component1...ComponentN
      }
      Component --> View
      state View {
        [*] --> View1...ViewN
      }
    }
    ModuleConfiguration --> ModuleRuntime
    state ModuleRuntime {
      [*] --> APIList
      APIList --> API0...APIN
      --
      [*] --> Workflow
      Workflow --> Logic0...LogicN
      --
      [*] --> Render&Interaction
      Render&Interaction --> UI0...UIN
    }
```

> Model List 為各 API 的 `openapi.json`

使用 One to One 的配置為佳，即操作者在單一 Backstage (實現 Module Configuration 的地方) 設置前台各個 View 的 Config，使用者透過單一介面、讀取各前台 View Config 後展示。比起部署在不同 Forestage，能夠：

1. 單一 Application 內操作複數 View
2. 各 View 前有一層展示跨 Forestage 資訊的 UI (各 API 狀態、帳戶列表、權限一覽等)
3. 統合驗證與權限
