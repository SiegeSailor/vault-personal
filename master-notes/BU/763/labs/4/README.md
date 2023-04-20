# Lab

A lab for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **April 23, 2023**.

## Lab Setup

Downloaded and unzipped the setup file:

![1-1](./1-1.png)

Executed `dcup -d` to run containers in the background:

![1-2](./1-2.png)

## Hosts Setup

Modified `/etc/hosts`:

![2-1](./2-1.png)

## Desktop Setup

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

I ended using another Windows machine.
