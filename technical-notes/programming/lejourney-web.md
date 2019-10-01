# LeJourney Web

> LeJourney is a online booking web application, it has reached version 2.0, but still needs a lot of maintenance and improvement. That is a need to write down all steps to survey this system since I am a Front-End Developer and UIUX Designer rather than a Back-End or MIS Engineer. The project name on server is **WebBooking2**.

## Links

All the relevant links.

* Release Web :: [LeJourney App Marketplace](https://class.lejourneyapp.com/)
* Test Web :: [LeJourney App Marketplace](https://classtest.lejourneyapp.com/)
* GitLab :: [LeJourneyWeb](http://35.197.146.136/ZhangJinYu/LeJourneyWeb)
* Gateway Return Code :: [Google Excel](https://docs.google.com/spreadsheets/d/1Etkwz83BIcCMmqXNvaLksgfApkGo_3G_V8LN1dUqe1Q/edit#gid=0)
* New Swagger :: [Swagger UI](http://54.254.192.10:9999/#/Rating/CommentCram)
* Previous Swagger :: [Swagger UI](https://test.edallianz.net/swagger/#/)

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

## Git Flow

Although this project is maintained by only one person, we still need to be prepared for unknown difficulty.

* master :: Can always be pulled as a stable version
* develop :: The branch that allows you to develop anything
* hotfix :: Only use this for emergency fix
* feature/featureName :: Specifically created for one feature 

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
npm run webpack-release
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

Use the following command to get in the server.

```bash
ssh -i ~/.ssh/edallianz-peter.pem ubuntu@52.77.174.67
```

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

### Front End Compile

```bash
npm run production
```

### Data Fetch

```bash
sh rating/auto_fetch_sh.sh test
```

### Activate

```bash
sudo supervisorctl restart web-booking2-test
```

{% hint style="info" %}
SGD test credit card number is **4242 4242 4242 4242,** TWD test credit card number is **4000 2211 1111 1111**.
{% endhint %}

### All in One

Execute this command in root path of the project.

```bash
sh auto_dispatch_on_server.sh test
```

Here is what `auto_dispatch_on_server.sh` is.

```bash
#!/bin/bash
./pull.sh
. venv/bin/activate
python rating/auto_fetch_school_list.py $1
npm run production
python manage.py collectstatic --noinput --clear
sudo supervisorctl restart web-booking2-$1
```

and this is what pull.sh is.

```bash
#!/usr/bin/expect -f
spawn git pull
expect {
    "Username for 'http://35.197.146.136': " { send "kenzhang@rd.edallianz.com\r\n" }
    interact
    expect {
        "Password for 'http://kenzhang@rd.edallianz.com@35.197.146.136': " { sned "talimusd11\r\n" }
        interact
        EOF
    }
}
```

for `rating/auto_fetch_school_list.py`, please see [Script 取得資料與儲存讀取](back-end/python/script-qu-de-json-yu-chu-cun-du-qu.md).

## Online Server Script

Use the following command to get in the server.

```bash
ssh -i ~/.ssh/edallianz-peter.pem ubuntu@52.77.174.67
```

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

### Data Fetch

```bash
sh rating/auto_fetch_sh.sh release
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

### All in One

Execute this command in root path of the project.

```bash
sh auto_dispatch_on_server.sh release
```

Here is what `auto_dispatch_on_server.sh` is.

```bash
#!/bin/bash
./pull.sh
. venv/bin/activate
python rating/auto_fetch_school_list.py $1
npm run production
python manage.py collectstatic --noinput --clear
sudo supervisorctl restart web-booking2-$1
```

and this is whaat pull.sh is.

```bash
#!/usr/bin/expect -f
spawn git pull
expect {
    "Username for 'http://35.197.146.136': " { send "kenzhang@rd.edallianz.com\r\n" }
    interact
    expect {
        "Password for 'http://kenzhang@rd.edallianz.com@35.197.146.136': " { sned "talimusd11\r\n" }
        interact
        EOF
    }
}
```

for `rating/auto_fetch_school_list.py`, please see [Script 取得資料與儲存讀取](back-end/python/script-qu-de-json-yu-chu-cun-du-qu.md).

