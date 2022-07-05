/**
 * Write a program that computes how much a customer has to pay after purchasing two items.
 * The price is calculated according to the following rules:
    • Buy one get one half off promotion: the lower price item is half price.
    • If the customer is club card member, additional 10% off.
    • Tax is added.
 * Inputs to the program include:
    • Two items’ prices
    • Have club card or not (User enters ‘Y’ or ‘y’ for “yes”; ‘N’ or ‘n’ for “no”)
    • Tax rate (User enters the percentage as a number; for example they enter 8.25 if the tax
    rate is 8.25%)
 * Program displays:
    • Base price - the price before the discounts and taxes
    • Price after discounts - the price after the buy one get one half off promotion and the
    member’s discount, if applicable
    • Total price – the amount of money the customer has to pay (after tax).
 * Your program should interact with the user exactly as it shows in the following example:
    Enter price of first item: 10
    Enter price of second item: 20
    Does customer have a club card? (Y/N): y
    Enter tax rate, e.g. 5.5 for 5.5% tax: 8.25
    Base price: 30.0
    Price after discounts: 22.5
    Total price: 24.35625
 */
#include <iostream>
using namespace std;

const int8_t TAX_MIN = 0;
const float PRICE_OFF_FOR_CLUB_CARD_MEMBER = 0.1, PRICE_OFF_FOR_LOWER_PRICE_ITEM = 0.5;
const char Y_CHARACTER_UPPERCASE = 'Y', Y_CHARACTER_LOWERCASE = 'y',
           N_CHARACTER_UPPERCASE = 'N', N_CHARACTER_LOWERCASE = 'n';
int main()
{
    double price_item_first, price_item_second, rate_tax;
    cout << "Enter price of first item: ";
    cin >> price_item_first;
    cout << "Enter price of second item: ";
    cin >> price_item_second;

    char input_club_card;
    bool is_club_member;
    cout << "Does customer have a club card? (Y/N): ";
    cin >> input_club_card;
    switch (input_club_card)
    {
    case Y_CHARACTER_UPPERCASE:
    case Y_CHARACTER_LOWERCASE:
        is_club_member = true;
        break;
    case N_CHARACTER_UPPERCASE:
    case N_CHARACTER_LOWERCASE:
        is_club_member = false;
        break;
    default:
        cout << "Invalid input. Please try again.\n";
        main();
        return 1;
    }

    cout << "Enter tax rate, e.g. 5.5 for 5.5% tax: ";
    cin >> rate_tax;
    if (rate_tax < TAX_MIN)
    {
        cout << "Invalid input. Please try again.\n";
        main();
        return 1;
    }
    rate_tax /= 100;

    double price_item_higher, price_item_lower;
    if (price_item_first > price_item_second)
    {
        price_item_higher = price_item_first;
        price_item_lower = price_item_second;
    }
    else
    {
        price_item_higher = price_item_second;
        price_item_lower = price_item_first;
    }

    double price_total_after_discount = price_item_higher + (price_item_lower * PRICE_OFF_FOR_LOWER_PRICE_ITEM);
    if (is_club_member)
    {
        price_total_after_discount -= price_total_after_discount * PRICE_OFF_FOR_CLUB_CARD_MEMBER;
    }

    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);

    cout.precision(1);
    cout << "Base price: " << price_item_first + price_item_second << endl;
    cout << "Price after discounts: " << price_total_after_discount << endl;
    cout.precision(5);
    cout << "Total price: " << (price_total_after_discount * (1 + rate_tax)) << endl;

    return 0;
}