Outline of the process to backup files from one Ubuntu server to another using SSH password-less, rsync, and crontab:

On the source Ubuntu server, generate an SSH key pair if you haven't already. You can use the following command to generate a new key pair:

```bash
ssh-keygen
```

This will create a public and private key pair in the ~/.ssh/ directory.

Copy the public key to the destination Ubuntu server. You can use the following command to copy the public key:

```bash
ssh-copy-id user@destination_server
```

Replace user with the username on the destination server and destination_server with the hostname or IP address of the destination server.

Test that you can connect to the destination server without a password. You can use the following command to test the connection:

```bash
ssh user@destination_server
```

On the source Ubuntu server, install rsync if it's not already installed. You can use the following command to install rsync:

```bash
sudo apt-get install rsync
```

Use the rsync command to backup the files from the source Ubuntu server to the destination Ubuntu server. Here's an example command:

```bash
rsync -avz /path/to/source user@destination_server:/path/to/destination
# Or
rsync -av -e "ssh -i <private_key>" /path/to/source user@destination_server:/path/to/destination
# Or
rsync -av -e ssh /path/to/source user@address:/path/to/destination
```

Replace /path/to/source with the path to the files you want to backup on the source server, user with the username on the destination server, destination_server with the hostname or IP address of the destination server, and /path/to/destination with the path to the directory on the destination server where you want to store the backup files.

Test the rsync command to ensure that it backs up the files correctly.

Use crontab to schedule the rsync command to run at regular intervals. You can use the following command to edit the crontab file:

```bash
crontab -e
```

Then add the following line to run the rsync command every day at midnight:

```bash
0 0 \* \* \* rsync -avz /path/to/source user@destination_server:/path/to/destination
```

Save the crontab file and exit the editor.

That's it! You should now have a scheduled backup of files from the source Ubuntu server to the destination Ubuntu server.
