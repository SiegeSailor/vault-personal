# Installation

## Theme

#### Via Backend

Use admin backend to upload your theme.

```text
wp-admin/Appearance/Themes/Add New
```

If you meet _**Unable to locate WordPress Content directory \(wp-content\)**_ , try to add this line to your `wp-config.php` . This is a common issue.

```php
define('FS_METHOD', 'direct');
```

{% hint style="info" %}
You should be able to find **wp-config.php** in your site **root folder**.
{% endhint %}

Then go back to backend interface, choose the theme you want to use.

#### Via FTP

Upload your theme to the following path via FTP.

```text
wordpress/wp-content/themes
```

{% hint style="info" %}
If you are not able to overwrite any file or upload any theme to your WordPress Site via FTP, please ask your host to grant you authentication **777**.
{% endhint %}

Then go back to backend interface, choose the theme you want to use.

{% hint style="warning" %}
If your themes have been successfully installed but not working, check your **PHP version** via your host. You can use plugin **PHP Compatibility Checker** to check out needed PHP version for your theme in the other hand.
{% endhint %}

## Plugin

Use backend interface to browse and install plugins.

```text
wp-admin/Plugins/Add New
```

{% hint style="warning" %}
If your plugins have been successfully installed but not working, check your **PHP version** via your host. You can use plugin **PHP Compatibility Checker** to check out needed PHP version for your plugin in the other hand.
{% endhint %}

