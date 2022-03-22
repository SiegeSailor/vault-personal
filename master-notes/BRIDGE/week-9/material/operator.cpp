#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cctype>
using namespace std;

class Money
{
private:
    long balance;

public:
    Money(){};
    Money(long input) : balance(input)
    {
        cout << "Money starts with " << input << endl;
    };

    Money operator+(const Money &that)
    {
        return Money(this->balance + that.balance);
    };

    long obtainBalance()
    {
        return balance;
    };

    void addBalance(long input)
    {
        balance += input;
    };

    friend istream &operator>>(istream &input, Money &main);

    friend ostream &operator<<(ostream &output, const Money &main);
};

istream &operator>>(istream &input, Money &main)
{
    long local;
    input >> local;
    cout << "Store " << local << endl;
    main.addBalance(local);
    return input;
};

ostream &operator<<(ostream &output, const Money &main)
{
    cout << "Now have " << labs(main.balance) << endl;
    output << main.balance;
    return output;
};

int main()
{
    Money first(10L);
    ifstream input;
    ofstream output;

    input.open("input.txt");
    if (input.fail())
    {
        cout << "Input open file failed." << endl;
        exit(1);
    };

    output.open("output.txt");
    if (output.fail())
    {
        cout << "Output open file failed." << endl;
        exit(1);
    }

    input >> first;
    output << first;

    return 0;
}