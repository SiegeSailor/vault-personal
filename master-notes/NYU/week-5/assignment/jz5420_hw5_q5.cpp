#include <iostream>
#include <cmath>
using namespace std;

void analyzeDividors(int num, int &outCountDivs, int &outSumDivs);
int ceiling(double input);
bool isPerfect(int num);

const int START = 2;

int main()
{
    cout << "Please enter a positive integer >= " << START << ": ";
    int input;
    cin >> input;

    cout << "List of perfect numbers between " << START << " and " << input << ":" << endl;
    for (int i = START; i <= input; i++)
    {
        if (isPerfect(i))
            cout << i << " ";
    }
    cout << endl;

    cout << "List of amicable numbers between " << START << " and " << input << ":" << endl;
    int currentPairOfAmicableNumber = 0;
    for (int i = START; i <= input; i++)
    {
        if (i == currentPairOfAmicableNumber)
            continue;
        int currentSum = 0, currentCount = 0;
        analyzeDividors(i, currentCount, currentSum);
        int nextCount = 0, nextSum = 0;
        analyzeDividors(currentSum, nextCount, nextSum);

        if (i == nextSum && i != currentSum)
        {
            cout << "(" << i << ", " << currentSum << ")"
                 << " ";
            currentPairOfAmicableNumber = currentSum;
        }
    }
    cout << endl;

    return 0;
}

void analyzeDividors(int num, int &outCountDivs, int &outSumDivs)
{
    double sqrtNum = sqrt(num);
    int lastNumber = 0;
    for (int i = 1; i <= ceiling(sqrtNum + 1); i++)
    {
        if (num % i == 0 && i != num)
        {
            outCountDivs++;
            outSumDivs += i;
            lastNumber = i;
        }
    }

    for (int i = sqrtNum - 1; i > 1; i--)
    {
        if (num % i == 0)
        {
            int times = num / i;
            if (times != lastNumber)
            {
                outCountDivs++;
                outSumDivs = outSumDivs + (num / i);
            }
        }
    }
}

int ceiling(double input)
{
    int inputInteger = static_cast<int>(input);
    if (input > inputInteger)
    {
        inputInteger += 1;
    }

    return inputInteger;
}

bool isPerfect(int num)
{
    int outCountDivs = 0, outSumDivs = 0;
    analyzeDividors(num, outCountDivs, outSumDivs);

    return num == outSumDivs;
}