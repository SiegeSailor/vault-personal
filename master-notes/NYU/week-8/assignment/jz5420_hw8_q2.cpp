#include <iostream>
using namespace std;

int sumOfSquares(int arr[], int arrSize);
bool isSorted(int arr[], int arrSize);

int main()
{
    const int arrSizeSumOfSquare = 4;
    int arrSumOfSquare[arrSizeSumOfSquare] = {2, -1, 3, 10};
    cout << "sumOfSquares:" << sumOfSquares(arrSumOfSquare, arrSizeSumOfSquare) << endl;

    const int arrSizeSorted = 3;
    int arrSorted[arrSizeSorted] = {0, 2, 3};
    bool result = isSorted(arrSorted, arrSizeSorted);
    if (result)
    {
        cout << "is sorted." << endl;
    }
    else
    {
        cout << "is not sorted." << endl;
    }
    return 0;
}

int sumOfSquares(int arr[], int arrSize)
{
    if (arrSize < 1)
    {
        return 0;
    }

    int result = arr[arrSize - 1] * arr[arrSize - 1];
    if (arrSize == 1)
    {
        return result;
    }
    else
    {
        return result += sumOfSquares(arr, arrSize - 1);
    }
}

bool isSorted(int arr[], int arrSize)
{
    if (arrSize == 1)
    {
        return arr[0] < arr[1];
    }
    else
    {
        if (isSorted(arr, arrSize - 1))
        {
            return arr[arrSize - 1] > arr[arrSize - 2];
        }
        else
        {
            return false;
        }
    }
}