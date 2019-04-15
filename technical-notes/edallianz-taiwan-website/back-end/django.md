# Django

### 

### 

### runserver\(if there is a project\)

python3.6 manage.py runserver



### django

若 static 和 assets 都有，會優先讀 static 內的



BASE_DIR = os.path.dirname\(os.path.dirname\(os.path.abspath\(\_file_\)\)\)

ROOT\_URLCONF = 'EdallianzTW.urls'

TEMPLATES = \[ { 'BACKEND': 'django.template.backends.django.DjangoTemplates', 'DIRS': \[os.path.join\(BASE\_DIR,'templates'\)\], 'APP\_DIRS': True, 'OPTIONS': { 'context\_processors': \[ 'django.template.context\_processors.debug', 'django.template.context\_processors.request', 'django.contrib.auth.context\_processors.auth', 'django.contrib.messages.context\_processors.messages', \], }, }, \]



STATIC\_URL = '/static/' STATICFILES\_DIRS = \[ os.path.join\(BASE\_DIR, "assets"\), \]

STATICFILES\_STORAGE = "django.contrib.staticfiles.storage.ManifestStaticFilesStorage"

STATIC\_ROOT = os.path.join\(BASE\_DIR, 'static'\)



from django.conf.urls import include, url from django.contrib import admin from Edallianzweb import views

urlpatterns = \[

```text
# url(r'^admin/', admin.site.urls),
url(r'^tw/', include('Edallianzweb.urls')),
```

\]



from django.conf.urls import include, url from django.contrib import admin from . import views urlpatterns = \[ url\(r'^sitemap.xml$', views.sitemap, name='sitemap'\), url\(r'^$', views.index, name='index'\), url\(r'^index/$', views.index, name='index'\), url\(r'^index.html$', views.index, name='index'\), url\(r'form/$', views.emailView, name='form'\), url\(r'signup/$', views.signupView, name='signup'\), url\(r'payment/$', views.paymentView, name='payment'\), url\(r'^captcha/', include\('captcha.urls'\)\), url\('^ajax\_val/', views.ajax\_val, name='ajax\_val'\), url\(r'refresh/$', views.captcha\_refresh, name='captcha-refresh'\), url\(r'privacy/$', views.privacy, name='privacy'\), \]



def index\(request\): form = ContactForm\(\) return render\(request, 'EdallianzTW/index.html', {'client\_list': client\_list, 'form': form\['captcha'\]}\)

def sitemap\(request\): return render\(request, 'EdallianzTW/sitemap.xml'\)



python manage.py collectstatic



