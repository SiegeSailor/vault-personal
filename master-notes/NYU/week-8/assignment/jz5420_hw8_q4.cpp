#include <iostream>
using namespace std;

int obtainLowestCostJumpIt(int listOfNumber[], int size);

int main()
{
    const int caseOneSize = 6;
    int caseOneArray[caseOneSize] = {0, 3, 80, 6, 57, 10};
    cout << "Lowest cost: " << obtainLowestCostJumpIt(caseOneArray, caseOneSize) << endl;
    return 0;
}

int obtainLowestCostJumpIt(int listOfNumber[], int size)
{
    if (size == 1)
    {
        return listOfNumber[0];
    }
    else if (size == 2)
    {
        return listOfNumber[0] + listOfNumber[1];
    }
    else
    {
        int sum = listOfNumber[0];
        int columnAdjacent = listOfNumber[1];
        int columnJump = listOfNumber[2];

        if (columnAdjacent >= columnJump)
        {
            sum += obtainLowestCostJumpIt(listOfNumber + 2, size - 2);
        }
        else
        {
            sum += obtainLowestCostJumpIt(listOfNumber + 1, size - 1);
        }
        return sum;
    }
}

// int obtainLowestCostJumpIt(int listOfNumber[], int size)
// {
//     int sum = 0;

//     if (size == 1)
//     {
//         return listOfNumber[0];
//     }
//     else if (size == 2)
//     {
//         return listOfNumber[1];
//     }
//     else if (size == 3)
//     {
//         if (listOfNumber[1] > listOfNumber[2])
//         {
//             return listOfNumber[2];
//         }
//         else
//         {
//             return listOfNumber[1];
//         }
//     }
//     else
//     {
//         sum += obtainLowestCostJumpIt(listOfNumber + 3, size - 3);
//     }

//     return sum;
// }