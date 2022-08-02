# Homework Assignment

![Week 3](https://img.shields.io/badge/Week-3-blue)

A homework assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **July 30, 2022**.

## File Structure

Submitted files.

```
├── README.md
└── Entry.java
```

## Question 1

Write a recursive method to produce a pattern of n lines of asterisks. The first line contains one asterisk, the next line contains 2, and so on, up to the nth line, which contains n asterisks, line n+1 contains n asterisks. The next line has n-1 asterisks, and so on until line number 2n which has 1 asterisk. (50 Points)

### Java Code

Please see `printRecursive` from [Entry.java](./Entry.java) or use `sh compile.sh` to compile and execute.

### Runs

Run results.

#### 1th

`max` is 10:

```
Please enter a valid integer number:
10
*
**
***
****
*****
******
*******
********
*********
**********
**********
*********
********
*******
******
*****
****
***
**
*
```

#### 2th

`max` is 3:

```
Please enter a valid integer number:
3
*
**
***
***
**
*
```

#### 3th

`max` is 1:

```
Please enter a valid integer number:
1
*
*
```

## Question 2

Write a recursive method to convert a character string of digits to an integer. Example convert(“1234”); returns 1234. Hint to convert a numeric character to a number subtract ASCII ‘0’ from it. (50 points)

### Java Code

Please see `convertRecursive` from [Entry.java](./Entry.java) or use `sh compile.sh` to compile and execute.

### Runs

Run results.

#### 1th

`input` is 1234:

```
Please enter a valid integer number:
1234
Your value: 1234
Convert 1234 (java.lang.String) to 1234 (java.lang.Integer).
```

#### 2th

`input` is 0:

```
Please enter a valid integer number:
0
Your value: 0
Convert 0 (java.lang.String) to 0 (java.lang.Integer).
```

#### 3th

`input` is 9999:

```
Please enter a valid integer number:
9999
Your value: 9999
Convert 9999 (java.lang.String) to 9999 (java.lang.Integer).
```
