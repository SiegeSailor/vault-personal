#include <iostream>
using namespace std;

int main()
{
    int input_decimal, input_decimal_reducer, output_binary = 0;
    cout << "Enter decimal number:" << endl;
    cin >> input_decimal;
    input_decimal_reducer = input_decimal;

    while (input_decimal_reducer > 0)
    {
        int power_two_result = 1, power_two_in_ten_times = 1;
        while ((power_two_result * 2) <= input_decimal_reducer)
        {
            power_two_result *= 2;
            power_two_in_ten_times *= 10;
        }

        input_decimal_reducer -= power_two_result;
        output_binary += power_two_in_ten_times;
    }

    cout << "The binary representation of " << input_decimal << " is "
         << output_binary << endl;
    return 0;
}