---
tags: ["cpp", "python", "sort"]
---

為 In Place Recursion Algorithm。核心是從 1 開始、在每個元素位置進行比對，直到尾端。比對時，每當發現前一位比當下所指的元素更大、就會將該值移到後一位。會 **直接** 更改輸入的陣列：

- **Worst Case Time Complexity**: $\mathcal{O}(n^2)$
- **Best Case Time Complexity**: $\Omega(n)$
- **Average Time Complexity**: $\Theta(n^2)$
- **Space Complexity**: $\Theta(1)$

```cpp
#include <iostream>

using namespace std;

void insertionSort(int listOfInteger[], int n);

int main()
{
    int listOfInteger[] = {12, 11, 13, 5, 6};
    int sizeOfList = sizeof(listOfInteger) / sizeof(listOfInteger[0]);

    insertionSort(listOfInteger, sizeOfList);
    for (int i = 0; i < sizeOfList; i++)
        cout << listOfInteger[i] << " ";
    cout << endl;

    return 0;
}

void insertionSort(int listOfInteger[], int n)
{
    for (int i = 1; i < n; i++)
    {
        int currentValue = listOfInteger[i];
        int indexPrevious = i - 1;

        while (indexPrevious >= 0 && listOfInteger[indexPrevious] > currentValue)
        {
            listOfInteger[indexPrevious + 1] = listOfInteger[indexPrevious];
            indexPrevious--;
        }
        listOfInteger[indexPrevious + 1] = currentValue;
    }
}
```

```python
def insertion_sort(list):
  for index_current in range(1, len(list)):
    value = list[index_current]
    index_previous = index_current

    while index_previous > 0 and list[index_previous - 1] > value:
      print("We swap {} for {}".format(list[index_previous], list[index_previous-1]))
      list[index_previous] = list[index_previous - 1]
      index_previous -= 1

    list[index_previous] = value
    print("Current State is: ", list)

  return list

list = [5,2,4,6,1,3]
print(insertion_sort(list))
```
