import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    public class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node (K key, V val){
            this.key = key;
            this.val = val;
            left = right = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    /**
     * Inserts a key-value pair into the binary search tree.
     * If the key exists, updates the corresponding value.
     * @param key The key of the element to insert.
     * @param val The value associated with the key
     */
    public void insert(K key, V val){
        root = insert(root, key, val);
    }

    /**
     * Helper method for inserting a key-value pair into the binary search tree.
     * @param root The root node of the subtree.
     * @param key The key of the element to insert.
     * @param val The value associated with the key.
     * @return The root node of the modified subtree.
     */
    private Node insert(Node root, K key, V val){
        if (root == null)
            return new Node(key, val);

        if (key.compareTo(root.key) < 0){
            root.left = insert(root.left, key, val);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insert(root.right, key, val);
        }else {
            root.val = val;
        }
        return root;
    }

    /**
     * Traverses the binary search tree in in-order fashion and prints the key-value pairs.
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * Helper method for in-order traversal of the binary search tree.
     * @param node The current node being visited.
     */
    private void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node.key + " : " + node.val);
            inOrder(node.right);
        }
    }

    /**
     * Retrieves the key associated with a given value in the binary search tree.
     * @param val The value for which to find the associated key.
     * @return The key associated with the value, or null if the value is not found.
     */
    public K getKey(V val){
        Node node = findForKey(root, val);
        return node != null ? node.key : null;
    }

    /**
     * Retrieves the value associated with a given key in the binary search tree.
     * @param key The key for which to find the associated value.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V getVal(K key){
        Node node = findForVal(root, key);
        return node != null ? node.val : null;
    }

    /**
     * Helper method to find a node with the specified value in the binary search tree.
     * @param node The current node being examined.
     * @param val The value to search for.
     * @return The node containing the specified value, or null if not found.
     */
    private Node findForKey(Node node, V val) {
        if (node == null)
            return null;

        if (val.equals(node.val))
            return node;

        Node leftNode = findForKey(node.left, val);
        if (leftNode != null)
            return leftNode;

        return findForKey(node.right, val);
    }

    /**
     * Helper method to find a node with the specified key in the binary search tree.
     * @param node The current node being examined.
     * @param key The key to search for.
     * @return The node containing the specified key, or null if not found.
     */
    private Node findForVal(Node node, K key) {
        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;

        Node leftNode = findForVal(node.left, key);
        if (leftNode != null)
            return leftNode;

        return findForVal(node.right, key);
    }

    /**
     * Removes a node with the specified key from the binary search tree.
     * @param key The key of the node to remove.
     */
    public void remove(K key){
        root = remove(root, key);
    }

    /**
     * Helper method to remove a node with the specified key from the binary search tree.
     * @param node The current node being examined.
     * @param key The key of the node to remove.
     * @return The root of the modified subtree after removal.
     */
    private Node remove(Node node, K key){
        if (node == null){
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0 ){
            node.left = remove(node.left, key);
        } else if (compare > 0 ) {
            node.right = remove(node.right, key);
        } else {
            //case 1: one child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            //case 2: two child
            Node smallestVal = findSmallestVal(node.right);
            node.key = smallestVal.key;
            node.val = smallestVal.val;
            node.right = remove(node.right, key);

            //case 3: no child
            if (node.left == null && node.right == null)
                return null;
        }
        return node;
    }

    /**
     * Helper method to find the node with the smallest key in a subtree.
     * @param node The root node of the subtree.
     * @return The node with the smallest key.
     */
    private Node findSmallestVal(Node node){
        return node.right == null ? node : findSmallestVal(node.right);
    }

    /**
     * Calculates the number of nodes in the binary search tree.
     * @return The number of nodes in the tree.
     */
    public int size(){
        return size(root);
    }

    /**
     * Helper method to calculate the number of nodes in a subtree rooted at the given node.
     * @param node The root node of the subtree.
     * @return The number of nodes in the subtree.
     */
    private int size(Node node){
        if (node == null){
            return 0;
        } else {
            return size(node.left) + size(node.right) + 1;
        }
    }

    public Iterable<Node> iterator() {
        Stack<Node> stack = new Stack<>();
        return new Iterable<Node>() {
            @Override
            public Iterator<Node> iterator() {
                return new Iterator<>() {
                    {
                        pushLeft(root);
                    }

                    private void pushLeft(Node node) {
                        while (node != null) {
                            stack.push(node);
                            node = node.left;
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return !stack.isEmpty();
                    }

                    @Override
                    public Node next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }

                        Node node = stack.pop();
                        pushLeft(node.right);
                        return node;
                    }
                };
            }
        };
    }
}