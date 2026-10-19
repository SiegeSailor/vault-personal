# Real Time Database

## 概述

關於 Firebase Real Time Database 的一些操作方法與已知問題。

## Issues

There are some credential authentication exist in firebase services such as **Real Time Database**. In order to use **Real Time Database** on **Cloud Function** locally, you need to set up **Google Cloud Service Account**, which can allow you to serve **Cloud Function** without Google Cloud Environment.

Running a **Cloud Function** with `admin.database()` doesn't require any arguments for `admin.initializeapp()`.

