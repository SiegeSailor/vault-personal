#include <iostream>

int main()
{
    using namespace std;
    cout << endl;

    long long input, result = 1;
    cout << "Enter the total number: ";
    cin >> input;

    for (int i = 1; i <= input; i++)
    {
        result *= i;
    }

    cout << input << "! is " << result << "." << endl;
    main();
    return 0;
}