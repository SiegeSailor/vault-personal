---
title: NCAE Cyber Games Networking
description: Knowledge learned during the preparation of NCAE Cyber Games.
slug: ncae-cyber-games
authors: maintainer
tags: [networking]
---

Test if a network is reachable:

```bash
ping <address>
```

:::info
There are differences between an unreachable network and a down network but reachable. Trying to reach an unreachable network will stop the looping command right away while a down network will respond some messages.
:::

Show network interface configs. Note that `ifconfig` only shows the first IP configuration while `ip` can list all additional IP addresses:

```bash
ifconfig # Mac and Kali
ip a # Ubuntu and CentOS
```

## IP Command

Use command line with `ip` to add configurations. This is temporary, which means it doesn't survive after restarting the machine:

```bash
ip addr add <#.#.#.#>/# dev <interface_name>
```

:::info
`dev` means device.
:::

Remove a network interface IP configuration. This is still temporary:

```bash
ip addr flush dev <interface_name>
```

Since all changes made with `ip` are temporary, you can use restart the system service to bring the configuration back:

```bash
sudo systemctl restart networking # Kali
sudo systemctl restart network # CentOS
sudo netpaln apply # Ubuntu
```

### IP Address

Let's say we have an IP address `172.20.66.100/16`, `172.20.66.100` is the address, and `16` is the netmask. `16` means from left to right, there are 16 sequential 1 bits out of 32 bits. `11111111111111110000000000000000` in 2-bit format, `11111111.11111111.00000000.00000000` in 8-bit format, and `255.255.0.0` in 10-bit format. 10-bit format is what we fill in the network interfaces.

onvert `address` and `netmask` to 2 bits, and do **AND** operation, the result would be the network you are in. For the example above, we will get `172.20.0.0`. Note that `172.20.9.0` is the network itself, and `172.20.0.255` is the broadcast address. In IPV4 we have $2^{16} - 2$ addresses can be distributed.

## Configure IP

Permanently configure on various OS.

### Kali

`/etc/network/interfaces` is where to configure network interfaces:

-   `<interface_name>`: Must be an existing interface, for example, `ens18` for Ubuntu and `eth0` for Kali
-   `<method>` can be `loopback` to direct back to the machine or `static` to use determined address, or `dhcp` for automatically being assigned if you have a DHCP server available

```
auto <interface_name>
iface <interface_name> inet <method>
    address <#.#.#.#>
    netmask <#.#.#.#>
```

Restart system services:

```bash
sudo systemctl restart networking
```

### CentOS

`/etc/sysconfig/network-scripts/` is where to configure network interfaces, each interface is contained in separate files. For example, `/etc/sysconfig/network-scripts/ifcfg-eth0`.

```
BOOTPROTO=<method>
ONBOOT=<boolean>
IPADDR=<#.#.#.#>
NETMASK=<#.#.#.#>
```

Restart system services:

```bash
sudo systemctl restart network
```

### Ubuntu

`/etc/netplan/` is where to configure network interfaces, each file in the folder is a network paln.

```
network:
  version: #
  renderer: NetworkManager
  ethernets:
    ens18:
      addresses:
        - #.#.#.#/#
```

Restart system services:

```bash
sudo netpaln apply
```

## Setup Network

Use `nc` or `netcat` to setup network service. Run the machine as a server:

```bash
nc -l -p <port_number> # -l means listening
nc -l -p <port_number> -e /bin/bash # Use the assigned program to execute the message
```

To connect a server. Once connected, both side can start typing and sending messages.:

```bash
nc <#.#.#.#> <port_number>
```

Python provides a way to use system packages:

```python
import os

while True:
  os.system("nc -l -p <port_number> -e /bin/bash")
```

:::info
You may need to use `control` + `z` to quit from Python.
:::
