---
sidebar_position: 2
---

# Why Test

先看一下一般前端開發後的測試流程：

```bash
├── 開發或重構
├── 部署
│   ├── PM 大致確認跟 PRD 描述一致
│   │   ├──通過，前往 QA
# highlight-next-line
│   │   └── 失敗，回到開發或重構
│   ├── QA 以 Test Cases 為準則逐項確認
│   │   ├──通過，前往 UAT
# highlight-next-line
│   │   └── 失敗，回到開發或重構
│   ├── UAT
│   │   ├──通過，前往 Live Testing
# highlight-next-line
│   │   └── 失敗，回到開發或重構
│   ├── Live Testing
│   │   ├──通過，前往上線
# highlight-next-line
│   │   └── 失敗，回到開發或重構
└── 上線
```

:::info 提醒
**UAT**，User Acceptance Testing，有測試項目與時程計劃地在一個環境內去測試，通常是 Live 的前一步或兩步。
:::

過程冗長，每次都需要重頭來過，而且仍然有些問題會產生：

- 通常 UAT 前不會測試全部功能，在 UAT 才發現改 A 壞 B 的情況
- 共用元件一直沒人敢動，最後自己實作一個一樣的
- Refactor 難以下手

而在引入測試後，可以將整個流程改為：

```bash
├── 開發或重構
├── 自動測試
│   ├── 通過，前往部署
# highlight-next-line
│   └──  失敗，回到開發或重構
├── 部署
│   ├── PM 大致確認跟 PRD 描述一致
│   └── 通過，前往 QA
└── ...
```

可以看見，自動測試會是在部署之前 (通常會在 Continuous Integration 內)，開發者可以直接看見最靠近 Source Code 的錯誤訊息。撇除有要求測試覆蓋率的情況，即使只針對關鍵功能撰寫測試，也能大量減少人員溝通來回的成本、排除通靈 Debug 的情況。
