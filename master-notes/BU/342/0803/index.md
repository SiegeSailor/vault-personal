# Sorting

#### Question

insertion sort (in-place?) vs selection sort
marge sort why no need to place right side
quick sort how to pick pivot

## Stack vs Heap

For array, java's dereference happens when do array[index] rather than just array = array2.

if we do array = array2, it only affect the current parameter's pointing memory address (reference), which means only affect the current stack.

```java
public static void main(){
    int[] array = {1,2,3};
    reference(array);
}

private void reference(int[] array) {
    // Affect the original one
    array[1] = 50;

    int[] arrayNew = {3,4,5};
    // Only affect in this stack (pointing to the memory address of arrayNew in this stack)
    array = arrayNew;
}
```

## Bubble Sort

Quartic

Best, Worst, Average should all be \theta{n^2}

## Insertion Sort

Quartic

## Selection Sort

Quartic

## Merge Sort

## Quick Sort

## Heap Sort

NOT IN FINAL

Binary tree but only require the parent is greater than its two children

Remove -> move smallest to the top, and swap the top to its biggest children, and so on
Add -> Swap the new one with its parent if the new one is bigger than its parent, and so on
