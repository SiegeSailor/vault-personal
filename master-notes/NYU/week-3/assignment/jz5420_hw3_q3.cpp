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
        cout << "This equation has infinite number of solutions." << endl;
    }
    else if (a == 0 && b == 0)
    {
        cout << "This equation has no soultion." << endl;
    }
    else if (a != 0)
    {
        cout.setf(ios::fixed);
        cout.setf(ios::showpoint);
        cout.precision(1);

        double result_quadratic_equation = (b * b) - (4 * a * c);
        if (result_quadratic_equation > 0)
        {
            double solution_1 = (-b + sqrt(result_quadratic_equation)) / (2 * a);
            double solution_2 = (-b - sqrt(result_quadratic_equation)) / (2 * a);
            cout << "This equation has two real soultions: " << solution_1 << " and " << solution_2 << endl;
        }
        else if (result_quadratic_equation == 0)
        {
            double solution = -b / (2 * a);
            cout << "This equation has one real soultion: " << solution << endl;
        }
        else
        {
            cout << "This equation has no real soultion." << endl;
        }
    }
    else
    {
        cout << "The input combo is not in the question scope. However, since a is 0 the solution should be linear: " << (-c) / b << endl;
    }

    return 0;
}