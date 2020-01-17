# 指令集

## 概述

在 MySQL 中常用的 SQL 指令。注意 SQL 並不區分大小寫，但習慣性指令使用大寫、參數使用小寫。

## 遠端登入

```sql
MYSQL -h ec2-3-1-156-249.ap-southeast-1.compute.amazonaws.com -P 3307 -u test_service_rating -p
```

* MYSQL :: 宣告使用 MySQL
* -h :: 主機的位址，如果已在該主機或 localhost 中則不需此參數
* -P :: 連線指定的 port
* -u :: 使用者帳號
* -p :: 說明此使用者需要密碼，會在此指令後出現命令輸入列

#### 常見 MySQL 資訊

```sql
DATABASE_NAME=edallianz2_release_rating
MYSQL_ROOT_ACCOUNT=service_rating
MYSQL_ROOT_PASSWORD=bsF3McM6Rn8gwFEN
MYSQL_PORT=3306
MYSQL_HOST=ec2-3-1-156-249.ap-southeast-1.compute.amazonaws.com
```

## 執行控制

若在 macOS 環境下，可由系統偏好設定進入 MySQL 來啟動與停止。

### 啟動與停止

啟動 MySQL Server。

```bash
sudo /usr/local/MySQL/support-files/mysql.server start
```

停止 MySQL Server。

```bash
sudo /usr/local/mysql/support-files/mysql.server stop
```

重啟 MySQL Server。

```bash
sudo /usr/local/mysql/support-files/mysql.server restart
```

#### 錯誤處理

當在啟動、停止或重啟 MySQL Server 時，若遇到 _**MySQL server PID file could not be found**_，必須進行以下的排除方法。首先，確認正在執行的線程，取得 mysql 的 `pid`。

```bash
ps aux | grep mysql
```

{% hint style="info" %}
ps aux 列出的線程中，pid 為左邊第二個欄位。
{% endhint %}

接著，利用 kill 刪除所有 MySQL 相關的線程。

```bash
sudo kill <pid1> <pid2> <pid3>
```

再來才繼續進行啟動、停止或重啟。

### 啟動 MySQL Safe Mode

```bash
mysqld_safe --skip-grant-tables &
```

* --skip-grant-tables :: 忽略權限表
* & :: 在背景執行

#### 更改 root 密碼

在遺失 root 密碼的情況下，可以藉由 MySQL Safe Mode 的無權限方式登入。在執行以下動作前，必須先停止 MySQL Server 以及啟動 MySQL Safe Mode。

```bash
mysql -u root
```

選擇 `mysql` 本資料庫。

```sql
USE mysql;
```

接著利用 `update` 修改密碼。

```sql
UPDATE user SET authentication_string=password("talimusd11") WHERE User="root";
```

使用者權限更改後的載入。

```sql
FLUSH PRIVILEGES;
```

啟動 MySQL Server。

```bash
sudo /usr/local/MySQL/support-files/mysql.server start
```

## MySQL Admin

建立 MySQL Admin 帳號。

```sql
MYSQLADMIN -u root password thepassword
```

更改 Admin 帳號名稱。

```sql
UPDATE SET USER="new_name" WHERE USER="old_name";
```

## MySQL

登入到相關操作。

#### 錯誤處理

若遇到 _**zsh: command not found: mysql**_，執行以下指令並重啟 Shell。

```bash
export PATH=${PATH}:/usr/local/mysql/bin/
source ~/.zshrc              #If you use Oh-My-Zsh
source ~/.bashrc             #If you use Default Bash
```

### 使用者操作

登入使用者。

```sql
MYSQL -u root -p
```

登入使用者，並直接查看一 database。

```sql
MYSQLSHOW -u root -p 'the_database';
```

建立使用者。

```sql
CREATE USER 'my_user'@'localhost' IDENTIFIED BY 'my_password';
```

* @'localhost' :: 使用者被允許進入的主機，**%** 為任意，**localhost** 為本機，通常為 IP

顯示所有使用者帳號。

```sql
SELECT USER FROM mysql.user;
```

* USER :: 帳號欄
* FROM :: 指定的資料庫，此處為 MySQL 本身，即全域。使用者資料是記在 MySQL 本身
* mysql.USER :: MySQL 本身下的 USER Table

