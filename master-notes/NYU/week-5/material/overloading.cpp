#include <iostream>

float average(float a, float b);
float average(float a, float b, float c);

int main()
{
    using namespace std;

    int a = 1, b = 2, c = 3;
    cout << average(a, b) << endl;
    cout << average(a, b, c) << endl;
}

float average(float a, float b)
{
    return (a + b) / 2;
}

float average(float a, float b, float c)
{
    return (a + b + c) / 3;
}