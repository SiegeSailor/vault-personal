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
            /**
             * 注意這邊是將 indexPrevious 的值放入 indexPrevious + 1，
             * 也就是第一次的話是將 i - 1 放入 i 中，因為 indexPrevious 由 i - 1 開始，
             * 而第二次則是 i - 2 放入 i - 1 中。
             * 是往前，因此 While Loop 以 indexPrevious 小於 0 為結束點之一。
             */
            listOfInteger[indexPrevious + 1] = listOfInteger[indexPrevious];
            indexPrevious--;
        }
        /**
         * 如果沒有發生 While Loop，則此行只是將 i 的值重新再指給 i，
         * 但若有，則代表將 i 的值指給最後一個 indexPrevious，
         * 這是因為在 While Loop 中是將比 i 的值還大的值一個接一個移到後面，
         * i 為這一 Loop 中的最小值，因此將其賦予到最前面有參與替換的 index 上。
         * 若無做此動作，則 i 位置的值會變為前一位、並遺失，
         * 而最前面的 indexPrevious 的值會與次前面的一樣、有重複。
         */
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

```java
class Main {
  public void insertionSort(int[] array, int index) {
    if (index >= array.length - 1)
      return;

    int indexPrevious = index;
    int value = array[index];

    while(indexPrevious > 0 && array[indexPrevious - 1] > value) {
      array[indexPrevious] = array[indexPrevious - 1];
      indexPrevious--;
    }

    array[indexPrevious] = value;

    this.insertionSort(array, index + 1);
  }

  public static void main (String[] argument){
    int[] array = new int[]{3, 7, 1, 10};
    new Main().insertionSort(array, 1);
    for (int value : array)
      System.out.println(value + " ");
  }
}
```
