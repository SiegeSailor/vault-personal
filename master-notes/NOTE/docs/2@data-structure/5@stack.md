---
sidebar_position: 5
---

# Stack

FILO、First In Last Out，由 Linked List 構成，主要使用在注重順序的場合，如 Compiler 解析 Syntax、Infix Math to Post-Fix Conversion (Shunting Yard Algorithm)、Post-Fix Math to Value Evaluation 等。

:::info 提醒
Compiler 解析 Parentheses, Curly Braces, Square Brackets 等，就是一但兩個對上了，就從 Stack 中移除，所以最後被移除的會是最外面的符號。
:::

:::info 提醒
Infix Math 是為人類閱讀用，因為數學教育，我們能夠輕易辨認計算的順序 (Operator Precedence)，然而電腦無法、進而需要用 Post-Fix (RPN) 來解析。以下比較各式表達：

| Math     | Serial        |
| -------- | ------------- |
| Infix    | `3`, `+`, `5` |
| Post-FIx | `3`, `5`, `+` |
| Prefix   | `+`, `3`, `5` |

:::

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

使用 Java Array 實作：

```java
public class ArrayStack {
    private static final int SIZE = 10;

    private Integer[] arrayOfStack;

    private int count;

    public ArrayStack() {
        this.arrayOfStack = new Integer[SIZE];
        this.count = 0;
    }

    public boolean push(Integer input) {
        /** Don't allow stack overflow */
        if (this.isFull()) return false;

        this.arrayOfStack[this.count++] = input;
        return true;
    }

    public Integer pop() {
        /** Don't allow stack underflow */
        if (this.isEmpty()) return null;

        return this.arrayOfStack[--this.count];
    }

    public int size() {
        return this.count;
    }

    public int getCapacity() {
        return this.arrayOfStack.length;
    }

    public Integer peak() {
        return this.arrayOfStack[this.count - 1];
    }

    public void clear() {
        this.count = 0;

        /** Not simple but more semantic */
        while(!this.isEmpty()) {
            this.pop();
        }
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return this.count == this.arrayOfStack.length;
    }

    public String toString() {
        String message = "";

        if (this.count == 0)
            message += "Empty array stack.\n";

        for (int index = this.count - 1; index >= 0; index--) {
            if (index == (this.count - 1)) {
                message += "    Stack top:";
            } else {
                message += "    ";
            }
            message += (this.arrayOfStack[index] + "\n");
        }

        return message;
    }
}
```

接著利用 Java Linked List 實作。Interface 與 Array 一樣：

```java
public class Node<T> {
    private T data;

    private Node<T> next;

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
}
```

```java
import java.util.Scanner;

public class LinkedListStack<T> {
    private Node<T> head;

    private int count;

    public boolean push(T input) {
        Node<T> node = new Node<T>();
        node.setData(input);
        node.setNext(this.head);
        this.head = node;
        this.count++;

        return true;
    }

    public T pop() {
        /** Don't allow stack underflow */
        if (this.isEmpty()) return null;

        T data = this.head.getData;
        this.head = this.head.getNext();
        this.count--;

        return data;
    }

    public int size() {
        return this.count;
    }

    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    public T peak() {
        return this.head.getData();
    }

    public void clear() {
        this.count = 0;
        this.head = null;
    }

    public boolean isEmpty() {
        return (this.count == 0) || (this.head == null);
    }

    public boolean isFull() {
        return false;
    }

    public String toString() {
        String message = "";

        if (this.count == 0)
            message += "Empty array stack.\n";

        Node<T> cache = this.head;
        /** No need to reverse since the incoming node will always be the head */
        for (int index = 0; index < this.count; index++) {
            if (index == 0) {
                message += "    Stack top:";
            } else {
                message += "    ";
            }
            message += (cache.getData() + "\n");
            cache = cache.getNext();
        }

        return message;
    }

    /** RPN Calculator implantation */
    static void main(String[] arguments) {
        LinkedListStack<Double> linkedListStack = new LinkedListStack<Double>();

        boolean isDone = false;
        Scanner keyboard = new Scanner(System.in);
        String input, inputMapped;
        while(!isDone) {
            System.out.println("Enter command (? for help)\n -> ");
            input = keyboard.nextLine();
            inputMapped = input.toUpperCase();

            switch(inputMapped) {
                case "Q":
                    isDone = true;
                    break;
                case "?":
                case "H":
                    System.out.println("Help goes here.");
                    break;
                case "+":
                    break;
                case "*":
                    break;
                case "-":
                    break;
                /** Should be single / due to syntax highlighting */
                case "\\":
                    break;
                case "C":
                    break;
                case "P":
                    break;
                case "D":
                    break;
                default:
                    Double value;
                    try {
                        value = Double.parseDouble(input);
                        linkedListStack.push(value);
                    } catch (NumberFormatException event) {
                        System.out.println("Invalid command (" + input + ")");
                    }
                    break;
            }
        }
    }
}
```
