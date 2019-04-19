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

```bash
sudo supervisorctl restart web-booking2-release
```



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

css js unable to be read problem: django.contrib.staticfiles.storage.ManifestStaticFilesStorage doesn't work while DEBUG is true, cos Django only read static on DEBUG:False False mode,

so if the version for static files don't match the real one, the most reason is because of that the static/ folder's files haven't been deleted, and Django reads static/ folder first than assest/



fastest way: modify css/js&gt; deploy locally &gt; push to git&gt; git pull &gt; venv &gt; deploy again&gt; use local setting



warning: js in block is also following the rule of template

recaptcha\_\_zh\_tw.js:520 Uncaught Error: Missing required parameters: sitekey is normal when you are not online



test credit card on test server

42424242424242

1111 111



mysite&gt;settings.py

mysite&gt;components&gt;urls.py

core&gt;urls.py

core&gt;views.py

templates&gt;core or ...&gt;page.html





## adding a page

\(create a vendor page\)

mysite/urls.py &gt; add url

url\(r'^booking/\(?P.+\)/\(?P.+\)/$', class\_views.VendorDetailFromSlug.as\_view\(\), name='class.detail.slug.vendor'\),

classpage&gt;views.py

classpage&gt;services.py

core&gt;lejservice.py

creat class-details-verdor.html in templates&gt;classpage



## reverse

core&gt;views/py

from django.urls import reverse

if user is not None and user.is\_active: auth.login\(request, user\) request.session\['token'\] = token request.session\['customer\_id'\] = customer\_id request.session\['class\_filter'\] = None request.session\['watchlist'\] = cs.get\_watchlist\(customer\_id, token\) device\_id = core\_service.generate\_device\_id\(request.META\) request.session\['device\_id'\] = device\_id return HttpResponseRedirect\(reverse\('index'\)\) else: return render\(request, 'core/login-signup.html',{'page':'login'}\)

mysite&gt;urls.py

url\(r'^$', class\_views.LandingPage.as\_view\(\), name='index'\),

> use reverse\('fdsa'\) to direct the user to the url which the name matches

## regular expression

`(?P<category_slug>)` creates a match group named `category_slug`.

The regex itself matches a string starting with `category/` and then a mix of alphanumeric characters, the dash `-` and the underscore `_`, followed by a trailing slash.

Example URLs accepted by the regex:

* category/foo/
* category/foo\_bar-baz/
* category/12345/



