#include <iostream>
using namespace std;

void printTriangle(int n);
void printOpositeTriangles(int n);
void printRuler(int n);

int main()
{
    cout << "printTriangle(4):" << endl;
    printTriangle(4);
    cout << "printOpositeTriangles(4):" << endl;
    printOpositeTriangles(4);
    cout << "printRuler(4):" << endl;
    printRuler(4);
    return 0;
}

void printTriangle(int n)
{
    if (n > 0)
    {
        printTriangle(n - 1);
        for (int i = 0; i < n; i++)
        {
            cout << "*";
        }
        cout << endl;
    }
}

void printOpositeTriangles(int n)
{
    if (n > 0)
    {
        printOpositeTriangles(-n);
        printOpositeTriangles(n - 1);
        for (int i = 0; i < n; i++)
        {
            cout << "*";
        }
        cout << endl;
    }
    else if (n < 0)
    {
        for (int i = 0; i > n; i--)
        {
            cout << "*";
        }
        cout << endl;
    }
}

/**
 * n == 4:
 * -
 * --
 * -
 * ---
 * -
 * --
 * -
 * ----
 * -
 * --
 * -
 * ---
 * -
 * --
 * -
 * n == 3:
 * -
 * --
 * -
 * ---
 * -
 * --
 * -
 * n == 2:
 * -
 * --
 * -
 * n == 1:
 * -
 */
void printRuler(int n)
{
    if (n > 0)
    {
        printRuler(n - 1);
        for (int i = 0; i < n; i++)
        {
            cout << "*";
        }
        cout << endl;
        printRuler(n - 1);
    }
}