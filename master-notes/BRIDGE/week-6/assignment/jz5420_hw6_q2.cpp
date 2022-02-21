#include <iostream>
#include <string>
using namespace std;

bool isPalindrome(string str);

int main()
{
    string inputWord;
    cout << "Please enter a word: ";
    cin >> inputWord;
    if (isPalindrome(inputWord))
        cout << inputWord << " is a palindrome" << endl;
    else
        cout << inputWord << " is not a palindrome" << endl;
    return 0;
}

bool isPalindrome(string str)
{
    int lengthOfStr = str.length(), halfLengthOfStr = lengthOfStr / 2;

    int indexOfStartOfSecondPart;
    bool isEven = lengthOfStr % 2 == 0;
    if (isEven)
        indexOfStartOfSecondPart = halfLengthOfStr;
    else
        indexOfStartOfSecondPart = halfLengthOfStr + 1;

    string secondPart = "";
    for (int i = lengthOfStr - 1; i >= indexOfStartOfSecondPart; i--)
    {
        secondPart += str[i];
    }

    return str.substr(0, halfLengthOfStr) == secondPart;
}