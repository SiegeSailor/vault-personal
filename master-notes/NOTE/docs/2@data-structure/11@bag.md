---
sidebar_position: 11
---

# Bag

又稱 Collection 或 Container Class。**不保證** 取出的順序。以下使用 `int` 作為 Data Type，但實務應用 Generic Type。此 Bag 使用 Array 建立：

```java
public class ArrayBag {
    static final int DEFAULT_SIZE = Integer.MAX_VALUE;

    private int count;

    private int capacity;

    private int[] arrayOfData;

    private int search(int input) {
        for (int index = 0; index < this.count; index++) {
            if (this.arrayOfData[index] == input) return index;
        }

        return -1;
    }

    private void resizeLoop() {
        int resizedCapacity = this.capacity + DEFAULT_SIZE;

        int[] arrayOfResizedData = new int[this.capacity + DEFAULT_SIZE];
        for (int index = 0; index < this.count; index++) {
            arrayOfResizedData[index] = arrayOfData[index];
        };

        this.arrayOfData = arrayOfResizedData;
        this.capacity = this.capacity + DEFAULT_SIZE;
    }

    private void resizeSystem() {
        int resizedCapacity = this.capacity + DEFAULT_SIZE;

        int[] arrayOfResizedData = new int[this.capacity + DEFAULT_SIZE];

        System.arraycopy(arrayOfData, 0, arrayOfResizedData, 0, count);
        this.capacity = this.capacity + DEFAULT_SIZE;
    }

    public ArrayBag() {
        this(DEFAULT_SIZE);
    }

    public ArrayBag(int initialCapacity) {
        this.capacity = initialCapacity;
        this.arrayOfData = new int[this.capacity];
    }

    public void add(int input) {
        if (this.isFull()) this.resizeLoop();
        arrayOfData[count++] = input;
    }

    public void addMany(int ... arrayOfInput) {
        for (int input : arrayOfInput) {
            this.add(input);
        }
    }

    public boolean deleteOne(int input) {
        int location = this.search(input);
        if (location < 0) return false;

        arrayOfData[location] = arrayOfData[count - 1];
        arrayOfData[--count] = 0;
        return true;
    }

    public int deleteAll(int input) {
        int count = 0;
        while(deleteOne(input)) {
            count++;
        }

        return count;
    }

    public boolean isExist(int input) {
        return (this.search(input) >= 0);
    }

    public boolean isFull() {
        return (this.count == this.capacity);
    }

    public boolean isEmpty() {
        return (this.count == 0);
    }

    public void clear() {
        this.count = 0;
    }

    public void addBag(Bag that) {
        for (int index = 0; index < that.count; index++) {
            this.add(that.arrayOfData[index]);
        }

        that.clear();
    }

    public String toString(){
        String message = "";

        for (int index = 0; index < this.capacity; index++) {
            message += "Data on position " + index + " = " + arrayOfData[index] + "\n";
        }
        return message;
    }
}
```

## Linked-List

首先，將 ArrayBag 的 Interface 取出：

```java
interface Bag {
    void add(int input);

    void addMany(int ... arrayOfInput);

    boolean deleteOne(int input);

    int deleteAll(int input);

    boolean isExist(int input);

    boolean isFull();

    boolean isEmpty();

    void clear();

    void addBag(Bag that);
}
```

接著使用 Linked List 來建立 Bag。而我們需要 Node 來建立 Linked List：

```java
public class Node {
    private int data;

    private Node next;

    public void setData(int input) {
        this.data = input;
    }

    public int getData() {
        return this.data;
    }

    public void setNext(Node input) {
        this.next = input;
    }

    public Node getNext() {
        return this.next;
    }
}
```

```java
public class LinkedListBag implements Bag {
    private Node head;

    private int count;

    void add(int input) {
        Node node = new Node();
        node.setData(input);
        node.setNext(this.head);
        this.head = node;

        return true;
    }

    void addMany(int ... arrayOfInput)

    boolean deleteOne(int input) {
        Node current = this.head;
        Node previous = null;

        while(current != null) {
            if (current.getData() == input) {
                Node previous = current;
                previous.setNext(current.getNExt);
                current = null;

                return true;
            }
        }

        return false;
    }

    int deleteAll(int input)

    boolean isExist(int input)

    boolean isFull()

    boolean isEmpty()

    void clear()

    void addBag(Bag that)
}
```
