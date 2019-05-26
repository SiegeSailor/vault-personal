# HTTP to HTTPS

an issue when change the website from http to https, when all links are broken 

0. if need, open auth for .htaccess, and force ftp view to see hiden files

1. edit the .htaccess file, change the whole code to:

`<IfModule mod_rewrite.c>RewriteEngine OnRewriteCond %{HTTPS} offRewriteRule ^(.*)$ https://%{HTTP_HOST}%{REQUEST_URI} [L,R=301]</IfModule>`

2. go to dashboard &gt; setting &gt; permalink setting, change to plain setting

3. install "really simple ssh" plugin

4. manully fix broekn link from homepage due to permalink changed to plain



[https://www.wpbeginner.com/wp-tutorials/how-to-fix-common-ssl-issues-in-wordpress-beginners-guide/](https://www.wpbeginner.com/wp-tutorials/how-to-fix-common-ssl-issues-in-wordpress-beginners-guide/)

