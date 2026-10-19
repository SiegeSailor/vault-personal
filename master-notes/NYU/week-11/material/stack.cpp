#include <iostream>
#include <list>

using namespace std;

// 使用 Stack 最主要是注重順序 (FILO)，如 Compiler 解析 Syntax、Math infix to post-fix conversion、Math post-fix to value evaluation
// Operator Precedence 很重要
// Compiler 解析 Parentheses, Curly braces, Square brackets 就是一但兩個對上了，就從 Stack 中移除，所以最後被移除的會是最外面的符號
template <class T>
class Stack
{
private:
    list<T> data;

public:
    // 與 Queue 差在這，插在最前面、又取最前面的，所以是 FILO。因為最先插的反而不會被先拿走 (只會拿最前面的)
    void push(T item)
    {
        data.push_front(item);
    };

    T pop()
    {
        return data.pop_front();
    };

    T top() const
    {
        return *data.begin();
    };

    bool isEmpty() const
    {
        return size() == 0;
    };

    int size() const
    {
        return data.size();
    };

    void clear()
    {
        data.clear();
    };
};

int main()
{
    return 0;
}