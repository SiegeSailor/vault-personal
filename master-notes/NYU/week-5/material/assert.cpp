// #define NDEBUG
#include <cassert>
#include <iostream>

int return_zero(int input);

int main()
{
    return_zero(-1);
    return 0;
}

int return_zero(int input)
{
    assert(input > 0);
    std::cout << "Execute return_zero" << std::endl;
    return 0;
}