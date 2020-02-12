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

## Find 尋找

使用以下指令在指定路徑下尋找檔案。

```bash
find /var/www/test/lejourney2/ -type f -name '*.json'
```

* -type :: 指定檔案的類型
  * d :: 目錄
  * p :: 具名的 FIFO，pipe
  * f :: 一般檔案
  * l :: 連結檔，與 `-L` 或 `-follow` 參數同時使用時，就只會搜尋到有問題的連結檔，如果想要與 `-L` 來同時使用，則必須得要使用 `-xtype`
  * s :: socket 檔案
* -name :: 指定檔案名稱，可使用 `-iname` 以忽略大小寫

{% hint style="info" %}
詳細參數可參考 GT Wang，[Unix/Linux 的 find 指令使用教學、技巧與範例整理](https://blog.gtwang.org/linux/unix-linux-find-command-examples/)。
{% endhint %}

使用 `-path /path/you/donot/want -prune -o` 指令，在搜尋時忽略指定路徑。

```bash
find /var/www/test/lejourney2/ -path /var/www/test/lejourney2/node_modules -prune -o -type f -name '*.json'
```

以 `-o -path` 連結，忽略多個指定路徑。

```bash
find /var/www/test/lejourney2/ \( -path /var/www/test/lejourney2/node_modules -o -path /var/www/test/lejourney2/storage \)  -prune -o  -type f -name '*.json'
```

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

## Rsync 同步備份

意在取代 `scp`，`rsync` 能夠以參數設定多種傳輸方式與條件，並且延伸使用還能做到常態的同步備份。

```bash
sudo rsync -ravh --rsync-path="sudo rsync" -e 'ssh -i /Users/eda-001/.ssh/edallianz-ml.pem' ubuntu@13.228.191.215:/home /Users/eda-001/Documents/AWS.Backup.20190910/ML/:/
```

* rsync :: 指令
* -raveh :: 由 5 個指令所組成的條件設定
  * -r :: 備份整個資料夾下的目錄與檔案。但沒有保留設定，多數都採用 `-a`
  * -a :: 除了遞迴備份，並將子目錄及目錄下檔案，擁有者、群組、權限及時間紀錄等，所有設定一併保留。等同 `--archive`
  * -v :: 將備份傳輸過程，顯示詳細資訊。等同 `--verbose`
  * -h :: 把數字以較合適的單位格式顯示，增加易讀性
  * -q :: 不顯示備份過程，但會顯示輸送過程有錯誤的訊息
* --rsync-path="sudo rsync" :: 當遇到 _**permission denned\(13\)**_ 時，可以藉由此指令以 sudo 身份執行
* --delete :: 根據來源端的檔案狀態，刪除來源端也刪除的檔案
* -e :: 遠端登入後接著使用的指令，這裡為指定 SSH Key

{% hint style="info" %}
SSH、來源端與備份端在此都使用絕對路徑，相對路徑在 Rsync 的運作方式不如預期。
{% endhint %}

#### 其他參數

* --bwlimit=&lt;限制頻寬&gt; :: 限制頻寬使用，以 KB 為單位，例如數字 100 的話為每秒 100 KB
* --progress :: 顯示傳輸進度、速度、剩餘時間
* --remove-source-files :: 檔案同步完成後，自動刪除來源檔案
* --whole-file :: 強制同步整個檔案
* --min-size=&lt;數字&gt;K/M :: 限制備份檔案大小下限，K 為 KB，M 為 MB
* --max-size=&lt;數字&gt;K/M :: 限制備份檔案大小上限，K 為 KB，M 為 MB
* --exclude &lt;特定檔案名稱或字元&gt;  :: 同步與備份時排除特定檔案
* --include &lt;特定檔案名稱或字元&gt;  :: 限定同步與備份特定檔案

{% hint style="info" %}
詳細指令可見 MyLife，[rsync 筆記](https://blog.xuite.net/chingwei/blog/25690131-%E3%80%90%EF%BC%AC%E3%80%91rsync+%E7%AD%86%E8%A8%98)。
{% endhint %}

#### 安裝指令

Ubuntu \| _Debian 系列_。

```bash
sudo apt-get install rsync
```

Centos \| _RedHat 系列_。

```bash
sudo yum install rsync
```

## Package 備份

取得清單、並統整到 `/home/ubuntu`。

```bash
dpkg --get-selections > ~/Package.list
sudo cp -R /etc/apt/sources.list* ~/
sudo apt-key exportall > ~/Repo.keys
```

在 `sudo apt-key exportall > ~/Repo.keys` 的階段，若是出現 _**Warning: apt-key output should not be parsed \(stdout is not a terminal\)**_ 的問題，可使用 `–no-tty` 處理。

```bash
sudo apt-key -no-tty exportall > ~/Repo.keys
```

在目的端還原。

```bash
sudo apt-key add ~/Repo.keys
sudo cp -R ~/sources.list* /etc/apt/
sudo apt-get update
sudo apt-get install dselect
sudo dpkg --set-selections < ~/Package.list
sudo dselect
```

{% hint style="info" %}
從伺服器端到本地、以及從本地到伺服器端的過程，可以使用 rsync。
{% endhint %}

{% hint style="info" %}
詳細過程請見 askUbuntu，[How to backup settings and list of installed packages](https://askubuntu.com/questions/9135/how-to-backup-settings-and-list-of-installed-packages)。
{% endhint %}

## Condition Setting

#### Identity SSH Key

```bash
-i keypath/key
```

Use this method to assign the SSH key you want to use, especially when meet _**permission denied \(publickey\).. The method**._

```bash
scp -i ~/.ssh/edallianz-peter.pem /Users/eda-001/Desktop/WebBooking2/templates/footer.html ubuntu@52.77.174.67:/var/www/test/WebBooking2/templates/footer.html
```

#### 列出使用中的 port

```bash
sudo lsof -i -P -n | grep LISTEN
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
* -f :: Force
* -F :: in ls is used to mark directories with a trailing slash \(/\), doors with a trailing greater-than sign \(&gt;\), executable files with a trailing asterisk \(\*\), FIFOs with a trailing vertical bar \(\|\), symbolic links with a trailing at-sign \(@\), and AF\_UNIX address family sockets with a trailing equals sign \(=\).

## WGET

* -t0 :: **Try time**, -t0 means endless
* -c :: Set **Continued** Transmission
* -nH :: Build a **same structure** in the current path rather than build sub path for the target website
* -np :: Don't go through parent path, no download action if there are links to other path or parent path
* -m :: means to use **-r** and **-N** at the same time
* -r :: Add this If you are using `mv`, `rm`, or `cp` to a folder. This means to do the same thing to all files in the path
* -N :: Check timestamp while downloading, only download the newest files
* -P :: Download to a pointed path

## Du 磁碟空間

```bash
du -abckms /var/www
```

* /var/www :: 目錄名稱，可以省略，省略即代表要統計目前所在目錄的檔案容量
* -abckms :: 由 6 個指令組成的條件設定
  * -a :: 列出全部的檔案目錄，預設是列出目錄的值
  * -b :: 列出的值以 bytes 輸出
  * -c :: 最後加總 total
  * -k :: 列出的值以 KB 輸出
  * -m :: 列出的值以 MB 輸出
  * -s :: 只列出最後加總的值

## Df 磁碟存量

```bash
df -h
```

## PS 靜態進程狀態

列出正在運行的進程和其狀態，此為一次性的展示，若要動態展示，則需使用 `top`。

```bash
ps aux | grep <aName>
```

當輸出時，由左而右的訊息會是如此。

```text
USER PID %CPU %MEM VSZ RSS TTY STAT START TIME COMMAND
```

* USER :: 進程擁有者
* PID :: 進程的 ID
* %CPU :: 佔用的 CPU 使用率
* %MEM :: 佔用的記憶體使用率
* VSZ :: 佔用的虛擬記憶體
* RSS :: 佔用的記憶體
* TTY :: 次要裝置號碼，Minor Device Number of TTY
* STATE :: 該進程的狀態，共有 5 種
  * D :: 不可中斷，Uninterruptible Sleep，通常為 IO
  * R :: 運行中，Runnable 或 On Run Queue
  * S :: 中斷中，Sleeping
  * T :: 停止，Traced 或 Stopped
  * Z :: 殭屍，a Defunct \( Zombie \) Process
  * W :: 無駐留
  * &lt; :: 高優先
  * N :: 低優先
  * L :: 內存鎖
* START :: 進程開始時間
* TIME :: 進程執行時間
* COMMAND :: 進程執行的指令

## Top 動態進程展示

```bash
top
```

{% hint style="info" %}
當在 Top 模式時，可以按下 o 開啟過濾器，輸入如 COMMAND=php 一類的條件來搜尋。
{% endhint %}

## ZIP 壓縮

將指定檔案壓縮，在之後會有兩次的指定與確認密碼。

```bash
zip -e SampleFile.zip SampleFile.txt
```

壓縮整個目錄，一樣會有兩次的指定與確認密碼。

```bash
zip -er SampleFolder.zip SampleFolder/
```

## Sudo 最高權限

進入 `root` 使用者模式。

```bash
sudo su
```

## 顯示所有使用中的 Port

```bash
sudo lsof -PiTCP -sTCP:LISTEN
```

## 刪除進程

```bash
kill -9 <UID>
```

