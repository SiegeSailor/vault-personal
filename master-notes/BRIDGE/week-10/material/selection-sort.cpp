#include <iostream>
using namespace std;

void selectionSort(int listOfInteger[], const int &sizeOfList);
int findIndexOfMin(const int listOfInteger[], const int &indexOfStart, const int &indexOfEnd);
void swap(int &first, int &second);

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

// T(n) = n + (n-1) + (n-2) + ...+ 2 + 1，這是因為在 for loop 中，每次 loop 的數量都遞減 (減去 i)
// 以上為 n*(n-1)/2，也就是 T(n) = O(n^2)
void selectionSort(int listOfInteger[], const int &sizeOfList)
{
    for (int i = 0; i < sizeOfList; i++)
    {
        // T(n) = O(n);
        int indexOfMin = findIndexOfMin(listOfInteger, i, sizeOfList - 1);
        swap(listOfInteger[i], listOfInteger[indexOfMin]);
    }
};