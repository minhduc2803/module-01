package vn.com.zalopay.syllabus.predictive.service;


import java.util.Set;

public class HashDictionary implements Dictionary {
    class HashNode
    {
        String key;
        HashNode next;

        // Constructor
        public HashNode()
        {
            this.key = null;
            next = null;
        }
        public HashNode(String key)
        {
            this.key = key;
            next = null;
        }
    }

    HashNode[] table;
    int n, k;

    public HashDictionary(){
        n = 5;
        k = 0;
        table = new HashNode[n];

    }
    public HashDictionary(Set<String> dataset){
        n = 5;
        k = 0;
        table = new HashNode[n];

        if(dataset == null)
            return;
        for(String s: dataset){
            insert(s);
        }
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
        if(s < 0)
            s = -s;
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
    public boolean insert(String key){
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
        HashNode newNode = new HashNode(key);
        newNode.next = head;
        table[bucketIndex] = newNode;

        return true;
    }
    @Override
    public boolean contains(String key){
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        while (head != null)
        {
            if (head.key.equals(key))
                return true;
            head = head.next;
        }
        return false;
    }
    public boolean delete(String key){
        if (contains(key) == false)
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
                insert(buckets[i].key);
            }
        }

    }


}
