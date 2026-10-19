# Concept

## 概述

一些關於 Shell Script 的概念。

### 宣告

當檔案開頭宣告 `# !/bin/sh`、在 macOS 上執行正確卻在 Linux 執行錯誤時，可以嘗試以下兩個解決方式。

1. 開頭宣告變為 `#!/bin/bash` 或 `#!/usr/bin/env bash`
2. 使用 `bash` 來執行檔案，如 _bash fileName.sh_ 而不是 _sh fileName.sh_

### 變數

變數只存在單一檔案內，當有複數檔案照順序執行時、亦不會因此引用到其他檔案的變數。

### 大型

若有同樣需求、但為大型專案，應使用 Makefile 構建。

