# Get Started

## Precondition

The deployment environment for **Edallianz Taiwan Website** is **macOS**, in this case Python is already installed as default. However, you might need more steps to begin using Python if you are using Windows.

* Python :: [Download](https://www.python.org/downloads/)
* Operating System :: macOS

## Installation

Since the operating system is macOS rather than Linux, we should use **Brew** instead of apt-get.

#### What is Brew

Homebrew, is a package management tool on Mac OSX.

#### Install Brew

Use ruby to install brew, Mac already has ruby, install **ruby** first if you are using other operating system.

```bash
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

Go this step only if you have installed \( with brew \) another version of Python 3.

```bash
brew unlink python
```

You can use this line to see what you have previously installed.

```bash
brew info python
```

Then download and install Python.

```bash
brew install --ignore-dependencies https://raw.githubusercontent.com/Homebrew/homebrew-core/f2a764ef944b1080be64bd88dca9a1d80130c558/Formula/python.rb
```

After all, you can switch your Python version by this. You can fill up the version such as **3.6.5\_1**

```bash
brew switch python 3.x.x_x
```

{% hint style="info" %}
You can find installed Python in _/System/Library/Frameworks/Python.framework/Versions/_
{% endhint %}

{% hint style="warning" %}
The version for this project of Python is **3.6.5**
{% endhint %}

## Display Version

Use the following command if you are going to use Python 2.

```bash
python --version
```

Use the following command if you are going to use Python 3.

```bash
python3 --version
```

{% hint style="info" %}
Check this [Article](https://www.zhihu.com/question/30941329) if you need to change the version of Python.
{% endhint %}

## Uninstall Python

You can uninstall specific version of Python by the following method if you have installed **Homebrew**.

{% hint style="info" %}
Homebrew is a package management tool for macOS, Click [Here](https://brew.sh/index_zh-tw) to know more.
{% endhint %}

```bash
brew list
```

All the packages that you have installed with Homebrew will be listed out.

```bash
brew uninstall Python
```

## Default Python Version

根據使用的 Shell，進入指定文件。

```bash
sudo vim ~/.bashrc
```

```bash
sudo vim ~/.zshrc
```

在最上面一行加入以下程式碼。

```bash
alias python=python3
```

`:wq` 儲存後，執行。

```bash
source ~/.bashrc
```

```bash
source ~/.zshrc
```

