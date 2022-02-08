#include <iostream>
#include <cmath>
#include <stdlib.h>
using namespace std;

enum SolutionType
{
    NO_SOLUTION = 0,
    ONE_REAL_SOLUTION = 1,
    TWO_REAL_SOLUTION = 2,
    ALL_REALS = 3,
    NO_REAL_SOLUTION = 4,
};

int quadratic(double a, double b, double c, double &output_x_1, double &output_x_2);
int linear(double b, double c, double &output_x_1);
void wrap_abort();

int main()
{
    double input_a, input_b, input_c, output_x_1, output_x_2;
    cout << "Enter the value of a, b, c for ax^2 + bx + c = 0 equation" << endl;
    cin >> input_a >> input_b >> input_c;
    cout << "Your quadratic equation is " << input_a << "x^2 + " << input_b << "x + " << input_c << " = 0" << endl;
    switch (quadratic(input_a, input_b, input_c, output_x_1, output_x_2))
    {
    case SolutionType::NO_SOLUTION:
        cout << "No solutions." << endl;
        break;
    case SolutionType::ONE_REAL_SOLUTION:
        cout << "Solution: " << output_x_1 << endl;
        break;
    case SolutionType::TWO_REAL_SOLUTION:
        cout << "Solution: " << output_x_1 << " and " << output_x_2 << endl;
        break;
    case SolutionType::ALL_REALS:
        cout << "All real numbers are solutions." << endl;
        break;
    case SolutionType::NO_REAL_SOLUTION:
        cout << "No real solutions." << endl;
        break;
    default:
        wrap_abort();
        break;
    }
    return 0;
}

/**
 * @brief Find the solutions of an quadratic equation.
 *
 * @param a a in ax^2 + bx + c
 * @param b b in ax^2 + bx + c
 * @param c c in ax^2 + bx + c
 * @param output_x_1 The first or the only solution
 * @param output_x_2 The second solution
 * @return SolutionType
 */
int quadratic(double a, double b, double c, double &output_x_1, double &output_x_2)
{
    if (a != 0.0)
    {
        double delta = b * b - 4 * a * c;
        if (delta > 0)
        {
            output_x_1 = (-b + sqrt(delta)) / (2 * a);
            output_x_2 = (-b - sqrt(delta)) / (2 * a);
            return SolutionType::TWO_REAL_SOLUTION;
        }
        else if (delta == 0)
        {
            output_x_1 = -b / (2 * a);
            return SolutionType::ONE_REAL_SOLUTION;
        }
        else if (delta < 0)
        {
            return SolutionType::NO_REAL_SOLUTION;
        }
        else
        {
            wrap_abort();
            return 1;
        }
    }
    else
    {
        return linear(b, c, output_x_1);
    };
};

/**
 * @brief Find the solutions of a linear equation.
 *
 * @param b b in ax^2 + bx + c where a = 0.0
 * @param c c in ax^2 + bx + c where a = 0.0
 * @param output_x_1 The solution when b != 0.0
 * @return SolutionType
 */
int linear(double b, double c, double &output_x_1)
{
    if (b != 0.0)
    {
        output_x_1 = -c / b;
        return SolutionType::ONE_REAL_SOLUTION;
    }
    else if ((b == 0) && (c == 0))
    {
        return SolutionType::ALL_REALS;
    }
    else if ((b == 0) && (c != 0))
    {
        return SolutionType::NO_SOLUTION;
    }
    else
    {
        wrap_abort();
        return 1;
    };
};

void wrap_abort()
{
    cout << "Error occurs." << endl;
    abort();
}