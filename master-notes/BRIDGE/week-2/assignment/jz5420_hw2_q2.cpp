/**
 * Write a program that asks the user to enter an amount of money in the format of dollars andremaining cents.
 * The program should calculate and print the minimum number of coins (quarters, dimes, nickels and pennies)
 * that are equivalent to the given amount.
 *
 * Hint: In order to find the minimum number of coins, first find the maximum number of quarters that fit in the given amount of money,
 * then find the maximum number of dimes that fit in the remaining amount, and so on.
 *
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter your amount in the format of dollars and cents separated by a space:
    4 37
    4 dollars and 37 cents are:
    17 quarters, 1 dimes, 0 nickels and 2 pennies
 */
#include <iostream>
using namespace std;

const int8_t CENT_TO_QUARTER = 25, CENT_TO_DIME = 10, CENT_TO_NICKEL = 5,
             CENT_TO_PENNIE = 1, DOLLAR_TO_CENT = 1e2;
int main()
{
    int dollars, cents;
    cout << "Please enter your amount in the format of dollars and cents separated by a space:\n";
    cin >> dollars >> cents;
    cout << dollars << " dollars and " << cents << " cents are:\n";

    auto total_cents = (dollars * DOLLAR_TO_CENT) + cents;
    auto quarters = total_cents / CENT_TO_QUARTER,
         offset_quarters = quarters * CENT_TO_QUARTER;
    auto dimes = (total_cents - offset_quarters) / CENT_TO_DIME,
         offset_dimes = dimes * CENT_TO_DIME;
    auto nickels = (total_cents - offset_quarters - offset_dimes) / CENT_TO_NICKEL,
         offset_nickel = nickels * CENT_TO_NICKEL;
    auto pennies = (total_cents - offset_quarters - offset_dimes - offset_nickel) * CENT_TO_PENNIE;
    cout << quarters << " quarters, " << dimes << " dimes, " << nickels << " nickels and " << pennies << " pennies\n";
    return 0;
}