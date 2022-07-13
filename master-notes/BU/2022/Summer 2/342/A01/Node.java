public class Node {
    private Node next;

    private String data;

    private int count;

    public Node(String data) {
        this.data = data;
        this.increaseCount();
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

    public void increaseCount() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()) + ": " + this.data + " ("
                + this.getLength() + " characters)";
    }
}
