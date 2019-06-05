# Multi Language

{% embed url="https://lance.moe/post-322.html" %}

```text
brew install gettext
brew link --force gettext
```

export PATH="/usr/local/opt/gettext/bin:$PATH"



## [Unknown encoding “utf8”](https://stackoverflow.com/questions/4370035/django-makemessages-errors-unknown-encoding-utf8)

cos django see all files while running makemessages, so we whave to ignore files in venv

run 

.... + -i venv

```text
django-admin makemessages -l zh-CN
```

## CommandError: Unable to find a locale path to store translations for file assets/plugins/tinymce/license.txt

mkdir locale

## After editing locale/fr/LC\_MESSAGES/django.co

```text
django-admin compilemessages
```

