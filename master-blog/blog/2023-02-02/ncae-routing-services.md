---
title: NCAE Cyber Games Routing and Services
description: Knowledge learned during the preparation of NCAE Cyber Games.
slug: ncae-cyber-games-routing-services
authors: maintainer
tags: [routing, service]
---

Check running services. `<service_name>` can be `apache2`, `httpd`, `nginx`, `sshd`, or any service-name-like folder located in `/etc`:

```bash
systemctl status <service_name>
```

Start a service:

```bash
systemctl start <service_name>
```

Enable a service to turn on automatically every time the machine is on:

```bash
systemctl enable <service_name>
```

:::info
`enabled/disabled` means whether the service will automatically turn on when the machine starts while `active/inactive` means whether the service is currently running.
:::

Make requests:

```bash
curl http://<#.#.#.#>
curl http://<#.#.#.#> > <file> # Download
wget http://<#.#.#.#>
```

## Apache

`/etc/apache2` is where to store the configuration files, and `etc/apache2/apache2.conf` is the entry point. `/etc/apache2/sites-enabled` are currently running site configuration.

:::info
For detailed documentation, visit https://httpd.apache.org/docs/2.4/. The video is only about where the files are.
:::

## CentOS Firewall

Check firewall:

```bash
sudo firewall-cmd
sudo firewall-cmd --list-all-zones
sudo firewall-cmd --list-all --zone=<zone_name>
```

:::info
Setting `masquerade` to `yes` allows the machine works as a route to let outside traffic to reach outer addresses.
:::

To run any command permanently. Usually it doesn't affect immediately, and therefore require `--reload` to restart:

```bash
sudo firewall-cmd --<command>=<argument> --permanent
```

Restart after any command:

```bash
sudo firewall-cmd --reload
```

Forward coming traffic in a zone from a port to another address. This allow the machine works as a router:

```bash
sudo firewall-cmd --zone=<zone_name> --add-forward-port=port=<prot_number>:proto=<tcp/udp>:toport=<prot_number>:toaddr=<#.#.#.#>
```

:::info
You have to set up gateways to allow the desired server's traffic to pass through the router's address first. The following is the configuration file on Ubuntu `/etc/netplan/<interface_name>`:

```
network:
  version: #
  renderer: NetworkManager
  ethernets:
    ens18:
      addresses:
        - #.#.#.#/#
      gateway4: #.#.#.#
      <!-- or -->
      routes:
        - to: default
          via: #.#.#.#
```

This is the configuration file on Kali `/etc/network/interfaces`:

```
auto <interface_name>
iface <interface_name> inet <method>
    address <#.#.#.#>
    netmask <#.#.#.#>
    gateway <#.#.#.#>
```

:::

### Interface and Zone

Add zone to a specific network interface:

```
BOOTPROTO=<method>
ONBOOT=<boolean>
IPADDR=<#.#.#.#>
NETMASK=<#.#.#.#>
ZONE=<zone_name>
```

Then restart system services:

```bash
sudo systemctl restart network
```

Change an interface to a desired zone with `firewall-cmd` command:

```bash
sudo firewall-cmd --change-interface=<interface_name> --zone=<target_zone_name>
```

## SSH

TO log in a server with SSH:

```bash
ssh <username>@<#.#.#.#>
exit # Leave
```

:::info
Logging in over SSH requires the user's password. You can give out your public key instead of your user password.
:::

:::info
A public key fingerprint is a short sequence of bytes used to identify a longer public key.
:::

Places storing SSH files:

-   Kali: `~/.ssh`
-   Ubuntu: `/etc/ssh`

Generate new keys:

```bash
sudo ssh-keygen -t <encryption_type> -f <generated_keys_location>
```

:::info
The permission for private keys should be `600` and `644` for public keys.
:::

### No Password Authentication

Login over SSH without user password.

#### Host Server

First of all, create a pair of key for a user. You may want to create a user specifically for this:

```bash
sudo ssh-keygen -t <encryption_type> -f <generated_keys_location>
```

:::info
After creating key pairs, you can use the simple command `sudo ssh-copy-id <private_key> <username>@<#.#.#.#>` to install the public key as `authorized_key` in `/home/<username>/.ssh` without any permission setup. The following commands can be ignored if you use it.
:::

Then copy the public key to the user's `.ssh` folder as a file `authorized_keys`. You may need to create the folder if there isn't any:

```bash
sudo cp <public_key> /home/<username>/.ssh/authorized_keys
```

We have to take care of the `.ssh` and `authorized_keys` folder's permission. Note that the owner and the group the folder belongs to should be as same as the user:

```bash
sudo chmod 700 /home/<username>/.ssh
sudo chmod 644 /home/<username>/.ssh/authorized_keys
```

#### Guest Server

Transfer the private key on the guest server using `scp` to send it from the host server:

```bash
scp <host_server_username>@<#.#.#.#>:<file> <target_location>
```

Then log in without password:

```bash
ssh -i <private_key> <host_server_username>@<#.#.#.#>
```
