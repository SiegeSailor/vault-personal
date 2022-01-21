/** 
 * Write a program that asks the user to enter a number of quarters,
 * dimes, nickels and pennies and then outputs the monetary value of the coins in the format of dollars andremaining cents.
 * 
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter number of coins:
    # of quarters: 13
    # of dimes: 4
    # of nickels: 11
    # of pennies: 17
    The total is 4 dollars and 37 cents
    (13*0.25)+(4*0.1)+(11*0.05)+(17*0.01) = 4.37
 */
#include <iostream>
using namespace std;

const float DOLLAR_PER_QUARTER = 0.25;
const float DOLLAR_PER_DIME = 0.1;
const float DOLLAR_PER_NICKEL = 0.05;
const float DOLLAR_PER_PENNIE = 0.01;
const short int CENT_PER_DOLLAR = 100;
int main()
{
    int quarters, dimes, nickels, pennies;
    cout << "Please enter number of coins:\n";
    cout << "# of quarters: ";
    cin >> quarters;
    cout << "# of dimes: ";
    cin >> dimes;
    cout << "# of nickels: ";
    cin >> nickels;
    cout << "# of pennies: ";
    cin >> pennies;

    float total = (quarters * DOLLAR_PER_QUARTER) + (dimes * DOLLAR_PER_DIME) + (nickels * DOLLAR_PER_NICKEL) + (pennies * DOLLAR_PER_PENNIE);
    int total_dollars = total;
    float total_cents = (total - total_dollars) * CENT_PER_DOLLAR;
    cout << "The total is " << total_dollars << " and " << total_cents << " cents\n";
    return 0;
}