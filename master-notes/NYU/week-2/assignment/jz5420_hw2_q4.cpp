/**
 * Write a program that reads from the user two positive integers,
 * and prints the result of when we add, subtract multiply, divide, div and mod them.
 *
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter two positive integers, separated by a space:
    14 4
    14 + 4 = 18
    14 – 4 = 10
    14 * 4 = 56
    14 / 4 = 3.5
    14 div 4 = 3
    14 mod 4 = 2
 */
#include <iostream>
using namespace std;
int main()
{
    int number_first, number_second;
    cout << "Please enter two positive integers, separated by a space:" << endl;
    cin >> number_first >> number_second;
    cout << number_first << " + " << number_second << " = " << number_first + number_second << endl;
    cout << number_first << " - " << number_second << " = " << number_first - number_second << endl;
    cout << number_first << " * " << number_second << " = " << number_first * number_second << endl;
    cout << number_first << " / " << number_second << " = " << ((double)number_first / (double)number_second) << endl;
    cout << number_first << " div " << number_second << " = " << number_first / number_second << endl;
    cout << number_first << " mod " << number_second << " = " << number_first % number_second << endl;
    return 0;
}