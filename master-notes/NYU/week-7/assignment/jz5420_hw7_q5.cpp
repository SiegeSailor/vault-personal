#include <iostream>
using namespace std;

typedef int *IntPointer;

int *getPosNums1(int *arr, int arrSize, int &outPosArrSize);
int *getPosNums2(int *arr, int arrSize, int *outPosArrSizePtr);
void getPosNums3(int *arr, int arrSize,
                 int *&outPosArr, int &outPosArrSize);
void getPosNums4(int *arr, int arrSize,
                 int **outPosArrPtr, int *outPosArrSizePtr);
void loopListOfInteger(int array[], int arrSize);
void printResult(int arrSize, int arr[]);

int main()
{
    const int arrSize1 = 6;
    int arr1[arrSize1] = {3, -1, -3, 0, 6, 4};
    printResult(arrSize1, arr1);

    const int arrSize2 = 3;
    int arr2[arrSize2] = {-1, 0, 1};
    printResult(arrSize2, arr2);

    const int arrSize3 = 1;
    int arr3[arrSize3] = {0};
    printResult(arrSize3, arr3);

    return 0;
}

int *getPosNums1(int *arr, int arrSize, int &outPosArrSize)
{
    IntPointer arrayTemporary = new int[arrSize];
    int index = 0;
    for (int i = 0; i < arrSize; i++)
    {
        if (arr[i] > 0)
        {
            arrayTemporary[index] = arr[i];
            index++;
        }
    }
    outPosArrSize = index;
    return arrayTemporary;
}

int *getPosNums2(int *arr, int arrSize, int *outPosArrSizePtr)
{
    IntPointer arrayTemporary = new int[arrSize];
    int index = 0;
    for (int i = 0; i < arrSize; i++)
    {
        if (arr[i] > 0)
        {
            arrayTemporary[index] = arr[i];
            index++;
        }
    }
    *outPosArrSizePtr = index;
    return arrayTemporary;
}

void getPosNums3(int *arr, int arrSize,
                 int *&outPosArr, int &outPosArrSize)
{
    IntPointer arrayTemporary = new int[arrSize];
    int index = 0;
    for (int i = 0; i < arrSize; i++)
    {
        if (arr[i] > 0)
        {
            arrayTemporary[index] = arr[i];
            index++;
        }
    }

    outPosArr = arrayTemporary;
    outPosArrSize = index;
}

void getPosNums4(int *arr, int arrSize,
                 int **outPosArrPtr, int *outPosArrSizePtr)
{
    IntPointer arrayTemporary = new int[arrSize];
    int index = 0;
    for (int i = 0; i < arrSize; i++)
    {
        if (arr[i] > 0)
        {
            arrayTemporary[index] = arr[i];
            index++;
        }
    }

    *outPosArrPtr = arrayTemporary;
    *outPosArrSizePtr = index;
}

void loopListOfInteger(int array[], int arrSize)
{
    for (int i = 0; i < arrSize; i++)
    {
        cout << "i: " << i << " value:" << array[i] << endl;
    }
}

void printResult(int arrSize, int arr[])
{
    cout << "=== Start of printResult ===" << endl;

    IntPointer pointerArr1 = arr;
    int outPosArrSize1;
    pointerArr1 = getPosNums1(pointerArr1, arrSize, outPosArrSize1);
    cout << "getPosNums1:" << endl;
    cout << "outPosArrSize: " << outPosArrSize1 << endl;
    cout << "return value:" << endl;
    loopListOfInteger(pointerArr1, outPosArrSize1);
    delete[] pointerArr1;
    pointerArr1 = nullptr;
    cout << endl;

    IntPointer pointerArr2 = arr;
    int outPosArrSize2;
    pointerArr2 = getPosNums2(pointerArr2, arrSize, &outPosArrSize2);
    cout << "getPosNums2:" << endl;
    cout << "outPosArrSize: " << outPosArrSize2 << endl;
    cout << "return value:" << endl;
    loopListOfInteger(pointerArr2, outPosArrSize2);
    delete[] pointerArr2;
    pointerArr2 = nullptr;
    cout << endl;

    IntPointer pointerArr3 = arr;
    IntPointer outPosArr;
    int outPosArrSize3;
    getPosNums3(pointerArr3, arrSize, outPosArr, outPosArrSize3);
    cout << "getPosNums3:" << endl;
    cout << "outPosArrSize3: " << outPosArrSize3 << endl;
    cout << "return value:" << endl;
    loopListOfInteger(outPosArr, outPosArrSize3);
    delete[] outPosArr;
    outPosArr = nullptr;
    cout << endl;

    IntPointer pointerArr4 = arr;
    IntPointer outPosArrPointer;
    int outPosArrSize4;
    getPosNums4(pointerArr4, arrSize, &outPosArrPointer, &outPosArrSize4);
    cout << "getPosNums4:" << endl;
    cout << "outPosArrSize: " << outPosArrSize4 << endl;
    cout << "return value:" << endl;
    loopListOfInteger(outPosArrPointer, outPosArrSize4);
    delete outPosArrPointer;
    outPosArrPointer = nullptr;
    cout << endl;

    cout << "=== End of printResult ===" << endl;
}