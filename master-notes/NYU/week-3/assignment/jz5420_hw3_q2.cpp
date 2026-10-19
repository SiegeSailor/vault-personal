/**
 * Write a program that:
    • Asks the user for their name.
    • Asks the user to input their graduation year.
    • Asks the user to input the current year.
 * Assume the student is in a four-year undergraduate program.Display the current status the student is in.
 * Possible status include: not in college yet, freshman, sophomore, junior, senior, graduated.
 * Note: If graduation year equals to current year, status is ‘Graduated’; if graduation year is
 * four years after current year, status is ‘Freshman’, etc.

 * Your program should interact with the user exactly as it shows in the following example:
    Please enter your name: Jessica
    Please enter your graduation year: 2019
    Please enter current year: 2015
    Jessica, you are a Freshman
 */
#include <iostream>
#include <string>
using namespace std;

const string FRESHMAN = "Freshman", SOPHOMORE = "Sophomore", JUNIOR = "Junior", SENIOR = "Senior", GRADUATED = "Graduated";
int main()
{
    string name;
    int year_graduation, year_current;
    cout << "Please enter your name: ";
    cin >> name;
    cout << "Please enter your graduation year: ";
    cin >> year_graduation;
    cout << "Please enter current year: ";
    cin >> year_current;

    string status;
    int year_gap = year_graduation - year_current;
    switch (year_gap)
    {
    case 1:
        status = SENIOR;
        break;
    case 2:
        status = JUNIOR;
        break;
    case 3:
        status = SOPHOMORE;
        break;
    case 4:
        status = FRESHMAN;
        break;
    default:
        status = GRADUATED;
        break;
    }

    if (year_gap > 4)
    {
        cout << "You are not a college student yet.\n";
        return 0;
    }

    cout << name << ", you are a " << status << endl;
    return 0;
}