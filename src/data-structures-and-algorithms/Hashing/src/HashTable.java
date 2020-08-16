



public abstract class HashTable {
    abstract boolean insert(String key, String value);
    abstract String search(String key);
    abstract boolean delete(String key);
    abstract boolean update(String key, String value);
    abstract void resize();

}
