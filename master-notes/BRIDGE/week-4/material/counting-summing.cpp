#include <iostream>
using namespace std;

int main()
{
    int input_number = 0, current_number = 0, sum = 0, count = 0;
    cout << "Enter any length of number: ";
    cin >> input_number;
    current_number = input_number;
    while (current_number > 0)
    {
        sum += current_number % 10;
        current_number /= 10;
        count += 1;
    }
    cout << input_number << " has " << count << " digits." << endl;
    cout << "The sum is " << sum << "." << endl;
    return 0;
}