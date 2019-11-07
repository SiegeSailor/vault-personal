# Relocate URL

## Relocate URL

First, open `wp-config.php`.

{% hint style="info" %}
You should be able to find wp-config.php in your site **root folder**.
{% endhint %}

After `define` statements, just before the comment line that says **That's all, stop editing!** , insert a new following line.

```php
define( 'RELOCATE', true );
```

Then save the file. Afterward, open a web browser and manually point it to wp-login.php on the new server, it will be like this line.

```markup
http://www.yourdomainname.com/wp-login.php
```

Login as per normal, look in your web browser's address bar to verify that you have, indeed, logged in to the correct server. If this is the case, go to the following path to verify that both the address setting are correct. Remember to **Save Changes**.

Once these have been fixed, edit `wp-config.php` and remove the line that you added with one of these options.

1. Delete the whole line
2. Comment the line out with **//**
3. Change the **true** value to **false**

{% hint style="danger" %}
When the **RELOCATE** flag is set to true, the Site URL will be automatically updated to whatever path you are using to access the login screen. This will get the admin section up and running on the new URL.
{% endhint %}

