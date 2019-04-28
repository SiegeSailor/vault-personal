# URL Modifying

變更網址

. Edit the wp-config.php file.

2. After the "define" statements \(just before the comment line that says "That's all, stop editing!"\), insert a new line, and type: `define( 'RELOCATE', true );`

3. Save your wp-config.php file.

4. Open a web browser and manually point it to wp-login.php on the new server. For example, if your new site is at [http://www.yourdomainname.com](http://www.yourdomainname.com/), then type [http://www.yourdomainname.com/wp-login.php](http://www.yourdomainname.com/wp-login.php) into your browser's address bar.

5. Login as per normal.

6. Look in your web browser's address bar to verify that you have, indeed, logged in to the correct server. If this is the case, then in the Admin back-end, navigate to Settings &gt; General and verify that both the address settings are correct. Remember to Save Changes.

7. Once this has been fixed, edit wp-config.php and either completely remove the line that you added \(delete the whole line\), comment it out \(with `//`\) or change the `true` value to `false` if you think it's likely you will be relocating again.

**Note:** When the RELOCATE flag is set to true, the Site URL will be automatically updated to whatever path you are using to access the login screen. This will get the admin section up and running on the new URL, but it will not correct any other part of the setup. Those you will still need to alter manually.

