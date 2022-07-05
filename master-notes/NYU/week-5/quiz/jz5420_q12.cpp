#include <iostream>
#include <string>
using namespace std;

const string UNIT_DOT_SINGLE = "dot";
const string UNIT_DOT_MULTIPLE = "dots";
const string UNIT_TIME_SINGLE = "time";
const string UNIT_TIME_MULTIPLE = "times";
const string FACE_ONE = "one", FACE_TWO = "two", FACE_THREE = "three",
             FACE_FOUR = "four", FACE_FIVE = "five", FACE_SIX = "six";
int main()
{
    cout << "Please enter a sequence of numbers in between 1 and 6,"
         << " each one in a separate line."
         << " End your sequence by typing -1:"
         << endl;
    int count_one = 0, count_two = 0, count_three = 0,
        count_four = 0, count_five = 0, count_six = 0;
    while (true)
    {
        int input;
        cin >> input;
        if (input == -1)
        {
            break;
        }

        switch (input)
        {
        case 1:
            count_one++;
            break;
        case 2:
            count_two++;
            break;
        case 3:
            count_three++;
            break;
        case 4:
            count_four++;
            break;
        case 5:
            count_five++;
            break;
        case 6:
            count_six++;
            break;
        default:
            cout << "Error." << endl;
            return 1;
        }
    }

    if (count_one > 1)
    {
        cout << "Face with " << FACE_ONE
             << " " << UNIT_DOT_SINGLE << " appeared: "
             << count_one << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_ONE
             << " " << UNIT_DOT_SINGLE << " appeared: "
             << count_one << " " << UNIT_TIME_SINGLE << endl;
    }

    if (count_two > 1)
    {
        cout << "Face with " << FACE_TWO
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_two << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_TWO
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_two << " " << UNIT_TIME_SINGLE << endl;
    }

    if (count_three > 1)
    {
        cout << "Face with " << FACE_THREE
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_three << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_THREE
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_three << " " << UNIT_TIME_SINGLE << endl;
    }

    if (count_four > 1)
    {
        cout << "Face with " << FACE_FOUR
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_four << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_FOUR
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_four << " " << UNIT_TIME_SINGLE << endl;
    }

    if (count_five > 1)
    {
        cout << "Face with " << FACE_FIVE
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_five << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_FIVE
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_five << " " << UNIT_TIME_SINGLE << endl;
    }

    if (count_six > 1)
    {
        cout << "Face with " << FACE_SIX
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_six << " " << UNIT_TIME_MULTIPLE << endl;
    }
    else
    {
        cout << "Face with " << FACE_SIX
             << " " << UNIT_DOT_MULTIPLE << " appeared: "
             << count_six << " " << UNIT_TIME_SINGLE << endl;
    }
    return 0;
}