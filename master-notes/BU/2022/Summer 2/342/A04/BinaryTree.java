public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    private int count;

    private void add(Node<T> current, T data) {
        this.count++;

        if (current.getData().compareTo(data) < 0)
            if (current.getLeft() == null)
                current.setLeft(new Node<T>(data));
            else
                this.add(current.getLeft(), data);
        else {
            if (current.getRight() == null)
                current.setRight(new Node<T>(data));
            else
                this.add(current.getRight(), data);
        }
    }

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

    private int inquireDepth(Node<T> current, int accumulator) {
        if (current == null)
            return accumulator - 1;

        int countLeft = this.inquireDepth(current.getLeft(), accumulator + 1);
        int countRight = this.inquireDepth(current.getRight(), accumulator + 1);
        return Math.max(countLeft, countRight);
    }

    private int inquireCount(Node<T> current, int accumulator) {
        if (current == null)
            return 0;

        int countLeft = this.inquireCount(current.getLeft(), accumulator);
        int countRight = this.inquireCount(current.getRight(), accumulator);
        return countLeft + countRight + current.getCount();
    }

    private void inquireLeaves(Node<T> current, int level, BinaryTree<T> tree, int depth) {
        if (current == null)
            return;

        if (current.getLeft() == null && current.getRight() == null && level == depth)
            tree.add(current.getData());
        this.inquireLeaves(current.getLeft(), level + 1, tree, depth);
        this.inquireLeaves(current.getRight(), level + 1, tree, depth);
    }

    private Node<T> inquireOccurMost(Node<T> current, Node<T> occurMost) {
        if (current == null)
            return occurMost;

        if (current.getCount() > occurMost.getCount())
            occurMost = current;
        this.inquireOccurMost(current.getLeft(), occurMost);
        this.inquireOccurMost(current.getRight(), occurMost);
        return current;
    }

    private void inOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        this.inOrder(current.getLeft(), result);
        result.append(current + " ");
        this.inOrder(current.getRight(), result);
    }

    private void preOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        result.append(current + " ");
        this.inOrder(current.getLeft(), result);
        this.inOrder(current.getRight(), result);
    }

    private void postOrder(Node<T> current, StringBuilder result) {
        if (current == null)
            return;

        this.inOrder(current.getLeft(), result);
        this.inOrder(current.getRight(), result);
        result.append(current + " ");
    }

    public BinaryTree() {
    }

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

    public Node<T> get(T data) {
        return this.get(this.root, data);
    }

    public void remove(T data) {
    }

    public String traversal(Order order) throws IllegalArgumentException {
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

    public int getCount() {
        return this.count;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public int inquireDepth() {
        return this.inquireDepth(this.getRoot(), 0);
    }

    public int inquireCount() {
        return this.inquireCount(this.getRoot(), 0);
    }

    public BinaryTree<T> inquireLeaves() {
        if (this.seeEmpty())
            return null;

        BinaryTree<T> tree = new BinaryTree<T>();
        this.inquireLeaves(this.getRoot(), 0, tree, this.inquireDepth());
        return tree;
    }

    public Node<T> inquireOccurMost() {
        if (this.seeEmpty())
            return null;

        return this.inquireOccurMost(this.getRoot(), this.getRoot());
    }

    public boolean seeEmpty() {
        return this.getRoot() == null && this.count == 0;
    }

    public boolean seeExist(T data) {
        return this.get(data) != null;
    }

    public void clear() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Use iterator to append all node's information by taking advantage their
     * toString method.
     * 
     * @return {@code String} A string representation for the held data.
     */
    @Override
    public String toString() {
        return this.traversal(Order.IN_ORDER);
    }
}
