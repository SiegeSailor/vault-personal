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

#### mysql\_config not found when installing mysqldb python interface

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

#### Failed building wheel for mysqlclient

Install first.

```bash
brew install openssl
```

Then assign the path.

```bash
export LIBRARY_PATH=$LIBRARY_PATH:/usr/local/opt/openssl/lib/ 
```

#### Other Solution

These two command might be a solution for you if you meet the following error message. This usually happens when you fail executing `pip install mysqlclient==1.3.12` .

```bash
pip install mysqlclient==1.3.12
Collecting mysqlclient==1.3.12
  Using cached https://files.pythonhosted.org/packages/6f/86/bad31f1c1bb0cc99e88ca2adb7cb5c71f7a6540c1bb001480513de76a931/mysqlclient-1.3.12.tar.gz
Building wheels for collected packages: mysqlclient
  Building wheel for mysqlclient (setup.py) ... error
  ERROR: Complete output from command /Users/apple/Desktop/Edallianz/venv/bin/python3.6 -u -c 'import setuptools, tokenize;__file__='"'"'/private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-install-1dzkbn03/mysqlclient/setup.py'"'"';f=getattr(tokenize, '"'"'open'"'"', open)(__file__);code=f.read().replace('"'"'\r\n'"'"', '"'"'\n'"'"');f.close();exec(compile(code, __file__, '"'"'exec'"'"'))' bdist_wheel -d /private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-wheel-871bgk7n --python-tag cp36:
  ERROR: running bdist_wheel
  running build
  running build_py
  creating build
  creating build/lib.macosx-10.13-x86_64-3.6
  copying _mysql_exceptions.py -> build/lib.macosx-10.13-x86_64-3.6
  creating build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/__init__.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/compat.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/connections.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/converters.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/cursors.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/release.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  copying MySQLdb/times.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
  creating build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/__init__.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/CLIENT.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/CR.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/ER.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/FIELD_TYPE.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/FLAG.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  copying MySQLdb/constants/REFRESH.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
  running build_ext
  building '_mysql' extension
  creating build/temp.macosx-10.13-x86_64-3.6
  clang -Wno-unused-result -Wsign-compare -Wunreachable-code -fno-common -dynamic -DNDEBUG -g
  -fwrapv -O3 -Wall -Wstrict-prototypes -Dversion_info=(1,3,12,'final',0) -D__version__=1.3.12
  -I/usr/local/Cellar/mysql/8.0.16/include/mysql
  -I/usr/local/Cellar/python/3.6.5_1/Frameworks/Python.framework/Versions/3.6/include/python3.6m
  -c _mysql.c -o build/temp.macosx-10.13-x86_64-3.6/_mysql.o
  _mysql.c:1894:3: error: use of undeclared identifier 'my_bool'
                  my_bool recon = reconnect;
                  ^
  _mysql.c:1895:58: error: use of undeclared identifier 'recon'
                  mysql_options(&self->connection, MYSQL_OPT_RECONNECT, &recon);
                                                                         ^
  2 errors generated.
  error: command 'clang' failed with exit status 1
  ----------------------------------------
  ERROR: Failed building wheel for mysqlclient
  Running setup.py clean for mysqlclient
Failed to build mysqlclient
Installing collected packages: mysqlclient
  Found existing installation: mysqlclient 1.4.2.post1
    Uninstalling mysqlclient-1.4.2.post1:
      Successfully uninstalled mysqlclient-1.4.2.post1
  Running setup.py install for mysqlclient ... error
    ERROR: Complete output from command /Users/apple/Desktop/Edallianz/venv/bin/python3.6 -u
    -c 'import setuptools, tokenize;__file__='"'"'/private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-install-1dzkbn03/mysqlclient/setup.py'"'"';f=getattr(tokenize, '"'"'open'"'"', open)(__file__);code=f.read().replace('"'"'\r\n'"'"', '"'"'\n'"'"');f.close();exec(compile(code, __file__, '"'"'exec'"'"'))' install --record /private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-record-6f45vrhi/install-record.txt --single-version-externally-managed --compile --install-headers /Users/apple/Desktop/Edallianz/venv/bin/../include/site/python3.6/mysqlclient:
    ERROR: running install
    running build
    running build_py
    creating build
    creating build/lib.macosx-10.13-x86_64-3.6
    copying _mysql_exceptions.py -> build/lib.macosx-10.13-x86_64-3.6
    creating build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/__init__.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/compat.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/connections.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/converters.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/cursors.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/release.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    copying MySQLdb/times.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb
    creating build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/__init__.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/CLIENT.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/CR.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/ER.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/FIELD_TYPE.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/FLAG.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    copying MySQLdb/constants/REFRESH.py -> build/lib.macosx-10.13-x86_64-3.6/MySQLdb/constants
    running build_ext
    building '_mysql' extension
    creating build/temp.macosx-10.13-x86_64-3.6
    clang -Wno-unused-result -Wsign-compare -Wunreachable-code -fno-common -dynamic -DNDEBUG -g
    -fwrapv -O3 -Wall -Wstrict-prototypes -Dversion_info=(1,3,12,'final',0) -D__version__=1.3.12 -I/usr/local/Cellar/mysql/8.0.16/include/mysql -I/usr/local/Cellar/python/3.6.5_1/Frameworks/Python.framework/Versions/3.6/include/python3.6m -c _mysql.c -o build/temp.macosx-10.13-x86_64-3.6/_mysql.o
    _mysql.c:1894:3: error: use of undeclared identifier 'my_bool'
                    my_bool recon = reconnect;
                    ^
    _mysql.c:1895:58: error: use of undeclared identifier 'recon'
                    mysql_options(&self->connection, MYSQL_OPT_RECONNECT, &recon);
                                                                           ^
    2 errors generated.
    error: command 'clang' failed with exit status 1
    ----------------------------------------
  Rolling back uninstall of mysqlclient
  Moving to /Users/apple/Desktop/Edallianz/venv/lib/python3.6/site-packages/MySQLdb/
   from /Users/apple/Desktop/Edallianz/venv/lib/python3.6/site-packages/~ySQLdb
  Moving to /Users/apple/Desktop/Edallianz/venv/lib/python3.6/site-packages/mysqlclient-1.4.2.post1.dist-info/
   from /Users/apple/Desktop/Edallianz/venv/lib/python3.6/site-packages/~ysqlclient-1.4.2.post1.dist-info
ERROR: Command "/Users/apple/Desktop/Edallianz/venv/bin/python3.6 -u -c 'import setuptools,
tokenize;__file__='"'"'/private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-install-1dzkbn03/mysqlclient/setup.py'"'"';
f=getattr(tokenize, '"'"'open'"'"', open)(__file__);code=f.read().replace('"'"'\r\n'"'"', '"'"'\n'"'"');
f.close();exec(compile(code, __file__, '"'"'exec'"'"'))' install --record /private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-record-6f45vrhi/install-record.txt
--single-version-externally-managed --compile --install-headers /Users/apple/Desktop/Edallianz/venv/bin/../include/site/python3.6/mysqlclient"
failed with error code 1 in /private/var/folders/hy/t8mf7yj150n5mx4jyc18l5j40000gn/T/pip-install-1dzkbn03/mysqlclient/
```

