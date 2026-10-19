# Assignment

An assignment for class **MET CS 575** at Boston University, instructed by **Dr. Nourai**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **September 19, 2022**.

## Review

I use MacBook Pro with M1 chip. I am able to install and run Ubuntu 22.04.1 Desktop on my machine, however, there are frequent error reports, and everything is extremely laggy. For example, I have to wait almost 10 seconds to run Files, and I often fail to run applications (Nothing pops out after clicking and lagging).

I decided to use Ubuntu 22.04.1 Server instead. The followings are the packages I installed to give a more desktop version experience on the server version:

- `ubuntu-desktop`
- `spice-vdagent`
- `spice-webdavd`
- `davfs2`

## Flow

First of all, install Ubuntu 22.04.1 Server with all default options by running the VM. After installation, select reboot and reject ISO image, then restart the VM. After rebooting:

```bash
sudo apt update && sudo apt upgrade
sudo reboot
sudo apt install spice-vdagent spice-webdavd davfs2
sudo apt install ubuntu-desktop
sudo reboot
# Ensure davfs is installed
curl http://127.0.0.1:9843/
sudo mkdir /mnt/shared
# The shared folder should be visable in "Other Location" after this. Use anyuser and anypass for credential
sudo mount -t davfs -o noexec http://127.0.0.1:9843/ /mnt/shared/
sudo apt install -y build - essential linux - headers -$( uname -r)
```

Move `forkcpu.c` from shared folder to desktop. After that:

```bash
vim /home/siege/Desktop/forkcpu.c
sudo gcc -Wall -ansi /home/siege/Desktop/forkcpu.c -o /home/siege/Desktop/forkcpu
/home/siege/Desktop/forkcpu
```

My server VM went down once, and I was never able to restart it, so I had to create another one. I have created more than 10 VMs to try out either the desktop version or the server version to find the most smooth experience for accomplishing the assignment.
