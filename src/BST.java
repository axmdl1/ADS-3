public class BST<K extends Comparable<K>, V> {
    private Node root;
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        public Node (K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val){
        root = putRecursive(root, key, val);
    }

    private Node putRecursive(Node root, K key, V val){
        if (root == null)
            return new Node(key, val);

        if (key.compareTo(root.key) < 0){
            root.left = putRecursive(root.left, key, val); 
        } else if (key.compareTo(root.key) > 0) {
            root.right = putRecursive(root.right, key, val);
        }else {
            root.val = val;
        }
        return root;
    }
}
