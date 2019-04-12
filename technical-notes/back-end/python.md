# Python

python --version

python3 --version

Homebrew&gt;

brew list

brew uninstalll 軟體名稱





download python

[https://www.python.org/downloads/](https://www.python.org/downloads/)



install pip

curl [https://bootstrap.pypa.io/get-pip.py](https://bootstrap.pypa.io/get-pip.py) -o get-pip.py

sudo python3.6 get-pip.py

pip3 -v



install virtualenv

```text
python3 -m pip install --user virtualenv
```



warning

The script virtualenv is installed in '/Users/eda-001/Library/Python/3.6/bin' which is not on PATH. Consider adding this directory to PATH or, if you prefer to suppress this warning, use --no-warn-script-location.

vi ~/.bashrc

add

```text
export PATH="/Users/crifan/Library/Python/3.6/bin:$PATH"
```

then command 

```text
source ~/.bashrc
```



establish venv

python3.6 -m venv myenv

cd myvenv

source bin/activate



install packages\(if there is a project\)

pip3 install -r requirements.txt

runserver\(if there is a project\)

python3.6 manage.py runserver



