# Trouble Shooting

## Trouble Shooting

Common issues.

### Unable to install mysqlclient

* Operating System :: Ubuntu LTS 16.04 LTS
* Python :: 3.6

```bash
sudo apt-get install python3.6-dev libmysqlclient-dev
```

After that I activate the virtual environment and execute the following command.

```bash
pip install mysqlclient
```

### mysql\_config not found when installing mysqldb python interface

mySQLdb is a python interface for mysql, but it is not mysql itself. And apparently mySQLdb needs the command **mysql\_config**, so you need to install that first.

You should run this following command to see if you have installed mysql itself, That should give you a response other than mysql: _**command not found**_.

```bash
mysql
```

Mysql is pre-packaged for most linux distributions. For example, for debian / ubuntu, installing mysql is as easy as this.

```bash
sudo apt-get install mysql-server
```

mysql-config is in a different package, which can be installed from \( again, assuming debian / ubuntu \) this.

```bash
sudo apt-get install libmysqlclient-dev
```

if you are using **mariadb**, the drop in replacement for mysql, then run

```bash
sudo apt-get install libmariadbclient-dev
```

#### On Mac

Ensure that you have gone through these steps.

* You installed Python
* You already did \( install brew first if you don't have \) `brew install mysql` to install mysql
* `export PATH=$PATH:/usr/local/mysql/bin` to Assign the path
* And finally the last step, for Python 2 you are going to use`pip install MySQL-Python`
* `pip3 install mysqlclient` if you are using Python 3

### Failed building wheel for mysqlclient

Install first.

```bash
brew install openssl
```

Then assign the path.

```bash
export LIBRARY_PATH=$LIBRARY_PATH:/usr/local/opt/openssl/lib/ 
```

