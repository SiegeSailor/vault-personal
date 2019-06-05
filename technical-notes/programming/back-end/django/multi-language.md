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

You can also use this command to locate the path by Python.

```python
export PATH="/usr/local/opt/gettext/bin:$PATH"
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

You can also use `os.path.join(BASE_DIR, "locale")` to point the path.

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

Declare the following command first.

```python
from django.utils.translation import ugettext_lazy as _
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
Except for values sent from server, this way can also help to translate text in **attributes**, such as **href,** **placeholder**.
{% endhint %}

### In Template

Declare the following code first of the HTML.

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

## Language Translation

### Create .po

Once you have at least one text you want to translate, enter this code in commander. Remember to get in **Venv** first. This code will generate a **django.po** file under `locale/zh_tw/LC_MESSAGES`.

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

This happens due to you didn't declare your encoding method in the files you are going to makemessages. Usually we already declare encoding method on every HTML but not files in Virtualenv, so the fastest way is just ignore them.

### Compile .po to .mo

```bash
django-admin compilemessages -l zh_tw -i venv
```

If you meet _**CommandError: Unable to find a locale path to store translations for file**_, which means you haven't create the folder for locale, just use the command to create the folder in your root.

```bash
mkdir locale
```

