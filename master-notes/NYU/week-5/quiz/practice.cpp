/**
 * @file practice.cpp
 * @author Ken Zhang (jz5420@nyu.edu)
 * @brief Practice exam for NYU CS Bridge Winter 2022 Exam 1
 * @version 0.1
 * @date 2022-02-10
 *
 * @copyright Copyright (c) 2022
 *
 * 1.
 * (a) true
 * (b) true
 * (c) false
 * (d) true
 *
 * 2.
 *
 * 3.
 * Z \implies N
 * { ..., -2, -1, 0, 1, 2, ... } \implies { 1, 2, 3 }
 * (a) |x| + x + 1
 * f(-2): 1
 * f(-1): 1
 * f(0): 1
 * f(1): 3
 * f(2): 5
 * (b) x^2 + 1 if x >= 0, x^2 + 2 if x < 0
 * f(-2): 6
 * f(-1): 3
 * f(0): 1
 * f(1): 2
 * f(2): 5
 * (c) \ceiling |x|/2 if x != 0, x + 1 if x == 0
 * f(-2): 1
 * f(-1): 1
 * f(0): 0
 * f(1): 1
 * f(2): 1
 * (d) x * 2 if x >= 0, |x| * 2 + 1 if x < 0, x = 1 if x == 0
 * f(-3): 7
 * f(-2): 5
 * f(-1): 3
 * f(0): 1
 * f(1): 2
 * f(2): 4
 * f(3): 6
 *
 * 4.
 * (a)
 * Proof.
 * Let a and b be real numbers. we will have the assumption equation below for
 * the sake of contradiction.
 * \[ a \in Q b \in Q c \in P. a \times b = c \]
 * We assume that a = x/y where x and y are integers and x != 0,
 * and then assume that b = m/n where m and n are integers and n != 0.
 * $ a \times b = (x \times m)/(y \times n) $
 * Since x, y, m, and n are all integers, $ x \times m $ and $ y \times n $ are
 * also integers, which means c is a fraction, $ c \in Q $.
 * $ c \in Q $ contradicts our assumption $ a \in Q b \in Q c \in P. a \times b = c $.
 * Therefore, if $ a $ is rational and $ ab $ is irrational, then $ b $ is irrational.
 * \blacksquare
 * (b)
 * Proof.
 * Let k be an integer. We will have the assumption below for the sake
 * of contradiction.
 * $ k $ is even, $ 3k + 1 $ is odd.
 * Suppose k is 2m where m is an integer. 3k + 1 = 3(2m) + 1 = 6m + 1. Any even/odd
 * number multiples even number will become even and 1 is considered as odd, 6m + 1 = even + odd = odd.
 * $ k $ is even, $ 3k + 1 $ is odd contradicts our proof, thus, the proof is valid.
 * \blacksquare
 * (c)
 * Proof.
 * Let x and y be integers and both odd, and then we have the following equation:
 * x = 2m + 1
 * y = 2n + 1
 * x + y = 2m + 1 + 2n + 1 = 2m + 2n + 2
 * For any odd/even number, multipling even number will make it be even, and 2 is even,
 * 2m + 2n + 2 = even + even + even = even.
 * \blacksquare
 */

#include <iostream>

int sumDigit(int input);
void printKite(int input);
void printDiamond(int input);

int main()
{
    using namespace std;

    cout << "Find the sum of each individual digit within an integer." << endl;
    cout << endl;
    cout << "For example:" << endl;
    cout << "Given n = 567, the code should output: " << sumDigit(567) << endl;
    cout << "Given n = 18, the code should output: " << sumDigit(18) << endl;
    cout << endl;
    cout << "Please enter a positive integer: ";
    int input;
    cin >> input;
    cout << "The sum of each individual digit is " << sumDigit(input) << endl;

    printDiamond(input);

    return 0;
}

int sumDigit(int input)
{
    int sum = 0;
    while (input > 0)
    {
        sum += input % 10;
        input /= 10;
    }

    return sum;
}

void printKite(int input)
{
    using namespace std;

    int initialSpace = input - 1;
    int stars = 1;

    for (int i = 0; i < input * 2 + 1; i++)
    {
        for (int j = 0; j <= initialSpace; j++)
        {
            cout << " ";
        }

        for (int j = 1; j < stars; j++)
        {
            cout << "* ";
        }
        cout << endl;

        if (i < input)
        {
            initialSpace--;
            stars++;
        }
        else
        {
            initialSpace++;
            stars--;
        }
    }
}

/**
 * If input is 3:
 * _*_
 * ***
 * _*_
 *
 * If input is 5:
 * __*__
 * _***_
 * *****
 * _***_
 * __*__
 */
void printDiamond(int input)
{
    using namespace std;

    int countSpace = input - 1;
    int countStar = 1;
    for (int countOuter = 0; countOuter < input * 2 + 1; countOuter++)
    {
        for (int countInner = 0; countInner <= countSpace; countInner++)
        {
            cout << "_";
        }

        for (int countInner = 1; countInner < countStar; countInner++)
        {
            cout << "*_";
        }
        cout << endl;

        if (countOuter < input)
        {
            countSpace--;
            countStar++;
        }
        else
        {
            countSpace++;
            countStar--;
        }
    }
}