#include <iostream>
using namespace std;

int main()
{
    cout << "section a" << endl;
    int section_a_input_positive_integer, count;
    cout << "Please enter a positive integer: ";
    cin >> section_a_input_positive_integer;

    count = 1;
    while (count <= section_a_input_positive_integer)
    {
        cout << (2 * count) << endl;
        count++;
    }

    cout << "section b" << endl;
    int section_b_input_positive_integer;
    cout << "Please enter a positive integer: ";
    cin >> section_b_input_positive_integer;

    for (int count = 1; count <= section_b_input_positive_integer; count++)
    {
        cout << (2 * count) << endl;
    }

    return 0;
}