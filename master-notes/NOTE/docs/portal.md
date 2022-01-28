---
sidebar_position: 10
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

## View Config Flow

View 藉由讀取 Backstage 產生的 Config 生成，不單獨跟隨 Deploy Flow。也就是説，每份 View Config 有自己的、供使用者閱讀與追蹤的版本紀錄，而 Forestage 的版本通常隨著本身使用的 Component 更新而變動。具體流程大致為：

```mermaid
stateDiagram-v2
    direction TB
    [*] --> Develop
    Develop --> ServerSideWorkspace
    state ServerSideWorkspace {
        [*] --> ServerSideSetup
        ServerSideSetup --> Model
            state Model {
            [*] --> FastAPI/Django/Flask
            FastAPI/Django/Flask --> APIEndpoints
            FastAPI/Django/Flask --> OpenAPIDocs
            OpenAPIDocs --> Specification
            OpenAPIDocs --> Schema
        }
    }

    APIEndpoints --> Forestage
    Specification --> ClientSideWorkspace
    Schema --> ClientSideWorkspace

    state ClientSideWorkspace {
        [*] --> Controller : Specification
        [*] --> SharedUIComponent : Schema
        state Controller {
            state if_state <<choice>>
            [*] --> IsGeneralizable
            IsGeneralizable --> if_state : If the logic and specification are predictable
            if_state --> Configurable : Yes
            if_state --> Customized : No
            Configurable --> ConfigurableLogic
            Customized --> CustomizedLogic
        }

        ConfigurableLogic --> ConfigurableLogicComponent
        CustomizedLogic --> OnePurposeUIComponent
    }

    ConfigurableLogicComponent --> Backstage
    OnePurposeUIComponent --> Backstage
    SharedUIComponent --> Backstage
    state Backstage {
        [*] --> SetupViews
        SetupViews --> PullOnePurposeUIComponent
        SetupViews --> PullConfigurableLogicComponent
        PullOnePurposeUIComponent --> OnePurposeComponents
        PullConfigurableLogicComponent --> ConfiguredComponent : Attach Logic to a Component
        PullConfigurableLogicComponent --> ViewScopeState : Attach Logic to the View
        ViewScopeState --> ViewStateComponent : Attach View Scope State to ViewStateComponent

        OnePurposeComponents --> ViewConfig
        ConfiguredComponent --> ViewConfig
        ViewStateComponent --> ViewConfig
    }

    ViewConfig --> Forestage
    Forestage --> ViewTechCenter/ViewSupplyChain/ViewMart/ViewInfoHub
    ViewTechCenter/ViewSupplyChain/ViewMart/ViewInfoHub --> [*]
```

### 主要問題

以下列出此 Workflow 會面臨的困難：

- **Model to Controller**: Model 如何對應到 Controller、`openapi.json` 與 Controller 的關係
- **Controller Combo Hint**: 如何處理有先後順序、前置要求的 API
- **Front-end Monorepo**: 前端在 ServerlessServices、Backstage、Forestage、Utilities 等的開發架構
- **Package**: 由於前端互相之間的關聯，需審慎考慮 Package 的版本對應、需求與否
- **View Scope State**: 同一個 View 內、多個 Component 對於同樣 View Scope State Controller 的處理
- **Listener**: 資料的主動更新、Controller 類型定義
- **Error Handling**: 錯誤處理，尤其來自載入同樣資料來源的 Component

### File Structure

```bash
├── node_modules
├── ServerlessServices
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

## Serverless Services

開發過程中，Authentication 和 View Config Versioning 的部分會隨前端有眾多變更，在此可使用 Serverless NoSQL Database 一類服務，由前端定義並控制。應整合於 ClientSideWorkspace 中，見 [File Structure](#file-structure)。

```mermaid
stateDiagram-v2
    direction LR
    [*] --> Develop
    Develop --> ServerSideWorkspace
    Develop --> ClientSideWorkspace
    state ServerSideWorkspace {
      [*] --> APIEndpoints
    }
    state ClientSideWorkspace {
      [*] --> ServerlessServices
      [*] --> ViewConfig
      ServerlessServices --> Authentication
      ServerlessServices --> ViewConfigVersioning
    }
    ViewConfig --> ViewConfigVersioning
    APIEndpoints --> Forestage
    Authentication --> Forestage
    ViewConfigVersioning --> Forestage
    Forestage --> [*]
