#include <iostream>
using namespace std;

int main()
{
    int student_number, sum;
    cout << "Input student numbers: ";
    cin >> student_number;
    cout << "Enter grades for each student: " << endl;
    for (int count = 1; count <= student_number; count += 1)
    {
        int grade;
        cin >> grade;
        sum += grade;
    }
    cout << "The average of the " << student_number << " students is " << ((double)sum / (double)student_number) << endl;
    return 0;
}