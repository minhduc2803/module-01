import java.util.ArrayList;

public class ChainingTable extends HashTable {
    class HashNode
    {
        String key;
        String value;
        HashNode next;

        // Constructor
        public HashNode()
        {
            this.key = null;
            this.value = null;
            next = null;
        }
        public HashNode(String key, String value)
        {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    HashNode[] table;
    int n, k;

    ChainingTable(){
        n = 5;
        k = 0;
        table = new HashNode[n];

    }
    public String getName(){
        return "Chaining Table";
    }
    public int getSize(){
        return n;
    }
    public int getNumberElements(){
        return k;
    }
    int hash(String key){
        int s = 0;
        for(int i=1;i<=key.length();i++){
            s += s*19+key.charAt(i-1)*i;
        }
        return s % n;
    }
    int findIndex(String key){
        int i = hash(key);
        while (table[i].key != null)
        {
            if (table[i].key.equals(key))
                break;
            i = (i + 1) % n;
        }
        return i;

    }
    public boolean insert(String key, String value){
        if(k > n/2)
            resize(true);
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        while (head != null)
        {
            if (head.key.equals(key))
            {
                return false;
            }
            head = head.next;
        }

        k++;
        head = table[bucketIndex];
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        table[bucketIndex] = newNode;

        return true;
    }
    public String search(String key){
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        while (head != null)
        {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }
    public boolean delete(String key){
        if (search(key) == null)
            return false;

        if(k < n/4)
            resize(false);

        int bucketIndex = hash(key);

        HashNode head = table[bucketIndex];

        HashNode prev = null;
        while (head != null)
        {
            if (head.key.equals(key))
                break;

            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null)
            return false;

        k--;

        if (prev != null)
            prev.next = head.next;
        else
            table[bucketIndex] = head.next;

        return true;
    }
    public boolean update(String key, String value){
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return true;
            }
            head = head.next;
        }

        return false;

    }
    void resize(boolean larger){

        int m = n;
        n = larger ? n*2 : n/2;
        if(n<5) {
            n = 5;
            return;
        }
        k=0;
        HashNode[] buckets = table;
        table = new HashNode[n];

        for(int i=0;i<m;i++){
            if(buckets[i] != null){
                insert(buckets[i].key,buckets[i].value);
            }
        }

    }


}
