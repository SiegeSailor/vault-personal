#include <iostream>
#include <string>
using namespace std;
auto main()
{
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.setf(ios::showbase);
    cout.precision(5);
    short a = 1;
    int8_t max = 127;
    int8_t min = -128;
    int b = 11;
    long c = 111;
    double d = 1.1;
    long double e = 1.11;
    string day = "Monday";
    // day.find("M", 1); return -1
    // day.substr(0,3); 從 0 開始取 3
    decltype(a * 20) y;
    return 0;
}