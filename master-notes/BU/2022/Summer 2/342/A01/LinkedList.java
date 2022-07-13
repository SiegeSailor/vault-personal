import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable<String> {
    private int numberOfNode;

    private Node longest;

    private Node occurMost;

    private Node head;

    public void append(Node node) {
        /** First time to append */
        if (this.isEmpty()) {
            this.head = this.longest = this.occurMost = node;
            this.numberOfNode++;
            return;
        }

        /** See if there is a duplicated node exist */
        Node cache = this.head;
        Node previous = null;
        while (cache != null) {
            if (cache.getData().equals(node.getData())) {
                this.numberOfNode++;
                cache.addCount(node.getCount());
                if (cache.getCount() > this.occurMost.getCount())
                    this.occurMost = cache;
                return;
            }
            previous = cache;
            cache = cache.getNext();
        }

        /** Append the data at the end */
        if (node.getLength() > this.longest.getLength())
            this.longest = node;
        previous.setNext(node);
        this.numberOfNode++;
    }

    public void append(String data, int count) {
        this.append(new Node(data, count));
    }

    public void append(String data) {
        this.append(new Node(data));
    }

    /**
     * Create a new linked list which contains nodes with corresponding data.
     * 
     * @param arrayOfData {@code String...} Wanted array of data. Should not have
     *                    duplicated data.
     * @return A linked list contains corresponding nodes. The linked list could be
     *         empty if there is no matched nodes.
     */
    LinkedList search(String... arrayOfData) {
        LinkedList linkedList = new LinkedList();
        if (this.isEmpty())
            return linkedList;

        Node cache = this.head;
        boolean isEveryFound = false;
        while (cache != null && !isEveryFound) {
            for (String data : arrayOfData) {
                if (cache.getData().equals(data)) {
                    linkedList.append(cache.getData(), cache.getCount());
                    break;
                }
            }

            isEveryFound = linkedList.getNumberOfNode() == arrayOfData.length;
            cache = cache.getNext();
        }

        return linkedList;
    }

    /**
     * Create a new linked list which contains nodes with count higher than
     * countBase.
     * 
     * @param countBase {@code int} Bottom line for count.
     * @return A linked list contains corresponding nodes. The linked list could be
     *         empty if there is no matched nodes.
     */
    LinkedList search(int countBase) {
        LinkedList linkedList = new LinkedList();
        if (this.isEmpty())
            return linkedList;

        Node cache = this.head;
        while (cache != null) {
            if (cache.getCount() > countBase)
                linkedList.append(cache.getData());
            cache = cache.getNext();
        }

        return linkedList;
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    public int getNumberOfNode() {
        return this.numberOfNode;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getLongest() {
        return this.longest;
    }

    public Node getOccurMost() {
        return this.occurMost;
    }

    @Override
    public String toString() {
        String message = "";
        if (isEmpty()) {
            message = "Empty linked list.";
        } else {
            message += this.getNumberOfNode() + " nodes in this linked list:\n";
            Node cache = this.head;
            while (cache != null) {
                message += cache + "\n";
                cache = cache.getNext();
            }
        }

        return message;
    }

    public class LinkedListIterator implements Iterator<String> {
        private Node iterator;

        public LinkedListIterator() {
            this.iterator = head;
        }

        @Override
        public boolean hasNext() {
            return this.iterator != null;
        }

        @Override
        public String next() throws NoSuchElementException {
            if (iterator == null) {
                throw new NoSuchElementException();
            } else {
                String data = iterator.getData();
                iterator = iterator.getNext();
                return data;
            }
        }

    }

    public void clear() {
        this.numberOfNode = 0;
        this.head = this.longest = this.occurMost = null;
    }

    @Override
    public Iterator<String> iterator() {
        return new LinkedListIterator();
    }
}
