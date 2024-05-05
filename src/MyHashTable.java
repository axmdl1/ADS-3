public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

        private HashNode<K, V>[] chainArray;
        private int M = 11;
        private int size;

        public MyHashTable(){
            chainArray = new HashNode[10];
        }

        public MyHashTable(int M){
            chainArray = new HashNode[M];
            size = 0;
        }

    /**
     * Calculates the hash value for a given key.
     * @param key The key value that needs to be calculated.
     * @return The index in the hash table where element located.
     */
        private int hash(K key){
            int getIndex = key.hashCode() % chainArray.length;
            if (getIndex > 0 || getIndex == 0)
                return getIndex;
            else
                return getIndex * (-1);
        }

    /**
     * Inserts a key-value into the hash table.
     * @param key The key to be inserted.
     * @param value The value to be associated with key.
     */
        public void put(K key, V value){
            int index = hash(key);
            HashNode<K,V> node = new HashNode<>(key, value);
            if (chainArray[index] != null){
                node.next = chainArray[index];
            }

            chainArray[index] = node;
            size++;
        }

    /**
     * Retrieves the value with given key from hash table.
     * @param key The key whose associated value is to be retrieved.
     * @return The value with the given key, or null if key is not found.
     */
    public V get(K key){
            if (key == null){
                throw new IllegalArgumentException("Key is null !");
            }

            int index = hash(key);
            HashNode<K,V> node = chainArray[index];
            while (node != null){
                if (node.key.equals(key)){
                    return node.value;
                }
                node = node.next;
            }
            return null;
        }
}
