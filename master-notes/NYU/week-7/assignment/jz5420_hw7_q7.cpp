#include <iostream>
#include <vector>
using namespace std;

int *findMissing(int arr[], int n, int &resArrSize);
void printResult(int size, int array[]);

int main()
{

    const int sizeCaseOne = 6;
    int listOfNumberCaseOne[sizeCaseOne] = {3, 1, 3, 0, 6, 4};
    printResult(sizeCaseOne, listOfNumberCaseOne);

    const int sizeCaseTwo = 5;
    int listOfNumberCaseTwo[sizeCaseTwo] = {1, 2, 3, 4};
    printResult(sizeCaseTwo, listOfNumberCaseTwo);

    const int sizeCaseThree = 8;
    int listOfNumberCaseThree[sizeCaseThree] = {6};
    printResult(sizeCaseThree, listOfNumberCaseThree);
    return 0;
}

void printResult(int size, int array[])
{
    int resultListSize;
    int *listOfResult;
    listOfResult = findMissing(array, size, resultListSize);
    cout << "Test: [ ";
    for (int i = 0; i < size; i++)
    {
        int number = array[i];
        if (number > 0)
            cout << array[i] << " ";
    }
    cout << "]" << endl;
    cout << "resArrSize: " << resultListSize << endl;
    cout << "return array:" << endl;
    for (int i = 0; i < resultListSize; i++)
    {
        cout << listOfResult[i] << " ";
    }
    cout << endl
         << endl;
}

int *findMissing(int arr[], int n, int &resArrSize)
{
    vector<int> listOfCorrspondingNumber(n + 1);
    for (int i = 0; i < n; i++)
    {
        int number = arr[i];
        listOfCorrspondingNumber[number]++;
    }

    vector<int> listOfMissingNumber;
    for (int i = 0; i < (n + 1); i++)
        if (listOfCorrspondingNumber[i] == 0)
            listOfMissingNumber.push_back(i);

    resArrSize = listOfMissingNumber.size();

    int *listOfResult = new int[resArrSize];
    for (int i = 0; i < resArrSize; i++)
    {
        listOfResult[i] = listOfMissingNumber[i];
    }

    return listOfResult;
}