```

### View Config Versioning

操作者在 Backstage 設置 View 後，應以 View 為單位產出供 Forestage 讀取展示的設置檔，大致應有：

```tsx
// 每次創建、儲存 View 後，應產出如下的資料，並以 View 為單位存在 ViewConfigVersioning 中
interface IViewDetail {
  title: string;
  identityAuthor: string;
  message: string;
  entryOfComponent: {
    [identityComponent]: {
      order: number;
      controllerRead: {
        identity: string;
        position: number;
      }[];
      controllerWrite: {
        identity: string;
        position: number;
      }[];
    };
  };
  entryOfViewScopeStateController: {
    [identityController]: string[];
  };
  timestampCreated: number;
}

// 每份 IViewConfig 應對應到每個 Forestage 的頁面
interface IViewConfig {
  identityView: string;
  // 若無則使用 identityView 作為 pathname
  pathname?: string;
  // 若無則使用 currentVersion 的 title
  label?: string;
  icon: string;
  order: number;
  currentVersion: string;
  timestampCreated: number;
  [version: string]: IViewDetail;
}
```

:::info 提醒
考慮到後續維護，UI 應以順序 `order` 為基準展現、不在意互相關係，如 Pinterest 或 MicroSite。
:::

### Authentication

由於每個端點的驗證機制不同，在未來，也有需要獨立驗證的可能，應同時儲存不同使用者的各式 Credential，並存在整個 Forestage 的週期中：

```tsx
interface IUser {
  identity: string;
  username: string;
  email: string;
  listOfPermission: {
    identityView: string;
    listOfAllowedController: string[];
  }[];
  entryOfToken: {
    [token]: string;
  };
}
```

:::info 提醒
對於 Backstage 的使用者控管，可直接使用如 Firebase Authentication 一類的服務作 SSO。
:::

## Backstage

操作者在 Backstage 中使用 3 種 Component (OnePurposeComponent、ConfigurableComponent、ViewStateComponent) 來設置 View Config。通常來說，一個 Component 的組成有：

1. 1 個 UI Component，可能是單一 Button 或 Tabs 與 Table 的組合
2. 針對該 UI Component 的設置，如 Button 文字、數量、Unit ID 等
3. 1 個或多個 Controller Component
4. 針對以上 Controller、互相之間的呼叫順序、Controller 資料、對應 Unit 的資料、類型等設置 (壓平資料並排序)
5. 將第 4 點間或最後的資料指定至 UI Component 上
6. 對能產生使用者行為的 UI Component，能夠綁上建立、更新、刪除類的 Controller，並將第 4 點的資料作為 Parameter 使用
7. Component 再往上的層級是 View。第 4 點產生的資料 可以拉到 View 供其他 Component 取用 (View Scope State)
8. View 再往上的層級是整個 Forestage。在這層，可以取用使用者的資料與驗證、權限
9. UI Component 內要呈現的資料 (第 5 點)、操作綁定使用行為時 (第 6 點)，可以取用 View Scope State

3 種 Component 分別為：

- **OnePurposeComponent**: 或有少數邏輯無法泛化到 Controller 與 Component 上，此時因應的是為特定邏輯開發的 Component，使用上不需設置、拉取即使用。然而應是特例
- **ConfigurableComponent**: 主要組成。透過編輯 Controller 與 UI 的相互關係作成
- **ViewStateComponent**: 與 ConfigurableComponent 大致相同，然而只取用 View Scope State 的資料並顯示

```mermaid
stateDiagram-v2
    direction TB
    [*] --> Backstage
    state Backstage {
      direction TB
      [*] --> View
      state View {
        direction LR
        [*] --> UIComponent
        [*] --> ControllerComponent
        [*] --> ViewScopeState
        UIComponent --> AssembleInterface
        state UIComponent {
          state if_state <<choice>>
          [*] --> IsInteractive
          IsInteractive --> if_state : If the component is interactive
          if_state --> DisplayUnit : No
          if_state --> UserBehaviorUnit : Yes
        }
        state ControllerComponent {
          direction TB
          [*] --> Add
          Add --> AssignParameter
          AssignParameter --> Response
          Response --> Add
        }

        ViewScopeState --> AssembleInterface
        ControllerComponent --> AssembleInterface
      }

      AssembleInterface --> ComponentConfig
      ComponentConfig --> ViewConfig
      ViewConfig --> Forestage
      Forestage --> [*]
    }
