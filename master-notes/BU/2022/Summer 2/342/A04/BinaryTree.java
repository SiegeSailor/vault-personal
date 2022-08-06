/**
 * Binary tree class.
 * 
 * @author Zhang, Jin Yu
 */
public class BinaryTree<T extends Comparable<T>> {
    /** Root node. */
    private Node<T> root;

    /** Number of total nodes. */
    private int count;

    /**
     * Helping recursive method to add a new node underneath a designated node.
     * 
     * @param current {@code Node<T>} Current node.
     * @param data    {@code T} Data to add.
     */
    private void add(Node<T> current, T data) {
        if (current.getData().compareTo(data) < 0)
            if (current.getLeft() == null) {
                this.count++;
                current.setLeft(new Node<T>(data));
            } else
                this.add(current.getLeft(), data);
        else {
            if (current.getRight() == null) {
                this.count++;
                current.setRight(new Node<T>(data));
            } else
                this.add(current.getRight(), data);
        }
    }

    /**
     * Helping recursive method to get a node with data underneath a designated
     * node.
     * 
     * @param current {@code Node<T>} Current node.
     * @param data    {@code T} Data to search.
     * @return {@code Node<T>} Could be {@code null} if there is no node have
     *         corresponding data.
     */
    private Node<T> get(Node<T> current, T data) {
        // Base case.
        if (current == null)
            return null;

        int difference = current.getData().compareTo(data);
        if (difference == 0)
            return current;
        else if (difference < 0)
            return this.get(current.getLeft(), data);
        else
            return this.get(current.getRight(), data);
    }

    /**
     * Helping recursive method to get the depth of the tree underneath a designated
     * node.
     * 
     * @param current     {@code Node<T>} Current node.
     * @param accumulator {@code int} Depth.
     * @return {@code int} Depth.
     */
    private int inquireDepth(Node<T> current, int accumulator) {
        // Minus 1 back since always adding 1 to accumulator before reach the base case.
        if (current == null)
            return accumulator - 1;

        int countLeft = this.inquireDepth(current.getLeft(), accumulator + 1);
        int countRight = this.inquireDepth(current.getRight(), accumulator + 1);
        return Math.max(countLeft, countRight);
    }

    /**
     * Helping recursive method to get the number of count property from every node
     * underneath a designated node.
     * 
     * @param current     {@code Node<T>} Current node.
     * @param accumulator {@code int} Number of count from every node.
     * @return {@code int} Number of count from every node.
     */
    private int inquireCount(Node<T> current, int accumulator) {
        // No node, no count.
        if (current == null)
            return 0;

        int countLeft = this.inquireCount(current.getLeft(), accumulator);
        int countRight = this.inquireCount(current.getRight(), accumulator);
        // Count from current node and its children.
        return countLeft + countRight + current.getCount();
    }

    /**
     * Helping recursive method to get the leaves at desired level.
     * 
     * @param current {@code Node<T>} Current node.
     * @param level   {@code int} Current level.
     * @param tree    {@code BinaryTree<T>} Tree contains leaves. Could be empty.
     * @param depth   {@code int} Desired level.
     */
    private void inquireLeaves(Node<T> current, int level, BinaryTree<T> tree, int depth) {
        if (current == null)
            return;

        if (current.getLeft() == null && current.getRight() == null && level == depth)
            tree.add(current.getData());
        this.inquireLeaves(current.getLeft(), level + 1, tree, depth);
        this.inquireLeaves(current.getRight(), level + 1, tree, depth);
    }

    /**
     * Helping recursing method to get the node with highest count.
     * 
     * @param current   {@code Node<T>} Current node.
     * @param occurMost {@code Node<T>} Node occurs most.
     */
    private void inquireOccurMost(Node<T> current, Node<T> occurMost) {
        if (current == null)
            return;

        if (current.getCount() > occurMost.getCount())
            occurMost = current;
        this.inquireOccurMost(current.getLeft(), occurMost);
        this.inquireOccurMost(current.getRight(), occurMost);
    }

