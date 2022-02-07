#include <iostream>
using namespace std;

void swap(int &first, int &second);

int main()
{
    int first = 1, second = 2;
    cout << "first is " << first << " second is " << second << "." << endl;
    swap(first, second);
    cout << "first is " << first << " second is " << second << "." << endl;
    return 0;
}

void swap(int &first, int &second)
{
    int temporary;
    temporary = first;
    first = second;
    second = temporary;
}