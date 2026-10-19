# 指令集

## 概述

在 PostgreSQL 中常用的 SQL 指令。

### 登入

```sql
psql -h <local_host> -d <database_name> -U <user_name> -p <port>
```

### 顯示

選擇資料庫。

```sql
\c <database_name>
```

顯示所有 Table。

```sql
\dt
```

### 匯入

```sql
psql -h <host_name> -d <database_name> -U <user_name> -f <file_name>
```



