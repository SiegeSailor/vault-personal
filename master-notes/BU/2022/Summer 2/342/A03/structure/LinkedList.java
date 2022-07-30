/**
 * Linked list class.
 * 
 * @author Zhang, Jin Yu
 */
public class LinkedList<T> {
    /** Head node. */
    private Node<T> head;

    /** Tail node. */
    private Node<T> tail;

    /** Counter. */
    private int count;

    /**
     * Clone the list. All the nodes and list will be different from the original
     * one.
     * 
     * @param current {@code Node<T>} Start node.
     * @param list    {@code LinkedList<T>} List to return.
     * @return {@code LinkedList<T>} Same as parameter list. Not as same as the
     *         original one.
     */
    private LinkedList<T> clone(Node<T> current, LinkedList<T> list) {
        if (current == null)
            return list;

        list.addLast(new Node<T>(current.getData(), current.getCount()));
        return this.clone(current.getNext(), list);
    }

    /** Default constructor. */
    public LinkedList() {
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
     * Get the tail node.
     * 
     * @return {@code Node<T>} The tail node.
     */
    public Node<T> getTail() {
        return this.tail;
    }

    /**
     * Add a node to the start of the linked list.
     * 
     * @param node {@code Node<T>} A node.
     */
    public void addFirst(Node<T> node) {
        // First time to add.
        if (this.seeEmpty()) {
            this.head = this.tail = node;
            this.count = 1;
            return;
        }

        Node<T> cache = this.getHead();
        while (cache != null) {
            // See if there is a duplicated node exist.
            if (cache.getData().equals(node.getData())) {
                cache.addCount(node.getCount());
                return;
            }
            cache = cache.getNext();
        }

        // Add the node at start.
        this.getHead().setPrevious(node);
        node.setNext(this.getHead());
        this.head = node;
        this.count++;
    }

    /**
     * Overloading addFirst. Instead of adding a node, creating a node with given
     * data and count then add it.
     * 
     * @param data  {@code T} Data.
     * @param count {@code int} Existing count.
     */
    public void addFirst(T data, int count) {
        this.addFirst(new Node<T>(data, count));
    }

    /**
     * Overloading addFirst. Instead of adding a node, creating a node with given
     * data then add it.
     * 
     * @param data {@code T} Data.
     */
    public void addFirst(T data) {
        this.addFirst(new Node<T>(data));
    }

    /**
     * Add a node to the end of the linked list.
     * 
     * @param node {@code Node} A node.
     */
    public void addLast(Node<T> node) {
        // First time to add.
        if (this.seeEmpty()) {
            this.head = this.tail = node;
            this.count = 1;
            return;
        }

        Node<T> cache = this.getHead();
        while (cache != null) {
            // See if there is a duplicated node exist.
            if (cache.getData().equals(node.getData())) {
                cache.addCount(node.getCount());
                return;
            }
            cache = cache.getNext();
        }

        // add the node at the end.
        this.getTail().setNext(node);
        node.setPrevious(this.getTail());
        this.tail = node;
        this.count++;
    }

    /**
     * Overloading addLast. Instead of adding a node, creating a node with given
     * data and count then add it.
     * 
     * @param data  {@code T} Data.
     * @param count {@code int} Existing count.
     */
    public void addLast(T data, int count) {
        this.addLast(new Node<T>(data, count));
    }

    /**
     * Overloading addLast. Instead of adding a node, creating a node with given
     * data then add it.
     * 
     * @param data {@code T} Data.
     */
    public void addLast(T data) {
        this.addLast(new Node<T>(data));
    }

    /**
     * Remove the first element and return it. The previous and next files in the
     * node will be {@code null}.
     * 
     * @return {@code Node<T>} First node.
     */
    public Node<T> removeFirst() {
        if (this.seeEmpty())
            return null;

        // Only one node.
        if (this.getHead().getNext() == null) {
            Node<T> cache = this.getHead();
            this.clear();
            return cache;
        }

        // More than one node.
        Node<T> cache = this.getHead();
        Node<T> next = cache.getNext();
        next.setPrevious(null);
        cache.setNext(null);
        this.head = next;
        this.count--;
        return cache;
    }

    /**
     * Remove the last element and return it. The previous and next files in the
     * node will be {@code null}.
     * 
     * @return {@code Node<T>} Last node.
     */
    public Node<T> removeLast() {
        if (this.seeEmpty())
            return null;

        // Only one node.
        if (this.getTail().getPrevious() == null) {
            Node<T> cache = this.getTail();
            this.clear();
            return cache;
        }

        // More than one node.
        Node<T> cache = this.getTail();
        Node<T> previous = cache.getPrevious();
        previous.setNext(null);
        cache.setPrevious(null);
        this.tail = previous;
        this.count--;
        return cache;
    }

    /**
     * See if the linked list is empty.
     * 
     * @return {@code boolean} True if the head node is null. The rest of nodes
     *         would be collected by Java Garbage Collection since the head node is
     *         the only place to point to the rest.
     */
    public boolean seeEmpty() {
        return this.getCount() == 0;
    }

    /**
     * Get count.
     * 
     * @return {@code int} Number of nodes.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Clear the list by removing the head node and tail node, which utilizes Java
     * Garbage Collection.
     */
    public void clear() {
        this.head = this.tail = null;
        this.count = 0;
    }

    /**
     * Clone the list.
     * 
     * @return {@code LinkedList<T>} List that different than the original one.
     */
    @Override
    public LinkedList<T> clone() {
        return this.clone(this.getHead(), new LinkedList<T>());
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
            Node<T> cache = this.head;
            while (cache != null) {
                message += cache + "\n";
                cache = cache.getNext();
            }
        }

        return message;
    }
}
