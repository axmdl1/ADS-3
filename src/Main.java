public class Main {
    public static void main(String[] args) {
        BST<Integer, String> myBST = new BST<>();
        myBST.insert(5, "Max");
        myBST.insert(7, "Ali");
        myBST.insert(1, "Carlos");
        myBST.insert(4, "Charles");
        myBST.insert(2, "Lewis");

        myBST.inOrder();
        myBST.remove(4);
        System.out.println();
        myBST.inOrder();
        System.out.println(myBST.size());
        System.out.println(myBST.getVal(1));
        System.out.println(myBST.getKey("Lewis"));
    }
}
