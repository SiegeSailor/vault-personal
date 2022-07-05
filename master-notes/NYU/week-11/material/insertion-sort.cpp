#include <iostream>

using namespace std;

// 跟當下範圍的最後一個比，如果當下範圍的最後一個比較大，就往後挪、給新的放在原本最後一個的位置
// 如果沒有，那就是直接放在最後一個後
// 如果是當下範圍最小的，就會所有當下範圍的都往後挪一個位置
/* Function to sort an array using insertion sort*/
void insertionSort(int arr[], int n)
{
    for (int i = 1; i < n; i++)
    {
        int currentValue = arr[i];
        int indexOfLast = i - 1;

        /* Move elements of arr[0..i-1], that are
        greater than currentValue, to one position ahead
        of their current position */
        while (indexOfLast >= 0 && arr[indexOfLast] > currentValue)
        {
            // indexOfLast 是 i - 1，所以後面一定有至少一個空位
            arr[indexOfLast + 1] = arr[indexOfLast];
            indexOfLast--;
        }
        arr[indexOfLast + 1] = currentValue;
    }
}

// A utility function to print an array of size n
void printArray(int arr[], int n)
{
    int i;
    for (i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

/* Driver code */
int main()
{
    int arr[] = {12, 11, 13, 5, 6};
    int n = sizeof(arr) / sizeof(arr[0]);

    insertionSort(arr, n);
    printArray(arr, n);

    return 0;
}