#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(4);

    cout << "section a" << endl;
    int section_a_input_number, section_a_input_total = 1;
    cout << "Please enter the length of the sequence:" << endl;
    cin >> section_a_input_number;
    cout << "Please enter your sequence:" << endl;
    for (int count = 0; count < section_a_input_number; count++)
    {
        int input;
        cin >> input;
        section_a_input_total *= input;
    }
    cout << "The geometric mean is: " << pow((double)section_a_input_total, (double)1 / (double)section_a_input_number) << endl;

    cout << "section b" << endl;
    int section_b_input_number = 0, section_b_input_total = 1;
    cout << " Please enter a non-empty sequence of positive integers, each one in a separate line. End your sequence by typing -1:" << endl;
    while (true)
    {
        int input;
        cin >> input;
        if (input == -1)
            break;

        section_b_input_total *= input;
        section_b_input_number++;
    }
    cout << "The geometric mean is: " << pow((double)section_b_input_total, (double)1 / (double)section_b_input_number) << endl;

    return 0;
}