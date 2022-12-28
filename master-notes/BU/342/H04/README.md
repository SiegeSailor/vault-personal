# Homework Assignment

A homework assignment for class **MET CS342** at Boston University, instructed by **Vic Berry**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **August 6, 2022**.

## File Structure

Submitted files:

```
├── README.md
└── Entry.java
```

## Open Address Hashing

Hash tables provide a mechanism by which you can create indexed tables in which the index is a value other than a string. Implement and test an integer key Open Address Hash table class. Implement the following interface.

```java
int get(int value);

void put(int value);

boolean contains(int value);

void delete(int value);

void printHash();
```

You should provide an interactive or command-line test application for the hash table. Make the hash table 31 entries, and make sure at least one collision occurs in your data input. Provide a run that demonstrates all the methods listed above. (100 points)

## Run

Run results.

### 1th

```
Enter a command:
PUT
Enter a value:
5
5 has been put into the table.
Enter a command:
PUT
Enter a value:
10
10 has been put into the table.
Enter a command:
PUT
Enter a value:
31
31 has been put into the table.
Enter a command:
PUT
Enter a value:
62
62 has been put into the table.
Enter a command:
PRINT_HASH
Index 0: 31
Index 1: 62
Index 2: NEVER_USED
Index 3: NEVER_USED
Index 4: NEVER_USED
Index 5: 5
Index 6: NEVER_USED
Index 7: NEVER_USED
Index 8: NEVER_USED
Index 9: NEVER_USED
Index 10: 10
Index 11: NEVER_USED
Index 12: NEVER_USED
Index 13: NEVER_USED
Index 14: NEVER_USED
Index 15: NEVER_USED
Index 16: NEVER_USED
Index 17: NEVER_USED
Index 18: NEVER_USED
Index 19: NEVER_USED
Index 20: NEVER_USED
Index 21: NEVER_USED
Index 22: NEVER_USED
Index 23: NEVER_USED
Index 24: NEVER_USED
Index 25: NEVER_USED
Index 26: NEVER_USED
Index 27: NEVER_USED
Index 28: NEVER_USED
Index 29: NEVER_USED
Index 30: NEVER_USED
Enter a command:
DELETE
Enter a value:
62
Value 62 has been deleted.
Enter a command:
PRINT_HASH
Index 0: 31
Index 1: PREVIOUS_USED
Index 2: NEVER_USED
Index 3: NEVER_USED
Index 4: NEVER_USED
Index 5: 5
Index 6: NEVER_USED
Index 7: NEVER_USED
Index 8: NEVER_USED
Index 9: NEVER_USED
Index 10: 10
Index 11: NEVER_USED
Index 12: NEVER_USED
Index 13: NEVER_USED
Index 14: NEVER_USED
Index 15: NEVER_USED
Index 16: NEVER_USED
Index 17: NEVER_USED
Index 18: NEVER_USED
Index 19: NEVER_USED
Index 20: NEVER_USED
Index 21: NEVER_USED
Index 22: NEVER_USED
Index 23: NEVER_USED
Index 24: NEVER_USED
Index 25: NEVER_USED
Index 26: NEVER_USED
Index 27: NEVER_USED
Index 28: NEVER_USED
Index 29: NEVER_USED
Index 30: NEVER_USED
Enter a command:
CONTAINS
Enter a value:
31
The value is in the table.
Enter a command:
CONTAINS
Enter a value:
62
The value is not in the table.
Enter a command:
GET
Enter a key:
5
5 has a value 5.
Enter a command:
STOP
System exists.
```

### 2th

```
Enter a command:
GET
Enter a key:
1
1 has a value -1.
Enter a command:
PUT
Enter a value:
1
1 has been put into the table.
Enter a command:
DELETE
Enter a value:
1
Value 1 has been deleted.
Enter a command:
CONTAINS
Enter a value:
1
The value is not in the table.
Enter a command:
PRINT_HASH
Index 0: NEVER_USED
Index 1: PREVIOUS_USED
Index 2: NEVER_USED
Index 3: NEVER_USED
Index 4: NEVER_USED
Index 5: NEVER_USED
Index 6: NEVER_USED
Index 7: NEVER_USED
Index 8: NEVER_USED
Index 9: NEVER_USED
Index 10: NEVER_USED
Index 11: NEVER_USED
Index 12: NEVER_USED
Index 13: NEVER_USED
Index 14: NEVER_USED
Index 15: NEVER_USED
Index 16: NEVER_USED
Index 17: NEVER_USED
Index 18: NEVER_USED
Index 19: NEVER_USED
Index 20: NEVER_USED
Index 21: NEVER_USED
Index 22: NEVER_USED
Index 23: NEVER_USED
Index 24: NEVER_USED
Index 25: NEVER_USED
Index 26: NEVER_USED
Index 27: NEVER_USED
Index 28: NEVER_USED
Index 29: NEVER_USED
Index 30: NEVER_USED
Enter a command:
STOP
System exists.
```

### 3th

```
Enter a command:
PUT
Enter a value:
31
31 has been put into the table.
Enter a command:
PUT
Enter a value:
62
62 has been put into the table.
Enter a command:
PUT
Enter a value:
93
93 has been put into the table.
Enter a command:
PRINT_HASH
Index 0: 31
Index 1: 62
Index 2: 93
Index 3: NEVER_USED
Index 4: NEVER_USED
Index 5: NEVER_USED
Index 6: NEVER_USED
Index 7: NEVER_USED
Index 8: NEVER_USED
Index 9: NEVER_USED
Index 10: NEVER_USED
Index 11: NEVER_USED
Index 12: NEVER_USED
Index 13: NEVER_USED
Index 14: NEVER_USED
Index 15: NEVER_USED
Index 16: NEVER_USED
Index 17: NEVER_USED
Index 18: NEVER_USED
Index 19: NEVER_USED
Index 20: NEVER_USED
Index 21: NEVER_USED
Index 22: NEVER_USED
Index 23: NEVER_USED
Index 24: NEVER_USED
Index 25: NEVER_USED
Index 26: NEVER_USED
Index 27: NEVER_USED
Index 28: NEVER_USED
Index 29: NEVER_USED
Index 30: NEVER_USED
Enter a command:
DELETE
Enter a value:
50
The value is not in the table.
Enter a command:
DELETE
Enter a value:
93
Value 93 has been deleted.
Enter a command:
PRINT_HASH
Index 0: 31
Index 1: 62
Index 2: PREVIOUS_USED
Index 3: NEVER_USED
Index 4: NEVER_USED
Index 5: NEVER_USED
Index 6: NEVER_USED
Index 7: NEVER_USED
Index 8: NEVER_USED
Index 9: NEVER_USED
Index 10: NEVER_USED
Index 11: NEVER_USED
Index 12: NEVER_USED
Index 13: NEVER_USED
Index 14: NEVER_USED
Index 15: NEVER_USED
Index 16: NEVER_USED
Index 17: NEVER_USED
Index 18: NEVER_USED
Index 19: NEVER_USED
Index 20: NEVER_USED
Index 21: NEVER_USED
Index 22: NEVER_USED
Index 23: NEVER_USED
Index 24: NEVER_USED
Index 25: NEVER_USED
Index 26: NEVER_USED
Index 27: NEVER_USED
Index 28: NEVER_USED
Index 29: NEVER_USED
Index 30: NEVER_USED
Enter a command:
GET
Enter a key:
1
1 has a value 62.
Enter a command:
DELETE
Enter a value:
1
The value is not in the table.
Enter a command:
STOP
System exists.
```
