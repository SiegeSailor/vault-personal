# Extra

Your configuration specifies to merge with the ref 'refs/heads/develop' from the remote, but no such ref was fetched.

git branch problem



after git pull

check the version of python pip node npm

```text
python3.6 -m venv venv
```

cd venv

source bin/activate

pip3 install -r ../requirements.txt

npm install

python manage.py migrate \(same with python manage.py syncdb, but you will get Unknown command: 'syncdb' if you are using django 1.7 or later\)

```text
npm run dev 
npm run production
```

python manage.py collectstatic \(python manage.py collectstatic --noinput --clear\)

Restart Django on prod????

python3.6 manage.py runserver

Error: That port is already in use.

sudo lsof -t -i tcp:8000 \| xargs kill -9



database setting is in local\_settings.py

dig +trace classtest.lejourneyapp.com to find the host for databases &gt; 52.77.174.67 \(test database\)

/etc/mysql/mysql.conf.d to find the port

comment bind-address = 127.0.0.1 if you want to connect to the database from external

or

mysite &gt; urls.py &gt; account

core &gt; urls.py &gt; login

core &gt; views.py &gt; Login \(result = lej.login\(params\)\)

notice that from core import lejservice as lej

core &gt; lejservice.py &gt; login\(result = api.do\_request\('/login', 'POST', None, params\)\)

notice that from core import apiservice as api

core  &gt; apiservice.py&gt;do\_request

```text
if (settings.DEBUG == True):
    base_url = settings.BASE_URL_TEST
else:
    base_url = settings.BASE_URL_RELEASE
```

mysite &gt; components &gt; extra.py

BASE\_URL\_TEST = '[http://test.edallianz.net](http://test.edallianz.net)'

BASE\_URL\_RELEASE = '[https://api2.lejourneyapp.com](https://api2.lejourneyapp.com)'

mysite&gt;components&gt;base.py

DEBUG &gt;&gt; from True to False

ALLOWED\_HOSTS = \[\] &gt;&gt; from \[\] to \['\*'\]

after connecting to the database &gt;&gt;&gt;&gt;

\[17/Apr/2019 03:14:44\] "GET /static/plugins/sweet-alert2/sweetalert2.min.190ae6f99653.css HTTP/1.1" 404 134 \[17/Apr/2019 03:14:44\] "GET /static/css/base.min.379ae1fa008a.css HTTP/1.1" 404 110 \[17/Apr/2019 03:14:44\] "GET /static/css/landingPageStyle.min.709d1c9684af.css HTTP/1.1" 404 122 \[17/Apr/2019 03:14:44\] "GET /static/plugins/sweet-alert2/sweetalert2.min.c69b8288412e.js HTTP/1.1" 404 133 \[17/Apr/2019 03:14:44\] "GET /static/images/Logo\_Lejourney\_600px\_white.f4587df16aca.png HTTP/1.1" 404 131 \[17/Apr/2019 03:14:44\] "GET /static/js/jquery.min.2f6b11a7e914.js HTTP/1.1" 404 110 \[17/Apr/2019 03:14:44\] "GET /static/js/jquery.blockUI.dac9d26681c6.js HTTP/1.1" 404 114 \[17/Apr/2019 03:14:44\] "GET /static/js/popper.min.6383a57baa14.js HTTP/1.1" 404 110 \[17/Apr/2019 03:14:44\] "GET /static/js/bootstrap.min.f5273414c773.js HTTP/1.1" 404 113

the _MD5_ Message-Digest Algorithm behind all the static files doesn't match

it is because this : mysite&gt;components&gt;base.py&gt;STATICFILES\_STORAGE = "django.contrib.staticfiles.storage.ManifestStaticFilesStorage"

{% hint style="info" %}
[https://docs.djangoproject.com/en/2.2/ref/contrib/staticfiles/](https://docs.djangoproject.com/en/2.2/ref/contrib/staticfiles/)  
`ManifestStaticFilesStorage`
{% endhint %}





mysite&gt;settings.py

mysite&gt;components&gt;urls.py

core&gt;urls.py

core&gt;views.py

templates&gt;core or ...&gt;page.html