```

### Controller

由 `openapi.json` 解析、自動產出的邏輯器，假設我們有以下檔案：

```json title="oai-people-portal.json"
{
  "openapi": "3.0.2",
  "info": {
    "title": "People Portal API",
    "version": "d5ea6895"
  },
  "paths": {
    "/people-portal/employees/{employees_type}": {
      "get": {
        "tags": ["People portal"],
        "summary": "List Employees",
        "operationId": "list_employees_people_portal_employees__employees_type__get",
        "parameters": [
          {
            "required": true,
            "schema": {
              "title": "Employees Type",
              "enum": ["full-time", "intern", "temporary"],
              "type": "string"
            },
            "name": "employees_type",
            "in": "path"
          }
        ],
        "responses": {
          "200": {
            "description": "Successful Response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ListObjectsOut"
                }
              }
            }
          },
          "422": {
            "description": "Validation Error",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/HTTPValidationError"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "ListObjectsOut": {
        "title": "ListObjectsOut",
        "required": ["count", "data"],
        "type": "object",
        "properties": {
          "count": {
            "title": "Count",
            "type": "integer"
          },
          "data": {
            "title": "Data",
            "type": "array",
            "items": {
              "anyOf": [
                {
                  "type": "string"
                },
                {
                  "type": "string",
                  "format": "uuid"
                }
              ]
            }
          }
        }
      },
      "HTTPValidationError": {
        "title": "HTTPValidationError",
        "type": "object",
        "properties": {
          "detail": {
            "title": "Detail",
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/ValidationError"
            }
          }
        }
      }
    }
  }
}
```

使用以下 Packages 來幫助解析 `openapi.json`：

- `openapi-types`: 提供 `openapi.json` 的 Type
- `@apidevtools/json-schema-ref-parser`: 幫助解析文件中以 `$ref` 定義的部分

首先建立 ParseOAI 以對 `openapi.json` 做整理：

```tsx title="helper/parse-oai.ts"
import { OpenAPIV3 } from 'openapi-types';
import $RefParser from '@apidevtools/json-schema-ref-parser';

export default class ParseOAI {
  openAPISource: string | $RefParser.JSONSchema;
  parser: $RefParser;
  document: OpenAPIV3.Document;
  constructor(parser: $RefParser, openAPISource: string | $RefParser.JSONSchema) {
    this.parser = parser;
    this.openAPISource = openAPISource;
  }

  async createSelfScopeDereferencedAPI() {
    const sourceBundled = await this.parser.bundle(this.openAPISource);
    const document = (await this.parser.dereference(sourceBundled)) as OpenAPIV3.Document;
    this.document = document;
    return document;
  }

