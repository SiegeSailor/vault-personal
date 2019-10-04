# Trouble Shooting

## Trouble Shooting

Common issues.

#### Incorrect string value: \xE7\xA8\x8B\xE5\xBA\x8F

The simplest way to set the default character set for the database to `utf8`.

{% hint style="info" %}
[Here](https://blog.csdn.net/ch717828/article/details/41357431) is an article in Chinese. [Here](https://stackoverflow.com/questions/2108824/mysql-incorrect-string-value-error-when-save-unicode-string-in-django) is another article in English.
{% endhint %}

You can also change the way how you save data,

{% hint style="info" %}
You can also change the way how you save data, check this [Article](https://blog.csdn.net/luo4105/article/details/50804148).
{% endhint %}

#### Deadlock found when trying to get lock; try restarting transaction

Two or more points are using the same source or waiting for the same source, to fix it is really depending on how you design.

{% hint style="info" %}
See the [Article](https://www.jb51.net/article/118468.htm).
{% endhint %}

#### ERROR 1064 \(42000\) near ' ■/ ' at line 1

We need two options.

* --default-character-set=utf8 :: This insures UTF8 is used for each field
* --result-file=file.sql :: This option prevents the dump data from passing through the Operating System which likely does not use UTF8. Instead it passes the dump data directly to the file specified.

Using these new options your dump command would look something like this.

```sql
mysqldump -u root -p --default-character-set=utf8 --result-file=database1.backup.sql database1
```

While Importing you can optionally use.

```sql
mysql --user=root --password=root --default_character_set utf8 < database1.backup.sql
```

## 登入 MySQL 時

在一般 Bash 的環境時。

### 無法連結

通常是在重啟後登入時，若遇到 _**ERROR 2002 \(HY000\): Can't connect to local MySQL server through socket '/var/run/mysqld/mysqld.sock' \(2\)**_，則可以透過指定主機位址的方式登入。

```bash
MYSQL -h 127.0.0.1 -u root -p
```

追朔問題，查看 `my.cnf`，可以發現 `[client]` `[mysqld_safe]` `[mysqld]` 皆有 _socket = /var/run/mysqld/mysqld.sock_。那麼，我們就要來尋找該檔案。

```bash
find / -name mysqld.sock
```

若是能找到 `mysqld.sock`，則將 `my.cnf` 中的路徑改為該文件的路徑。

接著重啟 MySQL，則可以正常登入。

```bash
sudo /usr/local/mysql/support-files/mysql.server restart
```

## 在 MySQL 內時

進入 MySQL 後。

### 無法跳脫輸入

每個 SQL 指令都必須以 `;` 結尾，然而，這是非常容易遺忘的步驟，而當忘記輸入結尾符號時，就會進入 MySQL 的 Input Mode。這是可能有以下情況。

* -&gt; :: 輸入 `\c` 即可離開此模式
* "&gt; :: 輸入 " 來回到 -&gt;
* '&gt; :: 輸入 ''' 來回到 -&gt;

### 指令無法生效

當在執行 SQL 語法，如利用 `update` 重置密碼時，若遇到 _**ERROR 1054 \(42S22\): Unknown column '‘root‘' in 'where clause'**_ ，則在語法中引用字串的符號在當下的 Shell 環境無法生效。如，在 Xshell 中以下的指令會產生錯誤。

```sql
UPDATE user SET password=passowrd('mypassword') WHERE User='root';
```

必須將 ' 改為 "，這樣在 Linux 一類的環境下才能生效。

```sql
UPDATE user SET password=passowrd("mypassword") WHERE User="root";
```



