# PIP

## Unable to install mysqlclient on Ubuntu LTS 16.04 LTS on Python3.6

sudo apt-get install python3.6-dev libmysqlclient-dev after that I activate the virtual environment and execute the following command pip install mysqlclient

## [mysql\_config not found when installing mysqldb python interface](https://stackoverflow.com/questions/7475223/mysql-config-not-found-when-installing-mysqldb-python-interface)

mySQLdb is a python interface for mysql, but it is not mysql itself. And apparently mySQLdb needs the command 'mysql\_config', so you need to install that first.

Can you confirm that you did or did not install mysql itself, by running "mysql" from the shell? That should give you a response other than "mysql: command not found".

Which linux distribution are you using? Mysql is pre-packaged for most linux distributions. For example, for debian / ubuntu, installing mysql is as easy as

```text
sudo apt-get install mysql-server
```

mysql-config is in a different package, which can be installed from \(again, assuming debian / ubuntu\):

```text
sudo apt-get install libmysqlclient-dev
```

if you are using mariadb, the drop in replacement for mysql, then run

```text
sudo apt-get install libmariadbclient-dev
```

## start virtualenv

virtualenv venv \(most be in the project root folder\)

virtualenv venv --python=python3 --no-site-packages

### assign python version



There is an easier way,

```text
virtualenv venv --python=python2.7
```

Thanks to a comment, this only works if you have python2.7 installed at the system level \(e.g. /usr/bin/python2.7\).

Otherwise, if you are using homebrew you can use the path to give you what you want.

```text
virtualenv venv --python=/usr/local/bin/python
```

You can find the path to your python installation with

```text
which python
```

This will also work with python 3.

```text
which python3
>> /usr/local/bin/python3
virtualenv venv --python=/usr/local/bin/python3
```

Ultimately condensing to:

```text
virtualenv venv -p `which python`
virtualenv venv -p `which python3`
```

## Command "python setup.py egg\_info" failed with error code 1



