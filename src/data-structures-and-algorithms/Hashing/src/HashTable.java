



public abstract class HashTable {
    abstract public String getName();
    abstract public int getSize();
    abstract public int getNumberElements();
    abstract public boolean insert(String key, String value);
    abstract public String search(String key);
    abstract public boolean delete(String key);
    abstract public boolean update(String key, String value);
    abstract void resize(boolean larger);

}
