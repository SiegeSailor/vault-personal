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

## Command "python setup.py egg\_info" failed with error code 1



