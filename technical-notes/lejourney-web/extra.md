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

python manage.py collectstatic

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



mysite&gt;settings.py

mysite&gt;components&gt;urls.py

core&gt;urls.py

core&gt;views.py

templates&gt;core or ...&gt;page.html

