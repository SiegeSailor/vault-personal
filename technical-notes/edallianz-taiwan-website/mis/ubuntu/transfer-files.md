# Command

## Precondition

Here is the information for **Edallianz Global TPE Server**.

* Server Name :: ubuntu
* Server IP :: 52.77.174.67
* Key :: edallianz-peter.pem

{% hint style="info" %}
The Key should be in _~/.ssh_ .
{% endhint %}

## Transfer Files

### Upload Files

```bash
scp /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/release/WebBooking2/templates/footer.html
```

### Download Files

```bash
scp ubuntu@52.77.174.67:/var/www/website/EdallianzTW/EdallianzTWPrevious.zip /Users/eda-001/Desktop/EdallianzTWPrevious.zip
```

## Condition Setting

### Identity SSH Key

```bash
-i keypath/key
```

Use this method to assign the SSH key you want to use, especially when meet permission denied \(publickey\).. The method

```bash
scp -i ~/.ssh/edallianz-peter.pem /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/test/WebBooking2/templates/footer.html
```





