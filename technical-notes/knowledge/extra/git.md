# Common Issues

## Methods

Common methods.

### List Config

```bash
git config --list
```

### Remove Git Tracking

```bash
rm -rf .git
```

### Update GitIgnore

```bash
git rm -r --cached .
git add .
git commit -m 'update .gitignore'
```

### Set Proxy and Account

Command this on your project.

```bash
git config http.proxy http://myusername:mypassword@proxy.server:port
```

If there is no need for user authentication, take off code before `@`.

```bash
git config http.proxy http://proxy.server:port
```

If you are running all projects on GitHub, you can use global setting.

```bash
git config --global http.proxy http://proxy.server:port
```

### Reset Git Proxy to Default Configuration

If you had a configuration like this.

```bash
git config --global core.gitproxy gitproxy
```

You can use this to remove it.

```bash
git config --global --unset core.gitproxy
```

You might need to add.

```bash
git config --global --unset http.proxy
```

Basically, you can just run.

```bash
git config --global -l 
```

to get _**the list of all proxy defined**_, and then use `--unset` to disable them

## Trouble Shootings

Common issues.

#### fatal: unable to access;Failed connect to github.com:8087; No error

There are three ways to fix this problem. First one, if you are using proxy, modify it.

```bash
git config --global http.proxy 192.168.1.1:8083
```

Second, if the above doesn't work, try to change **HTTPS** to **SSH**, vice versa. List all your remotes first.

```bash
git remote -v
```

Then use `git remote set-url` method.

```bash
git remote set-url origin git@github.com:USERNAME/REPOSITORY.git
```

Vice versa.

```bash
git remote set-url origin https://github.com/USERNAME/REPOSITORY.git
```

{% hint style="info" %}
See this [Official Document](https://help.github.com/en/articles/changing-a-remotes-url).
{% endhint %}

The third, just reset the proxy.

```bash
git config --global --unset http.proxy 
```

{% hint style="info" %}
See the [Article](https://www.zhihu.com/question/26954892).
{% endhint %}

#### fatal: unable to access

There are three reasons for this problem, first might be the token expired, you can use the following command.

```bash
git config --global credential.helper grayyin
```

Second, you can reset the `http_proxy`.

```bash
export http_proxy=http://{Your Proxy URL}:{Port}
export https_proxy=http://{Your Proxy URL}:{Port}
export no_proxy=****
```

Third, try to use SSH. Generate a key first.

```bash
ssh-keygen
Generating public/private rsa key pair.
Enter file in which to save the key (/Users/schacon/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /Users/schacon/.ssh/id_rsa.
Your public key has been saved in /Users/schacon/.ssh/id_rsa.pub.
The key fingerprint is:
43:c5:5b:5f:b1:f1:50:43:ad:20:a6:92:6a:1f:9a:3a schacon@agadorlaptop.local
```

Then send it to the admin.

```bash
cat ~/.ssh/id_rsa.pub
ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAklOUpkDHrfHY17SbrmTIpNLTGK9Tjom/BWDSU
GPl+nafzlHDTYW7hdI4yZ5ew18JH4JW9jbhUFrviQzM7xlELEVf4h9lFX5QVkbPppSwg0cda3
Pbv7kOdJ/MTyBlWXFCR+HAo3FXRitBqxiX1nKhXpHAZsMciLq8V6RjsNAQwdsdMFvSlVK/7XA
t3FaoJoAsncM1Q9x5+3V0Ww68/eIFmb1zuUFljQJKprrX88XypNDvjYNby6vw/Pb0rwert/En
mZ+AW4OZPnTPI89ZPmVMLuayrD2cE86Z/il8b+gw3r3+1nKatmIkjn2so1d01QraTlMqVSsbx
NrRFi9wrf+M7Q== schacon@agadorlaptop.local
```

Change to way you connect to the remote.

```bash
git remote set-url origin git@github.com:USERNAME/REPOSITORY.git
```

#### The authenticity of host can't be established

Which means that the remote host is unknown /untrusted. The fix is to have the remote host entry in the known\_hosts file. This is the steps.

1. Generate \(or use existing\) RSA keys and store them in the `<USER.HOME>/.ssh` file
2. Now your `<USER.HOME>/.ssh` would contain file; private key, public key and known\_hosts
3. share your public key with your trusted hosts
4. Open Git Bash Promp \( command prompt \) and enter below command.
   * `ssh -vt <user>@<hostname>`
   * Like ssh -vt git@mygithub.com
5. When it prompts _**error ... Are you sure you want to continue connecting \(yes/no\)?**_
6. Say **yes**
7. Now your remote host is added to the `known_hosts`
8. Done

{% hint style="info" %}
Source [Article](https://stackoverflow.com/questions/11443687/the-authenticity-of-host-cant-be-established).
{% endhint %}

#### git: 'credential-cache' is not a git command

This usually happens in **Windows** system.

```bash
git config --global credential.helper wincred
```

#### remote: The project you were looking for could not be found.

Add your account and password to your `git clone` command.

```bash
git clone http://root:Lianz6578=1235eda@35.197.146.136/Ed/EdToolBox2.git
```

