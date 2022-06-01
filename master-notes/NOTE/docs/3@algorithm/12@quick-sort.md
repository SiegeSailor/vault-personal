---
sidebar_position: 12
---

# Quick Sort

又稱 Partition-Exchange Sort。為 Divide and Conquer 的 In Place Recursion Algorithm，也是一種 Comparison Sort。核心是選定一個 Pivot，比其小的放到前面、大的放後面。會 **直接** 更改輸入的陣列：

- **Worst Case Time Complexity**: $\mathcal{O}(n^2)$
- **Best Case Time Complexity**: $\Omega(n \times \log n)$
- **Average Time Complexity**: $\Theta(n \times \log n)$
- **Space Complexity**: $\Theta(\log n)$

通常被認為比 Merge Sort 和 Heap Sort 還快。與 Merge Sort 相似，但最大的不同是，Merge Sort 是先分再排，而 Quick Sort 是先排再分：

```cpp
#include <iostream>

using namespace std;

void swap(int &first, int &second);
int partition(int listOfInteger[], int indexOfStart, int indexOfEnd);
void quickSort(int listOfInteger[], int indexOfStart, int indexOfEnd);

int main()
{

    int listOfInteger[] = { 9, 3, 4, 2, 1, 8 };
    int sizeOfList = 6;

    quickSort(listOfInteger, 0, sizeOfList - 1);

    for (int i = 0; i < sizeOfList; i++) {
        cout << listOfInteger[i] << " ";
    }

    return 0;
}

void swap(int &first, int &second)
{
    int local = first;
    first = second;
    second = local;
};

int partition(int listOfInteger[], int indexOfStart, int indexOfEnd)
{
    /** Always use start as pivot. End is applicable. */
    int pivot = listOfInteger[indexOfStart];

    int count = 0;
    for (int i = indexOfStart + 1; i <= indexOfEnd; i++) {
        if (listOfInteger[i] <= pivot)
            count++;
    }

    /** Giving pivot element its correct position */
    int indexOfPivot = indexOfStart + count;
    swap(listOfInteger[indexOfPivot], listOfInteger[indexOfStart]);

    /** Sorting left and right parts of the pivot element */
    int i = indexOfStart, e = indexOfEnd;

    while (i < indexOfPivot && e > indexOfPivot) {

        /** We want to put value smaller than pivot on the left, so skip it */
        while (listOfInteger[i] <= pivot) {
            i++;
        }

        /** We want to put value higher than pivot on the right, so skip it */
        while (listOfInteger[e] > pivot) {
            e--;
        }

        /**
         * Swap the position between value higher and lower than pivot.
         * Use If-Else statement to ensure that i and e are available after the two above While Loop.
         */
        if (i < indexOfPivot && e > indexOfPivot) {
            swap(listOfInteger[i++], listOfInteger[e--]);
        }
    }

    return indexOfPivot;
}

void quickSort(int listOfInteger[], int indexOfStart, int indexOfEnd)
{

    /** Base case and Stopping case */
    if (indexOfStart >= indexOfEnd)
        return;

    /**
     * Partitioning the list with lower-pivot-higher format.
     * Number of lower value and number of higher value are not necessary the same.
     * (indexOfPivot is not necessary indexMiddle)
     */
    int indexOfPivot = partition(listOfInteger, indexOfStart, indexOfEnd);

    /** Sorting the left(lower value) part */
    quickSort(listOfInteger, indexOfStart, indexOfPivot - 1);

    /** Sorting the right(higher value) part */
    quickSort(listOfInteger, indexOfPivot + 1, indexOfEnd);
}
```
