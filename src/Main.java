public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer, String> table = new MyHashTable<>();
        table.put(123, "Apple");
        table.put(111, "Juice");
        table.put(200, "Watermelon");

        System.out.println(table.get(200));
    }
}
