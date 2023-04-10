# Homework

A homework for class **MET CS 763** at Boston University, instructed by **Yuting Zhang**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **April 11, 2023**.

## Problem 1

Answer the questions based on the following program:

```c
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LEN 256

int main(int argc, char* argv[]) {
    char *greetingMsg = malloc(MAX_LEN);
    char name[MAX_LEN];

    char greeting[] = "Hello!";
    int len = 0;

    greetingMsg[0] = '\0';

    strncat(greetingMsg, greeting, sizeof(greeting));

    int i = 1;
    while (i < argc) {
        strncpy(name, argv[i], strlen(argv[i]));
        if (i > 1)
            strncat(greetingMsg, ", ", 1);
        strncat(greetingMsg, name, strlen(name));
        i++;
    }
    strncat(greetingMsg, "\n", 1);
    printf(greetingMsg);
    return 0;
}
```

**What are variables in this program? Where is each variable stored:**

- `MAX_LEN`:
- `greetingMsg`:
- `name`:
- `greeting`:
- `len`:

**What are bugs/vulnerabilities in this program? Briefly describe each of them:**

## Problem 2

Suppose Alice wants to communicate with Bob, please describe all necessary crypto means to provide confidentiality and integrity for this communication channel. In particular, your solution should prevent MITM. Please also state your assumptions:

## Problem 3

Please read the paper “Analysis of the HTTPS Certificate Ecosystem“ that is posted on Blackboard the assignment page and answer the following questions:

**What is PKI?**

**What are the differences and relationships between digital signature and digital certificate?**

**What is HTTPS? How is digital signature/digital certificate used?**

**Write one/two paragraphs to state issues in the HTTPS ecosystem described in the paper.**

**This paper was published in 2013. Do some research on the internet to find any solution that has been developed or used to address the issues and improve the situation in recent couple of years.**
