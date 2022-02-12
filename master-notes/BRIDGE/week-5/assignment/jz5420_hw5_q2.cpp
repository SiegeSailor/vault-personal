#include <iostream>

void printShiftedTriangle(int n, int m, char symbol);
void printPineTree(int n, char symbol);

int main()
{
    using namespace std;

    cout << "Please enter the number of trees you want: ";
    int number_tree;
    cin >> number_tree;
    cout << "Please enter the character you want to fill the trees: ";
    char symbol_tree;
    cin >> symbol_tree;
    printPineTree(number_tree, symbol_tree);

    return 0;
}

void printShiftedTriangle(int n, int m, char symbol)
{
    using namespace std;

    for (int i = 0; i < n; i++)
    {
        int j = n - i - 1 + m;
        while (j > 0)
        {
            cout << " ";
            j--;
        }

        int k = 2 * i + 1;
        while (k > 0)
        {
            cout << symbol;
            k--;
        }
        cout << endl;
    }
}

void printPineTree(int n, char symbol)
{
    for (int i = 1; i <= n; i++)
    {
        printShiftedTriangle(i + 1, n - i, symbol);
    }
}