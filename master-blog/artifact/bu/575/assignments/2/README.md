# Assignment

An assignment for class **MET CS 575** at Boston University, instructed by **Dr. Nourai**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **October 12, 2022**.

## Environment

- MacBook Pro
- M1 chip
- UTM
- Ubuntu 22.04.1

## Flow

1. Install essential packages:

```bash
sudo apt update
sudo apt install imagemagick
sudo apt install -y build -essential linux -headers -$(uname -r)
```

2. Move `hello.c` to the shared folder, and edit it
3. Move `Makefile` to the shared folder
4. I then tried to use `make`, and receiving **Makefile error: recipe commences before first target** error. I overcame the issue by moving the files from the shared folder to `~/Desktop`, and execute `make` there:

```bash
make
# Makefile error: recipe commences before first target
cp ./Makefile ~/Desktop/
cp ./hello.c ~/Desktop/
cd ~/Desktop
make
```

5. The files were generated. I then use the following commands:

```bash
sudo insmod hello.ko
dmesg | tail # Display message "Hello, Jin Yu Zhang!"
sudo rmmod hello # Display message "Goodbye, Jin Yu Zhang!"
dmesg > dmesg -`date +%d%m%Y`.log
tail dmesg -`date +%d%m%Y`.log
```

6. Copy all the generated files from `~/Desktop` to the shared folder

### Note

Error and debug steps are not mentioned such as `ls`, `cat`, and `dmesg > dmesg -`date +%d%m%y`.log`. After a few attempts, I am still not able to get imagemagick working. The screenshots were created by macOS.

It seems impossible to generate a single-terminal screenshot for all the commands I have executed above. As an alternative, the screenshot files are named with the time created.
