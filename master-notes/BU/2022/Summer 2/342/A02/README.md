# Programming Assignment

![Assignment 2](https://img.shields.io/badge/Assignment-2-blue)

A programming assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **July 20, 2022**.

## File Structure

Submitted files within the compressed folder.

```
├── class/
├── docs/
├── README.md
├── source.txt
├── Entry.java
├── LinkedList.java
├── Node.java
└── compile.sh
```

## 8 Queens Problem

You are to write a program that solves the 8 queens problem. The 8 queens problem postulates that you can place 8 queens on a chess board, so that no queen could capture any other. You will use a stack to solve this problem. On a standard chess board such as below left, a queen can move as shown. (Queen can travel to any of the gray squares)

### Detail

The premise of this program is to utilize a Stack class. You must use a Linked List stack to handle this. For the project you are to write a function that determines if there is a solution. If a solution is found, you must display a representation of a board showing the queens (As above).

Your program should solve the problem by making a sequence of choices, such as row 1, column 1, or row 5, column 3. Each time the choice is made it is pushed onto a stack,, that already contains all the previously made choices. The purpose of the stack is to make it easy to fix incorrect choices, using the following algorithm, or one of your own choosing. This algorithm assumes a stack s, and a Boolean variable called success.

## Algorithmic Design
