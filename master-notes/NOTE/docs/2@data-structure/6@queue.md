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

Linked List 在 Java 的 Implantation：

```java
public class Node {
    private Integer data;

    private Node next;

    public void setNext(Node input) {
        this.next = input;
    }

    public Node getNext() {
        return this.next;
    }

    public void setData(Integer input) {
        this.data = input;
    }

    public Integer getData() {
        return this.data;
    }
}
```

```java
public class LinkedListQueue {
    private Node head, tail;

    private int count;

    public boolean add(Integer input) {
        Node node = new Node();
        node.setData(input);

        if (isEmpty()) {
            this.count = 1;
            this.head = this.tail = node;

            return true;
        }

        this.tail.setNext(node);
        this.count++;
        this.tail = node;

        return true;
    }

    public Integer remove() {
        if (this.isEmpty())
            return null;

        Integer data = this.head.getData();
        this.head = this.head.getNext();
        this.count--;

        if (this.count == 0)
            this.head = this.tail = null;

        return data;
    }

    public Integer peak() {
        if (this.isEmpty())
            return null;

        return this.head.getData();
    }

    public int inquireSize() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        String message = "";
        message += "Size = " + this.count + "\n";

        Node cache = this.head;
        for (int i = 0; i < this.count; i++) {
            message += cache.getData() + "\n";
            cache = cache.getNext();
        }

        return message;
    }
}
```

## Array Based

以 Array 為基底的 Queue。注意在 Array Based 當中，indexOfTail 的值永遠為空：

```java
public class ArrayQueue {
    private static final int SIZE = 10;

    Integer[] arrayOfInteger;

    private int count, indexOfHead, indexOfTail;

    private void resize() {
        Integer[] arrayOfIncoming = new int[this.arrayOfInteger.length + SIZE];

        int indexCurrent = this.indexOfHead;
        for (int i = 0; i < this.count; i++) {
            /**
             * 注意不用在到底時重新指派 i 為 0，
             * 因為 arrayOfIncoming 的 Size 比原本的 arrayOfInteger 還大，
             * 除非是其他 Thread 的操作影響而且無保護，否則不可能到底
             */
            arrayOfIncoming[i] = this.arrayOfInteger[indexOfCurrent++];
            if (indexCurrent == this.arrayOfInteger.length)
                indexCurrent = 0;
        }

        this.indexOfHead = 0;
        this.indexOfTail = this.count;
        this.arrayOfInteger = arrayOfIncoming;
    }

    public ArrayQueue() {
        this.arrayOfInteger = new Integer[SIZE];
        this.count = this.indexOfHead = this.indexOfTail = 0;
    }

    public boolean add(Integer input) {
        if (this.isFull())
            this.resize();

        this.arrayOfInteger[this.indexOfTail++] = input;
        if (this.indexOfTail == arrayOfInteger.length)
            this.indexOfTail = 0;

        this.count++;

        return true;
    }

    public Integer remove() {
        if (this.isEmpty())
            return null;

        Integer head = this.arrayOfInteger[this.indexOfHead++];
        if (this.indexOfHead == this.arrayOfInteger.length)
            this.indexOfHead = 0;

        this.count--;

        return head;
    }

    public int inquireSize() {
        return this.count;
    }

    public Integer peek() {
        if (this.isEmpty())
            return null;

        return this.arrayOfInteger[this.indexOfHead];
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return this.count == SIZE;
    }

    @Override
    public String toString() {
        String message = "";
        message += "Head index = " + this.indexOfHead + "\n";
        message += "Tail index = " + this.indexOfTail + "\n";
        message += "Size = " + this.count + "/" + SIZE + "\n";

        int indexCurrent = this.indexOfHead;
        for (int i = 0; i < this.count; i++) {
            message += this.arrayOfInteger[indexCurrent++] + "\n";
            if (indexCurrent == this.arrayOfInteger.length)
                indexCurrent = 0;
        }

        return message;
    }
}
```

## Simulation

以銀行排隊為背景，作 Priority Queue 應用：

```java
public class Customer {
    private int timeArrival;

    public int getTimeArrival() {
        return this.timeArrival;
    }

    public void setTimeArrival(int input) {
        this.timeArrival = input;
    }
}
```

