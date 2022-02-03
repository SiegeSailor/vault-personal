#include <iostream>
using namespace std;

int main()
{
    int count_1 = 0;
    cout << count_1 << endl;
    // The statement first returns the value of count_1, and then the value of count_1 is increased by 1
    cout << count_1++ << endl;
    cout << count_1 << endl;

    int count_2 = 0;
    cout << count_2 << endl;
    // The value of count_2 is first increased by 1, and then returns the value of count_2
    cout << ++count_2 << endl;
    cout << count_2 << endl;
    return 0;
}