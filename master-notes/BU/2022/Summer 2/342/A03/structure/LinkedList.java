import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable linked list class.
 * 
 * @author Zhang, Jin Yu
 */
public class LinkedList<T> implements Iterable<Node<T>> {
    /** Head node. */
    private Node<T> head;

    /** Default constructor. */
    public LinkedList() {
    }

    /**
     * Append an existing node to this linked list.
     * 
     * @param node {@code Node} A node.
     */
    public void append(Node<T> node) {
        // First time to append.
        if (this.seeEmpty()) {
            this.head = node;
            return;
        }

        Node<T> cache = this.head;
        // Use previous to store the tail node if we need to append the node at the end.
        // Remove this and traverse again when we need to append the node at the end
        // when we have less space but time doesn't matter.
        Node<T> previous = null;
        while (cache != null) {
            // See if there is a duplicated node exist.
            if (cache.getData().equals(node.getData())) {
                cache.addCount(node.getCount());
                return;
            }
            previous = cache;
            cache = cache.getNext();
        }

        // Append the node at the end.
        previous.setNext(node);
    }

    /**
     * Overloading append. Instead of append a node, create a node with given data
     * and count then append it.
     * 
     * @param data  {@code String} A string.
     * @param count {@code T} Existing count.
     */
    public void append(T data, int count) {
        this.append(new Node<T>(data, count));
    }

    /**
     * Overloading append. Instead of append a node, create a node with given data
     * then append it.
     * 
     * @param data {@code String} A string.
     */
    public void append(T data) {
        this.append(new Node<T>(data));
    }

    /**
     * See if the linked list is empty.
     * 
     * @return {@code boolean} True if the head node is null. The rest of nodes
     *         would be collected by Java Garbage Collection since the head node is
     *         the only place to point to the rest.
     */
    public boolean seeEmpty() {
        return (this.head == null);
    }

    /**
     * Get the head node.
     * 
     * @return {@code Node<T>} The head node.
     */
    public Node<T> getHead() {
        return this.head;
    }

    /**
     * Use iterator to append all node's information by taking advantage their
     * toString method.
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        String message = "";
        if (this.seeEmpty()) {
            message = "<EMPTY>";
        } else {
            Iterator<Node<T>> iterator = this.iterator();
            while (iterator.hasNext()) {
                message += iterator.next() + "\n";
            }
        }

        return message;
    }

    /**
     * Iterator class for linked list.
     * 
     * @author Zhang, Jin Yu
     */
    public class LinkedListIterator implements Iterator<Node<T>> {
        /** Current node. */
        private Node<T> iterator;

        /** Constructor. Point iterator to the head node of the list. */
        public LinkedListIterator() {
            this.iterator = head;
        }

        /**
         * Check if the current node is null.
         * 
         * @return {@code boolean} True if the current is not null.
         */
        @Override
        public boolean hasNext() {
            return this.iterator != null;
        }

        /**
         * Get the current node.
         * 
         * @return {@coe Node<T>} The current node.
         */
        @Override
        public Node<T> next() throws NoSuchElementException {
            if (iterator == null) {
                throw new NoSuchElementException();
            } else {
                Node<T> node = iterator;
                iterator = iterator.getNext();
                return node;
            }
        }

    }

    /**
     * Clear the list by removing the head node, which utilizes Java Garbage
     * Collection.
     */
    public void clear() {
        this.head = null;
    }

    /**
     * Get iterator.
     * 
     * @return {@code Iterator<Node<T>>} A iterator.
     */
    @Override
    public Iterator<Node<T>> iterator() {
        return new LinkedListIterator();
    }
}
