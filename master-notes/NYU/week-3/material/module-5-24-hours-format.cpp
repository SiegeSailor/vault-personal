#include <iostream>
#include <string>
using namespace std;

int main()
{
    int hour12, hour24;
    int minute12, minute24;
    string period;
    char symbol;

    cout << "Enter a time in 24-hour format such as 13:12:\n";
    cin >> hour24 >> symbol >> minute24;

    minute12 = minute24;

    if (hour24 >= 0 && hour24 <= 12)
    {
        period = "AM";
        if (hour24 == 0)
        {
            hour12 = 12;
        }
        else
        {
            hour12 = hour24;
        }
    }
    else
    {
        period = "PM";
        if (hour24 == 12)
        {
            hour12 = 12;
        }
        else
        {
            hour12 = hour24 - 12;
        }
    }

    cout << hour24 << symbol << minute24 << " is " << hour12 << symbol << minute12 << period << "\n";

    main();

    return 0;
}