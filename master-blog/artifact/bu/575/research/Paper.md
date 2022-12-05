# Operating System Deadlock

Research project paper for class **MET CS 575** at Boston University, instructed by **Dr. Nourai**. Student [Zhang, Jin Yu](siege@bu.edu) submitted on **December 5, 2022**.

## Abstract

## Introduction

When we execute multiple instruction sequences simultaneously, which means concurrency, an Operating System Deadlock happens if we have two or more processes are waiting for each other to release the recourse they hold, or more than two processes are waiting for recourses in a circular chain.

### Four Conditions

Also, four conditions have to be satisfied to invoke a Deadlock:

- **Mutual Exclusion**: Only one process is allowed in a specific critical section at the same time
- **Hold and Wait**: Processes can hold recourses while waiting for resources
- **No Preemption**: No operating system or superior monitor program to control and distribute resources
- **Circular Wait**: A series of processes hold a recourse each other needs, such as C waits for B, B waits for A, and A waits for C

Currently, there are no practical solutions for Operating Systems Deadlocks. However, theoretically, we have three possible solutions:

- **Prevention**: Prevent one of the four conditions (Mutual Exclusion, Hold and Wait, No Preemption, Circular Wait) from happening
- **Avoidance**: Inspect the processes' status whenever we allocate resources to them. Cancel or insert back to the queue if the allocation would cause a Deadlock
- **Detection**: Detect the appearance of Deadlocks and solve them in advance

#### Prevention

Prevent one of the four conditions from happening:

- **Mutual Exclusion**: Usually not possible to prevent since it is a protection mechanism for concurrency
- **Hold and Wait**: A process can not request a resource while it is holding a lock
- **No Preemption**: A process will release the resources it holds once its request for locks denied
- **Circular Wait**: Number the processes, and processes with a higher number can not request resources from processes with a lower number

#### Avoidance

Notify the operating system that a process is requesting resources before completing the request, which is hard to achieve. Once the operating system gets notified, it runs **the Banker's algorithm** to detect if the resource allocation would cause any Deadlock. If it is the case, the allocation would be canceled or inserted back into the queue.

#### Detection

Acknowledge that Deadlocks happen and periodically run the Detection algorithm to identify them. When Deadlocks happen, solve it with one of the following methods:

- **Rollback**: Rollback to the previous unlocked state. Logging is needed
- **Abort All**: Abort all the deadlocked processes
- **Abort One**: Abort one of the processes and inspect if any Deadlock is still happening. Repeat until there is no Deadlock
- **Preemption Distribution**: Distribute resources in a preemption way until there is no Deadlock

All methods could damage or pollute the data except for **Rollback**.

Since there is no efficient solution for OS deadlocks and the existing ones (Prevention, Avoidance, Detection) are not really implemented in modern Operating Systems, I want to discuss an attempt to solve Operating Systems deadlocks in a **restricted** situation with **reasonable** costs.

## Main Contents

Deadlock is a situation that one or more processes are unable to meet the requirement for obtaining the recourses they need, and it will not be automatically fixed over time unless some superior actions are taken by the operator, some monitor programs, or the operating system.

Here is a possible scenario that will trigger Deadlocks:

```mermaid
flowchart LR
A[Process 1]
B[Process 2]
C[Process 3]
subgraph D [Resource 1]
1[*]
end
subgraph E [Resource 2]
2[*]
3[*]
end
subgraph F [Resource 3]
4[*]
end

A -->|request| D
1 -->|held| B
B -->|request| 4
4 -->|held| C
C -->|request| E
2 -->|held| B
3 -->|held| A
```

### Situation

As what we have mentioned in [Introduction](#introduction), what we mainly discuss here is to offer a solution that really solves Deadlocks rather ignore them in a **restricted** situation with **reasonable** costs. To begin, below is a simple scenario without Deadlocks:

```mermaid
flowchart LR
A[Process 1] -->|request| B[/Resource 1/]
B -->|held| C[Process 2]
C -->|request| D[/Resource 2/]
```

As we can see, Process 2 is holding Resource 1 and requesting Resource 2. Meanwhile, Process 1 is requesting Resource 1. There is no preemption here. Process 1 will eventually obtain the resource it needs as long as Process 2 obtain the resource it needs from Resource 2 and then release Resource 1. However, this scenario will not be Deadlock-free scenario once Process 1 holds Resource 2 while requesting resources:

```mermaid
flowchart LR
A[Process 1] -->|request| B[/Resource 1/]
B -->|held| C[Process 2]
C -->|request| D[/Resource 2/]
D -->|held| A
```

Let's verify if it is Deadlocked with the four conditions:

- **Mutual Exclusion**: Yes. Only one process is using one resource in the same time
- **Hold and Wait**: Yes. Process 1 and Process 2 are holding and requesting resources
- **No Preemption**: Yes. There is no preemption here
- **Circular Wait**: Yes. Process 1 and Process 2 hold recourses each other needs

Although there are only two processes and two resources, it is Deadlocked.

### Solution

## Conclusion

## References

The citation style includes follows [IEEE Editorial Style Manual](http://journals.ieeeauthorcenter.ieee.org/wp-content/uploads/sites/7/IEEE-Editorial-Style-Manual-for-Authors.pdf).

1. RICHARD C. HOLT, "Some Deadlock Properties of Computer Systems", University of Toronto, Toronto, Ontario, Canada, 1972
