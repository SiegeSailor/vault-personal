# Get Started

## Precondition

Here is what you should have installed before starting managing packages.

* Node.js :: [Download](https://nodejs.org/en/download/)

{% hint style="info" %}
npm will be automatically installed along with Node.js.
{% endhint %}

## Initial

NPM has a very unique file **Package.json** to keep our package setting, it allows us to record what packages we want to use while developing and producing, we can also keep the setting for transferring our project to different devices without moving the packages.

With **Package.json**, we can reinstall the packages we need in other environment with only one simple command, and due to this feature, we actually have two ways to initial our projects.

* Start in Local Fold :: Provide convenience for using different package setting to develop projects
* Start Globally :: Provide convenience for using the same setting to develop similar projects or a huge project in a device

Then here is the command, it generates **Package.json** file.

```bash
npm init
```

After it done, the command will help you to fill up the basic information for the **Package.json** file. It is fine to simply just enter to the end, NPM has its own default value.

* Package Name :: The name for this project, **only URL-friendly character available**
* Version :: Usually is 1.0.0, notice that **the formate must be x.x.x**
* Description :: The description for this project, can be filled with any character
* Entry Point :: The compiled JS file for the whole project, it can support you to **combine JS files into one to modularize JS**. It is also the file **for others to invoke** if this project is a plugin or package. This field **can't be empty even there is nothing in the entry point JS file**
* Author :: Usually is author's email
* License :: Default is ISC
* Test Command :: Not important

After done initialing, you will see two file in the folder.

* Node\_modules :: Where your packages are installed
* Package-lock :: Advanced record



