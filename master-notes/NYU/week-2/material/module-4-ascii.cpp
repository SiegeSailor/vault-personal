#include <iostream>
using namespace std;
int main()
{
    char inputValue;
    int asciiValue;
    cout << "Enter a character:\n";
    // 如果在程式內為 char 指定值，只能用單引號，雙引號是給 string 用的
    cin >> inputValue;
    cout << "You entered " << inputValue << endl;
    asciiValue = (int)inputValue;
    cout << "The ASCII value of " << inputValue << " is " << asciiValue << endl;
    cout << "The binary value is " << bitset<8>(asciiValue) << endl;
    return 0;
}