  /**
   * Parse value from key `$ref` such as
   * `#/components/schemas/UpdateEmployeeInfoFromGoogleSheetOut` with their module value.
   * @param {String} path `#/<fieldLevel1>/<fieldLevel2>/<module>`
   * @returns {Object} Module value from `this.document`
   * @deprecated Since started using `@apidevtools/json-schema-ref-parser`
   */
  static parseReference(document: OpenAPIV3.Document | $RefParser.JSONSchema, path: string): object {
    const listOfKey = path.split('/').slice(1);
    const reference = listOfKey.reduce((accumulator, currentValue, currentIndex) => {
      const currentField = accumulator[currentValue];
      if (currentField) {
        accumulator = currentField;
      } else {
        listOfKey.splice(currentIndex);
      }
      return accumulator;
    }, document);
    return reference;
  }
}
```

接著建立分別以 Path、Pattern、Method 為單位的 UI Component：

```tsx title="component/PathAll.tsx"
import React from 'react';
import { OpenAPIV3 } from 'openapi-types';

import PatternCombo from './PatternCombo';

interface IPathAll {
  entryOfPath: OpenAPIV3.PathsObject;
}

export default function PathAll({ entryOfPath }: IPathAll) {
  return (
    <div>
      {Object.entries(entryOfPath).map(([pattern, item]) => {
        return <PatternCombo key={pattern} pattern={pattern} entryOfItem={item} />;
      })}
    </div>
  );
}
```

```tsx title="component/PatternCombo.tsx"
import React from 'react';
import { OpenAPIV3 } from 'openapi-types';

import MethodSingle from './MethodSingle';

interface IPatternCombo {
  pattern: string;
  entryOfItem: OpenAPIV3.PathItemObject;
}

export default function PatternCombo({ pattern, entryOfItem }: IPatternCombo) {
  return (
    <div id={pattern}>
      <h4>{pattern}</h4>
      {Object.keys(entryOfItem)
        .filter((key: OpenAPIV3.HttpMethods | string) => {
          return Object.values<string>(OpenAPIV3.HttpMethods).includes(key);
        })
        .map((key: OpenAPIV3.HttpMethods) => {
          return <MethodSingle key={key} pattern={pattern} method={key} entryOfOperation={entryOfItem[key]} />;
        })}
    </div>
  );
}
```

```tsx title="MethodSingle.tsx"
import React from 'react';
import { OpenAPIV3 } from 'openapi-types';

interface IMethodSingle {
  pattern: string;
  method: OpenAPIV3.HttpMethods;
  entryOfOperation: OpenAPIV3.OperationObject;
}

export default function MethodSingle({ pattern, method, entryOfOperation }: IMethodSingle) {
  const identity = `${pattern}-${method}`;

  return (
    <div id={identity}>
      <h6>{method.toUpperCase()}</h6>
      <p>{entryOfOperation.summary}</p>
    </div>
  );
}
```

最終在 `Main.tsx` 中使用：

```tsx title="Main.tsx"
import React, { useEffect, useState } from 'react';
import { OpenAPIV3 } from 'openapi-types';

export default function Main() {
  const [document, setDocument] = useState<OpenAPIV3.Document>(null);
  useEffect(() => {
    const wrapAsync = async () => {
      const parser = new $RefParser();
      const parseOAI = new ParseOAI(parser, 'https://api.people.twtc.uat.shopee.tw/openapi.json');
      const document = await parseOAI.createSelfScopeDereferencedAPI();
      setDocument(document);
    };
    wrapAsync();
  }, []);
  return <section>{document && <PathAll entryOfPath={document} />}</section>;
}
```

可以再加上一些測試：

```tsx title="parse-oai.test.ts"
import $RefParser from '@apidevtools/json-schema-ref-parser';

import ParseOAI from './parse-oai';
import OAIPeoplePortal from './oai-people-portal.json';

const parser = new $RefParser();
const parseOAI = new ParseOAI(parser, OAIPeoplePortal);

describe('Structure method', () => {
  describe('createSelfScopeDereferencedAPI', () => {
    it('does the method dereference properly', async () => {
      const document = await parseOAI.createSelfScopeDereferencedAPI();
      expect(
        document.paths['/people-portal/employees/{employees_type}'].get.responses['200']['content']['application/json'].schema
      ).toEqual(OAIPeoplePortal.components.schemas.ListObjectsOut);
    });
  });
});

