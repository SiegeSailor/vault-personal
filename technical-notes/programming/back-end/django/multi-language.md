# Multi Language

## Precondition

Since usually people start Django projects in a **Virtualenv**, here is the versions you should use inside the **Virtualenv**.

* Django :: 1.11.14
* Gettext :: 0.19.8.1
* Operating System :: macOS

You can use the following command to install **Gettext**.

```bash
brew install gettext
```

Then use this command to symlink.

```bash
brew link --force gettext
```

Use this command to locate the path by Python.

```python
export PATH="/usr/local/opt/gettext/bin:$PATH"
```

If you need to use other ways to use **Gettext** function, try this **PIP** package.

```bash
pip install python-gettext
```

## Settings

You can find `settings.py` under your project **Root** app folder.

### Middleware

Insert Locale to your middleware like this. You must place **LocaleMiddleware** after **SessionMiddleware** and before **CommonMiddleware**.

```python
MIDDLEWARE = [
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.locale.LocaleMiddleware',
    'django.middleware.common.CommonMiddleware',
]
```

### I18N

Set these values as true, if there is no related values just create your own.

```python
USE_I18N = True
USE_L10N = True
USE_TZ = True
```

### Locale Path

```python
LOCALE_PATHS = (
    'locale',
)
```

You can also use these codes to point to the path.

```python
os.path.join(BASE_DIR, "locale")
```

```python
SITE_ROOT = os.path.dirname(os.path.realpath(__name__))
LOCALE_PATHS = (os.path.join(SITE_ROOT, 'locale'), )
```

The following code can help you to know all real path pointing to in your setting.

```bash
python manage.py diffsettings --all
```

### Language Menu

Command this on the head of `settings.py` to gettext in `settings.py`.

```python
from django.utils.translation import ugettext_lazy as _
```

Then add the following codes.

```python
LANGUAGES = (
    ('zh-tw', _('Tranditional Chinese')),
    ('en-us', _('English')),
)
```

### Language Code

Set up the current language for this website, this is only happening when the website only needs one kind of language or during developing. In this case, this website is developing `zh-tw` language.

```python
# LANGUAGE_CODE = 'en-us'
LANGUAGE_CODE = 'zh-tw'
```

## Get Text

The way Django translate is to collect the text you want to translate first, then translate them in a single **.po** file. There are different ways for different document to collect text.

### In View

Declare the following command first of the **View**.

```python
from django.utils.translation import ugettext as _
```

Then use this kind of way to collect text.

```python
text_trans = _("I am now English.")
response_text = text_trans
```

Usually we want to render the HTML we want to show, so your return code might be this.

```python
return render(request, 'index.html', {"response_text": response_text})
```

In the HTML you want to translate, you will be using the same way to set tags as what you normally do to values. Here is an example.

```markup
<a href="{{ response_text }}">I am a Link.</a>
```

{% hint style="info" %}
Except for values sent from server, this way can also help to translate text in **attributes**, such as href, placeholder, etc.
{% endhint %}

### In Modal

Declare the following command first of the **Modal**.

```text
from django.utils.translation import ugettext_lazy as _
```

The method is as same as **Views**.

{% hint style="warning" %}
If you translate text in modal, it might cause _**1366, "Incorrect string value: '\\xF0\\x9F\\x98\\x81**_**'** because of the MySQL table setting for encoding, the encode type for the value transmit between modal and table must be the same, the solutions can be these two.

* Only translate text in Front-End
* Change the encode setting for the table :: **utf8mb4**, **utf8mb4\_general\_ci**

