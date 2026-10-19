#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>
using namespace std;

void list(string prefix, int listOfInteger[], int sizeOfListOfInteger);
int randomize(int base);
void randomizeList(int listOfInteger[], int sizeOfListOfInteger);
void readDigit(int listOfInput[], int max);

const int NUMBER_OF_MAP_PIN = 10, NUMBER_OF_REAL_PIN = 5;
const int RANDOM_MIN = 1, RANDOM_MAX = 3;
const int ACTUAL_PIN_NUMBER[NUMBER_OF_REAL_PIN] = {1, 2, 3, 4, 5};
const string PREFIX_KEY = "PIN:", PREFIX_VALUE = "NUM:";
int main()
{
    cout << "Please	enter your PIN according to the following mapping:" << endl;
    int listOfMapInteger[NUMBER_OF_MAP_PIN] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    list(PREFIX_KEY, listOfMapInteger, NUMBER_OF_MAP_PIN);
    randomizeList(listOfMapInteger, NUMBER_OF_MAP_PIN);
    list(PREFIX_VALUE, listOfMapInteger, NUMBER_OF_MAP_PIN);
    int listOfInput[NUMBER_OF_REAL_PIN];
    readDigit(listOfInput, NUMBER_OF_REAL_PIN);

    for (int i = 0; i < NUMBER_OF_REAL_PIN; i++)
    {
        int indexOfCode = ACTUAL_PIN_NUMBER[i];
        int valueOfCode = listOfMapInteger[indexOfCode];
        if (listOfInput[i] != valueOfCode)
        {
            cout << "Your PIN is not correct" << endl;
            return 1;
        }
    }
    cout << "Your PIN is correct" << endl;
    return 0;
}

void list(string prefix, int listOfInteger[], int sizeOfListOfInteger)
{
    cout << prefix << "\t";
    for (int i = 0; i < sizeOfListOfInteger; i++)
    {
        cout << listOfInteger[i] << " ";
    }
    cout << endl;
}

int randomize(int base)
{
    srand(time(0));
    int randomizedNumber = 0;
    while (randomizedNumber < RANDOM_MIN || randomizedNumber > RANDOM_MAX)
    {
        randomizedNumber = (rand() % (base + 1)) + 1;
    }

    return randomizedNumber;
}

void randomizeList(int listOfInteger[], int sizeOfListOfInteger)
{
    for (int i = 0; i < sizeOfListOfInteger; i++)
    {
        listOfInteger[i] = randomize(listOfInteger[i]);
    }
};

void readDigit(int listOfInput[], int max)
{
    int input;
    cin >> input;

    int currentIndex = max - 1;
    while (input > 0 && currentIndex >= 0)
    {
        listOfInput[currentIndex] = input % 10;
        input /= 10;
        currentIndex--;
    }
}