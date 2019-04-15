# Activate Files

## Precondition

Here is the information for **Edallianz Global TPE Server**.

* Server Name :: ubuntu
* Server IP :: 52.77.174.67
* Key :: edallianz-peter.pem

{% file src="../../../.gitbook/assets/edallianz-peter.pem" %}

{% hint style="info" %}
The Key should be in _~/.ssh_ .
{% endhint %}

## Activate Files on Edallianz Server

It is enough to activate a file by only overwriting the file on the server from local file, but once there are some specific packages running on the server, you have to go extra steps.

For Edallianz Global TPE Server, the reason why you need to go the following steps is because these two.

* Supervisor :: Manage processes, such as assign them automatically run after starting the server
* Gunicorn :: WSGI Server service, use cache to speed up the request from clients

Due to **Gunicorn** uses cache to load the files while **Supervisor** keeps it running, the file will never refresh even if you have overwritten it, so these are what you have done before displaying the newest file on the server,

### Log In the Server

```bash
ssh ubuntu@52.77.174.67
```

In case if meet _**permission denied \( publickey \) .**_, please use the following command.

```bash
ssh -i ~/.ssh/edallianz-peter.pem ubuntu@52.77.174.67
```

### List Processes

```bash
ps -aux
```

Then you can check which process is stuck through the start date.

### Get into Supervisor Directory

```bash
cd /etc/supervisor/conf.d
```

{% hint style="info" %}
Check this [Article](https://blog.techbridge.cc/2017/12/23/linux-commnd-line-tutorial/) to know the basic command and the file structure.
{% endhint %}

### Running Files under Supervisor

```bash
ls -al
```

Then you can double check whether the stuck process is under Supervisor.

### Know Who is Stuck

```bash
cat web-booking2-release.conf
```

You shall see these.

> \[program:web-booking2-release\] command=/var/www/release/WebBooking2/venv/bin/gunicorn -b 127.0.0.1:8887 mysite.wsgi --reload --workers=2 directory=/var/www/release/WebBooking2 user=www-data autostart=true autorestart=true redirect\_stderr=true stdout\_logfile=/var/www/release/WebBooking2/storage/work.log

Then Restart the Process.

```bash
sudo supervisorctl restart web-booking2-release
```

Once you see these which means you have successfully refreshed the file.

> web-booking2-release: stopped  
> web-booking2-release: started

