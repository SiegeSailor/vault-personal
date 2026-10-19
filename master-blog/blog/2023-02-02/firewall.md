The article is based on Ubuntu.

## Basic

Check if firewall is running:

```bash
sudo ufw status
sudo ufw status verbose # Show status in plain text
```

Enable it:

```bash
sudo ufw enable
```

Allow traffic from a source:

```bash
sudo ufw allow from <source>
```

Allow a proto from anywhere:

```bash
sudo ufw allow <proto:ssh>
```

Deny traffic from a source:

```bash
sudo ufw deny from <source>
```

Delete a rule with number:

```bash
sudo ufw status numbered # Show numbers
sudo ufw delete <number>
```

:::info
`<source>` can be `192.168.195.100` for a specific machine or `192.168.195.0/24` for any machine from `192.168.195`.
:::

:::cation
ufw is order-matter.
:::

Reload to activate settings:

```bash
sudo ufw reload
```

Show all IP setup:

```bash
sudo iptables -L
```

### Setup

See `/etc/utw/`. `/etc/utw/before.rules` means process before user created rules, `/etc/utw/after.rules` means afterwards.

:::info
`ping` uses `icmp`. By default, Linux System firewall accepts `pint` while Windows drops.
:::

Backup and restore all setups:

```bash
sudo utw reset
```

## Active Defense

Show potential malicious connections:

```bash
netstat -tunap # t For TCP connections
               # u For UDPconnections
               # n Display port rather than names. For example, display 22 for SSH
               # a For hidden connections
               # p Show PID/Program name. This requires sudo
netstat | grep ESTABLISHED # Display only actively created connections
```

Kill connections/processes:

```bash
sudo kill <PID>
# Or
sudo pkill -KILL -u <username>
```

:::info
`ss` and `w` are other useful alternatives for `netstat`. `ss` is very similar to `netstat`, `w` shows only user connections. `top` and `htop` show processes from CPU perspective. `ps` shows minimum information, unless use with options such as `ps -aux`.
:::

Show messages to all connections:

```bash
wall # A prompt will display
```

Send message to a connection:

```bash
w # Show USER and TTY
sudo write <user> <tty> # A prompt will display
```
