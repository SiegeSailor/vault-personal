#include <iostream>
#include <string>
using namespace std;

bool indiecateIsDelimitingSymbol(char input);
int parseASCIIToLetterOrder(char input);

const int ASCII_A_UPPERCASE = 65, ASCII_Z_UPPERCASE = 90,
          ASCII_A_LOWERCASE = 97, ASCII_Z_LOWERCASE = 122;
const int NUMBER_OF_ALPHABAT = 26;
const char DELIMITING_SYMBOL_WHITESPACE = ' ',
           DELIMITING_SYMBOL_COMMA = ',',
           DELIMITING_SYMBOL_PERIOD = '.',
           DELIMITING_SYMBOL_NEWLINE = '\n';
int main()
{
    cout << "Please enter a line of text:" << endl;
    string inputLine;
    getline(cin, inputLine);

    int listOfCount[NUMBER_OF_ALPHABAT];
    for (int i = 0; i < NUMBER_OF_ALPHABAT; i++)
    {
        listOfCount[i] = 0;
    }

    bool isCounting = !indiecateIsDelimitingSymbol(inputLine[0]);
    int countWord = 0;
    for (int i = 0; i < inputLine.length(); i++)
    {
        bool isDelimitingSymbol = indiecateIsDelimitingSymbol(inputLine[i]);
        if (!isDelimitingSymbol)
        {
            int valueASCII = parseASCIIToLetterOrder(inputLine[i]);
            listOfCount[valueASCII - 1]++;

            if (isCounting)
                countWord++;
            isCounting = false;
        }
        else
        {
            isCounting = true;
        }
    }

    cout << countWord << "\twords" << endl;
    for (int i = 0; i < NUMBER_OF_ALPHABAT; i++)
    {
        if (listOfCount[i] != 0)
        {
            char character = static_cast<char>(i + (ASCII_A_LOWERCASE));
            cout << listOfCount[i] << "\t" << character << endl;
        }
    }

    return 0;
}

bool indiecateIsDelimitingSymbol(char input)
{
    const int sizeSymbol = 4;
    char listOfDelimitingSymbol[sizeSymbol] =
        {DELIMITING_SYMBOL_WHITESPACE,
         DELIMITING_SYMBOL_COMMA,
         DELIMITING_SYMBOL_PERIOD,
         DELIMITING_SYMBOL_NEWLINE};
    for (int i = 0; i < sizeSymbol; i++)
    {
        if (listOfDelimitingSymbol[i] == input)
            return true;
    }
    return false;
}

int parseASCIIToLetterOrder(char input)
{
    int valueASCII = static_cast<char>(input);
    bool isLetterUppercase = valueASCII >= ASCII_A_UPPERCASE && valueASCII <= ASCII_Z_UPPERCASE;
    if (isLetterUppercase)
        valueASCII -= (ASCII_A_UPPERCASE - 1);
    bool isLetterLowercase = valueASCII >= ASCII_A_LOWERCASE && valueASCII <= ASCII_Z_LOWERCASE;
    if (isLetterLowercase)
        valueASCII -= (ASCII_A_LOWERCASE - 1);
    return valueASCII;
}