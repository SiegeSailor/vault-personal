#include <iostream>
#include <string>
#include <vector>
using namespace std;

int parseASCIIToLetterOrder(char input);
void countLetter(vector<int> &listOfInput, string inputString);

const int ASCII_A_UPPERCASE = 65, ASCII_Z_UPPERCASE = 90,
          ASCII_A_LOWERCASE = 97, ASCII_Z_LOWERCASE = 122;
const int NUMBER_OF_ALPHABAT = 26;
int main()
{
    string stringFirst, stringSecond;
    cout << "Please enter the first string:" << endl;
    getline(cin, stringFirst);
    cout << "Now enter the second string:" << endl;
    getline(cin, stringSecond);

    vector<int> listOfCharacterCountFirst(NUMBER_OF_ALPHABAT),
        listOfCharacterCountSecond(NUMBER_OF_ALPHABAT);
    countLetter(listOfCharacterCountFirst, stringFirst);
    countLetter(listOfCharacterCountSecond, stringSecond);

    bool isAnagrams = true;
    for (int i = 0; i < listOfCharacterCountFirst.size(); i++)
    {
        if (listOfCharacterCountFirst[i] != listOfCharacterCountSecond[i])
        {
            isAnagrams = false;
            break;
        }
    }
    if (isAnagrams)
        cout << "The two strings are anagrams." << endl;
    else
        cout << "The two strings are not anagrams." << endl;
    return 0;
}

int parseASCIIToLetterOrder(char input)
{
    int valueASCII = static_cast<char>(input);
    bool isLetterUppercase = valueASCII >= ASCII_A_UPPERCASE && valueASCII <= ASCII_Z_UPPERCASE;
    bool isLetterLowercase = valueASCII >= ASCII_A_LOWERCASE && valueASCII <= ASCII_Z_LOWERCASE;
    if (isLetterUppercase)
        valueASCII -= (ASCII_A_UPPERCASE - 1);
    else if (isLetterLowercase)
        valueASCII -= (ASCII_A_LOWERCASE - 1);
    else
        valueASCII = NUMBER_OF_ALPHABAT + 1;
    return valueASCII;
}

void countLetter(vector<int> &listOfInput, string inputString)
{
    for (int i = 0; i < inputString.length(); i++)
    {
        int valueASCII = parseASCIIToLetterOrder(inputString[i]);
        listOfInput[valueASCII - 1]++;
    }
    listOfInput.resize(NUMBER_OF_ALPHABAT);
}