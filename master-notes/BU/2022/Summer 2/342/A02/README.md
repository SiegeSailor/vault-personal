# Programming Assignment

![Assignment 2](https://img.shields.io/badge/Assignment-2-orange)

A programming assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](mailto:siege@bu.edu) submitted on **July 20, 2022**.

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

The user has to input 8 times coordinates, and the program will check if the input coordinate is valid by walking through all the choices in the stack. Once 8 non-conflict choices are made, then the problem is solved. Operation pseudo-code:

```
create a stack
while the size of is less than 8
    display current graph
    ask the user to input a coordinate
    if the user enters clear
        start over
    if there is a conflict
        continue
    else
        push the coordinate onto stack
```

Operation flowchart:

[![Flowchart](https://mermaid.ink/img/pako:eNpNkEFPhEAMhf9K05Mmu3fDwURGNF50DV4Mw2HCFJkEOmToxBjgvzu4rNpT8973mrYzNt4SZvgRzNjBW64ZUt1VLyMFI84zlGKC1HA83kJ-qsrOf4KKIRALPG6h-hzJTxuSXz3xGAWU98E6NkLXu_0zQM3Kc9u7RtazrDZ5eadpSQP-S89-gWK-gddIxNNOF7_WBS7-8g9VwbbGAw4UBuNsOmreII3S0UAas9Raak3sRaPmNaFxtGnFwjrxAbPW9BMd0ETx5Rc3mEmIdIHunUk_GnZq_QZH5WMK)](https://mermaid.live/edit#pako:eNpNkEFPhEAMhf9K05Mmu3fDwURGNF50DV4Mw2HCFJkEOmToxBjgvzu4rNpT8973mrYzNt4SZvgRzNjBW64ZUt1VLyMFI84zlGKC1HA83kJ-qsrOf4KKIRALPG6h-hzJTxuSXz3xGAWU98E6NkLXu_0zQM3Kc9u7RtazrDZ5eadpSQP-S89-gWK-gddIxNNOF7_WBS7-8g9VwbbGAw4UBuNsOmreII3S0UAas9Raak3sRaPmNaFxtGnFwjrxAbPW9BMd0ETx5Rc3mEmIdIHunUk_GnZq_QZH5WMK)

### Component

There are 4 components based on the design:

- Main Operation
- Current Board Graph
- Conflict Detecting
- Input Interface

## Runs

Use many helping commands:

```

```

A few conflict:

```

```

Prefect maths. No conflicts:

```

```
