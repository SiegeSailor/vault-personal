#include <iostream>
using namespace std;

class Money
{
private:
    int balance;

public:
    Money(){};
    Money(int input) : balance(input)
    {
        cout << "Construct: " << input << endl;
    };

    Money operator+(const Money &that)
    {
        return Money(this->balance + that.balance);
    };

    int obtainBalance()
    {
        return balance;
    };
};

int main()
{
    Money first(10), second;
    // first 是 Money, 25 是 int。在有合適的 operator (+) 和 constructor 時，c++ 會自動轉換
    // 像 25 先是被自動用 Money 建起來，接著 first 再用 operator+ 與 Money(25) 合併，於是 second 變成 Money(35)
    // second = ;
    cout << (first + 25).obtainBalance() << endl;
    cout << (second).obtainBalance() << endl;
    return 0;
}