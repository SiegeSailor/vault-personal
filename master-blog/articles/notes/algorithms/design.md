- incremental: selection-sort
- divide-and-conquer: merge-sort

# Divide and Conquer

- Divide: the problem into a number of subproblems that are smaller instances of the
  same problem.
- Conquer: the subproblems by solving them recursively. If the subproblem sizes are
  small enough, however, just solve the subproblems in a straightforward manner.
- Combine: the solutions to the subproblems into the solution for the original problem.

## Merge Sort

- Divide: Divide the n-element sequence to be sorted into two subsequences of n=2 elements each.
- Conquer: Sort the two subsequences recursively using merge sort.
- Combine: Merge the two sorted subsequences to produce the sorted answer.
