# Trouble Shooting

## Trouble Shooting

Common issues.

### Incorrect string value: \xE7\xA8\x8B\xE5\xBA\x8F

The simplest way to set the default character set for the database to `utf8`.

{% hint style="info" %}
[Here](https://blog.csdn.net/ch717828/article/details/41357431) is an article in Chinese. [Here](https://stackoverflow.com/questions/2108824/mysql-incorrect-string-value-error-when-save-unicode-string-in-django) is another article in English.
{% endhint %}

You can also change the way how you save data,

{% hint style="info" %}
You can also change the way how you save data, check this [Article](https://blog.csdn.net/luo4105/article/details/50804148).
{% endhint %}

### Deadlock found when trying to get lock; try restarting transaction

Two or more points are using the same source or waiting for the same source, to fix it is really depending on how you design.

{% hint style="info" %}
See the [Article](https://www.jb51.net/article/118468.htm).
{% endhint %}

### ERROR 1064 \(42000\) near ' ■/ ' at line 1

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



