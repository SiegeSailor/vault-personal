---
sidebar_position: 23
---

# Binary Search

```cpp
#include <iostream>
using namespace std;

int search(const int listOfInteger[], const int &sizeOfList, const int &value);

int main()
{
    const int sizeOfList = 8;
    /** 搜尋對象必須為排序過 */
    const int listOfInteger[sizeOfList] = {1, 2, 3, 4, 5, 6, 7, 8};
    cout << search(listOfInteger, sizeOfList, 5) << endl;
    return 0;
};

int search(const int listOfInteger[], const int &sizeOfList, const int &value)
{
    int indexOfMin = 0, indexOfMax = sizeOfList - 1, indexOfValue = -1;
    while (indexOfValue == -1 && indexOfMin <= indexOfMax)
    {
        int indexOfMiddle = (indexOfMin + indexOfMax) / 2;
        if (value == listOfInteger[indexOfMiddle])
        {
            indexOfValue = indexOfMiddle;
        }
        else if (value < listOfInteger[indexOfMiddle])
        {
            indexOfMax = indexOfMiddle - 1;
        }
        else if (value > listOfInteger[indexOfMiddle])
        {
            indexOfMin = indexOfMiddle + 1;
        }
    }

    return indexOfValue;
};
```