    /**
     * Helping recursive method to add nodes in an in-order way to the
     * {@code StringBuilder} result.
     * 
     * @param current {@code Node<T>} Current node.
     * @param result  {@code StringBuilder} Result string.
     */
    private void inOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        this.inOrder(current.getLeft(), result);
        result.append(current + " ");
        this.inOrder(current.getRight(), result);
    }

    /**
     * Helping recursive method to add nodes in a pre-order way to the
     * {@code StringBuilder} result.
     * 
     * @param current {@code Node<T>} Current node.
     * @param result  {@code StringBuilder} Result string.
     */
    private void preOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        result.append(current + " ");
        this.preOrder(current.getLeft(), result);
        this.preOrder(current.getRight(), result);
    }

    /**
     * Helping recursive method to add nodes in a post-order way to the
     * {@code StringBuilder} result.
     * 
     * @param current {@code Node<T>} Current node.
     * @param result  {@code StringBuilder} Result string.
     */
    private void postOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        this.postOrder(current.getLeft(), result);
        this.postOrder(current.getRight(), result);
        result.append(current + " ");
    }

    /** Default constructor. */
    public BinaryTree() {
    }

    /**
     * Add a data into the tree. The data will be the root node if the tree is
     * empty. If there is already a node with the same data existing in the tree,
     * adding count by 1 for the node instead of creating a new node.
     * 
     * @param data {@code T} Data.
     */
    public void add(T data) {
        if (this.seeEmpty()) {
            this.root = new Node<T>(data);
            this.count = 1;
            return;
        }

        Node<T> node = this.get(data);
        if (node != null)
            node.addCount(1);
        else
            this.add(this.root, data);
    }

    /**
     * Get a node with desired data. Could return {@code null}.
     * 
     * @param data {@code T} Desired data.
     * @return {@code Node<T>} Node with desired data. Could return {@code null}.
     */
    public Node<T> get(T data) {
        return this.get(this.root, data);
    }

    /**
     * Return a string with desired way to traverse.
     * 
     * @param order {@code Order} Way to traverse.
     * @return {@code String} Result string.
     * @throws IllegalArgumentException If parameter order is not a valid value.
     */
    public String traverse(Order order) throws IllegalArgumentException {
        if (this.seeEmpty())
            return "<EMPTY>";

        StringBuilder result = new StringBuilder();
        switch (order) {
            case IN_ORDER:
                this.inOrder(this.root, result);
                break;
            case PRE_ORDER:
                this.preOrder(this.root, result);
                break;
            case POST_ORDER:
                this.postOrder(this.root, result);
                break;
            default:
                throw new IllegalArgumentException("Illegal order.");
        }
        return result.toString();
    }

    /**
     * Get number of nodes.
     * 
     * @return {@code int} Number of nodes.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Get the root node.
     * 
     * @return {@code Node<T>} Root node. Could be {@code null}.
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Inquire the depth of the tree.
     * 
     * @return {@code int} Depth.
     */
    public int inquireDepth() {
        return this.inquireDepth(this.getRoot(), 0);
    }

    /**
     * Inquire the total count of every node in the tree.
     * 
     * @return {@code int} Total count of every node in the tree.
     */
    public int inquireCount() {
        return this.inquireCount(this.getRoot(), 0);
    }

    /**
     * Inquire the leaves at deepest level. The returned tree data structure can be
     * replaced with any list-like data structure in the future.
     * 
     * @return {@code BinaryTree<T>} Leaves at deepest level.
     */
    public BinaryTree<T> inquireLeaves() {
        if (this.seeEmpty())
            return null;

        BinaryTree<T> tree = new BinaryTree<T>();
        this.inquireLeaves(this.getRoot(), 0, tree, this.inquireDepth());
        return tree;
    }

    /**
     * Inquire the node with highest count.
     * 
     * @return {@code Node<T>} Node with highest count.
     */
    public Node<T> inquireOccurMost() {
        if (this.seeEmpty())
            return null;
        Node<T> cache = this.getRoot();
        this.inquireOccurMost(this.getRoot(), cache);
        return cache;
    }

    /**
     * See if the tree is empty.
     * 
     * @return {@code boolean} True if the tree is empty.
     */
    public boolean seeEmpty() {
        return this.getRoot() == null && this.count == 0;
    }

    /**
     * See if a data exist in the tree.
     * 
     * @param data {@code T} Data to search.
     * @return {@code boolean} True if the data exist in the tree.
     */
    public boolean seeExist(T data) {
        return this.get(data) != null;
    }

    /**
     * Clear the tree by utilizing Java Garbage Collection mechanism. Nodes have
     * been referenced from other places may not be removed.
     */
    public void clear() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Use iterator to append all node's information by taking advantage their
     * toString method in an in-order way.
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        return this.traverse(Order.IN_ORDER);
    }
}
