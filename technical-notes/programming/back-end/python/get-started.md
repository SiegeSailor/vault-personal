# Get Started

## Precondition

The deployment environment for **Edallianz Taiwan Website** is **macOS**, in this case Python is already installed as default. However, you might need more steps to begin using Python if you are using Windows.

* Python :: [Download](https://www.python.org/downloads/)
* Operating System :: macOS



### **使用brew代替apt-get** <a id="&#x89E3;&#x51B3;&#x65B9;&#x6848;1&#x4F7F;&#x7528;brew&#x4EE3;&#x66FF;apt-get"></a>

#### What is brew? <a id="what-is-brew"></a>

**brew** 全称Homebrew，是Mac OSX上的软件包管理工具，能在Mac中方便的安装软件或者卸载软件。

#### 如何安装？ <a id="&#x5982;&#x4F55;&#x5B89;&#x88C5;"></a>

安装只需要在终端下输入下面一条命令（Mac自带ruby不需要安装）

ruby -e "$\(curl -fsSL [https://raw.githubusercontent.com/Homebrew/install/master/install](https://raw.githubusercontent.com/Homebrew/install/master/install)\)"

```text
brew unlink python # ONLY if you have installed (with brew) another version of python 3
brew install --ignore-dependencies https://raw.githubusercontent.com/Homebrew/homebrew-core/f2a764ef944b1080be64bd88dca9a1d80130c558/Formula/python.rb
```

```text
brew info python           # To see what you have previously installed
brew switch python 3.x.x_x # Ex. 3.6.5_1
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



