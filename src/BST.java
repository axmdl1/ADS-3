public class BST<K extends Comparable<K>, V> {
    private Node root;
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node (K key, V val){
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    public void insert(K key, V val){
        root = insert(root, key, val);
    }

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

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node.key + " : " + node.val);
            inOrder(node.right);
        }
    }

    public K getKey(V val){
        Node node = findForKey(root, val);
        return node != null ? node.key : null;
    }

    public V getVal(K key){
        Node node = findForVal(root, key);
        return node != null ? node.val : null;
    }

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

    public void remove(K key){
        root = remove(root, key);
    }

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

    private Node findSmallestVal(Node node){
        return node.right == null ? node : findSmallestVal(node.right);
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if (node == null){
            return 0;
        } else {
            return size(node.left) + size(node.right) + 1;
        }
    }

    public Iterable<K> iterator(){
        return null;
    }
}