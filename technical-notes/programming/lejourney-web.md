# LeJourney WEB

> LeJourney is a online booking web application, it has reached version 2.0, but still needs a lot of maintenance and improvement. That is a need to write down all steps to survey this system since I am a Front-End Developer and UIUX Designer rather than a Back-End or MIS Engineer. The project name on server is **WebBooking2**.

## Links

All the relevant links.

* Release Web :: [LeJourney App Marketplace](https://class.lejourneyapp.com/)
* Test Web :: [LeJourney App Marketplace](https://classtest.lejourneyapp.com/)
* GitLab :: [LeJourneyWeb](http://35.197.146.136/ZhangJinYu/LeJourneyWeb)

## Environment

* Operating System :: macOS
* Server System :: Ubuntu
* Apache ::
* PHP :: 7.1.23 NTS
* Composer :: 1.6.4
* MySQL ::
* Node :: 10.15.1
* NPM :: 6.4.1
* Webpack :: 3.12.0
* Python :: 3.6.5
* PIP :: 9.0.3
* Django :: 1.11.14

## Start Local Project

Assuming that you have initialed a virtual environment.

```bash
pip install -r requirements.txt
```

You might need to use `pip3` instead of `pip`.

```bash
npm install
```

```bash
python manage.py migrate
```

You can also use `manage.py syncdb`, but this script doesn't work if you are using Django 1.7 or later, you will get _**Unknown command: 'syncdb'**_.

## Local Script

Due to this project is mixed with **Virtualenv** and **NPM**, the script might be a little bit complex.

### Start

First enter venv mode.

```bash
source venv/bin/activate
```

Then start the local server.

```bash
python manage.py runserver
```

### Front End Compile

```bash
npm run dev
```

### Language Compile

Make and get text from **Template**, **BackEnd**, and **FrontEnd**.

```bash
django-admin makemessages -l zh_tw -i venv
```

Remember you have to make twice, including `zh_tw` and `en_us`.

Make and get text from **JavaScript**

```bash
django-admin makemessages -d djangojs -i assets/plugins -i node_modules -i static
```

**Compile** Language

```bash
django-admin compilemessages -l zh_tw
```

Remember you have to Compile twice, including `zh_tw` and `en_us`.

If you meet Error: That port is already in use. while testing locally, use the following code.

```bash
sudo lsof -t -i tcp:8000 | xargs kill -9
```

### Git

This step should follow your **Git Flow**. Switch to master branch.

```bash
git merge develop
```

```bash
git push
```

## Test Server Script

### Git

This step should follow your **Git Flow**.

```bash
git pull
```

After pulling, check your locale folder, you might see **zh\_tw** and **zh\_TW** in the same time, in this case, try to reset to last head, after ward us `mv zh_TW zh_tw`, then `git pull` again to fully replace the original one.

The folder's country code should be **uppercase**, but sometimes git might cause capital letter / small letter issue after pulling. when it happens, you should just make sure your `locale` is replaced by the newest commit.

Here is the steps if you find out there are these folders in your **locale** folder after git `pull`.

* zh\_tw
* zh\_TW
* en\_us
* en\_US

```bash
git reset lastCommitNumber
```

```bash
git add .
```

```bash
git stash
```

Now you are totally back to the last commit. Then you can rename **zh\_tw** and **en\_us** to **zh\_TW** and **en\_US**. Then use this to fully update.

```bash
git pull
```

### Start

First enter venv mode.

```bash
source venv/bin/activate
```

### Front End Compile

```bash
npm run dev
```

### Activate

```bash
sudo supervisorctl restart web-booking2-test
```

{% hint style="info" %}
SGD test credit card number is **4242 4242 4242 4242,** TWD test credit card number is **4000 2211 1111 1111**.
{% endhint %}

## Online Server Script

### Git

This step should follow your **Git Flow**.

```bash
git pull
```

After pulling, check your locale folder, you might see **zh\_tw** and **zh\_TW** in the same time, in this case, try to reset to last head, after ward us `mv zh_TW zh_tw`, then `git pull` again to fully replace the original one.

The folder's country code should be **uppercase**, but sometimes git might cause capital letter / small letter issue after pulling. when it happens, you should just make sure your `locale` is replaced by the newest commit.

Here is the steps if you find out there are these folders in your **locale** folder after git `pull`.

* zh\_tw
* zh\_TW
* en\_us
* en\_US

```bash
git reset lastCommitNumber
```

```bash
git add .
```

```bash
git stash
```

Now you are totally back to the last commit. Then you can rename **zh\_tw** and **en\_us** to **zh\_TW** and **en\_US**. Then use this to fully update.

```bash
git pull
```

### Start

First enter venv mode.

```bash
source venv/bin/activate
```

### Front End Compile

```bash
npm run production
```

### Static Compile

```bash
python manage.py collectstatic
```

This one is faster.

```bash
python manage.py collectstatic --noinput --clear
```

### Activate

```bash
sudo supervisorctl restart web-booking2-release
```

## Adding a Page

\(create a vendor page\)

mysite/urls.py &gt; add url

url\(r'^booking/\(?P.+\)/\(?P.+\)/$', class\_views.VendorDetailFromSlug.as\_view\(\), name='class.detail.slug.vendor'\),

classpage&gt;views.py

classpage&gt;services.py

core&gt;lejservice.py

creat class-details-verdor.html in templates&gt;classpage

## Reverse

core&gt;views/py

from django.urls import reverse

if user is not None and user.is\_active: auth.login\(request, user\) request.session\['token'\] = token request.session\['customer\_id'\] = customer\_id request.session\['class\_filter'\] = None request.session\['watchlist'\] = cs.get\_watchlist\(customer\_id, token\) device\_id = core\_service.generate\_device\_id\(request.META\) request.session\['device\_id'\] = device\_id return HttpResponseRedirect\(reverse\('index'\)\) else: return render\(request, 'core/login-signup.html',{'page':'login'}\)

mysite&gt;urls.py

url\(r'^$', class\_views.LandingPage.as\_view\(\), name='index'\),

> use reverse\('fdsa'\) to direct the user to the url which the name matches

## 



