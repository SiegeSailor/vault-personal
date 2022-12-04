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

Notify the operating system that a process is requesting resources before completing the request, which is hard to achieve. Once the operating system gets notified, it runs the Banker's algorithm to detect if the resource allocation would cause any Deadlock. If it is the case, the allocation would be canceled or inserted back into the queue.

#### Detection

Acknowledge that Deadlocks happen and periodically run the Detection algorithm to identify them. When Deadlocks happen, solve it with one of the following methods:

- **Rollback**: Rollback to the previous unlocked state. Logging is needed
- **Abort All**: Abort all the deadlocked processes
- **Abort One**: Abort one of the processes and inspect if any Deadlock is still happening. Repeat until there is no Deadlock
- **Preemption Distribution**: Distribute resources in a preemption way until there is no Deadlock

All methods could damage or pollute the data except for **Rollback**.

Since there is no efficient solution for OS deadlocks and the existing ones (Prevention, Avoidance, Detection) are not really implemented in modern Operating Systems, I want to discuses some attempts to solve Operating Systems deadlocks in some **restricted** situation with **reasonable** costs.

## Main Contents

## Conclusion

## References
