/** Linked list version queue. */
public class Queue<T> {
    /** Head node. */
    private Node<T> head;

    /** Tail node. */
    private Node<T> tail;

    /** Number of nodes. */
    private int count;

    /** Default constructor. */
    public Queue() {
    }

    /**
     * Add a data to head. The method will wrap the data into a node and point it as
     * head.
     * 
     * @param data {@code T} Data.
     */
    public void enqueue(T data) {
        if (this.seeEmpty()) {
            this.head = this.tail = new Node<T>(data);
            this.count = 1;
            return;
        }

        Node<T> node = new Node<T>(data);
        node.setNext(this.head);
        this.head = node;
        this.count++;
    }

    /**
     * Loop through the queue to find the tail node and pop it out of the queue.
     * 
     * @return {@code Node<T>} Current tail node.
     */
    public Node<T> dequeue() {
        if (this.seeEmpty())
            return null;

        // Only one node.
        if (this.head.getNext() == null) {
            Node<T> cache = this.head;
            this.head = this.tail = null;
            return cache;
        }

        // More than one node.
        Node<T> cache = this.head;
        Node<T> previous = null;
        while (cache.getNext() != null) {
            previous = cache;
            cache = cache.getNext();
        }
        previous.setNext(null);
        this.tail = previous;

        return cache;
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
     * See if the queue is empty.
     * 
     * @return {@code boolean} True if the queue is empty.
     */
    public boolean seeEmpty() {
        return this.head == null;
    }
}
