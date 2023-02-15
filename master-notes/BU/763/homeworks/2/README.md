# Homework

A homework for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **February 14, 2023**.

## Problem 1

_Based on the use case diagram provided below for the blackboard system, identify 1 or more possible adversaries (as additional actors). Add 5 abuse cases that may threaten existing use cases and additional use cases that can mitigate these abuse cases. Make sure that a use case (or an abuse case) should be written as a short, active verb phrase that defines the goal of the use case:_

![Diagram](./Problem%201.png)

## Problem 2

_Choose one of the abuse cases you added in the above problem, and create an attack tree to show possible ways to achieve that goal. The attack tree should have at least 3 levels and 6 nodes:_

![Diagram](./Problem%202.png)

## Problem 3

_Suppose we have the following simplified data flow diagram for the ProjectPortal project, focusing on the project data flow. Apply STRIDE on each of the following elements: project owner, general user, project data, web client, web server, database. Identify at least 6 possible threats and for each threat, propose 1 or 2 mitigation:_

![Diagram](./Problem%203.png)

| STRIDE                 | Element       | Threat                                                        | Mitigation                                                                        |
| ---------------------- | ------------- | ------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| Spoofing               | General User  | Pretend to be others and leave false account history          | Password, MFA                                                                     |
| Tampering              | Project Data  | Existing data are modified by an attacker                     | Access Control, MFA                                                               |
| Repudiation            | Project Owner | Don't know which owner sent the data                          | Logging, Digital Signature                                                        |
| Information Disclosure | Web Client    | Users can view project summary even if they are not the owner | Access Control                                                                    |
| Denial of Service      | Database      | Queries are pending due to high volume transactions           | Application Load Balancer, Network Load Balancer, Gateway Load Balancer, Sharding |
| Elevation of Privilege | Web Server    | SSH connections gain root privilege by using buffer overflow  | Data Execution Prevention, Address Space Layout Randomization                     |

## Problem 4

_Choose one technique from one tactic in the MITRE ATT&CK framework, and do some research. Answer the following questions:_

**Spearphishing Link** (T1566.002) from **Initial Access** (TA0001).

_Briefly describe the chosen tactic and technique:_

Initial Access (TA0001) is a tactic in the MITRE ATT&CK framework that refers to the methods used by an attacker to gain a foothold into a target system or network. This may involve exploiting vulnerabilities in software, hardware, or human behavior to gain initial access. Common techniques used in this tactic include phishing, credential stuffing, exploiting vulnerable software or hardware, and compromising trusted third-party services.

Spearphishing Link (T1566.002) is a technique within the Initial Access tactic that involves sending targeted phishing emails containing a malicious link to a victim. The email is often crafted to look like it came from a legitimate source, such as a trusted business partner or service provider. When the victim clicks the link, they are redirected to a malicious website that may prompt them to enter their login credentials or download malware onto their system. This technique is often used to gain access to a victim's system or network, allowing the attacker to carry out further attacks or steal sensitive data.

_Can you provide some detail about how you can exploit/test this technique?_

One approach is to conduct simulated phishing campaigns on employees, which involves sending fake Spearphishing Link emails to employees to see how many fall for the scam. This can be a valuable way to measure an organization's susceptibility to Spearphishing Link attacks and identify areas for improvement.

Another way to test defenses against Spearphishing Link attacks is to conduct a vulnerability assessment and penetration testing. This involves hiring ethical hackers to identify vulnerabilities in an organization's systems and attempt to exploit them using techniques like Spearphishing Link. The results of these tests can be used to identify weaknesses in an organization's security posture and prioritize remediation efforts.

_Is it used by any APT group?_

In 2020, a group of Chinese hackers known as APT41 used Spearphishing Link attacks to gain access to the systems of over 75 companies across 20 countries. The group targeted companies in a wide range of industries, including healthcare, finance, and technology.

The attackers sent emails containing malicious links to employees of the targeted companies, hoping to trick them into clicking on the links and downloading malware onto their systems. The emails were designed to look like they came from legitimate sources, such as business partners or suppliers.

Once the malware was installed on a victim's system, the attackers could use it to steal sensitive data, install additional malware, or carry out other malicious activities. The group was particularly adept at using "watering hole" attacks, in which they would compromise a website frequently visited by the target company's employees, and then use the website to distribute the malware.

In one instance, APT41 targeted a healthcare company and used Spearphishing Link attacks to gain access to the company's network. Once inside, they were able to steal the personal information of over 5 million patients, including their names, addresses, and dates of birth.

_What are possible mitigation techniques?_

- **Security Awareness Training**: Educate employees on how to recognize and avoid phishing emails. This includes providing regular training sessions and creating policies and guidelines for safe email practices
- **Email Filters**: Use email filters to detect and block suspicious emails by scanning incoming emails for known malicious links and attachments, or blocking emails that come from untrusted sources or contain suspicious content
- **Multi-Factor Authentication**: Require an additional form of authentication beyond a username and password can make it more difficult for attackers to gain access to sensitive data or systems
- **Network Segmentation**: Segment a network can limit the impact of a successful Spearphishing Link attack by isolating compromised systems and limiting lateral movement by attackers
- **Software and System Updates**: Keep software and systems up-to-date can help prevent Spearphishing Link attacks by ensuring that known vulnerabilities are patched and addressed
- **Incident Response Plan**: Have an incident response plan in place can help organizations quickly identify and respond to Spearphishing Link attacks. The plan should include procedures for isolating compromised systems, conducting a forensic analysis, and notifying law enforcement if necessary

_Based on your research, is there anything that can be added to the existing MITRE ATT&CK technique page?_

The following is the original paragraph from **Spearphishing Link** (T1566.002):

> Adversaries may send spearphishing emails with a malicious link in an attempt to gain access to victim systems. Spearphishing with a link is a specific variant of spearphishing. It is different from other forms of spearphishing in that it employs the use of links to download malware contained in email, instead of attaching malicious files to the email itself, to avoid defenses that may inspect email attachments. Spearphishing may also involve social engineering techniques, such as posing as a trusted source.

There are a few points that I think can be added to the page:

- The email is often crafted to look like it came from a legitimate source, such as a trusted business partner or service provider
- After clicking the link, they are redirected to a malicious website that may prompt them to enter their login credentials or download malware onto their system
