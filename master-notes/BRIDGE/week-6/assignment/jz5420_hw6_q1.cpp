#include <iostream>
using namespace std;

int minInArray(int arr[], int arrSize);

const int SIZE_INTEGER = 20;

int main()
{
    int listOfInteger[SIZE_INTEGER];
    cout << "Please enter " << SIZE_INTEGER << " integers separated by a space: ";
    for (int i = 0; i <= SIZE_INTEGER - 1; i++)
    {
        cin >> listOfInteger[i];
    }

    int min = minInArray(listOfInteger, SIZE_INTEGER);
    cout << "The minimum value is " << min << ", and it is located in the following indices: ";
    for (int i = 0; i <= SIZE_INTEGER - 1; i++)
    {
        if (listOfInteger[i] == min)
            cout << i << " ";
    }
    cout << endl;
    return 0;
}

int minInArray(const int arr[], int arrSize)
{
    int min = arr[0];
    for (int i = 1; i <= arrSize - 1; i++)
    {
        if (min > arr[i])
            min = arr[i];
    }

    return min;
}