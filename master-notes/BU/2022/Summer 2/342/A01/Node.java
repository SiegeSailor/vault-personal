public class Node {
    private Node next;

    private String data;

    private int count;

    public Node(String data) {
        this.data = data;
        this.increaseCount();
    }

    public Node(String data, int count) {
        this.data = data;
        this.addCount(count);
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public String getData() {
        return this.data;
    }

    public int getLength() {
        return this.data.length();
    }

    public void addCount(int input) {
        this.count += input;
    }

    public void increaseCount() {
        this.addCount(1);
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()) + ": " + this.data + " (appear "
                + this.getCount() + " times, " + this.getLength() + " characters)";
    }
}
