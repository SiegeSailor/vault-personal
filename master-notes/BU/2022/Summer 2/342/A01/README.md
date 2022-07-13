# Programming Assignment

![Assignment 1](https://img.shields.io/badge/Assignment-1-blue)

A programming assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **July 13, 2022**.

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

## Text Parser

You are to write a program that reads in a body of text and inserts the text into a Linked List class that you write from scratch. You should keep an instance count for each word in the text. Each time a duplicate word is added you should be able to update the instance count for that word inside the Node (this will require you to search the Linked List for each word entered). At the end of the text insertion you will show statistics for the run.

### Detail

The purpose of this exercise is to give you familiarity with Text Parsing, and Linked Lists. You will read in a lengthy text file; [Oscar Wilde’s The Picture of Dorian Gray](./source.txt) as text. As you read the text you will map all words to lower case and remove all punctuation characters (Only numbers or letters should remain) removed characters should be eaten, NOT replaced with a space. Once the words are pre-processed you will insert them into a Linked List.

When the Linked List is completely loaded you will perform queries to demonstrate the following things:

- How many times do the following words appear in the text?
  - `portrait`
  - `persian`
  - `dorian`
  - `experimental`
  - `magnetic`
- How many entries are in the Linked List?
- How many words occur more than 20 times?
  - Display them with their respective counts
- Which word occurs most frequently?
- Which word is the longest in the book?

## Algorithmic Design

The core flow is relatively simple. We should read the text file first, then follow the assignment requirements to parse and store into a linked list. After the parsing process done, we print out the 5 queries listed in [Detail](#detail).

Since user interface is not required, we will assume that every file is placed as how [File Structure](#file-structure) represents. No input for filename.

```
Read the text file
Try-Catch if there is any problem with pathname or empty content

list = empty linked list
scanner = scanner with parameter file
while scanner still has content
    remove punctuation character and separate them as different words
    if a character exist in list
        add count inside the existing node
    else
        append a new node
close scanner
print statistics
```

According to the requirements, we should keep tracking of the following in the linked list while parsing the text file:

- Number of nodes
- Longest node

UML made with mermaid class diagram:

[![UML](https://mermaid.ink/img/pako:eNqFVMtu4zAM_BVBpxRt8gFGkMM2C2wBbwJsr74wEm0La1OBJBcbtPn30s84trf1wRCGw9GQlPQuldUoI6kK8H5vIHNQJiT408ahCsaSiP-0SMMRLwEdnAoU7y0qxHZriMEUFO52PfjY8IJ1wnSL1UMbu87UatY3aidrCwQSOfgD_gu9FkdegzOUCRqhdzvEhv6ijo0P873WB65-MPhl8iiJ_QmqyhO6Y1oL3KsVljL04R60SlXut53COYIeCnmzRgs4n5H0qitKQ4BbpSMzHsGpvKdtNhsBzsHlmO6_yai9K1tR-AEeb8TGTYbhFxtazeG4rWkhcuwLG8XqPTh0GLVoPq9g28XizBr1yZTqAQ_IqD93Y2lKm5mcnJim0b6DW23-zRxyYt3NhcQGXhxRV3qMlIV8mmlIOeS2P9cm5_2awl83ajTa9fpjd7uWkXgpzwWWSMEvUHdLFyISz5YCGJpnbPlsNT2KxB7rs4mkLlPWIHSz0sr-38pAWtxAPskSXQlG89vUHIREhpyVEhnxUmMKVRESmdCVqdWZp4A_tWE9GaVQeHySUHHnLqRkFFyFPal74jrW9RMxtJUD)](https://mermaid.live/edit#pako:eNqFVMtu4zAM_BVBpxRt8gFGkMM2C2wBbwJsr74wEm0La1OBJBcbtPn30s84trf1wRCGw9GQlPQuldUoI6kK8H5vIHNQJiT408ahCsaSiP-0SMMRLwEdnAoU7y0qxHZriMEUFO52PfjY8IJ1wnSL1UMbu87UatY3aidrCwQSOfgD_gu9FkdegzOUCRqhdzvEhv6ijo0P873WB65-MPhl8iiJ_QmqyhO6Y1oL3KsVljL04R60SlXut53COYIeCnmzRgs4n5H0qitKQ4BbpSMzHsGpvKdtNhsBzsHlmO6_yai9K1tR-AEeb8TGTYbhFxtazeG4rWkhcuwLG8XqPTh0GLVoPq9g28XizBr1yZTqAQ_IqD93Y2lKm5mcnJim0b6DW23-zRxyYt3NhcQGXhxRV3qMlIV8mmlIOeS2P9cm5_2awl83ajTa9fpjd7uWkXgpzwWWSMEvUHdLFyISz5YCGJpnbPlsNT2KxB7rs4mkLlPWIHSz0sr-38pAWtxAPskSXQlG89vUHIREhpyVEhnxUmMKVRESmdCVqdWZp4A_tWE9GaVQeHySUHHnLqRkFFyFPal74jrW9RMxtJUD)

The linked list will be solely for `String` rather than being generic since the purpose is rather clear.
