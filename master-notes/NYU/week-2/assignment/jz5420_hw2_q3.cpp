/**
 * Suppose John and Bill worked for some time and we want to calculate the total time both of them worked.
 * Write a program that reads number of days, hours, minutes each of them worked,
 * and prints the total time both of them worked together as days, hours, minutes.
 *
 * Hint: Try to adapt the elementary method for addition of numbers to this use.
 *
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter the number of days John has worked: 2
    Please enter the number of hours John has worked: 12
    Please enter the number of minutes John has worked: 15
    Please enter the number of days Bill has worked: 3
    Please enter the number of hours Bill has worked: 15
    Please enter the number of minutes Bill has worked: 20
    The total time both of them worked together is: 6 days, 3 hours and 35 minutes.
 */
#include <iostream>
#include <string>
using namespace std;

enum EMinuteConversion
{
    HOUR = 60,
    DAY = 24 * EMinuteConversion::HOUR
};
const string UNIT_DAYS = "days", UNIT_HOURS = "hours", UNIT_MINUTES = "minutes",
             NAME_JOHN = "John", NAME_BILL = "Bill";
int main()
{
    int john_days, john_hours, john_minutes;
    cout << "Please enter the number of " << UNIT_DAYS << " " << NAME_JOHN << " has worked: ";
    cin >> john_days;
    cout << "Please enter the number of " << UNIT_HOURS << " " << NAME_JOHN << " has worked: ";
    cin >> john_hours;
    cout << "Please enter the number of " << UNIT_MINUTES << " " << NAME_JOHN << " has worked: ";
    cin >> john_minutes;
    john_minutes = john_minutes + (john_days * EMinuteConversion::DAY) + (john_hours * EMinuteConversion::HOUR);

    int bill_days, bill_hours, bill_minutes;
    cout << "Please enter the number of " << UNIT_DAYS << " " << NAME_BILL << " has worked: ";
    cin >> bill_days;
    cout << "Please enter the number of " << UNIT_HOURS << " " << NAME_BILL << " has worked: ";
    cin >> bill_hours;
    cout << "Please enter the number of " << UNIT_MINUTES << " " << NAME_BILL << " has worked: ";
    cin >> bill_minutes;
    bill_minutes = bill_minutes + (bill_days * EMinuteConversion::DAY) + (bill_hours * EMinuteConversion::HOUR);

    int total_minutes, minutes;
    total_minutes = minutes = john_minutes + bill_minutes;
    int days = total_minutes / EMinuteConversion::DAY;
    minutes = total_minutes % EMinuteConversion::DAY;
    int hours = minutes / EMinuteConversion::HOUR;
    minutes = minutes % EMinuteConversion::HOUR;
    cout << "The total time both of them worked together is: " << days << " " << UNIT_DAYS << ", "
         << hours << " " << UNIT_HOURS << " and " << minutes << " " << UNIT_MINUTES << ".\n";
    return 0;
}