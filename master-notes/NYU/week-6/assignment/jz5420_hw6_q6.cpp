#include <iostream>
#include <string>
using namespace std;

bool isContainNonDigit(string input);

const int ASCII_A_UPPERCASE = 65, ASCII_Z_UPPERCASE = 90,
          ASCII_A_LOWERCASE = 97, ASCII_Z_LOWERCASE = 122;
const char SYMBOL = 'x';
int main()
{
    string input, output;
    cout << "Please enter a line of text:" << endl;
    getline(cin, input);
    output = input;

    int indexOfStart = 0, indexOfEnd = 0, indexOfCurrent = 0;
    while (input.length() > 0)
    {
        indexOfEnd = input.find(" ");
        if (indexOfEnd == string::npos)
            indexOfEnd = input.length() - 1;

        string word = input.substr(indexOfStart, indexOfEnd);

        if (!isContainNonDigit(word))
        {
            for (int i = 0; i < word.length(); i++)
            {
                output[i + indexOfCurrent] = SYMBOL;
            }
        }

        indexOfCurrent += (indexOfEnd + 1);
        input = input.substr(indexOfEnd + 1, input.length() - 1);
    }
    cout << output << endl;
}

bool isContainNonDigit(string input)
{
    for (int i = 0; i < input.length(); i++)
    {
        char character = input[i];
        int valueASCII = static_cast<int>(character);
        bool isLetterUppercase = valueASCII >= ASCII_A_UPPERCASE && valueASCII <= ASCII_Z_UPPERCASE;
        bool isLetterLowercase = valueASCII >= ASCII_A_LOWERCASE && valueASCII <= ASCII_Z_LOWERCASE;
        if (isLetterUppercase || isLetterLowercase)
        {
            return true;
        }
    }
    return false;
}

// My userID is john17 and my 4 digit pin is 1234 which is secret