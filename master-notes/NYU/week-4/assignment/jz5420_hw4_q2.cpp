#include <iostream>
#include <string>
using namespace std;

enum RomanDigit
{
    I = 1,
    V = 5,
    X = 10,
    L = 50,
    C = 100,
    D = 500,
    M = 1000
};
const char ROMAN_DIGIT_I = 'I', ROMAN_DIGIT_V = 'V', ROMAN_DIGIT_X = 'X', ROMAN_DIGIT_L = 'L',
           ROMAN_DIGIT_C = 'C', ROMAN_DIGIT_D = 'D', ROMAN_DIGIT_M = 'M';
int main()
{
    int input_decimal, input_decimal_reducer;
    cout << "Enter decimal number: " << endl;
    cin >> input_decimal;
    input_decimal_reducer = input_decimal;

    string output_roman_digit;
    while (input_decimal_reducer > 0)
    {
        if (input_decimal_reducer >= RomanDigit::M)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::M; count++)
            {
                output_roman_digit += ROMAN_DIGIT_M;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::M;
        }
        else if (input_decimal_reducer >= RomanDigit::D)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::D; count++)
            {
                output_roman_digit += ROMAN_DIGIT_D;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::D;
        }
        else if (input_decimal_reducer >= RomanDigit::C)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::C; count++)
            {
                output_roman_digit += ROMAN_DIGIT_C;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::C;
        }
        else if (input_decimal_reducer >= RomanDigit::L)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::L; count++)
            {
                output_roman_digit += ROMAN_DIGIT_L;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::L;
        }
        else if (input_decimal_reducer >= RomanDigit::X)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::X; count++)
            {
                output_roman_digit += ROMAN_DIGIT_X;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::X;
        }
        else if (input_decimal_reducer >= RomanDigit::V)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::V; count++)
            {
                output_roman_digit += ROMAN_DIGIT_V;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::V;
        }
        else if (input_decimal_reducer >= RomanDigit::I)
        {
            for (int count = 1; count <= input_decimal_reducer / RomanDigit::I; count++)
            {
                output_roman_digit += ROMAN_DIGIT_I;
            }
            input_decimal_reducer = input_decimal_reducer % RomanDigit::I;
        }
        else
        {
            cout << "It should not be possible to enter this else condition." << endl;
            break;
        }
    }

    cout << input_decimal << " is " << output_roman_digit << endl;
    return 0;
}