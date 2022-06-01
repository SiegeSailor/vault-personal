---
sidebar_position: 6
---

# Queue

FIFO、First In First Out，主要使用在 Buffering Information，如 `iostream`。`iostream` 會先將檔案資料存在記憶體中，再一步步餵給使用者，這時順序就必須是精準的 FIFO。

:::caution 注意
Queue 中用的 Linked List 必須有 `head` 和 `tail` 2 個 Pointer (Doubly-Linked List)。
:::

```cpp
#include <iostream>
/** Doubly-Linked List */
#include <list>

using namespace std;

template <class T>
class Queue
{
private:
    list<T> data;

public:
    /**
     * 與 Stack 的最主要差異，
     * 注意 dequeue 是將前方的元素取出，而 enqueue 則是將元素插入在後方，
     * 因此是 FIFO，因為最先插入的會最先取走。
     */
    void enqueue(T item)
    {
        data.push_back(item);
    };

    T dequeue()
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
