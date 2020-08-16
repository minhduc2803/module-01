


public class LinearTable extends HashTable{
    String[][] table;
    int n, k;

    LinearTable(){
        n = 5;
        k = 0;
        table = new String[n][2];

    }
    public String getName(){
        return "Linear Table";
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
        while (table[i][0] != null)
        {
            if (table[i][0].equals(key))
                break;
            i = (i + 1) % n;
        }
        return i;

    }
    public boolean insert(String key, String value){
        if(k > n/2)
            resize(true);
        int i = findIndex(key);
        if(key.equals(table[i][0]))
            return false;
        table[i][0] = key;
        table[i][1] = value;
        k++;
        return true;
    }
    public String search(String key){
        int i = findIndex(key);
        if(key.equals(table[i][0]))
            return table[i][1];
        else
            return null;
    }
    public boolean delete(String key){
        if (search(key) == null)
            return false;

        if(k < n/4)
            resize(false);

        int i = hash(key);
        while (!key.equals(table[i][0]))
            i = (i + 1) % n;
        table[i][0] = table[i][1] = null;

        for (i = (i + 1) % n; table[i][0] != null; i = (i + 1) % n)
        {
            String tmp1 = table[i][0], tmp2 = table[i][1];
            table[i][0] = table[i][1] = null;

            insert(tmp1, tmp2);
        }
        k--;
        return true;
    }
    public boolean update(String key, String value){
        int i = findIndex(key);
        if(!key.equals(table[i][0]))
            return false;

        table[i][1] = value;
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
        String[][] buckets = table;
        table = new String[n][2];

        for(int i=0;i<m;i++){
            if(buckets[i][0] != null){
                insert(buckets[i][0],buckets[i][1]);
            }
        }

    }


}
