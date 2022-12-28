linear \theta{n} = \frac{n-1}{2} / unsorted / good for small arrays
binary \theta{\log n} = \frac{\log n}{2} / sorted / good for sorted big arrays

SORTING
\theta{n^2} bad
\theta{n \log n} good

## Duplicated Data

If we want to build a randomized array that has no duplicated data, the best way is to build a non-randomized array first hen randomize it

```java
import java.util.Random;

int SIZE = 10000;
int[] arrayOfSorted = new int[SIZE];
int[] arrayOfUnsorted = new int[SIZE];

// We can input an integer seed as parameter to get the same randomized result. Default `Random` uses timestamp as seed to generate different result every time.
Random generator = new Random();
for (int index = 0; index < arrayOfSorted.length; index++){
    arrayOfSorted[index] = index == 0 ? generator.nextInt(1000) : array[index - 1] + generator.nextInt(1000) + 1;
    arrayOfUnsorted[index] = arrayOfSorted[index];
}

for (int index = 0; index < arrayOfUnsorted.length; index++){
    int index1 = generator.nextInt(arrayOfUnsorted.length);
    int index2 = generator.nextInt(arrayOfUnsorted.length);

    int temporary = arrayOfUnsorted[index1];
    arrayOfUnsorted[index1] = arrayOfUnsorted[index2];
    arrayOfUnsorted[index2] = temporary;
}
```

## Searching

```java
private int binarySearch(int[] array, int data) {
    int indexOfStart = 0, indexOfEnd = array.length - 1;

    while(indexOfStart <= indexOfEnd) {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        if (array[indexOfMiddle] == data)
            return indexOfMiddle;

        if (array[indexOfMiddle] < data)
            indexOfStart = indexOfMiddle + 1;
        else
            indexOfEnd = indexOfMiddle - 1;
    }

    return -1;
}
```

## Hashing

probing (probe) happens when there is a collision for a hashmap (sections/slots is called bucket)
for example:
hash function = input % 10 (open address hashing)
368 will have the same key with 362
so collision happen, we will look for the next slot, then /theta{1} will be /theta{n}

cluster is a bunch of buckets

### Quartic Probing

Prime number for table `SIZE` works best for quartic probing.

```java
private int probeCount = 0;

private int probe(int location) {
    return (location + (probeCount * probeCount)) % SIZE;
}
```

### Chained Hashing

Embraces linear. While making hashing table is a linked list, all the bucket represents a stack or queue.

```java

```
