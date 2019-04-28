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

### Identity SSH Key

```bash
-i keypath/key
```

Use this method to assign the SSH key you want to use, especially when meet permission denied \(publickey\).. The method

```bash
scp -i ~/.ssh/edallianz-peter.pem /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/test/WebBooking2/templates/footer.html
```

mv

rm

cp -r home/ayu/xyz/. /tmp

vi

ls -al

sudo

df du



```text
not a regular file
-r
```

wget

[https://blog.gtwang.org/linux/linux-wget-command-download-web-pages-and-files-tutorial-examples/](https://blog.gtwang.org/linux/linux-wget-command-download-web-pages-and-files-tutorial-examples/)

```text
# -t0: 設定重試次數。當連結中斷或超時，wget會重新連接。-t0代表把重試次數設為無窮多。
# -c:  設定續傳功能。
# -nH: 不建立該網站名稱的子目錄 /example.com/，而直接在當前目錄下建立鏡像的目錄結構。
# -np: 不遍歷父目錄，如果有連結連到目標資料夾的parent或其他目錄，不下載。
# -m:  鏡像，相當同時使用-r和-N。
# -r:  遞迴下載，把文件中所有的連結都下載回來。
# -N:  下載時檢查timestamp，只下載有更新的文件，如果檔案大小和最修改日期都一樣就不下載。
# -P:  指定下載到本機的某個目錄下。
```

* `Ctrl-A`: go to the beginning of line
* `Ctrl-E`: go to the end of line

[https://medium.com/@wifferlin0505/%E5%9C%A8-ubuntu-16-04-lts-%E4%B8%AD%E5%AE%89%E8%A3%9D%E4%BD%BF%E7%94%A8-oh-my-zsh-cf92203ca8a2](https://medium.com/@wifferlin0505/%E5%9C%A8-ubuntu-16-04-lts-%E4%B8%AD%E5%AE%89%E8%A3%9D%E4%BD%BF%E7%94%A8-oh-my-zsh-cf92203ca8a2)

