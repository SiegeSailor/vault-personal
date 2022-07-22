package structure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack made with linked list.
 * 
 * @author Zhang, Jin Yu
 */
public class Stack<T> implements Iterable<Node<T>> {
    /** Head node. */
    private Node<T> head;

    /** Total number of nodes. */
    private int count;

    /** Max number of nodes. */
    private int size;

    /**
     * Construct a stack with initial size. There is no way to change a size of a
     * stack unless constructing a new one and append the old nodes to it.
     * 
     * @param initialSize {@code int} Initial size.
     */
    public Stack(int initialSize) {
        this.size = initialSize;
    }

    /**
     * Get the reference of the top element, the head node.
     * 
     * @return {@code Node<T>} The top element. Head node.
     */
    public Node<T> getTop() {
        return this.head;
    }

    /**
     * Push a node onto the top. Since the incoming node will be on the top, we can
     * get the reference of the node by using {@code getTop} afterward.
     * 
     * @param node {@code Node<T>} Incoming node.
     */
    public void push(Node<T> node) {
        node.setNext(this.head);
        this.head = node;

        this.count++;
    }

    /**
     * Return the top element and remove it from the stack. Could return
     * {@code null}.
     * 
     * @return {@code Node<T>} Head node.
     */
    public Node<T> pop() {
        if (this.seeEmpty())
            return null;

        Node<T> cache = this.head;
        this.head = this.head.getNext();

        this.count--;

        return cache;
    }

    /**
     * Get the size of this stack.
     * 
     * @return {@code int} Size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get the number of nodes of this stack.
     * 
     * @return {@code int} Number of nodes.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Clear data inside this stack by utilizing Java garbage collection. This
     * operation will not walk through all nodes and delete them. Nodes that have
     * reference from other place will still exist.
     */
    public void clear() {
        this.head = null;
        this.count = 0;
    }

    /**
     * See if this stack is full. This method is equivalent to:
     * <blockquote>
     * 
     * <pre>
     * list.getCount() == list.getSize();
     * </pre>
     * 
     * </blockquote>
     * 
     * @return {@code boolean} True if this stack is full.
     */
    public boolean seeFull() {
        return this.getCount() == this.getSize();
    }

    /**
     * See if this stack is empty.
     * 
     * @return {@code boolean} True if this stack is empty.
     */
    public boolean seeEmpty() {
        return this.head == null || this.count == 0;
    }

    /**
     * Return the string representation for each node.
     * 
     * @return {@code String} A string representation for each node.
     */
    @Override
    public String toString() {
        String message = "";
        if (this.seeEmpty()) {
            message = "<Empty Stack>";
        } else {
            Iterator<Node<T>> iterator = this.iterator();
            while (iterator.hasNext()) {
                Node<T> node = iterator.next();
                message += node;
                if (iterator.hasNext())
                    message += "\n";
            }
        }

        return message;
    }

    public class StackIterator<I> implements Iterator<Node<I>> {
        /** Current node. */
        private Node<I> iterator;

        /**
         * Constructor. Point iterator to the input node.
         * 
         * @param node {@code Node<I>} A node. Usually a head node of a linked list.
         */
        public StackIterator(Node<I> node) {
            this.iterator = node;
        }

        /**
         * Check if the current node is {@code null}.
         * 
         * @return {@code boolean} True if the current is {@code null}.
         */
        @Override
        public boolean hasNext() {
            return this.iterator != null;
        }

        /**
         * Get current node.
         * 
         * @return {@code Node<I>} The current node.
         */
        @Override
        public Node<I> next() throws NoSuchElementException {
            if (this.iterator == null) {
                throw new NoSuchElementException();
            } else {
                Node<I> cache = this.iterator;
                this.iterator = this.iterator.getNext();
                return cache;
            }
        }
    }

    /**
     * Get iterator.
     * 
     * @return {@code Iterator<Node<T>>} Iterator.
     */
    @Override
    public Iterator<Node<T>> iterator() {
        return new StackIterator<T>(this.head);
    }
}
