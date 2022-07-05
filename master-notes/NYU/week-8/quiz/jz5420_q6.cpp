#include <iostream>
using namespace std;

int findOccurrences(int *arr, int n, int x);

int main()
{
    int caseOne[] = {-1, -2, -2, 7, -5, -2, 4, 8, 1, -2};
    cout << "Case one (4): " << findOccurrences(caseOne, 10, -2) << endl;
    int caseTwo[] = {1, 3, 4, 6, 1, 2, 3, 4, 3, -2, 3, 3, 6};
    cout << "Case two (5): " << findOccurrences(caseTwo, 13, 3) << endl;
    int caseThree[] = {1, 3, 1, 2, 3, 4, -2, 6};
    cout << "Case three (0): " << findOccurrences(caseThree, 8, 5) << endl;
    return 0;
}

int findOccurrences(int *arr, int n, int x)
{
    if (n == 1)
    {
        if (arr[0] == x)
            return 1;
        else
            return 0;
    }
    else
    {
        int result = 0;
        if (arr[n - 1] == x)
            result = 1;
        return result + findOccurrences(arr, n - 1, x);
    }
}