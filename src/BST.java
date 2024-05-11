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

    public void inOrder(){}

    public void remove(K key, V val){
        root = remove(root, key, val);
    }

    private Node remove(Node curr, K key, V val){
        if (curr == null){
            return null;
        }

        int compare = key.compareTo(curr.key);
        if (compare < 0 ){
            curr.left = remove(curr.left, key, val); 
        } else if (compare > 0 ) {
            curr.right = remove(curr, key, val);
        } else {
            //case 1: one child
            if (curr.left == null)
                return curr.right;
            else if (curr.right == null)
                return curr.left;

            //case 2: two child
            Node smallestVal = findSmallestVal(curr.right);
            curr.key = smallestVal.key;
            curr.val = smallestVal.val;
            curr.right = remove(curr.right, key, val);
        }
        return curr;
    }

    private Node findSmallestVal(Node node){
        return node.right == null ? node : findSmallestVal(node.right);
    }
}