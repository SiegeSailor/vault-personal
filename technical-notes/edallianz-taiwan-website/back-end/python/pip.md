# PIP

## Precondition

You have to install Python first in order to use PIP since PIP is a package management tool for Python. Python is a default program in macOS, and the deployment environment for **Edallianz Taiwan Website** is **macOS**.

* Python :: [Download](https://www.python.org/downloads/)
* Operating System :: macOS

## Installation

Use the following command to get the installation file of the latest version of PIP.

```bash
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
```

Then initial it with.

```bash
sudo python3.6 get-pip.py
```

## Display Version

After installation, you should be able to check PIP version with the following command.

```bash
pip3 --version
```

{% hint style="warning" %}
The version for this project of PIP is **9.0.3**
{% endhint %}

## Version Control

Use the command to install the assigned version of PIP.

```bash
pip install pip==9.0.3
```

You can also use this command to install the assigned version of PIP since **PIP is a Package/Module of Python**. PIP stands for Pip Installs Python or Pip Install Packages.

```bash
python -m pip install pip==9.0.3
```

These are also the solution for _**command not found: pip3**_ 

{% hint style="info" %}
**-m** Means to use a module, in this case the module is PIP.
{% endhint %}

## Virtualenv

For every project, whether it's a simple application or a giant Django project, you should create a new virtual environment to assign every package with the suitable version in the project even Python or PIP its self. Virtualenv can create a totally isolated environment to deploy different project with different condition.

### Installation

```bash
python3 -m pip install --user virtualenv
```

{% hint style="info" %}
**--user** Means install globally.
{% endhint %}

### Path Warning

You might face such warning after installing Virtualenv.

> The script virtualenv is installed in '/Users/eda-001/Library/Python/3.6/bin' which is not on PATH. Consider adding this directory to PATH or, if you prefer to suppress this warning, use --no-warn-script-location.

In this case, You need to add a path for the package Virtualenv, first you should use.

```bash
vi ~/.bashrc
```

Then in vi screen, add.

```text
export PATH="/Users/crifan/Library/Python/3.6/bin:$PATH"
```

After using `ESC` + `:wq` , use the following command to activate the file.

```bash
source ~/.bashrc
```

### Establish Venv

```text
python3.6 -m venv myenv
```

The command will create a folder in your current path, which should include several sub folder.

* bin :: Where the local interpreter and any executable scripts are installed
* include :: Directory for files related to building C extensions
* lib :: Where the packages are installed

Then enter the Virtualenv folder.

```bash
cd myvenv
```

Use the following command to activate Virtualenv.

```bash
source bin/activate
```

After activating Virtualenv, you shall see a `( venv )` tag in the front of command line. To exit use.

```bash
deactivate
```

## Install packages

There is a file to instruct PIP to install all the packages **Edallianz Taiwan Website** needs, it is a common way to help developer to build development environment. NPM has a similar feature **Package.json**.

After pulling the project into myvenv folder \( The Virtualenv folder you have created during Establishing Venv \) , use the following command to install all the packages you need.

```bash
pip3 install -r EdallianzTW/requirements.txt
```

