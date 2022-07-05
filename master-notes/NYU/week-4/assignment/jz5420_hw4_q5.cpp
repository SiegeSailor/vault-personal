#include <iostream>
#include <string>
using namespace std;

const char STAR = '*', SPACE = ' ';
int main()
{
    int input_line, max_line;
    cout << "Please enter a positive integer: ";
    cin >> input_line;
    max_line = (input_line * 2) - 1;

    for (int count_outer = 0; count_outer < (input_line * 2); count_outer++)
    {
        string line;
        bool is_reversed = count_outer >= input_line;
        for (int count_inner = 1; count_inner <= max_line; count_inner++)
        {
            int border;
            if (is_reversed)
                border = max_line - count_outer;
            else
                border = count_outer;
            bool is_front_space = count_inner <= border,
                 is_back_space = count_inner > (max_line - border);
            if (border > 0 && (is_front_space || is_back_space))
            {
                line += SPACE;
            }
            else
            {
                line += STAR;
            }
        }
        cout << line << endl;
    }

    return 0;
}