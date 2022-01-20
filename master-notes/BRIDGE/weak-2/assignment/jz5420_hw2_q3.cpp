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
using namespace std;

const int HOUR_TO_MINUTE = 60;
const int DAY_TO_MINUTE = 24 * HOUR_TO_MINUTE;
int main()
{
    int john_days, john_hours, john_minutes;
    cout << "Please enter the number of days John has worked: ";
    cin >> john_days;
    cout << "Please enter the number of hours John has worked: ";
    cin >> john_hours;
    cout << "Please enter the number of minutes John has worked: ";
    cin >> john_minutes;
    john_minutes = john_minutes + (john_days * DAY_TO_MINUTE) + (john_hours * HOUR_TO_MINUTE);

    int bill_days, bill_hours, bill_minutes;
    cout << "Please enter the number of days John has worked: ";
    cin >> bill_days;
    cout << "Please enter the number of hours John has worked: ";
    cin >> bill_hours;
    cout << "Please enter the number of minutes John has worked: ";
    cin >> bill_minutes;
    bill_minutes = bill_minutes + (bill_days * DAY_TO_MINUTE) + (bill_hours * HOUR_TO_MINUTE);

    int total_minutes = john_minutes + bill_minutes;
    int days = total_minutes / DAY_TO_MINUTE,
        offset_days = days * DAY_TO_MINUTE;
    int hours = (total_minutes - offset_days) / HOUR_TO_MINUTE,
        offset_hours = hours * HOUR_TO_MINUTE;
    int minutes = total_minutes - offset_days - offset_hours;
    cout << "The total time both of them worked together is: " << days << " days, " << hours << " hours and " << minutes << " minutes.\n";
    return 0;
}