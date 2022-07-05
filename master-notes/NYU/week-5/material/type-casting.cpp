#include <iostream>
using namespace std;

int main()
{
    int input;
    cin >> input;

    cout << "==" << endl;
    float divided_by_two;
    divided_by_two = (float)input / 2;
    cout << divided_by_two << endl;

    cout << "==" << endl;
    float divided_by_three;
    divided_by_three = static_cast<float>(input) / 3;
    cout << divided_by_three << endl;

    cout << "==" << endl;
    float divided_by_five, input_divided_by_five = input;
    cout << input_divided_by_five << endl;
    divided_by_three = input_divided_by_five / 5;
    cout << divided_by_three << endl;
    return 0;
}