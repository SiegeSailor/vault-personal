#include <iostream>
using namespace std;

int main()
{
    char userInput;
    cout << "Enter a character: (Enter ! to leave)\n";
    cin >> userInput;

    if (userInput == '!')
        return 0;

    if (userInput >= 'a' && userInput <= 'z')
        cout << userInput << " is a lowercase character.\n";
    else if (userInput >= 'A' && userInput <= 'Z')
        cout << userInput << " is a uppercase character.\n";
    else if (userInput >= '0' && userInput <= '9')
        cout << userInput << " is a digit.\n";
    else
        cout << userInput << " is not an alpha-numeric character.\n";

    main();
    return 0;
}