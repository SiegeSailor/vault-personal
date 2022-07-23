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

Use as many commands as possible and input invalid command:

```
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 - - - - - - - -
1 - - - - - - - -
2 - - - - - - - -
3 - - - - - - - -
4 - - - - - - - -
5 - - - - - - - -
6 - - - - - - - -
7 - - - - - - - -

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X - - - - - -
2 X - X - - - - -
3 X - - X - - - -
4 X - - - X - - -
5 X - - - - X - -
6 X - - - - - X -
7 X - - - - - - X

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

BACK
(0, 0) has been removed.

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 - - - - - - - -
1 - - - - - - - -
2 - - - - - - - -
3 - - - - - - - -
4 - - - - - - - -
5 - - - - - - - -
6 - - - - - - - -
7 - - - - - - - -

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

Test for invalid input
Invalid format

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

X,X
Invalid input.

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0
(0, 0) has a conflict with (0, 0).

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X - - - - - -
2 X - X - - - - -
3 X - - X - - - -
4 X - - - X - - -
5 X - - - - X - -
6 X - - - - - X -
7 X - - - - - - X

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,2

You have made 2 choices: (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X Q X X X X X
2 X X X X - - - -
3 X - X X X - - -
4 X - X - X X - -
5 X - X - - X X -
6 X - X - - - X X
7 X - X - - - - X

You have made 2 choices: (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

3,1

You have made 3 choices: (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X Q X X X X X
2 X X X X - - - -
3 X Q X X X X X X
4 X X X - X X - -
5 X X X X - X X -
6 X X X - X - X X
7 X X X - - X - X

You have made 3 choices: (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

4,3

You have made 4 choices: (4, 3) (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X Q X X X X X
2 X X X X - X - -
3 X Q X X X X X X
4 X X X Q X X X X
5 X X X X X X X -
6 X X X X X X X X
7 X X X X - X X X

You have made 4 choices: (4, 3) (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,4

You have made 5 choices: (2, 4) (4, 3) (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X Q X X X X X
2 X X X X Q X X X
3 X Q X X X X X X
4 X X X Q X X X X
5 X X X X X X X X
6 X X X X X X X X
7 X X X X X X X X
There is no enough slots. You may want to BACK or CLEAR.

You have made 5 choices: (2, 4) (4, 3) (3, 1) (1, 2) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

CLEAR
Choices have been cleared.

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

6,1

You have made 2 choices: (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

4,2

You have made 3 choices: (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,4

You have made 4 choices: (7, 4) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,5
(1, 5) has a conflict with (4, 2).

You have made 4 choices: (7, 4) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X - X X X -
2 X X X - X X - -
3 X X X X X - - -
4 X X Q X X X X X
5 X X X X X X X -
6 X Q X X X X X X
7 X X X X Q X X X

You have made 4 choices: (7, 4) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,4
(1, 4) has a conflict with (7, 4).

You have made 4 choices: (7, 4) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

BACK
(7, 4) has been removed.

You have made 3 choices: (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,4

You have made 4 choices: (7, 4) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

BACK
(7, 4) has been removed.

You have made 3 choices: (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,3

You have made 4 choices: (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,4
(2, 4) has a conflict with (4, 2).

You have made 4 choices: (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,5
(1, 5) has a conflict with (4, 2).

You have made 4 choices: (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,4

You have made 5 choices: (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

3,5

You have made 6 choices: (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X -
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X - X
6 X Q X X X X X X
7 X X X Q X X X X

You have made 6 choices: (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

5,6

You have made 7 choices: (5, 6) (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X -
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X Q X
6 X Q X X X X X X
7 X X X Q X X X X

You have made 7 choices: (5, 6) (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,7
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X Q
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X Q X
6 X Q X X X X X X
7 X X X Q X X X X
Congratulation! You have placed 8 queens on the board without conflict. Would you like to start over? (Y)
Y
Choices have been cleared.

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

DRAW
Row / Column
  0 1 2 3 4 5 6 7
0 - - - - - - - -
1 - - - - - - - -
2 - - - - - - - -
3 - - - - - - - -
4 - - - - - - - -
5 - - - - - - - -
6 - - - - - - - -
7 - - - - - - - -

Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,7

You have made 1 choices: (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

5,6

You have made 2 choices: (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

3,5

You have made 3 choices: (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,4

You have made 4 choices: (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,3

You have made 5 choices: (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

4,2

You have made 6 choices: (4, 2) (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

6,1

You have made 7 choices: (6, 1) (4, 2) (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X Q
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X Q X
6 X Q X X X X X X
7 X X X Q X X X X
Congratulation! You have placed 8 queens on the board without conflict. Would you like to start over? (Y)
nope
8 queens game finished.
```

One conflict:

```
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0

You have made 1 choices: (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

6,1

You have made 2 choices: (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

4,2

You have made 3 choices: (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,3

You have made 4 choices: (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,5
(1, 5) has a conflict with (4, 2).

You have made 4 choices: (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,4

You have made 5 choices: (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

3,5

You have made 6 choices: (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

6,6
(6, 6) has a conflict with (6, 1).

You have made 6 choices: (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

5,6

You have made 7 choices: (5, 6) (3, 5) (1, 4) (7, 3) (4, 2) (6, 1) (0, 0).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,7
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X Q
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X Q X
6 X Q X X X X X X
7 X X X Q X X X X
Congratulation! You have placed 8 queens on the board without conflict. Would you like to start over? (Y)
N
8 queens game finished.
```

No conflicts:

```
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

2,7

You have made 1 choices: (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

5,6

You have made 2 choices: (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

3,5

You have made 3 choices: (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

1,4

You have made 4 choices: (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

7,3

You have made 5 choices: (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

4,2

You have made 6 choices: (4, 2) (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

6,1

You have made 7 choices: (6, 1) (4, 2) (7, 3) (1, 4) (3, 5) (5, 6) (2, 7).
Please enter a coordinate such as 1,2 or use helping commands:
DRAW  - Display available slots.
BACK  - Remove the latest choice.
CLEAR - Start over.

0,0
Row / Column
  0 1 2 3 4 5 6 7
0 Q X X X X X X X
1 X X X X Q X X X
2 X X X X X X X Q
3 X X X X X Q X X
4 X X Q X X X X X
5 X X X X X X Q X
6 X Q X X X X X X
7 X X X Q X X X X
Congratulation! You have placed 8 queens on the board without conflict. Would you like to start over? (Y)
No
8 queens game finished.
```
