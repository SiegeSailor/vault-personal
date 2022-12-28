/**
 * Class for linked list version chained hash map.
 * 
 * @author Zhang, Jin Yu
 */
public class HashMap<T> {
    /** A linked list with nodes contain linked lists. */
    LinkedList<LinkedList<T>> list;

    /** Construct a hash map with an empty linked list which contains lists. */
    public HashMap() {
        this.list = new LinkedList<LinkedList<T>>();
    }

    /**
     * Append the data to one of the existing node or add a new node list for it.
     * 
     * @param data {@code T} Data.
     */
    public void add(T data) {
        // Add data to one of the list if it already exists
        Node<LinkedList<T>> cache = this.list.getHead();
        while (cache != null) {
            if (cache.getData().getHead().getData().equals(data)) {
                cache.getData().addLast(data);
                return;
            }
            cache = cache.getNext();
        }

        // Add the new node which contains a list, and the list's head contains the data
        LinkedList<T> list = new LinkedList<T>();
        list.addLast(data);
        this.list.addLast(list);
    }

    /**
     * Get the node list if the data exists in the map.
     * 
     * @param data {@code T} Data.
     * @return {@code Node<LinkedList<T>>} Could be {@code null} if there is no such
     *         data.
     */
    public Node<LinkedList<T>> get(T data) {
        // Return the node list if it exists
        Node<LinkedList<T>> cache = this.list.getHead();
        while (cache != null) {
            if (cache.getData().getHead().getData().equals(data))
                return cache;
            cache = cache.getNext();
        }

        return null;
    }

    /**
     * See if the data exists in the map.
     * 
     * @param data {@code T} Data.
     * @return {@code boolean} True if the data exists.
     */
    public boolean seeExist(T data) {
        Node<LinkedList<T>> cache = this.list.getHead();
        while (cache != null) {
            if (cache.getData().getHead().getData().equals(data))
                return true;
            cache = cache.getNext();
        }
        return false;
    }

    /**
     * Get the head node.
     * 
     * @return {@code Node<T>} The head node.
     */
    public Node<LinkedList<T>> getHead() {
        return this.list.getHead();
    }

    /**
     * Get count for keys.
     * 
     * @return {@code int} Number of lists.
     */
    public int getCount() {
        return this.list.getCount();
    }

    /**
     * See if the map is empty.
     * 
     * @return {@code boolean} True if the map is empty.
     */
    public boolean seeEmpty() {
        return this.list.seeEmpty();
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
            Node<LinkedList<T>> cache = this.list.getHead();
            while (cache != null) {
                message += cache.getData() + "\n";
                cache = cache.getNext();
            }
        }

        return message;
    }
}