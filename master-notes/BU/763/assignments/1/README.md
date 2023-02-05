# Assignment

A lab for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **February 4, 2023**.

## Problem 1

**Search the Internet for a cyber attack in the last two or three years. Describe the attack in more details (e.g. who, when, what) and also address the following questions:**

- **Who are attackers and targets? When and How was the attack done? What underlying vulnerabilities were exploited?**
- **How much damage did the attack cause? What are possible mitigation?**

| Label                      | Detail                                                                                                                                                                                                                              |
| -------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Attacker                   | Unknown                                                                                                                                                                                                                             |
| Target                     | Mailchimp                                                                                                                                                                                                                           |
| When and How               | On 11 January 2023. The unauthorized actors conducted a social engineering attack on Mailchimp employees and contractors, and obtained access to selected Mailchimp accounts using employee credentials compromised in that attack. |
| Underlying Vulnerabilities | Access credentials exposed by the company's employees and contractors. Lack of account information security on personal machines.                                                                                                   |
| Damage                     | 133 customer information (users' names, store URLs, addresses, and email addresses but not their payment data, passwords, or other sensitive information).                                                                          |
| Possible Mitigation        | Security education. Internal nework access only.                                                                                                                                                                                    |

## Problem 2

**Compare Seven Touch-Points, Microsoft SDL and OWASP SAMM model, describe the similarities and differences in your own words. Which model would you like to adopt if you are in charge of a software development project? States your assumptions (about the project, develop team, organization) and relational.**

### Similarities

All three frameworks focus on a comprehensive approach to software security and address security risks throughout the software development lifecycle (Security should be considered in all activities in the DevOps lifecycle.). They emphasize the importance of involving all stakeholders, including developers, security professionals, and management.

### Differences

the Seven Touch-Points framework provides guidance on how to address security risks at specific stages of the software development process, the Microsoft SDL provides a comprehensive set of best practices for secure software development (and they provide Microsoft SDL Process Guidance illustrates the way Microsoft applies the SDL to its own technologies and software), and the OWASP SAMM provides a maturity model for organizations to assess and improve their software security posture.

- Seven Touch-Points: 7 Stages
- Microsoft SDL: 12 Practices
- OWASP SAMM: 5 Business Functions

### Choice of Model

OWASP SAMM.

### Assumption

This answer is based on my plan for the research project. I am going to conduct security analyze for an Open Source project [Formulas](https://github.com/SiegeSailor/Formulas), which was inspired by course 789 Cryptograph from last semester.

The project provides a collection of cryptography-related functions for software developers to use in their products and a command line tool with interactive prompts to demonstrate key encryption flows and algorithms. Furthermore, I plan to get a backend side with database to store Contributed Test Cases/Cryptograph APIs/Account Information and History.

I would be the only developer in for this project, and the organization would have only one person, me, signed.

It is unlikely for me to choose Microsoft since the main tools/frameworks are not Microsoft based (although there is still a simplified version of the guideline for non-Microsoft based product). In addition, the project has been ongoing for awhile, so I am not going to choose the Seven Touch-Points since I will have to do some extra work for earlier stages. OWASP SAMM seems to be the best choice for me because it can adapt to a wild range of organization and softwares.
