import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iterable linked list class. */
public class LinkedList<T> implements Iterable<T> {
    /** Head node. */
    private Node<T> head;

    /** Default constructor. */
    public LinkedList() {
    }

    /**
     * Construct a linked list with given array of data.
     * 
     * @param arrayOfInput {@code T[]} Array of data.
     */
    public LinkedList(T[] arrayOfInput) {
        for (T input : arrayOfInput) {
            this.add(input);
        }
    }

    /**
     * Append an existing node to the end of this linked list. The node will be the
     * head node if there is no head node.
     * 
     * @param node {@code Node<T>} A node.
     */
    public void add(Node<T> node) {
        /* First time to add */
        if (this.isEmpty()) {
            this.head = node;
            return;
        }

        /* Append the node at the end. */
        this.getTail().setNext(node);
    }

    /**
     * Overloading add. Instead of add a node, create a node with given data
     * then add it.
     * 
     * @param data {@code T} Content.
     */
    public void add(T data) {
        this.add(new Node<T>(data));
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
     * Loop through the whole linked list to get the last element.
     * 
     * @return The tail node.
     */
    public Node<T> getTail() {
        if (this.isEmpty())
            return null;

        Node<T> cache = this.head;
        Node<T> previous = null;
        while (cache != null) {
            previous = cache;
            cache = cache.getNext();
        }

        return previous;
    }

    /** Delete the last element. */
    public void delete() {
        if (isEmpty())
            return;

        // If there is only one node
        if (this.getHead().getNext() == null) {
            this.head = null;
            return;
        }

        Node<T> cache = this.head;
        while (cache.getNext().getNext() != null) {
            cache = cache.getNext();
        }

        cache.setNext(null);
    }

    /**
     * See if the linked list is empty.
     * 
     * @return {@code boolean} True if the head node is null. The rest of nodes
     *         would be collected by Java Garbage Collection since the head node is
     *         the only place to point to the rest.
     */
    public boolean isEmpty() {
        return (this.head == null);
    }

    /**
     * Clear the list by removing the head node, which utilize Java Garbage
     * Collection.
     */
    public void clear() {
        this.head = null;
    }

    /**
     * Use iterator to loop all node's information by taking advantage their
     * toString method.
     * 
     * @return {@code String} A string representation.
     */
    @Override
    public String toString() {
        String message = "";
        if (isEmpty()) {
            message = "Empty linked list.";
        } else {
            Iterator<T> iterator = this.iterator();
            while (iterator.hasNext()) {
                T data = iterator.next();
                message += data;
                if (iterator.hasNext())
                    message += " -> ";
            }
        }

        return message;
    }

    /** Iterator for linked list. */
    public class LinkedListIterator<I> implements Iterator<I> {
        /** Current node. */
        private Node<I> iterator;

        /** Constructor. Point iterator to the head node of the list. */
        @SuppressWarnings("unchecked")
        public LinkedListIterator() {
            this.iterator = (Node<I>) head;
        }

        /**
         * Check if the current node is null.
         * 
         * @return {@code boolean} True if the current node is not null.
         */
        @Override
        public boolean hasNext() {
            return this.iterator != null;
        }

        /**
         * Get the data of the current node.
         * 
         * @return {@code I} Current node data.
         */
        @Override
        public I next() throws NoSuchElementException {
            if (this.iterator == null) {
                throw new NoSuchElementException();
            } else {
                I data = this.iterator.getData();
                this.iterator = this.iterator.getNext();
                return data;
            }
        }

    }

    /**
     * Get iterator.
     * 
     * @return {@code Iterator<T>} Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>();
    }
}
