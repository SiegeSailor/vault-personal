# Programming Assignment

![Assignment 3](https://img.shields.io/badge/Assignment-3-orange)

A programming assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](mailto:siege@bu.edu) submitted on **July 29, 2022**.

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

## Queues

You are to write a program that reads in a body of text and inserts the text into a Queue class that you write from scratch. Each word must be inserted in the Queue irrespective of duplicates (add all words to the queue). At the end of the text insertion you will show statistics for the run.

### Detail

The purpose of this exercise is to give you familiarity with Queues. You should have already built a Text
Parser from earlier in the course. As you read the text you will map all words to lower case and remove all punctuation characters (Only numbers or letters should remain) removed characters should be eaten, NOT replaced with a space. Once the words are pre-processed you will add them to your Queue. You can use the TextParser.jar I gave you.

When the Queue is completely loaded, make a copy of it. You will need to implement a copy method in your Queue. Once the Queue is copied you will perform queries to answer the following questions:

- How many times do you have to remove the head from an unmodified queue until you reach the following words?
  - `superhuman`
  - `chiromancy`
  - `unsatisfactory`
  - `percutaneous`
  - `discernible`
- How many entries are in the Queue?
- In the first 1000 entries, how many words occur more than 20 times? (this requires some thought)
- Remove all entries from the queue and determine which word occurs most frequently?
- Remove all entries from the queue and determine which word is the longest in the book?

No User Interface is required for this project.

## Algorithmic Design

### Component

## Runs
