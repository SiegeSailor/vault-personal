#include <iostream>
using namespace std;

bool isAllEven(int listOfInput[], int size);

int main()
{
    const int size = 6;
    int listOfInput[size] = {2, 4, 6, 8, 10, 11};
    if (isAllEven(listOfInput, size))
    {
        cout << "Even";
    }
    else
    {
        cout << "Not all even";
    }
    cout << endl;
    return 0;
}

bool isAllEven(int listOfInput[], int size)
{
    if (size == 1)
    {
        cout << "size == 1: " << listOfInput[0] << endl;
        return listOfInput[0] % 2 == 0;
    }
    else
    {
        if (isAllEven(listOfInput, size - 1))
        {
            cout << "isAllEven: " << listOfInput[size - 1] << endl;
            return listOfInput[size - 1] % 2 == 0;
        }
        else
        {
            return false;
        }
    }
}