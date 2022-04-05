---
sidebar_position: 2
---

# View Config Flow

View 藉由讀取 Backstage 產生的 Config 生成，不單獨跟隨 Deploy Flow。也就是説，每份 View Config 有自己的、供使用者閱讀與追蹤的版本紀錄，而 Forestage 的版本通常隨著本身使用的 Unit 更新而變動。具體流程大致為：

```mermaid
stateDiagram-v2
    direction TB
    ServerSideWorkspace: Server Side Workspace
    ServerSideSetup: Server Side Setup
    APIEndpoints: API Endpoints
    OpenAPIDocs: Open API Docs
    ClientSideWorkspace: Client Side Workspace
    SharedUIUnit: Shared UI Unit
    IsGeneralizable: Is Generalizable?
    ConfigurableLogic: Configurable Logic
    CustomizedLogic: Customized Logic
    ConfigurableLogicUnit: Configurable Logic Unit
    OnePurposeUIUnit: One-purpose UI Unit
    PullOnePurposeUIUnit: Pull One-purpose UI Unit
    PullConfigurableLogicUnit: Pull Configurable Logic Unit
    OnePurposeUnits: One-purpose Units
    ConfiguredUnit: Configured Unit
    ViewStateUnit: View State Unit
    ViewScopeState: View Scope State
    ViewConfig: View Config
    View1...ViewN: View 1...View N

    [*] --> Develop
    Develop --> ServerSideWorkspace
    state ServerSideWorkspace {
        [*] --> ServerSideSetup
        ServerSideSetup --> Model
            state Model {
            [*] --> FastAPI/Django/Flask
            FastAPI/Django/Flask --> APIEndpoints
            FastAPI/Django/Flask --> OpenAPIDocs
            OpenAPIDocs --> Specification/Schema
        }
    }

    APIEndpoints --> Forestage
    Specification/Schema --> ClientSideWorkspace

    state ClientSideWorkspace {
        [*] --> Controller : Specification
        [*] --> SharedUIUnit : Schema
        state Controller {
            state if_state <<choice>>
            [*] --> IsGeneralizable
            IsGeneralizable --> if_state : If the logic and specification are predictable
            if_state --> Configurable : Yes
            if_state --> Customized : No
            Configurable --> ConfigurableLogic
            Customized --> CustomizedLogic
        }

        ConfigurableLogic --> ConfigurableLogicUnit
        CustomizedLogic --> OnePurposeUIUnit
    }

    ConfigurableLogicUnit --> Backstage
    OnePurposeUIUnit --> Backstage
    SharedUIUnit --> Backstage
    state Backstage {
        [*] --> SetupViews
        SetupViews --> PullOnePurposeUIUnit
        SetupViews --> PullConfigurableLogicUnit
        PullOnePurposeUIUnit --> OnePurposeUnits
        PullConfigurableLogicUnit --> ConfiguredUnit : Attach Logic to a Unit
        PullConfigurableLogicUnit --> ViewScopeState : Attach Logic to the View
        ViewScopeState --> ViewStateUnit : Attach View Scope State to View State Unit

        OnePurposeUnits --> ViewConfig
        ConfiguredUnit --> ViewConfig
        ViewStateUnit --> ViewConfig
    }

    ViewConfig --> Forestage
    Forestage --> View1...ViewN
    View1...ViewN --> [*]
```

### File Structure

以 NX 為基礎的專案結構：

```bash
├── node_modules
├── Serverless Services
│   ├── tsconfig.json
│   └── ...
├── Backstage
│   ├── tsconfig.json
│   └── ...
├── Forestage
│   ├── tsconfig.json
│   └── ...
├── Utilities
│   ├── tsconfig.json
│   └── ...
├── package.json
├── gitlab-ci.yml
├── tsconfig.json
└── ...
```

## 主要問題

以下列出此 Workflow 會面臨的困難：

- **Model to Controller**: Model 如何對應到 Controller、`openapi.json` 與 Controller 的關係
- **Controller Combo Hint**: 如何處理有先後順序或前置要求的 API
- **Front-end Monorepo**: 前端在 Serverless Services、Backstage、Forestage、Utilities 等的開發架構
- **Package**: 由於前端互相之間的關聯，需審慎考慮 Package 的版本對應和使用與否
- **View Scope State**: 同一個 View 內，多個 Unit 對於同樣 View Scope State Controller 的處理
- **Listener**: 資料的主動更新、Controller 類型定義
- **Error Handling**: 錯誤處理，尤其來自載入同樣資料來源的 Unit
