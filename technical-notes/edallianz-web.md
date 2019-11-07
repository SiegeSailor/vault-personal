# Edallianz Web

> The origin for renewing the website is because of that the company needs more information to get clients, and I found out that the original one is a way simple. Actually I need to build it from scratch, also come out with **Basic Web Visual Guideline** and **All Development Environment Document** in order to assist other branches.

## Links

All the new content is from other team members.

* Website :: [Edallianz Taiwan](https://www.edallianz.com/tw/)
* Current GitLab :: [EdalianzTW](http://35.197.146.136/ZhangJinYu/edallianztw)
* Previous GitLab :: [Edallianz.TaiwanWebsite.Development](http://192.168.0.120:10080/ZhangJinYu/edallianz.taiwanwebsite.web)
* Text Content :: [Google Document](https://docs.google.com/document/d/1bgNMkz-AItqQqHHA7i4Hk_gSDcJpRUrJwKXtJukRhTY/edit?usp=sharing)
* Pricing :: [Google Document](https://docs.google.com/document/d/1A9qkCnutg6vcAttezC60SPGTmSGs68z78_M1VYTQWuI/edit)
* Material :: [Google Drive](https://drive.google.com/drive/folders/143dS15nCgOZiZjP32vHV2twCoYYKeUxt)
* Articles :: [Google Drive](https://drive.google.com/drive/folders/1HRHvy4bK10AXh-f7MQLxX6FtKSoU3o60)
* Partners Logo :: [Bitrix Drive](https://edallianz.bitrix24.com/docs/pub/dd9e84f033ae24ae7e703001c5d4d5c0/default/?&)
* Partners List :: [Google Drive](https://docs.google.com/spreadsheets/d...edit#gid=0)
* Previous Prototype :: [Sketch Cloud](https://sketch.cloud/s/zqzly/KvYarQy/play)

## Environment

Server.

* System :: Ubuntu 16.04.4
* Apache :: 2.4.18
* PHP :: 7.0.28
* Composer :: 1.5.2
* MySQL ::

Local.

* System :: macOS 10.14.1
* Node :: 10.15.1
* NPM :: 6.4.1
* Laravel-Mix :: 2.1.14
* Webpack :: 3.11.0
* Python :: 3.6.5
* PIP :: 9.0.3
* Django :: 1.11.14

## Status

Summary for the website.

* [x] Sitemap
* [x] Google Analytics Code

## User Experience

Edallianz Taiwan Website is a B2B website, it helps the sales to introduce our services to our clients, and show our image to public as well, but there isn't more requirement for this website, so that my focus in UX field will only be **Following Marketing Need** and **Branding**.

However, it isn't really correct to put marketing need to the first priority, but since there is no user data to analysis UX need, I can only focus on how to provide the better **Visual Animation Guide**, **Branding**, and **Common Layouts** as UX design.

* [ ] Persona
* [x] Sitemap
* [x] UI Flow
* [x] Wireframe

## User Interface

As a B2B website, it is doomed that it can't be a stylish website, and it has to suit the flavor of education field, so the first thing should be considered is **Our Brand Color**, then **how to Insert Modern Design under Classic Layout to Attract our Educator Customer from Youths to Elders**.

In the same time, the UI design has to consider about **Componentization** and **Modularization** in Front-End way for fast development and maintenance, such as only using established color, font, component, layouts, and structure for SASS and AJAX.

* [ ] Wireframe
* [x] Mockup
* [x] Prototype
* [ ] Slicing

## Start Local Project

A long journey leads to build your project locally.

### In Project Folder

First, You should.

```bash
git pull
```

Then get into your project folder, init a virtual environment.

```bash
Virtual venv
```

Activate it.

```bash
source venv/bin/activate
```

Install the packages you need.

```bash
pip install -r requirements.txt
```

You might need to use `pip3` instead of `pip`.

```bash
npm install
```

```bash
npx bower install
```

```bash
python manage.py migrate
```

You can also use `manage.py syncdb`, but this script doesn't work if you are using Django 1.7 or later, you will get _**Unknown command: 'syncdb'**_.

### In Semantic UI Folder

After installing your project package, you have to install the package for what **Semantic UI** needs.

First, get into its folder.

```bash
cd bower_components/semantic
```

Then install its NPM packages so that you can use Gulp to build Semantic UI files.

```bash
npm install
```

Use this Semantic command to build it up.

```bash
npx gulp build
```

{% hint style="info" %}
[Here](https://semantic-ui.com/introduction/build-tools.html) is all commands for Semantic UI.
{% endhint %}

### In React Folder

First, go back to the project folder then go to React folder.

```bash
cd ../../cd react-semantic
```

React also uses NPM as a package management tool, you should use the command to install the dependency it needs.

```bash
npm install
```

Use this React command to build it up.

```bash
npm start
```

### After All

We are using **Create-React-App** to build our React project, so that it can compile all scripts by its self, but if we want to build it with Semantic UI, there are more steps to do. Here we use Gulp to help us, they are basically moving files for us.

This helps us to move Semantic UI file to our React folder. This should be done at the beginning.

```bash
npm run gulp reactInit
```

Then once you reach some progress and ready to publish, you should use this React command to compile your React files.

```bash
npm run build
```

Then use Our Gulp script to move the files into Django project.

```bash
npm run gulp reactProduction
```