See this [Article](https://blog.csdn.net/HHTNAN/article/details/76769264) to know why.
{% endhint %}

{% hint style="warning" %}
If you meet _**manager\_method\(\) keywords must be strings**_, which means there are some text which you translate must be a **string**, check the view if there is any, or modal.
{% endhint %}

### In Template

Declare the following code first of the **HTML**.

```python
{% load i18n %}
```

{% hint style="info" %}
This code should be declared in every single HTML which has text you want to translate, no matter you use **extends** or **include**.
{% endhint %}

For simple text, such as a word.

```markup
<div>{% trans 'You may want to know more.' %}</div>
```

For complex text, such as a sentence, or text with punctuation marks

```markup
 <div>{% blocktrans %}This is a very, greatly, complex sentence!{% endblocktrans %}</div>
```

### In JavaScript

Declare this code first of the **URL**.

```python
from django.views.i18n import JavaScriptCatalog
```

{% hint style="warning" %}
**javascript\_catalog** has been deprecated after Django 1.10, since we are using Django 1.11, we use JavaScriptCatalog instead.
{% endhint %}

Then add this to your urlpatterns. Notice that this line **can not be modified**.

```python
urlpatterns = [
        url(r'^jsi18n/$', JavaScriptCatalog.as_view(), name='javascript-catalog'),
]
```

Import the URL in the bottom of the HTML.

```markup
<script type="text/javascript" src="{% url 'javascript-catalog' %}"></script>
```

After putting the URL into HTML, you might meet some issue such as _**500 INTERNAL SERVER ERROR**_ on console or _**invalid token in plural form:%s' % value i**n log_, this is because of that the plural form setting in .po file is too open, this solution is to remove `Plural-Forms: nplurals=INTEGER; plural=EXPRESSION;\n` from all`.po` and `.mo` files in the project.

If you already makemessages to all files in the project, just remove all `.po` and `.mo` at once and makemessages again.

Use this way to get the text you want to translate in JS, it is similar with view.

```javascript
var districtText = gettext('District');
```

Here are other methods to get text in JavaScript files.

* gettext
* ngettext
* interpolate
* get\_format
* gettext\_noop
* pgettext
* npgettext
* pluralidx

{% hint style="info" %}
To know how to use the methods, read this [Article](https://docs.djangoproject.com/en/2.2/topics/i18n/translation/).
{% endhint %}

### In CSS

There is no possible for Django i18N to directly translate content in CSS, but you can use JavaScript to append your style and translation content to your HTML head. The following example uses jQuery.

```javascript
var testTrans = gettext('This is a CSS content text.')
$('<style>.test:before {content:" ' + testTrans + '" !important;}' +'</style>').appendTo('head');
```

## Language Translation

### Create .po

Once you have at least one text you want to translate, enter this code in commander. Remember to activate **Venv** first. This code will generate a **django.po** file under `locale/zh_tw/LC_MESSAGES`.

#### Views, Templates, and Modals Usage

```bash
django-admin makemessages -l zh_tw -i venv
```

`zh_tw` is your language folder name, also it is according to your language code.

{% hint style="info" %}
In this project, we set one of our LANGUAGE\_CODE as **zh-tw** in **settings.py**, so we use zh\_tw as the folder name. Notice the difference between \_ and -.
{% endhint %}

{% hint style="info" %}
If you are using Django which is above 1.7, use **zh-hant** instead of **zh-tw**.
{% endhint %}

-i venv means to ignore files in your **Virtualenv** folder, because of that if we makemessages including files in Virtualenv folder will occur _**Unknown encoding "utf8"**_ issue.

#### Unknown Encoding UTF-8

This happens due to you didn't declare your encoding method in the files you are going to makemessages. Usually we already declare encoding method on every HTML but not files in Virtualenv, so the fastest way is just ignore them with `-i venv`.

You can also insert these code in `settings.py` to prevent it.

```python
DEFAULT_CHARSET = 'UTF-8'
FILE_CHARSET = 'UTF-8'
```

#### JS files Usage

```bash
django-admin makemessages -d djangojs -i assets/plugins -i node_modules -i static
```

Use `-i` to ignore files you don't need to translate.

{% hint style="warning" %}
You might meet _**warning: unterminated string**_, which probably means you misplace some **'** and **"** in your script sentence, check your JavaScript file if there is any.
{% endhint %}

### Compile .po to .mo

```bash
django-admin compilemessages -l zh_tw
```

If you meet _**CommandError: Unable to find a locale path to store translations for file**_, which means you haven't create the folder for locale, just use the command to create the folder in your root.

```bash
mkdir locale
```

## Switch

Ways to switch language on Website.

### Activate

The code `activate('language code')` can force a view turn to a specific language, so there is a way to achieve language switching.

#### In Service

First, declare this on the top of the service file.

```python
from django.utils.translation import activate
```

Code a service so that you can use this service in all views.

```python
def activate_language(request):
    if 'language' in request.session:
        if request.session['language'] == 'Chinese':
            activate('zh-tw')
            request.LANGUAGE_CODE = 'zh-tw'
        else:
            activate('en-us')
            request.LANGUAGE_CODE = 'en-us'
    return
```

As you can see, we use `session` to save the current language selection, and decide which language we are forcing to turn by the selection. We also set `LANGUAGE_CODE` to know which language is the current one.

Use `if` in case the error caused by _**no default language code setting**_.

#### In URL

Assign an URL to make a simple API so that you can trigger the activate language service by JS.

```python
url(r'^translation/(?P<language>.+)/$', views.Translation.as_view(), name='translation'),
```

Then create a view for the URL.

```python
class Translation(View):
    def get(self, request, language=''):
        service.activate_language(request)
        request.session['language'] = language
        return HttpResponseRedirect('/')
```

#### In View

Import the service to activate the language.

```python
service.activate_language(request)
```

Remember, If you are using such ways to get text in view, you must put the service upon them. **Activate line must be the first in the view**.

```python
class LandingPage(View):
    def get(self, request):
        service.activate_language(request)
        response_first_purchase = ''
        if request.user.is_authenticated():
            result_first_purchase = lej.check_first_purchase(customer_id)
            if result_first_purchase['data'] is True:
                first_purchase_trans = _("10% Off For Your First Purchase")
                response_first_purchase = first_purchase_trans
            if result_first_purchase['data'] is False:
                response_first_purchase = ''

        translation = {
            "read_url": _("https://read.lejourneyapp.com"),
            "search_placeholder": _("Search"),
        }

        results = cs.get_landing_page(search_filter=search_filter)
        for index in range(len(results['lower'])):
            if results['lower'][index]['price_1'] == 'Reservation':
                results['lower'][index]['price_1'] = _('Reservation')
            if results['lower'][index]['price_1'] == 'Free':
                results['lower'][index]['price_1'] = _('Free')
        for index in range(len(results['upper'])):
            if results['upper'][index]['price_1'] == 'Reservation':
                results['upper'][index]['price_1'] = _('Reservation')
            if results['upper'][index]['price_1'] == 'Free':
                results['upper'][index]['price_1'] = _('Free')

        return render(request, 'classpage/landing-index.html', {"classes": results, "response_first_purchase": response_first_purchase, "translation": translation})
```

{% hint style="warning" %}
You must use **render** rather than **render\_to\_response** in this method.
{% endhint %}

#### In Static

Assume you have the following code as your language function part.

```markup
<div class="languageSwitch-container">
     <div class="languageSwitch">
          <div class="languageSwitch-item"><a href="#">{% trans 'English' %}</a></div>
          <div class="languageSwitch-item"><a href="#">{% trans 'Chinese' %}</a></div>
     </div>
</div>
```

And use this simple JS to trigger the service.

```javascript
var languageMenu = document.querySelectorAll('.languageSwitch-item a');
  for (var i = 0; i < languageMenu.length; i = i + 1) {
    languageMenu[i].addEventListener('click', function (event) {
      event.preventDefault();
      var languageCode = this.textContent;
      var request = new XMLHttpRequest();
      request.open('get', '/translation/' + languageCode + '/', true);
      request.send(null);
      request.onload = function () {
        if (request.readyState == 4 && request.status == 200) {
          location.reload();
        } else {
          alert("{% trans 'Language did not switch for some reason.' %}")
        }
      }
    })
  }
```

## Unknown Issue

There are some issues that i couldn't figure out.

#### List Assigning

This does work.

```python
for index in range(len(results['upper'])):
    if results['upper'][index]['price_1'] == 'Reservation':
        results['upper'][index]['price_1'] = _('Reservation')
    if results['upper'][index]['price_1'] == 'Free':
        results['upper'][index]['price_1'] = _('Free')
```

But this doesn't work.

```python
for index in range(len(results['upper'])):
    upper_price = results['upper'][index]['price_1']
    if upper_price == 'Reservation':
        upper_price = _('Reservation')
    if upper_price == 'Free':
        upper_price = _('Free')
```

#### URL With Include

This does work. This is in main `url.py`.

```python
urlpatterns = [
    url(r'^account/', include('core.urls')),
    url(r'^translation/switch/$', views.TranslationSwitch.as_view(), name='translation.switch'),
]
```

This doesn't work. This is `core.urls`.`core_views` is a `views.py` file in another app filed.

```python
urlpatterns = [
    url(r'^translation/switch/$', core_views.TranslationSwitch.as_view(), name='translation.switch'),
]
```