顯示使用者帳號權限。

```sql
SHOW GRANTS FOR 'rating_release_vernal'@'%';
```

改變使用者密碼。

```sql
ALTER USER 'service_social'@'%' IDENTIFIED BY '86QPSV6dJawNNcBdgjdCjVPhwk47JfRweC53hvcN';
```

* ALTER :: 改變既有資料
* USER :: 指定使用者
* @'%' :: 使用者被允許進入的主機，**%** 為任意，**localhost** 為本機，通常為 IP
* IDENTIFIED BY :: 密碼

給予使用者一資料庫的表的全部權限。

```sql
GRANT ALL PRIVILEGES ON the_database.the_table TO 'service_social'@'%';
```

給予使用者一資料庫的觀看權限。

```sql
GRANT SELECT ON `edallianz2_release_lejourney`.* TO 'rating_release_vernal'@'%'
```

使用者權限更改後的載入。

```sql
FLUSH PRIVILEGES;
```

### 資料庫操作

顯示該主機上的 database。

```sql
SHOW DATABASES;
```

建立 database。

```sql
CREATE DATABASE the_database;
```

刪除 database。

```sql
DROP DATABASE the_database;
```

備份 database。

```sql
MYSQLDUMP -u root -p 'the_database' > /db/the_database.sql
```

```sql
MYSQLDUMP -P 3306 -h 192.168.20.151 -u root -p 'the_database' > /db/the_database.sql
```

```bash
ssh -i ~/.ssh/edallianz-dl.pem ubuntu@13.250.137.148 'mysqldump -u root -p --all-databases' > /Users/eda-001/Documents/AWS.Backup.20190910/DL/allDatabases.sql
```

匯入 database。

```sql
MYSQL -u root -p 'the_database' < /db/the_database.sql
```

進入指定的 database。

```sql
USE the_database;
```

### 其他操作

查看狀態。

```sql
SHOW STATUS;
```

查看進程。

```sql
SHOW PROCESSLIST;
```

離開 MySQL。

```sql
EXIT
```

## MySQL 資料庫內

{% hint style="info" %}
操作範例，來自 GTWang，[MySQL/MariaDB 新增資料庫、建立使用者帳號與資料表指令教學](https://blog.gtwang.org/linux/mysql-create-database-add-user-table-tutorial/)。
{% endhint %}

顯示 database 內的 table。

```sql
SHOW TABLES;
```

建立 table `product`。

```sql
CREATE TABLE products (  # 新增產品資料表
  id INT NOT NULL AUTO_INCREMENT, # 產品 ID
  name varchar(50) NOT NULL,  # 名稱
  descr varchar(200),  # 說明
  price INT NOT NULL,  # 價格
  PRIMARY KEY(id)      # 主要索引
);
```

對該 table `product` insert 資料。

```sql
INSERT INTO products (name, descr, price)
  VALUES ("葵花寶典", "蓋世武功密集", 990);
INSERT INTO products (name, descr, price)
  VALUES ("獨孤九劍", "劍魔獨孤求敗所創劍法", 890);
```

查詢資料。

```sql
SELECT * FROM products;
```

```sql
DESCRIBE products;
```

{% hint style="info" %}
若要給予使用者僅讀的權限，可以參考查詢資料的語法。
{% endhint %}

修改資料。

```sql
UPDATE products SET descr = "東方不敗的絕世武功" WHERE name = "葵花寶典";
```

刪除資料。

```sql
DELETE FROM products WHERE name = "葵花寶典";
```

刪除資料表。

```sql
DROP TABLE products;
```

### 檢查與修復

* ISAMCHK :: 是用來修復、檢查和優化 .ism 後綴的數據庫文件
* MYISAMCHK :: 是用來修復 .myi 後綴的數據庫文件

假設我們要對一個名為 linux 數據庫的 `.myi` 數據庫表檢查是否存在問題，首先應停止 MySQL Server。

```bash
sudo /usr/local/mysql/support-files/mysql.server stop
```

再來執行以下指令檢查所有的 `.myi` 文件，而數據庫的目錄就在 `../var/linux/` 中。

```bash
myisamchk ../var/linux/*.MYI
```

若是有問題，應用 `-r` 參數修復。

```bash
myisamchk -r ../var/linux/*.MYI
```