```bash
pip install --only-binary :all: mysqlclient
```

```bash
pip install mysqlclient‑1.3.13..your Python version.whl
```

### 無法 Runserver

當執行 `python manage.py runserver` 時，若有遇到 _**ImportError: No module named drf\_yasg**_，很有可能是因為 Python 版本的差異所導致。這時應使用 `python3` 指定版本來執行動作。

```bash
sudo pip3 install -r requirments.txt
```

```bash
python3 manage.py runserver
```

若在使用 Python 3 後，仍然出現 _**ImportError: cannot import name 'SchemaGenerator'**_ 的訊息，則是因為 `SchemaGenerator` 已經更名為 `BaseSchemaGenerator`，必須在 SWAGGER\_SETTINGS 內新增以下資訊。

```python
SWAGGER_SETTINGS = {
    "DEFAULT_GENERATOR_CLASS": "rest_framework.schemas.generators.BaseSchemaGenerator",
}
```

### 套件相依失效

當出現如 _**ERROR: Failed building wheel for mysqlclient**_ 的錯誤訊息，或在安裝 `drf_yasg` 時不斷出現相依套件無法安裝的問題時，很有可能是因為 Python 版本的差異所導致。這時應使用 `python3` 指定版本來執行動作。

```bash
sudo pip3 install -r requirments.txt
```

```bash
python3 manage.py runserver
```

