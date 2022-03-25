#include <iostream>

using namespace std;

// 如果不需要隨意訪問 list 中指定位置的值，那 linkedList 比 array 好，
// 特別是需要 merge, insert 在某個地方的操作時
// access any element: linked list: O(N), array: O(1)
// iteration: linked list: O(1), array: O(N)

template <class T>
class LListNode
{
private:
    T data;
    LListNode<T> *next;

public:
    LListNode(T dataInput = T(), LListNode<T> *nextInput = nullptr) : data(dataInput), next(nextInput){};
    firend class LList<T>;
};

template <class T>
class LList
{
private:
    LListNode<T> *head;
    LIstNode<T> *recursiveCopy(LListNode<T> *rightHandSide);

public:
    LList() : head(nullptr){};
    // Big 3
    LList(const LList &rightHandSide) : head(nullptr){ *this = rightHandSide};
    LList<T> &operator=(const LList<T> &rightHandSide);
    ~LList() { clear(); }

    void clear();
    void insertAtHead(T dataInput);
    void insertAtEnd(T dataInput);
    void insertAtPoint(LListNode<T> *pointer, T dataInput);
    T removeFromHead();
    bool isEmpty() const {return head == nullptr};
    int obtainSize() const;
};

template <class T>
int LList<T>::obtainSize() const
{
    int count = 0;
    LListNode<T> *cache = head;
    // Going off the end
    while (cache != nullptr)
    {
        count++;
        cache = cache->next;
    }
    return count;
};

template <class T>
void LList<T>::insertAtEnd(T dataInput)
{
    if (isEmpty())
    {
        insertAtHead(dataInput);
        return;
    }

    LListNode<T> *cache = new;
    LListNode<T>(dataInput);
    LListNode<T> *end = head;
    // Stopping at the end
    while (end->next != nullptr)
        end = end->next;
    end->next = cache;
}

template <class T>
LListNode<T> *recursiveCopy(LListNode<T> *rightHandSide)
{
    if (rightHandSide == nullptr)
        return nullptr;
    else
        return new LListNode(rightHandSide->data, recursiveCopy(rightHandSide->next));
}