public class Queue<T> {
    private Node<T> head;

    private Node<T> tail;

    private int count;

    /** Default constructor. */
    public Queue() {
    }

    public void enqueue(T data) {
        if (this.seeEmpty()) {
            this.head = this.tail = new Node<T>(data);
            this.count = 1;
            return;
        }

        Node<T> node = new Node<T>(data);
        node.setNext(this.head);
        this.head = node;
    }

    public Node<T> dequeue() {
        if (this.seeEmpty())
            return null;

        Node<T> cache = this.head;
        Node<T> previous = null;
        while (cache.getNext() != null) {
            previous = cache;
            cache = cache.getNext();
        }

        previous.setNext(null);

        return cache;
    }

    public Node<T> getTop() {
        return this.head;
    }

    public boolean seeEmpty() {
        return this.head == null;
    }
}
