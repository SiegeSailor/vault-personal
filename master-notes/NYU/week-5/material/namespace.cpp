#include <iostream>

void other();

int main()
{
    using namespace std;
    cout << "Use namespace locally rather than globally." << endl;
    other();
    return 0;
}

void other()
{
    std::cout << "Declare namespace in functions." << std::endl;
}