#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main()
{
    srand(time(0));
    int target = (rand() % 100) + 1, range_lower = 1, range_upper = 100,
        left_guess = 5, guess;
    cout << "I thought of a number between 1 and 100! Try to guess it." << endl;

    while (left_guess > 0)
    {
        cout << "Range: [" << range_lower << ", " << range_upper << "], Number of guesses left: " << left_guess << endl;
        cout << "Your guess: ";
        cin >> guess;

        left_guess--;

        if (guess == target)
        {
            cout << "Congrets! You guessed my number in " << left_guess
                 << " guesses." << endl;
            break;
        }

        if (left_guess <= 0)
        {
            break;
        }

        if (guess < target)
        {
            cout << "Wrong! My number is bigger." << endl;
            cout << endl;
            if (guess > range_lower)
            {
                range_lower = guess;
            }
        }
        else if (guess > target)
        {
            cout << "Wrong! My number is smaller." << endl;
            cout << endl;
            if (guess < range_upper)
            {
                range_upper = guess;
            }
        }
    }

    if (left_guess <= 0)
    {
        cout << "Out of guess! My number is " << target << endl;
    }

    return 0;
}