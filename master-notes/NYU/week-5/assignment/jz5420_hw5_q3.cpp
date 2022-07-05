#include <iostream>
#include <string>
using namespace std;

enum WeekDay
{
    Mon = 1,
    Tue = 2,
    Wed = 3,
    Thr = 4,
    Fri = 5,
    Sat = 6,
    Sun = 7
};
enum Month
{
    Jan = 1,
    Feb = 2,
    Mar = 3,
    Apr = 4,
    May = 5,
    Jun = 6,
    Jul = 7,
    Aug = 8,
    Sep = 9,
    Oct = 10,
    Nov = 11,
    Dec = 12
};
const string MON = "Mon", TUE = "Tue", WED = "Wed",
             THR = "Thr", FRI = "Fri", SAT = "Sat", SUN = "Sun";
const string JAN = "January", FEB = "February", MAR = "March", APR = "April",
             MAY = "May", JUN = "Jun", JUL = "July", AUG = "August", SEP = "September",
             OCT = "October", NOV = "November", DEC = "December";

int printMonthCalender(int numOfDays, int startingDay);
bool isLeapYear(int year);
void printYearCalender(int year, int startingDay);
void printMonthHeader(int year, int month);

int main()
{
    cout << "Enter an integer that represents a year (e.g. 2016): ";
    int year;
    cin >> year;
    int startingDay;
    cout << "Enter a number 1-7 that represents the day in the week of 1/1 in that year "
         << "(1 for Monday, 2 for Tuesday, 3 for Wednesday, etc.): ";
    cin >> startingDay;

    printYearCalender(year, startingDay);
    return 0;
}

int printMonthCalender(int numOfDays, int startingDay)
{

    for (int i = 1; i <= 7; i++)
    {
        switch (i)
        {
        case WeekDay::Mon:
            cout << MON << "\t";
            break;
        case WeekDay::Tue:
            cout << TUE << "\t";
            break;
        case WeekDay::Wed:
            cout << WED << "\t";
            break;
        case WeekDay::Thr:
            cout << THR << "\t";
            break;
        case WeekDay::Fri:
            cout << FRI << "\t";
            break;
        case WeekDay::Sat:
            cout << SAT << "\t";
            break;
        case WeekDay::Sun:
            cout << SUN << "\t";
            break;
        default:
            cout << "Error." << endl;
            return 1;
        }
    }
    cout << endl;

    int totalDay = numOfDays + startingDay;
    for (int i = 1; i < totalDay; i++)
    {
        if (i < startingDay)
        {
            cout << " \t";
        }
        else
        {
            cout << (i - (startingDay - 1)) << "\t";
        }

        if (i % 7 == 0 && i < totalDay - 1)
            cout << endl;
    }
    cout << endl;

    int lastWeekday = totalDay % 7;
    if (lastWeekday == 0)
        lastWeekday = 7;
    return lastWeekday;
}

bool isLeapYear(int year)
{
    return (((year % 4 == 0) && (year % 100 > 0)) || (year % 400 == 0));
}

void printYearCalender(int year, int startingDay)
{
    int extraDay = 0;
    if (isLeapYear(year))
        extraDay = 1;

    int lastWeekday = startingDay;
    int numOfDay = 0;
    for (int i = 1; i <= 12; i++)
    {
        switch (i)
        {
        case Month::Apr:
        case Month::Jun:
        case Month::Sep:
        case Month::Nov:
            numOfDay = 30;
            break;
        case Month::Jan:
        case Month::Mar:
        case Month::May:
        case Month::Jul:
        case Month::Aug:
        case Month::Oct:
        case Month::Dec:
            numOfDay = 31;
            break;
        case Month::Feb:
            numOfDay = 28 + extraDay;
            break;
        default:
            cout << "Error." << endl;
            return;
        }
        cout << endl;
        printMonthHeader(year, i);
        lastWeekday = printMonthCalender(numOfDay, lastWeekday);
    }
}

void printMonthHeader(int year, int month)
{
    switch (month)
    {
    case Month::Jan:
        cout << JAN;
        break;
    case Month::Feb:
        cout << FEB;
        break;
    case Month::Mar:
        cout << MAR;
        break;
    case Month::Apr:
        cout << APR;
        break;
    case Month::May:
        cout << MAY;
        break;
    case Month::Jun:
        cout << JUN;
        break;
    case Month::Jul:
        cout << JUL;
        break;
    case Month::Aug:
        cout << AUG;
        break;
    case Month::Sep:
        cout << SEP;
        break;
    case Month::Oct:
        cout << OCT;
        break;
    case Month::Nov:
        cout << NOV;
        break;
    case Month::Dec:
        cout << DEC;
        break;
    default:
        cout << "Error." << endl;
        return;
    }
    cout << " " << year << endl;
}