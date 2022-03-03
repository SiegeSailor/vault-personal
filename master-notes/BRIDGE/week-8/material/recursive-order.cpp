#include <iostream>
using namespace std;

void printAscending1(int start, int end);
void printAscending2(int start, int end);
void printAscending3(int start, int end);
void printAscendingDsecending(int start, int end);

int main()
{
    int option;
    cin >> option;
    cout << "===" << endl;
    switch (option)
    {
    case 1:
        printAscending1(1, 5);
        break;
    case 2:
        printAscending2(1, 5);
        break;
    case 3:
        printAscending3(1, 5);
        break;
    case 4:
        printAscendingDsecending(1, 5);
        break;
    }

    return 0;
}

void printAscending1(int start, int end)
{
    if (start == end)
    {
        cout << start << endl;
    }
    else
    {
        printAscending1(start, end - 1);
        cout << end << endl;
    }
}

void printAscending2(int start, int end)
{
    if (start == end)
    {
        cout << start << endl;
    }
    else
    {
        cout << start << endl;
        printAscending2(start + 1, end);
    }
}

void printAscending3(int start, int end)
{
    if (start == end)
    {
        cout << start << endl;
    }
    else
    {
        int mid = (start + end) / 2;
        printAscending3(start, mid);
        printAscending3(mid + 1, end);
    }
}

void printAscendingDsecending(int start, int end)
{
    if (start == end)
    {
        cout << start << endl;
    }
    else
    {
        cout << start << endl;
        printAscendingDsecending(start + 1, end);
        cout << start << endl;
    }
}