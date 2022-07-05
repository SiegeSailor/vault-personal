#include <iostream>
#include <string>
using namespace std;

int main()
{
    string nameFirst, nameMiddle, nameLast;
    cout << "Pleas enter first name, then middle name or initial, and then last name separated by a space:" << endl;
    cin >> nameFirst >> nameMiddle >> nameLast;
    cout << nameLast << ", " << nameFirst << " " << nameMiddle[0] << "." << endl;
    return 0;
}