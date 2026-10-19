# Static Files

## Static Files 404

After connecting to the database, if you see these.

```bash
[17/Apr/2019 03:14:44] "GET /static/plugins/sweet-alert2/sweetalert2.min.190ae6f99653.css HTTP/1.1" 404 134
[17/Apr/2019 03:14:44] "GET /static/css/base.min.379ae1fa008a.css HTTP/1.1" 404 110
[17/Apr/2019 03:14:44] "GET /static/css/landingPageStyle.min.709d1c9684af.css HTTP/1.1" 404 122
[17/Apr/2019 03:14:44] "GET /static/plugins/sweet-alert2/sweetalert2.min.c69b8288412e.js HTTP/1.1" 404 133
[17/Apr/2019 03:14:44] "GET /static/images/Logo_Lejourney_600px_white.f4587df16aca.png HTTP/1.1" 404 131
[17/Apr/2019 03:14:44] "GET /static/js/jquery.min.2f6b11a7e914.js HTTP/1.1" 404 110
[17/Apr/2019 03:14:44] "GET /static/js/jquery.blockUI.dac9d26681c6.js HTTP/1.1" 404 114
[17/Apr/2019 03:14:44] "GET /static/js/popper.min.6383a57baa14.js HTTP/1.1" 404 110
[17/Apr/2019 03:14:44] "GET /static/js/bootstrap.min.f5273414c773.js HTTP/1.1" 404 113
```

You will see the **MD5** Message-Digest Algorithm behind all the static files do not match. It is because `django.contrib.staticfiles.storage.ManifestStaticFilesStorage` .

Path :: mysite -&gt; components -&gt; base.py -&gt;

`STATICFILES_STORAGE = "django.contrib.staticfiles.storage.ManifestStaticFilesStorage"`

The reason for static files unable to be read is because that `ManifestStaticFilesStorage` doesn't work while **DEBUG** is **True**, Django only read static files on **DEBUG: False** mode. The other reason might be that static/ folder haven't been deleted, and Django reads `/static` folder first rather than `/assets`. 

In the end, you should just add `/assets` folder to the path of `/static`.

```python
STATIC_URL = '/static/'
STATICFILES_DIRS = [
    os.path.join(BASE_DIR, "assets"),
]
STATICFILES_STORAGE = "django.contrib.staticfiles.storage.ManifestStaticFilesStorage"
STATIC_ROOT = os.path.join(BASE_DIR, 'static')
```

{% hint style="info" %}
See the [Official Document](https://docs.djangoproject.com/en/2.2/ref/contrib/staticfiles/) to know more.
{% endhint %}

