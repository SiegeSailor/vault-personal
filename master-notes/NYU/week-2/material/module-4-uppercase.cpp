#include <iostream>
using namespace std;
int main()
{
    char input;
    cout << "Enter a lowercase character:\n";
    cin >> input;
    cout << "The uppcase character for " << input << " is " << (char)(input - 32) << endl;

    // 也可以計算輸入字元到 a 之間的距離，再用 A 算出
    int offset;
    offset = 'a' - input;
    cout << (char)('A' - offset) << endl;
    return 0;
}