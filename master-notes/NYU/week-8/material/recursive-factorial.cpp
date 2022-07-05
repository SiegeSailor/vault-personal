#include <iostream>
using namespace std;

int produceFactorial(int input);

int main()
{
    int input;
    cin >> input;
    cout << input << "!: " << produceFactorial(input) << endl;
    return 0;
}

int produceFactorial(int input)
{
    if (input == 1)
        return 1;
    else
    {
        return input *= produceFactorial(input - 1);
    }
}