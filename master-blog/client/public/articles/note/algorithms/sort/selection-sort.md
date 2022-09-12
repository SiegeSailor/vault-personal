為 In Place Recursion Algorithm。核心是從頭開始，每次都在剩餘元素中找到最小值、並將位置交換。會 **直接** 更改輸入的陣列：

- **Worst Case Time Complexity**: $\mathcal{O}(n^2)$
- **Best Case Time Complexity**: $\Omega(n^2)$
- **Average Time Complexity**: $\Theta(n^2)$
- **Space Complexity**: $\Theta(1)$

之所以 Time Complexity 為 $\Theta(n^2)$，是因為雖然在每次 `selectionSort` 的 For Loop 中 `findIndexOfMin` 的總數都會遞減、為 $\Theta(n)$、$\Theta(n - 1)$、$\Theta(n - 2)$、...、$\Theta(2)$、$\Theta(1)$，是為 $\Theta(\frac{n \times (n-1)}{2})$。然而在看整體時，只考慮 $n$ 的最高項次方，因此為 $\Theta(n^2)$：

```cpp
#include <iostream>

using namespace std;

void swap(int &first, int &second);
int findIndexOfMin(const int listOfInteger[], const int &indexOfStart, const int &indexOfEnd);
void selectionSort(int listOfInteger[], const int &sizeOfList);

int main()
{
    const int sizeOfList = 8;
    int listOfInteger[sizeOfList] = {4, 12, 7, 1, 3, 2, 1, 10};
    selectionSort(listOfInteger, sizeOfList);
    for (int i = 0; i < sizeOfList; i++)
    {
        cout << listOfInteger[i] << " ";
    }
    cout << endl;
    return 0;
}

void swap(int &first, int &second)
{
    int local = first;
    first = second;
    second = local;
};

int findIndexOfMin(int listOfInteger[], const int &indexOfStart, const int &indexOfEnd)
{
    int indexOfMin = indexOfStart, min = listOfInteger[indexOfStart];
    for (int i = indexOfStart + 1; i <= indexOfEnd; i++)
    {
        if (listOfInteger[i] < min)
        {
            min = listOfInteger[i];
            indexOfMin = i;
        };
    };

    return indexOfMin;
};

void selectionSort(int listOfInteger[], const int &sizeOfList)
{
    for (int i = 0; i < sizeOfList; i++)
    {
        int indexOfMin = findIndexOfMin(listOfInteger, i, sizeOfList - 1);
        swap(listOfInteger[i], listOfInteger[indexOfMin]);
    }
};
```

```java
class Solution {
    public static void main (String[] arguments) {
        int[] array = new int[] {5, 4, 1, 2, 7};
        for (int i = 0; i < array.length - 1; i++) {
            int indexNext = i + 1;
            while(indexNext < array.length) {
                if (array[indexNext] < array[i]) {
                    int cache = array[indexNext];
                    array[indexNext] = array[i];
                    array[i] = cache;
                }
                indexNext++;
            }
        }

        for (int value : array)
            System.out.println(value + " ");
    }
}
```
