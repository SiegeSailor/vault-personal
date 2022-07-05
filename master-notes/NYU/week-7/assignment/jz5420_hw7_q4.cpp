#include <iostream>
#include <string>
#include <vector>
using namespace std;

string *createWordsArray(string sentence, int &outWordsArrSize);
void printResult(string prefix, string sentence);

const char SEPERATOR = ' ';
int main()
{
    printResult("Test 1:", "You can do it");
    printResult("Test 2:", "Life is long and short depends on the shoes you wear");
    printResult("Test 3:", "If you are doing your best you will not have to worry about failure");

    return 0;
}

string *createWordsArray(string sentence, int &outWordsArrSize)
{
    vector<string> listOfCountWord;
    int indexOfFindStart = 0;
    int indexOfEnd = sentence.length() - 1;
    while (indexOfFindStart < indexOfEnd)
    {
        int indexOfSeperator = sentence.find(SEPERATOR, indexOfFindStart);
        if (indexOfSeperator == -1)
            indexOfSeperator = indexOfEnd;

        int offset = 0;
        if (indexOfSeperator == indexOfEnd)
            offset = 1;
        string subString = sentence.substr(indexOfFindStart, indexOfSeperator - indexOfFindStart + offset);
        indexOfFindStart = indexOfSeperator + 1;

        listOfCountWord.push_back(subString);
    }

    outWordsArrSize = listOfCountWord.size();
    string *listOfWord = new string[outWordsArrSize];
    for (int i = 0; i < outWordsArrSize; i++)
    {
        listOfWord[i] = listOfCountWord[i];
    }

    return listOfWord;
}

void printResult(string prefix, string sentence)
{
    cout << prefix << endl;
    cout << sentence << endl;
    int outWordsArrSize = 0;
    string *result = createWordsArray(sentence, outWordsArrSize);
    cout << "outWordsArrSize: " << outWordsArrSize << endl;
    cout << "return array:" << endl;
    for (int i = 0; i < outWordsArrSize; i++)
    {
        cout << i << ": " << result[i] << endl;
    }
    delete[] result;
    result = nullptr;
    cout << endl;
}