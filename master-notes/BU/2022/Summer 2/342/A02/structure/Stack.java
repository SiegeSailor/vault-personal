package structure;

/**
 * Stack made with linked list.
 * 
 * @author Zhang, Jin Yu
 */
public class Stack<T> {
    /** Head node. */
    private Node<T> head;

    /** Total number of nodes. */
    private int count;

    /** Max number of nodes. */
    private int size;

    public Stack(int initialSize) {
        this.size = initialSize;
    }

    public int getSize() {
        return this.size;
    }

    public int getCount() {
        return this.count;
    }

    public void clear() {
        this.head = null;
        this.count = 0;
    }

    public boolean isFull() {
        return this.getCount() == this.getSize();
    }

    public boolean isEmpty() {
        return this.head == null || this.count == 0;
    }
}
