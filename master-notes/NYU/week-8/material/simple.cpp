#include <iostream>
using namespace std;

void countdown(int input);

int main()
{
    countdown(10);
    return 0;
}

void countdown(int input)
{
    if (input == 1)
    {
        cout << input << endl;
    }
    else
    {
        countdown(input - 1);
        cout << input << endl;
    }
}
