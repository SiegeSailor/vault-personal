- Represents an organized memory part of a computer that the algorithm can
  access
- Similar but not Random Access Memory
- A giant array of data to read and write values
- The storage unit is called “Words”
- During computation, we can read “Words” from RAM and process
  them

  The RAM model contains instructions commonly found in real computers:
  arithmetic (such as add, subtract, multiply, divide, remainder, floor, ceiling), data
  movement (load, store, copy), and control (conditional and unconditional branch,
  subroutine call and return). Each such instruction takes a constant amount of time.

# Read and Writing

Algorithm will read data from the array, process it, and put it back
▪ An algorithm can read in constant time a constant number of
words, execute its computations on it, and then write them out
▪ Load words, run computations on words, and write words in
constant time

# Process

Load words in constant time
▪ Run computations on the words in constant time
▪ Write them out or store them back in constant time
»The words are loaded in so-named Registers and then run computations on
them
▪ Working with words in RAM is actually doing “assembly
programming”
▪ The assembly programming is a low-level programming instruction
on how hardware should run computations on the specific
hardware
