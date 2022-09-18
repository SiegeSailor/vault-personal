Interrupt transfers control to the interrupt service routine generally, **interrupt vector**, which contains the addresses of all the service routines

Interrupt architecture must save the address of the interrupted instruction

A trap or exception is a software-generated interrupt caused either by an error or a user request

An operating system is interrupt driven

# Handling

1. The operating system preserves the state of the CPU by storing the registers and the program counter
2. Determines which type of interrupt has occurred:
3. Separate segments of code determine what action should be taken for each type of interrupt

## Two methods for handling I/O

### After I/O starts, control returns to user program only upon I/O completion

Wait instruction idles the CPU until the next interrupt
Wait loop (contention for memory access)
At most one I/O request is outstanding at a time, no simultaneous I/O processing

### After I/O starts, control returns to user program without waiting for I/O completion

System call – request to the OS to allow user to wait for I/O completion
Device-status table contains entry for each I/O device indicating its type, address, and state
OS indexes into I/O device table to determine device status and to modify table entry to include interrupt
