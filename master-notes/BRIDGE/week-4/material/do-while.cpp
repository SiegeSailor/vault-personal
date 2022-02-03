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

    char input;
    while (true)
    {
        cout << "Enter a character: \n";
        cin >> input;
        if (input == 'a')
            break;
    }
    return 0;
}