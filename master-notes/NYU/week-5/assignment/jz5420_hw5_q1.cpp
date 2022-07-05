#include <iostream>

int fib(int n);

int main()
{
    using namespace std;

    cout << "Please enter a positive integer: ";
    int num;
    cin >> num;
    cout << fib(num) << endl;
    return 0;
}

int fib(int n)
{
    if (n <= 2)
    {
        return 1;
    }

    int cache_1 = 1, cache_2 = 1, cache_total = 2;
    for (int i = 3; i <= n; i++)
    {
        cache_total = cache_1 + cache_2;
        cache_2 = cache_1;
        cache_1 = cache_total;
    }

    return cache_total;
}