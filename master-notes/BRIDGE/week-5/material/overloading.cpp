#include <iostream>

float average(int a, int b);
float average(int a, int b, int c);

int main()
{
    using namespace std;

    int a = 1, b = 2, c = 3;
    cout << average(a, b) << endl;
    cout << average(a, b, c) << endl;
}

float average(int a, int b)
{
    return (static_cast<float>(a) + static_cast<float>(b)) / 2;
}

float average(int a, int b, int c)
{
    return (static_cast<float>(a) + static_cast<float>(b) + static_cast<float>(c)) / 3;
}