```java
public class Teller {
    private int type;

    private double salaryPerHour;

    private int transactionTime;

    private int timeStart;

    private int timeFinish;

    private boolean isBusy;

    private Customer customerCurrent;

    private int numberOfCustomerServed;

    public Teller(int type, double salary, int transactionTime) {
        this.type = type;
        this.salary = salary;
        this.transactionTime = transactionTime;
        this.numberOfCustomerServed = 0;
    }

    public void setCustomerCurrent(Customer input) {
        this.customerCurrent = input;
    }

    public Customer getCustomerCurrent() {
        return this.customerCurrent;
    }

    public setType(int input) {
        this.type = input;
    }

    ...Setters and Getters
}
```

```java
public class BankSimulator {
    private int timeCurrent;

    private int timeMax;

    private int numberOfTeller;

    private Teller[] arrayOfTeller;

    private int timeNextArrival;

    private int timeAverageArrival;

    private LinkedListQueue<Customer> linkedListOfCustomer;

    private int maxQueueSize;

    private int customerServed;

    private int numberOfTotalCustomer;

    private int getTimeNextArrival() {
        Random dice = new Random();

        return this.timeCurrent + (dice.nextInt(this.timeAverageArrival * 2) + 1);
    }

    public void setup() {
        this.timeCurrent = 0;
        /* 8 hours. */
        this.timeMax = 480;
        this.numberOfTeller = 3;
        this.timeAverageArrival = 3;
        this.linkedListOfCustomer = new linkedListOfCustomer<Customer>();
        this.numberOfTotalCustomer = 0;
        this.maxQueueSize = 0;
        this.customerServed = 0;
        this.arrayOfTeller = new Teller[this.numberOfTeller];
        this.arrayOfTeller = new Teller(0, 15.00, 6);
        this.arrayOfTeller = new Teller(1, 18.00, 4);
        this.arrayOfTeller = new Teller(2, 21.00, 2);
    }

    public void run() {
        /* Arrival time for the first customer. */
        this.timeNextArrival = this.getTimeNextArrival();
        for (this.timeCurrent = 0; this.timeCurrent < this.timeMax; this.timeCurrent++) {
            for (int i = 0; i < this.numberOfTeller; i++) {
                Teller teller = this.arrayOfTeller[i];
                if (teller.isBusy()) {
                    /* See if the teller is done. */
                    if (teller.getTimeFinish() <= this.timeCurrent) {
                        this.customerServed++;
                        this.arrayOfTeller[i].setNumberOfCustomerServed(this.arrayOfTeller[i].getNumberOfCustomerServed()++);
                        this.arrayOfTeller[i].setBusy(false);
                    }
                }
            }

            /* 在 Customer 到達後更新下一個到達的時間 */
            if (this.timeCUrrent == this.timeNextArrival) {
                Customer customerNew = new Customer();
                customerNew.setTimeArrival(this.timeCurrent);
                this.linkedListOfCustomer.add(customerNew);
                this.numberOfTotalCustomer++;
                if (this.linkedListOfCustomer.inquireSize() > this.maxQueueSize)
                    this.maxQueueSize = this.linkedListOfCustomer.inquireSize()
                this.timeNextArrival = this.getTimeNextArrival();
            }

            for (int i = 0; i < this.numberOfTeller; i++) {
                if (this.linkedListOfCustomer.inquireSize() == 0)
                    break;

                /* 代表有 Customer 在 Queue 中，需要被服務 */
                if (!this.arrayOfTeller[i].isBusy()) {
                    Customer customer = this.linkedListOfCustomer.remove();
                    this.arrayOfTeller[i].setCustomer(customer);
                    this.arrayOfTeller[i].setBusy(true);
                    this.arrayOfTeller[i].setTimeFinish(this.timeCurrent + this.arrayOfTeller[i].getTimeTransaction());
                }
            }
        }
    }

    public void report() {
        System.out.println("\n\tSimulation Complete\n");
        System.out.println("Total Customer: " + this.numberOfTotalCustomer);
        System.out.println("Customer Served: " + this.customerServed);
        System.out.println("Max Customers in Queue: " + this.maxQueueSize);
        System.out.println("Teller Performance: ");
        for (int i = 0; i < this.numberOfTeller; i++) {
            System.out.println("\tTeller " + i + ": " + this.arrayOfTeller[i].getNumberOfCustomerServed());
        }
    }
}
```
