#include <iostream>
using namespace std;

double eApprox(int n);

int main()
{
    cout.precision(30);
    for (int n = 1; n <= 15; n++)
    {
        cout << "n = " << n << '\t' << eApprox(n) << endl;
    }
    return 0;
};

double eApprox(int n)
{
    double sum = 0.0;
    int currentFactorial = 1;
    for (int i = 0; i <= n; i++)
    {
        currentFactorial *= i;
        if (currentFactorial == 0)
            currentFactorial = 1;
        sum += 1.0 / currentFactorial;
    }

    return sum;
}