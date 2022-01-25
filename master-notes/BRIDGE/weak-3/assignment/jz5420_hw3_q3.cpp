#include <iostream>
#include <cmath>
using namespace std;

// Solutions for ax^2 + bx + c = 0
int main()
{
    double a, b, c;
    cout << "Please enter value of a: ";
    cin >> a;
    cout << "Please enter value of b: ";
    cin >> b;
    cout << "Please enter value of c: ";
    cin >> c;

    if (a == 0 && b == 0 && c == 0)
    {
        cout << "This equation has infinite solutions." << endl;
    }
    else if (a == 0 && b == 0)
    {
        cout << "This equation has no soultions." << endl;
    }
    else
    {
        double result_quadratic_equation = (b * b) - (4 * a * c);
        if (result_quadratic_equation > 0)
        {
            cout << "This equation has two soultions." << endl;
        }
        else if (result_quadratic_equation == 0)
        {
            cout << "This equation has one soultion." << endl;
        }
        else
        {
            cout << "This equation has no real soultions." << endl;
        }
    }

    return 0;
}