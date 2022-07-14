import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iterable linked list class. */
public class LinkedList implements Iterable<Node> {
    /** Total number of nodes (entries) in this linked list. */
    private int numberOfNode;

    /** Node with longest length of its data. */
    private Node longest;

    /** Node with highest count. */
    private Node occurMost;

    /** Head node. */
    private Node head;

    /** Default constructor. */
    public LinkedList() {
    }

    /**
     * Append an existing node to this linked list.
     * 
     * @param node {@code Node} A node.
     */
    public void append(Node node) {
        /* First time to append */
        if (this.isEmpty()) {
            this.head = this.longest = this.occurMost = node;
            this.numberOfNode++;
            return;
        }

        Node cache = this.head;
        /*
         * Use previous to store the tail node if we need to append the node at the end.
         * Remove this and traverse again when we need to append the node at the end
         * when we have less space but time doesn't matter.
         */
        Node previous = null;
        while (cache != null) {
            /* See if there is a duplicated node exist. */
            if (cache.getData().equals(node.getData())) {
                cache.addCount(node.getCount());
                /* Replace occurMost node if the incoming node has higher count. */
                if (cache.getCount() > this.occurMost.getCount())
                    this.occurMost = cache;
                return;
            }
            previous = cache;
            cache = cache.getNext();
        }

        /*
         * Only update longest node when appending a new node since data inside nodes
         * don't change.
         */
        if (node.getLength() > this.longest.getLength())
            this.longest = node;
        /* Append the node at the end. */
        previous.setNext(node);
        this.numberOfNode++;
    }

    /**
     * Overloading append. Instead of append a node, create a node with given data
     * and count then append it.
     * 
     * @param data  {@code String} A string.
     * @param count {@code int} Existing count.
     */
    public void append(String data, int count) {
        this.append(new Node(data, count));
    }

    /**
     * Overloading append. Instead of append a node, create a node with given data
     * then append it.
     * 
     * @param data {@code String} A string.
     */
    public void append(String data) {
        this.append(new Node(data));
    }

    /**
     * Create a new linked list which contains nodes with corresponding data.
     * 
     * @param arrayOfData {@code String...} Wanted array of data. If there is
     *                    duplicated data then the search will never stop early.
     * @return A linked list contains corresponding nodes. The linked list could be
     *         empty if there is no matched nodes.
     */
    LinkedList search(String... arrayOfData) {
        LinkedList linkedList = new LinkedList();
        if (this.isEmpty())
            return linkedList;

        Node cache = this.head;
        boolean isEveryFound = false;
        String[] arrayOfCopiedData = arrayOfData.clone();
        /* Check every node with every element in the given array. */
        while (cache != null && !isEveryFound) {
            for (String copiedData : arrayOfCopiedData) {
                /* Append the node to return linked list if matched. */
                if (cache.getData().equals(copiedData)) {
                    linkedList.append(cache.getData(), cache.getCount());

                    /* Remove found element from the given array. */
                    String[] arrayOfCacheData = new String[arrayOfCopiedData.length - 1];
                    int indexCacheData = 0;
                    for (String data : arrayOfCopiedData) {
                        if (!data.equals(copiedData)) {
                            arrayOfCacheData[indexCacheData] = data;
                            indexCacheData++;
                        }
                    }
                    arrayOfCopiedData = arrayOfCacheData;
                    break;
                }
            }

            /* Stop the while-loop early if we already found all elements. */
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
            /* Only append nodes that have count higher than countBase. */
            if (cache.getCount() > countBase)
                linkedList.append(cache.getData(), cache.getCount());
            cache = cache.getNext();
        }

        return linkedList;
    }

    /**
     * See if the linked list is empty.
     * 
     * @return True if the head node is null. The rest of nodes would be collected
     *         by Java Garbage Collection since the head node is the only place to
     *         point to the rest.
     */
    public boolean isEmpty() {
        return (this.head == null);
    }

    /**
     * Get number of nodes in the list.
     * 
     * @return Number of nodes.
     */
    public int getNumberOfNode() {
        return this.numberOfNode;
    }

    /**
     * Get the node with longest data.
     * 
     * @return A node with longest data in the list.
     */
    public Node getLongest() {
        return this.longest;
    }

    /**
     * Get the head node.
     * 
     * @return The head node.
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * Get the node with highest count.
     * 
     * @return A node with highest count.
     */
    public Node getOccurMost() {
        return this.occurMost;
    }

    /**
     * Use iterator to append all node's information by taking advantage their
     * toString method.
     */
    @Override
    public String toString() {
        String message = "";
        if (isEmpty()) {
            message = "Empty linked list.\n";
        } else {
            message += this.getNumberOfNode() + " nodes in this linked list:\n";

            Iterator<Node> iterator = this.iterator();
            while (iterator.hasNext()) {
                message += iterator.next() + ". ";
            }
        }

        return message;
    }

    /** Iterator for linked list. */
    public class LinkedListIterator implements Iterator<Node> {
        /** Current node. */
        private Node iterator;

        /** Constructor. Point iterator to the head node of the list. */
        public LinkedListIterator() {
            this.iterator = head;
        }

        /** Check if the current node is null. */
        @Override
        public boolean hasNext() {
            return this.iterator != null;
        }

        /** Get a clone (No next node) of the current node. */
        @Override
        public Node next() throws NoSuchElementException {
            if (iterator == null) {
                throw new NoSuchElementException();
            } else {
                /* Clone. */
                Node node = new Node(iterator.getData(), iterator.getCount());
                iterator = iterator.getNext();
                return node;
            }
        }

    }

    /**
     * Clear the list by removing the head node, which utilize Java Garbage
     * Collection.
     */
    public void clear() {
        this.numberOfNode = 0;
        this.head = this.longest = this.occurMost = null;
    }

    /** Get iterator. */
    @Override
    public Iterator<Node> iterator() {
        return new LinkedListIterator();
    }
}
