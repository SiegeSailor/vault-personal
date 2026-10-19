#include <iostream>
#include <vector>
using namespace std;

int main1();
int main2();

const string DESCRIPTION_SEQUENCE = "Please enter a sequence of positive integers,\
 each in a separate line.\
 \nEnd you input by typing -1.",
             DESCRIPTION_SEARCH = "Please enter a number you want to search:",
             DESCRIPTION_RESULT_SUCCESS = " shows in lines ",
             DESCRIPTION_RESULT_FAIL = " does not show at all in the sequence.";
int main()
{
    cout << "Enter the corrsponding number to test (1) non-vector (2) vector:" << endl;
    int input;
    cin >> input;
    switch (input)
    {
    case 1:
        main1();
        break;
    case 2:
        main2();
        break;
    default:
        cout << "You just entered a wrong number." << endl;
        return 1;
    }
    return 0;
}

int main1()
{
    cout << DESCRIPTION_SEQUENCE << endl;
    int size = 0;
    int *listOfExistInput = new int[size];
    while (true)
    {
        int input;
        cin >> input;
        if (input == -1)
            break;

        int *listOfAddedInput = new int[size + 1];
        for (int i = 0; i < size; i++)
        {
            listOfAddedInput[i] = listOfExistInput[i];
        }
        listOfAddedInput[size] = input;
        delete[] listOfExistInput;
        listOfExistInput = listOfAddedInput;
        size++;
    }

    cout << DESCRIPTION_SEARCH << endl;
    int search;
    cin >> search;

    int sizeSearch = 0;
    int *listOfSearch = new int[sizeSearch];
    for (int i = 0; i < size; i++)
    {
        if (search == listOfExistInput[i])
        {
            int *listOfAddedSearch = new int[sizeSearch + 1];
            for (int i = 0; i < sizeSearch; i++)
            {
                listOfAddedSearch[i] = listOfSearch[i];
            }
            listOfAddedSearch[sizeSearch] = i;
            delete[] listOfSearch;
            listOfSearch = listOfAddedSearch;
            sizeSearch++;
        }
    }

    if (sizeSearch < 1)
    {
        cout << search << DESCRIPTION_RESULT_FAIL << endl;
        return 0;
    }

    cout << search << DESCRIPTION_RESULT_SUCCESS;
    for (int i = 0; i < sizeSearch; i++)
    {
        if (i != 0)
            cout << ", ";
        cout << listOfSearch[i];
    }
    cout << "." << endl;
    return 0;
};

int main2()
{
    cout << DESCRIPTION_SEQUENCE << endl;

    vector<int> listOfInput;
    while (true)
    {
        int input;
        cin >> input;
        if (input == -1)
            break;

        listOfInput.push_back(input);
    }

    cout << DESCRIPTION_SEARCH << endl;
    int search;
    cin >> search;

    vector<int> listOfSearch;
    for (int i = 0; i < listOfInput.size(); i++)
    {
        if (search == listOfInput[i])
        {
            listOfSearch.push_back(i);
        }
    }

    int sizeSearch = listOfSearch.size();
    if (sizeSearch < 1)
    {
        cout << search << DESCRIPTION_RESULT_FAIL << endl;
        return 0;
    }
    else
    {
        cout << search << DESCRIPTION_RESULT_SUCCESS;
        for (int i = 0; i < sizeSearch; i++)
        {
            if (i != 0)
                cout << ", ";
            cout << listOfSearch[i];
        }
        cout << "." << endl;
    }

    return 0;
}