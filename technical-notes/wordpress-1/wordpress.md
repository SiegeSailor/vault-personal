# WordPress

![WordPress](../.gitbook/assets/image.png)

> WordPress is a free and open-source content management system \(CMS\) based on PHP & MySQL. Features include a plugin architecture and a template system. It is most associated with blogging but supports other types of web content including more traditional mailing lists and forums, media galleries, and online stores. Used by more than 60 million websites, including 33.6% of the top 10 million websites as of April 2019, WordPress is the most popular website management system in use.

Unable to locate WordPress Content directory \(wp-content\)

無法新建目錄

&gt;更改wp-config.php

&gt;ftp 再無法：權限777





安裝主題

upload by backend or ftp

cpenal, php version

變更網址

. Edit the wp-config.php file.

2. After the "define" statements \(just before the comment line that says "That's all, stop editing!"\), insert a new line, and type: `define( 'RELOCATE', true );`

3. Save your wp-config.php file.

4. Open a web browser and manually point it to wp-login.php on the new server. For example, if your new site is at [http://www.yourdomainname.com](http://www.yourdomainname.com/), then type [http://www.yourdomainname.com/wp-login.php](http://www.yourdomainname.com/wp-login.php) into your browser's address bar.

5. Login as per normal.

6. Look in your web browser's address bar to verify that you have, indeed, logged in to the correct server. If this is the case, then in the Admin back-end, navigate to Settings &gt; General and verify that both the address settings are correct. Remember to Save Changes.

7. Once this has been fixed, edit wp-config.php and either completely remove the line that you added \(delete the whole line\), comment it out \(with `//`\) or change the `true` value to `false` if you think it's likely you will be relocating again.

**Note:** When the RELOCATE flag is set to true, the Site URL will be automatically updated to whatever path you are using to access the login screen. This will get the admin section up and running on the new URL, but it will not correct any other part of the setup. Those you will still need to alter manually.

製作流程



form problem &gt; smtp &gt; host problem\(when the sender's email doesn't math with the domain\)



\*\*\*\*

## backup

download the whole files from ftp and use plugins to backup

down Wordpress its self

[https://wordpress.org/download/](https://wordpress.org/download/)

upload Wordpress its self to new ftp path

install

import



extra