describe('Static method', () => {
  describe('parseReference', () => {
    it('does the method work properly', () => {
      expect(ParseOAI.parseReference(OAIPeoplePortal, '#/components/schemas/ListObjectsOut')).toEqual(
        OAIPeoplePortal.components.schemas.ListObjectsOut
      );
    });
  });
});
```

:::info 提醒
後端在上版後，應更新一處供 Backstage 取用的 Open API Config，如：

```json
{
  "peoplePortal": {
    "versionCurrent": "https://api.people.twtc.uat.shopee.tw/v1.5.0/openapi.json",
    "versionPrevious": "https://api.people.twtc.uat.shopee.tw/v1.4.8/openapi.json",
    "versionStable": "https://api.people.twtc.uat.shopee.tw/v1.1.2/openapi.json"
  },
  "supplyChain": {
    "versionCurrent": "https://api.sci.twtc.uat.shopee.tw/v1.1.4/openapi.json",
    "versionPrevious": "https://api.sci.twtc.uat.shopee.tw/v1.1.3/openapi.json",
    "versionStable": "https://api.sci.twtc.uat.shopee.tw/v1.1.0/openapi.json"
  }
}
```

如上，後端上版後會更新對應版本號，而前端可根據配置的 Key (Remote Config、Firestore、Envs 等) 來決定當前 Backstage 內 Controller 與對應設置該解析的資料。理論上會維持在 `versionStable`，當有測試或優先需求時使用 `versionCurrent`，`versionPrevious` 作為 Rollback 選項。

:::

:::caution 注意
API 更新後的規格差異導致的錯誤，應會優先被 Forestage 執行時各 Component 的 `onError` 與 `ErrorBoundary` 捕捉。操作者應與後端同步規格更新，並在 Backstage 編輯以相容。Backstage 日後可新增比對既有 View Config 與當前 Controller Interface (最主要是 Key 與資料類型)，在進入 Backstage 時即自動偵錯，操作者能不透過 Forestage 反應排錯。
:::

## Forestage

使用者登入後使用，流程大致為：

```mermaid
stateDiagram-v2
    direction LR
    [*] --> Backstage
    state Forestage {
      direction LR
      [*] --> User
      User --> Authentication
      User --> Profile
      Authentication --> View
      Profile --> View
    }
    state Backstage {
      direction TB
      [*] --> ViewConfig
    }
    state View {
      [*] --> ViewScopeState
      ViewScopeState --> Components
      Components --> [*]
    }

    ViewConfig --> Forestage
```

## Compatibility

無論是 Controller 或 Component，都會在更新時面臨一個問題：如何處理已在 View 上的邏輯與展示。將問題抽取出來，會造成在 View 上讀寫出錯的原因是因為輸入輸出不合預期 ( 通常發生在前後端更新不一致的情況，或者 UI 與 Input 對不上 )，那麼前端若要保證 Controller 與 Component 間的關係，應在最終的 Component 層做捕捉，以達到 Controller 與 Component 不合時，能直接反應在 UI 上、禁止使用，或者顯示統一的錯誤：

```tsx
import React from 'react';
import { ErrorBoundary } from 'react-error-boundary';
import FallbackComponent from '@component/FallbackComponent';

interface IComponent extends React.Component {}

class Component implements IComponent {
  render() {
    return (
      <ErrorBoundary
        onError={(error) => {
          // 處理 Controller Component 或 UI Component 拋出的錯誤，如 Toast
        }}
        FallbackComponent={FallbackComponent}
      ></ErrorBoundary>
    );
  }
}
```

:::info 提醒
Controller 的 Read 與 Write 仰賴 `IUser.listOfPermission[any].listOfAllowedController` 的識別，那麼當沒有對應權限時應如何處理？所有 Controller 給予 Component 的反應應一致，這樣 Component 可以做統一 UI 展現。
:::

:::info 提醒
每個 Component 都應有兩種表現：Backstage 編輯用以及 Forestage 使用用。
:::
