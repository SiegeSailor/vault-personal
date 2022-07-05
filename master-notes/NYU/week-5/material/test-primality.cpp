#include <iostream>
#include <cmath>
using namespace std;

bool isPrime(int input);

int main()
{
    int input;
    cout << "Enter a positive integer: ";
    cin >> input;
    if (input < 1)
        cout << "Please enter a positive integer." << endl;
    else if (isPrime(input))
        cout << input << " is a prime number." << endl;
    else
        cout << input << " is not a prime number." << endl;
    return 0;
};

bool isPrime(int input)
{
    if (input == 1)
        return true;

    for (int i = 2; i <= sqrt(input); i++)
    {
        cout << i << endl;
        if (input % i == 0)
            return false;
    }

    return true;
}