#include <iostream>
#include <cmath>
using namespace std;

int ceiling(double input);
void printDivisors(int num);

int main()
{
    cout << "Please enter a positive integer >= 2: ";
    int input;
    cin >> input;
    printDivisors(input);
    return 0;
}

void printDivisors(int num)
{
    double sqrtNum = sqrt(num);
    int lastNumber = 0;
    for (int i = 1; i <= ceiling(sqrtNum + 1); i++)
    {
        if (num % i == 0)
        {
            cout << i << " ";
            lastNumber = i;
        }
    }

    for (int i = sqrtNum; i >= 1; i--)
    {
        if (num % i == 0)
        {
            int times = num / i;
            if (times != lastNumber)
                cout << (num / i) << " ";
        }
    }
    cout << endl;
};

int ceiling(double input)
{
    int inputInteger = static_cast<int>(input);
    if (input > inputInteger)
    {
        inputInteger += 1;
    }

    return inputInteger;
}