# Get Started

## Precondition

Here is what you should have installed before starting managing packages.

[https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/](https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/)

* Node.js :: [Download](https://nodejs.org/en/download/)

{% hint style="info" %}
NPM will be automatically installed along with Node.js.
{% endhint %}

## Initial

NPM stands for Node Package Manager, it has a very unique file **Package.json** to keep our package setting, it allows us to record what packages we want to use while developing and producing, we can also keep the setting for transferring our project to different devices without moving the packages.

With **Package.json**, we can reinstall the packages we need in other environment with only one simple command, and due to this feature, we actually have two ways to initial our projects.

* Start in Local Fold :: Provide convenience for using different package setting to develop projects
* Start Globally :: Provide convenience for using the same setting to develop similar projects or a huge project in a device

Then here is the command, it generates **package.json** file.

```bash
npm init
```

But if you have a **package.json** already, just use the following code to install all saved packages in the list.

```bash
npm install
```

After it done, the command will help you to fill up the basic information for the **package.json** file. It is fine to simply just enter to the end, NPM has its own default value.

* Package Name :: The name for this project, only URL-friendly character available
* Version :: The format must be 1.0.0.
* Description :: The description for this project, can be filled with any character
* Entry Point :: Supports you to combine JS files into one in order to modular JS. It is also the file for others to invoke the project as a plugin or package. This field can't be empty even there is nothing in the entry point JS file
* Author :: Usually is author's email
* License :: Default is ISC
* Test Command :: Not important

After done initialing, you will see two files in the folder.

* Node\_modules :: Where your packages are installed
* Package-lock :: Advanced record

## Node Version Manager

**NVM** is a version manager for Node.js, you can download it from [Here](https://github.com/nvm-sh/nvm#install-script).

#### Show All Installed Versions

```bash
nvm ls
```

This command will show all the versions you have installed in the device.

#### Show All Available Versions

```bash
nvm ls-remote
```

This command will show all the versions that you can install, notice that the list can be very long.

#### Install the Specific Version

```bash
nvm install 8.1.2
```

#### Switch between Versions

```bash
nvm use 4.2.1
```

上述指令僅限當前的 Shell Tab 有效，若要永久切換，需使用以下指令。

```bash
nvm alias default 10.15.3
```

{% hint style="info" %}
關於 NVM 的完整操作，參考 GitHub，[NVM Usage](https://github.com/nvm-sh/nvm#usage)。
{% endhint %}

## Installation

First, you have to check out that you already have `package.json` in your root folder, if you don't, use `npm init` to create one.

#### Install Packages

```bash
npm install gulp
```

You can add `-g` to make the package globally, in this case, you don't need a `package.json` first.

```bash
npm install gulp -g
```

#### Assign Package

When you install a package, you are just installing a package in the folder, if you want to use your `package.json` as a very convenient install tool, you have to add these commands.

Use this to save the package and its version to your `package.json`.

```bash
npm install gulp --save
```

Use this to save the package but only in development process.

```bash
npm install gulp --save-dev
```

#### Specify Version for Packages

Use this command when you want to download a version you need for the package, this can work when you **already have a different version for the package**.

```bash
npm install --save-dev gulp@3.9.1
```

#### Check Package Version

If the package is global.

```bash
gulp -v
```

If the package is under a folder.

```bash
npm list gulp
```

### 錯誤處理

If you meet _**Not compatible with your operating system or architecture: fsevents@1.2.9**_, you can simply add this code to solve it.

```bash
npm install --save-dev gulp@3.9.1 --no-optional
```

或是使用以下指令。

```bash
sudo npm install --unsafe-perm=true --allow-root
```

此指令在遇上 _**unable to save binary**_ 時同樣有效。

```bash
sudo npm install gulp-sass --unsafe-perm=ture
```

Then you can checkout the version in `package.json`.

若是遇上安裝的套件與現今版本不合，必須先切換 Node 版本，再重新安裝。

```bash
nvm install 8.1.2
```

```bash
nvm use 4.2.1
```

```bash
npm rebuild node-sass
```

## Reinstall

Steps that you need to go through if you meet errors when reinstall NPM.

```bash
sudo rm -rf node_modules
sudo rm package-lock.json yarn.lock
sudo npm cache clear --force
sudo npm install
```

## Uninstall

`global` is an option.

```bash
sudo npm uninstall --global firebase
```

