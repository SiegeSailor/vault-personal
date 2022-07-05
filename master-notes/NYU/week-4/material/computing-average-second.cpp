#include <iostream>
using namespace std;

int main()
{
    int student_number = 0, sum = 0, current_grade = 0;
    cout << "Enter grades for each student until entering -1: " << endl;
    while (current_grade != -1)
    {
        cin >> current_grade;
        sum += current_grade;
        student_number += 1;
    }
    cout << "The average of the " << student_number << " students is " << ((double)sum / (double)student_number) << endl;
    return 0;
}