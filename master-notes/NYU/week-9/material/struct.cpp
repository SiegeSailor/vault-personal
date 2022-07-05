#include <iostream>
using namespace std;

int main()
{
    struct BankAccount
    {
        long double balance = 5000.0;
        double rate = 1.0;
    };

    BankAccount account_first;
    cout << account_first.balance << endl;

    return 0;
}