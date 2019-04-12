# WordPress

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



**Neuromath**  


* URL: [http://35.237.42.42/wp-login.php?loggedout=true](http://35.237.42.42/wp-login.php?loggedout=true) 
* Server tmp: [http://35.237.42.42/wp-login.php?loggedout=true](http://35.237.42.42/wp-login.php?loggedout=true) 
* WordPress account: Neuromath 
* WordPress password: ZPico\#D@lI%j\#UKXca 
* FTP account: marketing 
* FTP password: 123BSwxw 
* FTP path: 35.237.42.42
* ask@neuromath.com.sg
* murena.websitewelcome.com
* hfe\_SnhRIzq!

**abrakadoodle**  


* URL: [http://www.abrakadoodle.com.sg/](http://www.abrakadoodle.com.sg/) 
* Server tmp: [http://103.104.196.82/~abrakado/wp-admin](http://103.104.196.82/~abrakado/wp-admin) 
* WordPress account: FAdmin 
* WordPress password: fesfe3$ 
* FTP account: [awios@abrakadoodle.com.sg](mailto:awios@abrakadoodle.com.sg) 
* FTP password: fesfe3$\#@! 
* FTP path: 103.104.196.82 
* CPenal account: [gracelow@abrakadoodle.com.sg](mailto:gracelow@abrakadoodle.com.sg) 
* CPenal password: 93660260



webmaster@edallianz.com \| Edallianz$\#[  
](https://edallianz.bitrix24.com/desktop_app/?BXD_API_VERSION=42&user_lang=en&login=rd5@rd.edallianz.com#bx-im-go-Wed%20Mar%2006%202019%2009:56:07%20GMT+0800%20%28CST%29)[https://read.lejourneyapp.com/wp-admin/](https://read.lejourneyapp.com/wp-admin/)

{% embed url="https://read.lejourneyapp.com/tw/wp-admin/" %}

acccount wordpress

password 123456789JJJWWWiii!!!

url 52.77.174.67



## backup

download the whole files from ftp and use plugins to backup

down Wordpress its self

[https://wordpress.org/download/](https://wordpress.org/download/)

upload Wordpress its self to new ftp path

install

import

