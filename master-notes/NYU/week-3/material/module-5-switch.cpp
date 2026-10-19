#include <iostream>
using namespace std;

int main()
{
    double argument1, argument2;
    char operator_symbol;

    cout << "Enter a numeric experssion such as 5*2:\n";
    cin >> argument1 >> operator_symbol >> argument2;

    // Only int, char, or bool.
    switch (operator_symbol)
    {
    case '+':
        cout << (argument1 + argument2);
        break;
    case '-':
        cout << (argument1 - argument2);
        break;
    case '*':
        cout << (argument1 * argument2);
        break;
    case '/':
        if (argument2 != 0)
        {
            cout << (argument1 / argument2);
        }
        else
        {
            cout << "Illegal expression.";
        }
        break;
    default:
        cout << "Illegal expression.";
        break;
    };

    cout << "\n";
    return 0;
}