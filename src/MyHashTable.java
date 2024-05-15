import java.util.HashSet;
import java.util.Set;

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
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M){
        this.M = size;
        this.chainArray = new HashNode[size];
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

    /**
     * Removes key-value pair with the given key from the hash table.
     * @param key The key of the key-value pair to remove
     * @return The removed key-value pair, or null if the key is not found.
     */
    public V remove(K key){
        int index = hash(key);
        HashNode<K,V> prev = null;
        HashNode<K,V> curr = chainArray[index];

        while (curr != null){
            if (curr.key.equals(key)){
                if (prev == null){
                    chainArray[index] = curr.next;
                }
                else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    /**
     * This method checks the hash table contains the specific value.
     * @param value The value to be checked for existence.
     * @return True if hash table contains value, false otherwise.
     */
    public boolean contains(V value){
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K,V> node = chainArray[i];
            while (node != null){
                if (node.value.toString().toLowerCase().equals(value)){
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    /**
     * Retrieves the key of value in the hash table.
     * @param value The value for which to retrieve.
     * @return The key if it's found, null otherwise.
     */
    public K getKey(V value){
        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K,V> node = chainArray[i];
            while (node != null){
                if (node.value.toString().toLowerCase().equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    public Set<K> keySet(){
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < M; i++) {
            HashNode<K, V> curr = chainArray[i];
            while (curr != null){
                keySet.add(curr.key);
                curr=curr.next;
            }
        }
        return keySet;
    }

    /**
     * This method shows length of hash table.
     * @return Size of hash table.
     */
    public int size(){
        return size;
    }

    /**
     * toString method, representation of hash table.
     * @return string instead of garbage.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < chainArray.length; i++) {
            HashNode<K,V> node = chainArray[i];
            while (node != null){
                sb.append(node.key).append(" = ").append(node.value).append(", ");
                node = node.next;
            }
        }

        if (sb.length() > 1){
            sb.setLength(sb.length() - 2);
        }

        sb.append("}");
        return sb.toString();
    }
}
