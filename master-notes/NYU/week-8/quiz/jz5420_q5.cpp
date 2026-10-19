#include <iostream>
using namespace std;

int findMissingNumber(int S[]);

int main()
{
    int caseOne[] = {1, 2, 0, 7, 5, 6, 4, 8, 9};
    cout << "Case one (missing 3): " << findMissingNumber(caseOne) << endl;
    int caseTwo[] = {0, 6, 1, 3, 2, 4};
    cout << "Case two (missing 5): " << findMissingNumber(caseTwo) << endl;
    int caseThree[] = {1, 0, 2, 3, 5, 4};
    cout << "Case three (missing 6): " << findMissingNumber(caseThree) << endl;
    return 0;
}

// int findMissingNumber(int S[])
// {

// }

int findMissingNumber(int S[])
{
    int currentIndex = 0;
    int accumulator = 0;
    int timesZero = 0;
    while (true)
    {
        int currentValue = S[currentIndex];
        if (currentValue == 0)
            timesZero++;
        if (timesZero > 1)
            break;
        if (currentValue < 0)
            break;
        if (currentValue > 100) // Don't know when to stop since the value is random for non-existing index
            break;
        // cout << currentValue << endl;
        accumulator += currentValue;
        currentIndex++;
    }

    int total = (currentIndex * (currentIndex + 1)) / 2;
    // cout << "Total: " << total << " CurrentIndex: " << currentIndex << " Accumulator: " << accumulator << endl;

    return total - accumulator;
}