---
sidebar_position: 5
---

# Stack

FILO、First In Last Out，由 Linked List 構成，主要使用在注重順序的場合，如 Compiler 解析 Syntax、Math Infix to Post-Fix Conversion、Math Post-Fix to Value Evaluation 等。

```cpp
#include <iostream>
/** Doubly-Linked List */
#include <list>

using namespace std;

template <class T>
class Stack
{
private:
    list<T> data;

public:
    /**
     * 與 Queue 的最主要差異，
     * 注意 pop 是將前方的元素取出，而 push 又將元素插入在前方，
     * 因此是 FILO，因為最先插入的反而最後被取走。
     * (只會從最前面開始取)
     */
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
```

:::info 提醒
Compiler 解析 Parentheses, Curly Braces, Square Brackets 等，就是一但兩個對上了，就從 Stack 中移除，所以最後被移除的會是最外面的符號。
:::

:::info 提醒
Infix 是為人類閱讀用，因為數學教育，我們能夠輕易辨認計算的順序 (Operator Precedence)，然而電腦無法、進而需要用 Post-Fix 來解析。
:::
