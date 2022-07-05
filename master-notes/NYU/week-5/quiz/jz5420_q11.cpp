#include <iostream>
using namespace std;

const char STAR = '*';
int main()
{
    cout << "Please enter a positive integer:" << endl;
    int input;
    cin >> input;

    for (int i = 1; i <= input * 2 - 1; i++)
    {
        int limit = 1;
        if (i <= input)
        {
            limit = i;
        }
        else
        {
            limit = input - (i - input);
        }
        for (int j = 1; j <= limit; j++)
        {
            cout << STAR;
        }
        cout << endl;
    }

    return 0;
}