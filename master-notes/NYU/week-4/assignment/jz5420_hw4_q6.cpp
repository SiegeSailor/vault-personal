#include <iostream>
using namespace std;

int main()
{
    int input;
    cout << "Input a positive integer: ";
    cin >> input;

    for (int count = 1; count <= input; count++)
    {
        int number = count, number_even = 0, number_odd = 0;
        while (number > 0)
        {
            int left = number % 10;
            number /= 10;
            if (left % 2 == 0)
                number_even += 1;
            else
                number_odd += 1;
        }
        if (number_even > number_odd)
            cout << count << endl;
    }

    return 0;
}