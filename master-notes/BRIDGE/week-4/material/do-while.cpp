#include <iostream>
using namespace std;

int main()
{
    int count = 0;

    do
    {
        cout << " do-while: " << count << endl;
        count--;
    } while (count > 0);

    while (count > 0)
    {
        cout << " while: " << count << endl;
    };
    return 0;
}