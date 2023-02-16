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

Check ports:

```bash
netstat
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

Running command with `--permanent` can affect permanently, but it usually doesn't affect immediately, and therefore require `--reload` to restart:

```bash
sudo firewall-cmd --<command>=<argument> --permanent
```

Restart the firewall:

```bash
sudo firewall-cmd --reload
```

Forward coming traffic in a zone from a port to another address. This allow the machine works as a router:

```bash
sudo firewall-cmd --zone=<zone_name> --add-forward-port=port=<prot_number>:proto=<tcp/udp>:toport=<prot_number>:toaddr=<#.#.#.#>
sudo firewall-cmd --zone=external --add-forward-port=80:proto=tcp:toport=80:toaddr=192.168.66.2 # HTTP example
sudo firewall-cmd --zone=external --add-forward-port=22:proto=tcp:toport=22:toaddr=192.168.66.2 # SSH example
```

Remove a record:

```bash
sudo firewall-cmd --zone=<zone_name> --remove-forward-port=port=<prot_number>:proto=<tcp/udp>:toport=<prot_number>:toaddr=<#.#.#.#>
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

Allow or disallow certain service through certain zone:

```bash
sudo firewall-cmd --zone=<zone_name> --permanent --remove-service=ssh
sudo firewall-cmd --zone=<zone_name> --permanent --add-service=ssh
```

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

To log in a server with SSH:

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
-   CentOS: `/etc/ssh`

Generate new keys:

```bash
sudo ssh-keygen -t <encryption_type> -f <generated_keys_location>
```

:::info
The permission is `600` for private, and `644` for public keys.
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

:::info
If the host server still asks for password, check for the permissions of the private key on guest server and `authorized_key` on host server.
:::

## DNS

`/etc/bind/named.conf*` is where to lookup. We can use the following command to check if it is running:

```bash
systemctl status named
```

`/etc/bind/named.conf.default-zones` will be the main file to define DNS. Refer to the format:

```bash
# Domain Lookup
zone "<domain.name.org>" IN {
  type master;
  file "/etc/bind/zones/forward.<domain.name.org>";
  allow-update { none; };
};

# IP Address Lookup
# You may need to add more than one zone if you need multiple reverse lookup for different IPs
zone "8.168.192.in-addr.arpa" IN { # 8.168.192 is a reverse from 192.168.8
  type master;
  file "/etc/bind/zones/reverse.<domain.name.org>";
  allow-update { none; };
};
```

:::info
`/zones/` are not default created. You have to create it manually. You will also want to set its permission to `644` in environments such as CentOS.
:::

:::caution
For IP addresses defined for zone, we need to see the line and the configuration in the file as one. If zone already takes care of the first three part, then the file only need to take care of the last part at `@`.
:::

Then, do `sudo cp /etc/bind/db.empty /etc/bind/zones/forward.<domain.name.org>` and modify it:

```bash
$TTL 86400
@       IN        SOA       <domain.name.org> <username.name:root> (
                                    1       ; Serial # Update this by one every time modifying the file
                                  ...       ; ...
                                  ... )     ; ...
;
@       IN        NS        <computer_name:sandbox-Ubuntu>
<subdomain>       IN A      <address> # Find <subdomain.domain.name.org> will find <address>. <address> is #.#.#.#
<subdomain>       IN A      <address> # Could be as same as the above
```

Then, do `sudo cp /etc/bind/db.empty /etc/bind/zones/reverse.<domain.name.org>` and modify it:

```bash
$TTL 86400
@       IN        SOA       <domain.name.org>. <username.name:root>. (
                                    1       ; Serial # Update this by one every time modifying the file
                                  ...       ; ...
                                  ... )     ; ...
;
@       IN        NS        <computer_name:sandbox-Ubuntu>.
<address>         IN PTR    <subdomain>. # <address> here only the first part of the reversed one. For example here will be 2 for 192.168.8.2
<address>         IN PTR    <subdomain>. # <address> Must be the same
```

:::info
`www` is one of the most common subdomain.
:::

Finally, run:

```bash
sudo systemctl restart named
```

### Setup

Configure `/etc/netplan/` to tell the computer where the DNS server is, each file in the folder is a network paln.

```
network:
  version: #
  renderer: NetworkManager
  ethernets:
    ens18:
      addresses:
        - #.#.#.#/#
```

Or configure `/etc/resolv.conf`:

```
nameserver <address>
nameserver 127.0.0.53
```

:::info
`<address>` doesn't need to be as same as the current machine. A machine can include DNS servers on other IP addresses as long as they are in the same network topology.
:::

:::info
You can use `nslookup <domain_name> | <address>` to check DNS directing instead of using browsers.
:::

:::caution
If there is a router between the DNS server and your machine, the router must configure forward with port `53` and proto `udp` in oder to connect.
:::
