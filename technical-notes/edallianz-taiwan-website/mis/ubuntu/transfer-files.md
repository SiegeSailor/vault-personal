# Transfer Files

## Precondition

Here is the information for **Edallianz Global TPE Server**.

* Server Name :: ubuntu
* Server IP :: 52.77.174.67
* Key :: edallianz-peter.pem

{% hint style="info" %}
The Key should be in _~/.ssh_ .
{% endhint %}

## Upload Files

```bash
scp /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/release/WebBooking2/templates/footer.html
```

In case if meet _permission denied \(publickey\)._ or when need to assign a specific key, please add `-i keypath/key` .

```bash
scp -i ~/.ssh/edallianz-peter.pem /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/test/WebBooking2/templates/footer.html
```

## Download Files

```bash
scp ubuntu@52.77.174.67:/var/www/website/EdallianzTW/EdallianzTWPrevious.zip /Users/eda-001/Desktop/EdallianzTWPrevious.zip
```

In case if meet _permission denied \(publickey\)._ or when need to assign a specific key, please add `-i keypath/key` .

```bash
scp -i ~/.ssh/edallianz-peter.pem ubuntu@52.77.174.67:/var/www/website/EdallianzTW/EdallianzTWPrevious.zip /Users/eda-001/Desktop/EdallianzTWPrevious.zip
```

## Command Issue

## 

## [Error using SCP: “not a regular file”](https://unix.stackexchange.com/questions/52634/error-using-scp-not-a-regular-file)

