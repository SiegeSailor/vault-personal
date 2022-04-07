#include <iostream>

using namespace std;

void mergeSort(int listOfInteger[], int indexOfStart, int indexOfEnd);
void merge(int listOfInteger[], int indexOfStart, int indexOfMiddle, int indexOfEnd);

int main()
{
    const int sizeOfList = 7;
    int listOfInteger[sizeOfList] = {4, 7, 1, 1, 3, 2, 12};
    mergeSort(listOfInteger, 0, sizeOfList - 1);
    for (int i = 0; i < sizeOfList; i++)
    {
        cout << listOfInteger[i] << " ";
    }
    cout << endl;
    return 0;
}

void merge(int listOfInteger[], int indexOfStart, int indexOfMiddle, int indexOfEnd)
{
    int sizeOfList = indexOfEnd - indexOfStart + 1;
    int *listOfMerged = new int[sizeOfList];

    int indexOfLow = indexOfStart;
    int indexOfHigh = indexOfMiddle + 1;
    int indexOfMerged = 0;

    cout << "Range: " << listOfInteger[indexOfStart] << "/" << listOfInteger[indexOfMiddle] << "/" << listOfInteger[indexOfEnd] << endl;

    while (indexOfLow <= indexOfMiddle && indexOfHigh <= indexOfEnd)
    {
        if (listOfInteger[indexOfLow] < listOfInteger[indexOfHigh])
        {
            listOfMerged[indexOfMerged] = listOfInteger[indexOfLow];
            indexOfLow++;
            indexOfMerged++;
        }
        else // 相等時，以 high 為主
        {
            listOfMerged[indexOfMerged] = listOfInteger[indexOfHigh];
            indexOfHigh++;
            indexOfMerged++;
        }
    }

    // 在前半段 5,5,6 而後半段 5,5,5 的情況下，前半段不會跑完，反之亦然，所以需要直接放入
    // 以下只有一個 while 會執行，因為只有當一個半段執行完的情況下，前面的 while 才會停止
    while (indexOfLow <= indexOfMiddle)
    {
        listOfMerged[indexOfMerged] = listOfInteger[indexOfLow];
        indexOfLow++;
        indexOfMerged++;
    }

    while (indexOfHigh <= indexOfEnd)
    {
        listOfMerged[indexOfMerged] = listOfInteger[indexOfHigh];
        indexOfHigh++;
        indexOfMerged++;
    }

    // listOfInteger 由 indexOfStart 開始，因此額外設 j
    for (int i = 0, j = indexOfStart;
         i < sizeOfList;
         i++, j++)
    {
        cout << listOfMerged[i] << " ";
        listOfInteger[j] = listOfMerged[i];
    }
    cout << endl;
}

// T(n) = O(n) * O(log n)，總共需要花費的回合為 O(log n) (因為一直分為 n/2, n/4, ...)，而每回合的合併需要花費的為 O(n) (如第一次分為 2 個 n/2，總共執行就是 n)
void mergeSort(int listOfInteger[], int indexOfStart, int indexOfEnd)
{
    if (indexOfStart == indexOfEnd)
    {
        return;
    }
    // 也可以用 if (indexOfStart < indexOfEnd)
    else
    {
        cout << "Recursion: " << listOfInteger[indexOfStart] << "-" << listOfInteger[indexOfEnd] << endl;
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        mergeSort(listOfInteger, indexOfStart, indexOfMiddle);
        mergeSort(listOfInteger, indexOfMiddle + 1, indexOfEnd);
        merge(listOfInteger, indexOfStart, indexOfMiddle, indexOfEnd);
    }
}