- [Lab](#lab)
  - [Question 1](#question-1)
    - [Solution](#solution)
  - [Question 2](#question-2)
    - [Solution](#solution-1)
  - [Question 3](#question-3)
    - [Solution](#solution-2)
  - [Question 4](#question-4)
    - [Solution](#solution-3)
      - [1. Non-constant string passed to execute on an SQL statement](#1-non-constant-string-passed-to-execute-on-an-sql-statement)
  - [Question 5](#question-5)
    - [Solution](#solution-4)
  - [Question 6](#question-6)
    - [Solution](#solution-5)
  - [Summary](#summary)

# Lab

A lab for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **February 24, 2023**.

## Question 1

How many bug items are identified by SpotBugs? What are the categories of these bugs? What are security bugs identified? Modify the configuration to only report the scary bugs. How many are they? Can you find any false positives?

### Solution

There are **66** bugs identified in **15** classes by SpotBugs. Categories of these bugs are **Security**, **Bad Practice**, **Experimental**, **Internationalization**, **Dodgy Code**, **Correctness**, and **Performance**:

![SpotBugs](./SpotBugs.png)

Security bugs identified:

![SpotBugsSecurity](./SpotBugsSecurity.png)

Modify the configuration to only report the scary bugs:

![IntelliJSpotBugsSettings](./IntelliJSpotBugsSettings.png)

**5** scary bugs identified in **15** classes:

![SpotBugsScary](./SpotBugsScary.png)

False positives found as following. The developer has actually indicated a TODO below `out` variable, but SpotBugs still reports it as _dead local store_:

![SpotBugsFalsePositive](./SpotBugsFalsePositive.png)

## Question 2

How many issues are identified by SonarQube? What are the categories of these issues? What is the difference between vulnerability and hot-spots? How many critical security issues are identified by SonarQube? Can you find any false positives?

### Solution

There are **139** issues identified by SonarQube. Categories of these issues are **Bug**, **Vulnerability**, and **Code Smell**:

![SonarQubeCMD](./SonarQubeCMD.png)

![SonarQubeWeb](./SonarQubeWeb.png)

The difference between vulnerability and hotspots can be found [here](https://docs.sonarqube.org/9.6/user-guide/security-hotspots/):

> The main difference between a hotspot and a vulnerability is the _need_ for review before deciding whether to apply a fix:
>
> - With a hotspot, a security-sensitive piece of code is highlighted, but the overall application security may not be impacted. It's up to the developer to review the code to determine whether or not a fix is needed to secure the code.
> - With a vulnerability, a problem that impacts the application's security has been discovered and needs to be fixed immediately.

There are **0** critical security issues are identified by SonarQube:

![SonarQubeNoSecurity](./SonarQubeNoSecurity.png)

False positives found as following. The developer has actually indicated a TODO below `out` variable, but SonarQube still reports it as _remove this useless assignment to local variable_:

![SonarQubeFalsePositive](./SonarQubeFalsePositive.png)

## Question 3

Compare the results by SpotBugs and SonarQube and state your findings.

### Solution

SpotBugs and SonarQube are kind reversed version for each's names. I suppose that SpotBugs would provide more messages for Java related bugs while SonarQube would offer more suggestions for general programming issue. However, it turns out that SpotBugs has very detailed documentation for bugs such as _HTTP Response splitting vulnerability_, which all programming language would face, while SonarQube indicates the cause and solution of _Make the enclosing method "static" or remove this set_ that the syntax only exists in Java.

Overall, SpotBugs are faster, and it is easy-to-use since it has been well integrated within IDEs (can write the solution and cause at one single window). SonarQube is slower, but it has many other features that SpotBug doesn't have such as tagging, issuing tickets, and visualizing.

## Question 4

Choose 5 security bugs (or security issues) from any report and explain them in more detail. Do you know how to fix them? You may need to do additional research on them

### Solution

#### 1. Non-constant string passed to execute on an SQL statement

Located at `src/main/java/org/cysecurity/cspf/jvl/controller/EmailCheck.java` line 48 to 59. The method invokes the execute or addBatch method on an SQL statement with a String that seems to be dynamically generated:

```java
Statement stmt = con.createStatement();
rs=stmt.executeQuery("select * from users where email='"+email+"'");
```

Consider using a prepared statement instead. It is more efficient and less vulnerable to SQL injection attacks:

```java
stmt = con.prepareStatement("UPDATE menu set menu.name=?, menu.info=?, menu.price=? where menu.menuID=?")
stmt.setString(1, value2); /* menu.name */
stmt.setString(2, value3); /* menu.info */
stmt.setFloat(3, value4);  /* menu.price */
stmt.setInt(4, value1);    /* menuID */
stmt.executeUpdate();
```

## Question 5

If you are familiar with Java and web development, you may review the code manually, and see if you can find any security issues that are not identified by SpotBugs or SonarQube?

### Solution

## Question 6

If you have done any software project before, use SpotBugs and SonarQube to examine one of your projects and report your findings.

### Solution

## Summary

A summary of your own reflection on the lab exercise, such as:

- What is the purpose of the lab in your own words?
- What did you learn? Did you achieve the objectives?
- Was this lab hard or easy? Are the lab instructions clear?
- What do you think about the tools used? What worked? What didn’t? Are there other better alternatives?
- Any other feedback?
