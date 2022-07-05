#include <iostream>
using namespace std;

int main()
{
    // 0 的 ASCII 編碼是 48，用 51 - 48 的方式得出 3
    cout << static_cast<int>('3') - static_cast<int>('0') << endl;
    return 0;
}