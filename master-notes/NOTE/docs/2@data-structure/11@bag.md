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
        while(this.deleteOne(input)) {
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

    public void addBag(ArrayBag that) {
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

## Singly-Linked List

首先，將 ArrayBag 的 Interface 取出。注意 `addBag` 不在內，因爲我們需要對 Linked List 獨有的 `head` 進行操作：

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
}
```

接著使用 Singly-Linked List 來建立 Bag。而我們需要 Node 來建立 Singly-Linked List：

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

Bag 本身：

```java
public class SinglyLinkedListBag implements Bag {
    private Node head;

    private int count;

    public void add(int input) {
        Node node = new Node();
        node.setData(input);
        node.setNext(this.head);
        this.head = node;

        return true;
    }

    public void addMany(int ... arrayOfInput) {
        for (int input : arrayOfInput) {
            this.add(input);
        }
    }

    public boolean deleteOne(int input) {
        Node current = this.head;
        Node previous = null;

        while(current != null) {
            if (current.getData() == input) {
                if (previous == null) {
                    this.head = this.head.getNext();
                    this.count--;
                    return true;
                }

                previous.setNext(current.getNext);
                current--;

                return true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        return false;
    }

    public int deleteAll(int input) {
        int count;
        while(this.deleteOne(input)) {
            count++;
        }

        return count;
    }

    public boolean isExist(int input) {
        Node cache = this.head;
        while (cache != null) {
            if (cache.getData() == input) {
                return true;
            }

            cache = cache.getNext();
        }

        return false;
    }

    /** Depends on memory amount */
    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return (this.count == 0);
    }

    public void clear() {
        this.count = 0;
        this.head = null;
    }

    public void addBag(SinglyLinkedListBag that) {
        Node cache = that.head;
        while(cache != null) {
            this.add(cache.getData());

            cache = cache.getNext();
        }

        that.clear();
    }

    public void addBagTraversal(SinglyLinkedListBag that) {
        Node cache = this.head;
        while (cache != null && cache.getNext() != null) {
            cache = cache.getNext();
        }

        if (cache != null) {
            cache.setNext(that.head);
            this.count += that.count;
        } else {
            this.head = that.head;
            count = that.count;
        }

        that.head = null;
        that.count = 0;
    }

    public String toString(){
        String message = "";
        Node cache = this.head;
        while(cache != null) {
            message += "-> " + cache.getData() + "\n";

            cache = cache.getNext();
        }

        return message;
    }
}
```

## Doubly-Linked List

接著使用 Doubly-Linked List 來建立 Bag。而我們需要 Node 來建立 Doubly-Linked List。注意這邊使用 Generic Type：

```java
public class Node<T> {
    private T data;

    private Node<T> next;

    private Node<T> previous;

    public void setData(T input) {
        this.data = input;
    }

    public T getData() {
        return this.data;
    }

    public void setNext(Node<T> input) {
        this.next = input;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setPrevious(Node<T> input) {
        this.next = input;
    }

    public Node<T> getPrevious() {
        return this.next;
    }
}
```

Bag 本身：

```java
public class DoublyLinkedListBag<T> implements Bag {
    private Node<T> head;

    private Node<T> tail;

    private int count;

    private Node<T> search(T input) {
        Node<T> cache = this.head;

        while(cache != null) {
            if (cache.getData().equals(input)) return cache;

            cache = cache.getNext();
        }

        return null;
    }

    void add(T input) {
        Node<T> node = new Node<T>();
        node.setData(input);

        if (this.count == 0) {
            this.head = this.tail = node;
            return true;
        }

        node.setNext(this.head);
        this.head.setPrevious(node);
        this.head = node;
        this.count++;

        return true;
    }

    /** Possibly head pollution */
    void addMany(T ... arrayOfInput) {
        for (T input : arrayOfInput) {
            this.add(input);
        }
    }

    boolean deleteOne(T input) {
        Node<T> cache = this.search(input);
        if (cache != null) {
            if (this.count == 1) {
                this.head = this.tail = null;
                this.count = 0;
                return true;
            }

            /** Compare memory address */
            if (cache == this.head) {
                this.head = this.head.getNext();
                this.head.setPrevious(null);
                this.count--;
                return true;
            }

            /** Compare memory address */
            if (cache == this.tail) {
                this.tail = this.getPrevious();
                this.tail.setNext(null);
                this.count--;
                return true;
            }

            cache.getPrevious().setNext(cache.getNext());
            cache.getNext().setPrevious(cache.getPrevious());
            this.count--;
            return true;
        }

        return false;
    }

    int deleteAll(T input) {
        int count;
        while(this.deleteOne(input)) {
            count++;
        }

        return count;
    }

    boolean isExist(T input) {
        return (this.search(input) != null);
    }

    /** Depends on memory amount */
    boolean isFull() {
        return false;
    }

    boolean isEmpty() {
        return (this.count == 0);
    }

    void clear() {
        this.count = 0;
        this.head = this.tail = null;
    }

    void addBag(DoublyLinkedListBag that) {
        Node<T> cache = that.head;
        while(cache != null) {
            add(cache.getData());

            cache = cache.getNext();
        }

        that.clear();
    }

    public String toString(){
        String message = "";
        Node<T> cache = this.head;
        while(cache != null) {
            message += "-> " + cache.getData() + "\n";

            cache = cache.getNext();
        }

        return message;
    }
}
```
