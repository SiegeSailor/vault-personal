# HTTP to HTTPS

## Issue

An issue happens when change the website from HTTP to HTTPS, It might cause all links **broken**. Please following these steps if you encounter this issue.

* Open authentication for `.htaccess,` then force to see hidden files through FTP **FileZilla**.
* Edit `.htaccess`, change the whole code.

```php
<IfModule mod_rewrite.c>RewriteEngine OnRewriteCond %{HTTPS} offRewriteRule ^(.*)$ https://%{HTTP_HOST}%{REQUEST_URI} [L,R=301]</IfModule>
```

* Go to WordPress, Dashboard -&gt; Setting -&gt; Permalink Setting, set to **plain setting**.
* Install **Really Simple SSH** plugin
* Manually fix broken link from homepage due to permalink changed to plain

{% hint style="info" %}
[Here](https://www.wpbeginner.com/wp-tutorials/how-to-fix-common-ssl-issues-in-wordpress-beginners-guide/) is a reference.
{% endhint %}

