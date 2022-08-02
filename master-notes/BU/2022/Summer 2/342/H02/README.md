# Homework Assignment

![Week 2](https://img.shields.io/badge/Week-2-blue)

A homework assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **July 14, 2022**.

## File Structure

Submitted files.

```
├── README.md
├── Entry.java
├── LinkedList.java
└── Node.java
```

## Question 1

Write the pseudo code for a program to build a linked list by adding Nodes to the end of the list. Also include pseudo code for the displaying of the list and deleting the last element in the list by scanning the list to find the last element. (40 Points)

```
<!-- main -->
list = empty linked list with generic type T
arrayOfData = array of T
for data from index 0 to arrayOfData length - 1
    add data to list
```

Since the question mentions `deleting the last element in the list by scanning the list to find the last element`. I assume that the linked list is a singly-linked list.

```
<!-- LinkedList Class -->
method add (input)
if no head node
    head node = input
else
    cache = head node
    while cache still has next node
        cache = next node in cache
    append input after cache
```

```
<!-- LinkedList Class -->
method delete ()
if no head node
    return
else
    cache = head node
    previous = null
    while cache still has next node
        previous = cache;
        cache = next node in cache
    remove the next node from previous
```

## Question 2

Write the program designed in question 1, as a Generic Linked list application, show the code running as an Integer, and String based list. Turn in source code and an output from a run for the program (60 Points)

### Java Code

Please see [Entry.java](./Entry.java) or use `sh compile.sh` to compile and execute.

### Runs

`arrayOfInteger` is `{ 0, 1, 2, 3, 4, 5 }` and `arrayOfString` is `{ "a", "b", "c", "d", "e" }`:

```
=== Integer Linked List ===
Loop through the whole linked list: 0 -> 1 -> 2 -> 3 -> 4 -> 5
The head node: Node@55f96302 (0)
The tail node: Node@74a14482 (5)
Empty: false

Delete the last element.

Loop through the whole linked list: 0 -> 1 -> 2 -> 3 -> 4
The head node: Node@55f96302 (0)
The tail node: Node@330bedb4 (4)
Empty: false

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true


=== String Linked List ===
Loop through the whole linked list: a -> b -> c -> d -> e
The head node: Node@2503dbd3 (a)
The tail node: Node@4b67cf4d (e)
Empty: false

Delete the last element.

Loop through the whole linked list: a -> b -> c -> d
The head node: Node@2503dbd3 (a)
The tail node: Node@7ea987ac (d)
Empty: false

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true
```

`arrayOfInteger` and `arrayOfString` are both empty:

```
=== Integer Linked List ===
Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true

Delete the last element.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true


=== String Linked List ===
Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true

Delete the last element.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true
```

`arrayOfInteger` is `{ 0, 0, 1, 1, 2, 2 }` and `arrayOfString` is `{ "A", "B", "C", "D", "E" }`:

```
=== Integer Linked List ===
Loop through the whole linked list: 0 -> 0 -> 1 -> 1 -> 2 -> 2
The head node: Node@55f96302 (0)
The tail node: Node@74a14482 (2)
Empty: false

Delete the last element.

Loop through the whole linked list: 0 -> 0 -> 1 -> 1 -> 2
The head node: Node@55f96302 (0)
The tail node: Node@330bedb4 (2)
Empty: false

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true


=== String Linked List ===
Loop through the whole linked list: A -> B -> C -> D -> E
The head node: Node@2503dbd3 (A)
The tail node: Node@4b67cf4d (E)
Empty: false

Delete the last element.

Loop through the whole linked list: A -> B -> C -> D
The head node: Node@2503dbd3 (A)
The tail node: Node@7ea987ac (D)
Empty: false

Clear the whole linked list.

Loop through the whole linked list: Empty linked list.
The head node: null
The tail node: null
Empty: true
```
