#include <iostream>
using namespace std;

void swap(int &first, int &second);
void beFive(int &input);

int main()
{
    int first = 1, second = 2;
    cout << "first is " << first << " second is " << second << "." << endl;
    swap(first, second);
    cout << "first is " << first << " second is " << second << "." << endl;

    int input = 3;
    beFive(input);
    cout << "input is " << input << "." << endl;
    return 0;
}

void swap(int &first, int &second)
{
    int temporary;
    temporary = first;
    first = second;
    second = temporary;
}

void beFive(int &input)
{
    int temporary;
    temporary = input;
    temporary = 5;
    input = 5;
}