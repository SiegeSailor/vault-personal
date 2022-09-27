Many computers have a “shift left” instruction, which
in constant time shifts the bits of an integer by k positions to the left. In most
computers, shifting the bits of an integer by one position to the left is equivalent
to multiplication by 2, so that shifting the bits by k positions to the left is equivalent to multiplication by 2k. Therefore, such computers can compute 2k in one
constant-time instruction by shifting the integer 1 by k positions to the left, as long
as k is no more than the number of bits in a computer word.
