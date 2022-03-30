#include <iostream>
#include <list>

using namespace std;

// Queue 為 FIFO，主要使用在 Buffering Information 上，像是 iostream。iostream 會先拿出一塊記憶體存檔案資料，再一步步喂給使用者，這時順序就必須是精準的 FIFO
// Linked List 需要有 head 和 tail 兩個 pointer
template <class T>
class Queue
{
private:
    list<T> data;

public:
    // 與 Stack 差在這，插在最尾端、取最前面 (dequeue) 的，所以是 FIFO
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

int main()
{
    return 0;
}