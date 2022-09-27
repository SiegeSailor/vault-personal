經典搜尋法。每次取最中間的值，若當下的值小於則往左、大於則往右，以此反覆：

- **Worst Case Time Complexity**: $\mathcal{O}(\log n)$
- **Best Case Time Complexity**: $\Omega(1)$
- **Average Time Complexity**: $\Theta(\log n)$
- **Space Complexity**: $\Theta(1)$

:::info 提醒
Space Complexity 在此 Iteration 中為 $\Theta(1)$，但在 Recursion 中為 $\Theta(\log n)$。Recursive Binary Search 可參考 [/Problem Solving/Recursion#Iteration](/docs/4@problem-solving/8@recursion#iteration)。
:::

```cpp
#include <iostream>

using namespace std;

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue);

int main()
{
    const int size = 10;
    /** 搜尋對象必須為 Ordered Ascending */
    int listOfNumber[size] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int value, indexOfValue;
    bool isFound;
    cout << "Enter a value to search:" << endl;
    cin >> value;

    search(listOfNumber, 0, size - 1, value, isFound, indexOfValue);
    if (isFound)
        cout << value << " is at position " << indexOfValue << " of the list." << endl;
    else
        cout << value << " is not in the list." << endl;

    return 0;
}

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue)
{
    // highlight-next-line
    /** Stopping Case */
    if (indexOfStart > indexOfEnd)
    {
        isFound = false;
    }
    else
    {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        // highlight-next-line
        /** Base Case */
        if (value == listOfNumber[indexOfMiddle])
        {
            isFound = true;
            indexOfValue = indexOfMiddle;
        }
        // highlight-next-line
        /** 根據兩種不同情況，以不同參數再次呼叫自己 */
        else if (value < listOfNumber[indexOfMiddle])
            search(listOfNumber, indexOfStart,
                   indexOfMiddle - 1, value,
                   isFound, indexOfValue);
        else if (value > listOfNumber[indexOfMiddle])
            search(listOfNumber, indexOfMiddle + 1,
                   indexOfEnd, value,
                   isFound, indexOfValue);
    }
}
```

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
