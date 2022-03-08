#include <iostream>
using namespace std;

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue);

int main()
{
    const int size = 10;
    int listOfNumber[size] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int value, indexOfValue;
    bool isFound;
    cout << "Enter a value to search:" << endl;
    cin >> value;

    search(listOfNumber, 0, size - 1, value, isFound, indexOfValue);
    if (isFound)
        cout << value << " is at position " << indexOfValue << " of the list." << endl;
    else
        cout << value << " is not in the list." << endl;

    return 0;
}

void search(const int listOfNumber[], int indexOfStart,
            int indexOfEnd, int value,
            bool &isFound, int &indexOfValue)
{
    isFound = false;
    while ((indexOfStart <= indexOfEnd) && !isFound)
    {
        int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
        if (value == listOfNumber[indexOfMiddle])
        {
            isFound = true;
            indexOfValue = indexOfMiddle;
        }
        else if (value < listOfNumber[indexOfMiddle])
            indexOfEnd--;
        else if (value > listOfNumber[indexOfMiddle])
            indexOfStart++;
    }
}

// void search(const int listOfNumber[], int indexOfStart,
//             int indexOfEnd, int value,
//             bool &isFound, int &indexOfValue)
// {
//     if (indexOfStart > indexOfEnd)
//     {
//         isFound = false;
//     }
//     else
//     {
//         int indexOfMiddle = (indexOfStart + indexOfEnd) / 2;
//         if (value == listOfNumber[indexOfMiddle])
//         {
//             isFound = true;
//             indexOfValue = indexOfMiddle;
//         }
//         else if (value < listOfNumber[indexOfMiddle])
//             search(listOfNumber, indexOfStart,
//                    indexOfMiddle - 1, value,
//                    isFound, indexOfValue);
//         else if (value > listOfNumber[indexOfMiddle])
//             search(listOfNumber, indexOfMiddle + 1,
//                    indexOfEnd, value,
//                    isFound, indexOfValue);
//     }
// }