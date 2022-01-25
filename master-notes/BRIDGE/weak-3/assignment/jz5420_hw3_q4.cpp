/**
 * Define the following constants:
    const int FLOOR_ROUND = 1;
    const int CEILING_ROUND = 2;
    const int ROUND = 3;
 * Write a program that asks the user to enter a Real number,
 * then it asks the user to enter the method by which they want to round that number (floor, ceiling or to the nearest integer). 
 * The program will then print the rounded result.
 *
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter a Real number:
    4.78
    Choose your rounding method:
    1. Floor round
    2. Ceiling round
    3. Round to the nearest whole number
    2
    5
 * Implementation requirements: 
    1. Use a switch statement.
    2. You are not allowed to include and use the math library.
 */
#include <iostream>
using namespace std;

const int FLOOR_ROUND = 1;
const int CEILING_ROUND = 2;
const int ROUND = 3;
int main()
{
    double input_number;
    cout << "Please enter a Real number:" << endl;
    cin >> input_number;

    int input_option;
    cout << "Choose your rounding method:" << endl;
    cout << FLOOR_ROUND << ". Floor round" << endl;
    cout << CEILING_ROUND << ". Ceiling round" << endl;
    cout << ROUND << ". Round to the nearest whole number" << endl;
    cin >> input_option;

    int input_number_integer = (int)input_number;
    switch (input_option)
    {
    case FLOOR_ROUND:
        cout << input_number_integer << endl;
        break;
    case CEILING_ROUND:
        if (input_number > input_number_integer)
        {
            input_number_integer += 1;
        }
        cout << input_number_integer << endl;
        break;
    case ROUND:
        if (input_number >= (input_number_integer + 0.5))
        {
            input_number_integer += 1;
        }
        cout << input_number_integer << endl;
        break;
    default:
        cout << "Invalid input. Please try again." << endl;
        return 1;
    }

    return 0;
}