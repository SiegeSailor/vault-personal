---
sidebar_position: 3
---

# Linked List

:::info 提醒
C++ 中有現成 Doubly-Linked List 可使用：

```cpp
#include <list>
```

:::

```cpp
#include <iostream>

using namespace std;

template <class T>
class Node
{
    template <class U>
    friend class LinkedList;

private:
    T data;
    Node<T> *next;

public:
    Node(T data) : data(data), next(nullptr){};
    Node(T data, Node<T> *next) : data(data), next(next){};

    Node<T> *inquireNext() const
    {
        return next;
    }

    T &inquireData()
    {
        return data;
    }
};

template <class T>
class LinkedList
{
private:
    Node<T> *head;

    LinkedList<T> *copyCircular(LinkedList<T> *rightHandSide)
    {
        if (rightHandSide == nullptr)
            return nullptr;
        else
            return new Node(rightHandSide->data, copyCircular(rightHandSide->next));
    };

public:
    LinkedList() : head(nullptr){};

    LinkedList(const LinkedList &rightHandSide) : head(nullptr)
    {
        *this = rightHandSide;
    };
    LinkedList<T> &operator=(const LinkedList<T> &rightHandSide)
    {
        *head = *rightHandSide.head;
        return *this;
    };
    ~LinkedList()
    {
        clear();
    }

    void clear()
    {
        Node<T> *cache = head;
        while (cache->next != nullptr)
        {
            Node<T> *discard = cache;
            cache = cache->next;
            delete discard;
        }
    }

    bool inquireEmpty() const
    {
        return head == nullptr;
    }

    int inquireSize() const
    {
        int count = 0;
        Node<T> *cache = head;
        while (cache != nullptr)
        {
            count++;
            cache = cache->next;
        }
        return count;
    }

    Node<T> *inquireHead() const
    {
        return head;
    }

    void addHead(T item)
    {
        Node<T> *node = new Node<T>(item);
        if (inquireEmpty())
        {
            head = node;
            return;
        }
        else
        {
            node->next = head;
            head = node;
        }
    }

    void addAfter(Node<T> *previous, T item)
    {
        if (previous == nullptr)
            return;
        Node<T> *node = new Node<T>(item);
        node->next = previous->next;
        previous->next = node;
    }

    void addEnd(T item)
    {
        Node<T> *node = new Node<T>(item);
        if (inquireEmpty())
        {
            addHead(item);
            return;
        };

        Node<T> *cache = head;
        while (cache->next != nullptr)
            cache = cache->next;
        cache->next = node;
    };

    void removeFirst()
    {
        if (inquireEmpty())
            return;
        Node<T> *cache = head;
        head = head->next;
        delete cache;
    }

    void removeLast()
    {
        if (inquireEmpty())
            return;
        Node<T> *cache = head;
        while (cache->next != nullptr)
            cache = cache->next;
        delete cache;
    }
};
```
