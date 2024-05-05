public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer, String> table = new MyHashTable<>();
        table.put(123, "Apple");
        table.put(111, "Juice");
        table.put(200, "Watermelon");

        System.out.println(table);
        table.remove(111);
        System.out.println(table);
        System.out.println(table.contains("watermelon"));
        System.out.println(table.getKey("apple"));
        table.put(142, "Potato");
        System.out.println(table.size());
    }
}
