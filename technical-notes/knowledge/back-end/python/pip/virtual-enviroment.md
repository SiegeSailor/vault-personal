# Virtual Enviroment

## Start

Assuming that you are currently in your project path.

```bash
virtualenv venv
```

You can also use this command to assign Python version and get rid of root packages.

```bash
virtualenv venv --python=python3 --no-site-packages
```

### Assign Python Version

There is an easier way.

```bash
virtualenv venv --python=python2.7
```

This only works if you have python2.7 installed at the system level \( Like /usr/bin/python2.7 \). Otherwise, if you are using **homebrew** you can use the path to give you what you want.

```bash
virtualenv venv --python=/usr/local/bin/python
```

You can find the path to your python installation with

```bash
which python
```

This will also work with Python 3.

```bash
which python3>> /usr/local/bin/python3virtualenv venv --python=/usr/local/bin/python3
```

Ultimately condensing to these.

```bash
virtualenv venv -p `which python3`
```

## Trouble Shooting

#### ERROR: Could not find a version that satisfies the requirement requirements.txt \(from versions: none\)

```bash
pip install --upgrade -r requirements.txt
```

#### ERROR: No matching distribution found for requirements.txt

```bash
pip install --upgrade -r requirements.txt
```

#### ModuleNotFoundError: No module named pysqlite2

This happens while `python manage.py runserver` status. On mac, you can solve it with this command.

```bash
brew install sqlite
```

#### If Install or package location is wrong

```bash
pip show pip
```

{% hint style="info" %}
See this [Article](https://www.itread01.com/content/1541720947.html) to know what the steps are.
{% endhint %}

