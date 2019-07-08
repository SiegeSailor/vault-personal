# Command

## Precondition

Here is the information for **Edallianz Global TPE Server**.

* Server Name :: ubuntu
* Server IP :: 52.77.174.67
* Key :: edallianz-peter.pem

{% file src="../../../.gitbook/assets/edallianz-peter.pem" %}

{% hint style="info" %}
The Key should be in _~/.ssh_ .
{% endhint %}

## Command Line

* Ctrl-A :: go to the beginning of line
* Ctrl-E :: go to the end of line

## Transfer Files

SCP stands for Secure Copy Protocol, it is a means of securely transferring computer files between a local host and a remote host or between two remote hosts. It is based on the SSH \( Secure Shell Protocol \) .

### Upload Files

```bash
scp /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/release/WebBooking2/templates/footer.html
```

### Download Files

```bash
scp ubuntu@52.77.174.67:/var/www/website/EdallianzTW/EdallianzTWPrevious.zip /Users/eda-001/Desktop/EdallianzTWPrevious.zip
```

## Condition Setting

#### Identity SSH Key

```bash
-i keypath/key
```

Use this method to assign the SSH key you want to use, especially when meet _**permission denied \(publickey\).. The method**._

```bash
scp -i ~/.ssh/edallianz-peter.pem /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/test/WebBooking2/templates/footer.html
```

## Major Command

* mv :: **Move** or **rename** a file or a folder
* rm :: **Remove** a file or a folder
* cp :: **Copy** a file or a folder
* vi :: **Edit** a file
* vim :: **Edit** a file advance version
* ls :: **List** all files in the current path
* df :: reports the amount of available disk space being used by file systems
* du :: can be used to track the files and directories which are consuming excessive amount of space on hard disk drive
* sudo :: should be the first, super user is doing things

## Assistant Command

* -r :: Add this If you are using `mv`, `rm`, or `cp` to a folder. This means to do the same thing to all files in the path
* -al :: List detail

## WGET

* -t0 :: **Try time**, -t0 means endless
* -c :: Set **Continued** Transmission
* -nH :: Build a **same structure** in the current path rather than build sub path for the target website
* -np :: Don't go through parent path, no download action if there are links to other path or parent path
* -m :: means to use **-r** and **-N** at the same time
* -r :: Add this If you are using `mv`, `rm`, or `cp` to a folder. This means to do the same thing to all files in the path
* -N :: Check timestamp while downloading, only download the newest files
* -P :: Download to a pointed path

