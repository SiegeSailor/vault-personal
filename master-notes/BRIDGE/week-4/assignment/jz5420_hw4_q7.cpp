#include <iostream>
#include <string>
using namespace std;

int main()
{
    int input;
    cout << "Please enter a positive integer:" << endl;
    cin >> input;

    for (int count_outer = 1; count_outer <= input; count_outer++)
    {
        for (int count_inner = 1; count_inner <= input; count_inner++)
        {
            cout << (count_outer * count_inner) << "\t";
        }
        cout << endl;
    }
    return 0;
}