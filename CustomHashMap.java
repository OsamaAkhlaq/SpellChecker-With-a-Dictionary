package spellcheck;

class CustomHashmap <Key, Value> {
    private Insert <Key, Value> [] map;  //array structure of hashmap
    private int size = 26;                    // size of the structure
    String hold[] = new String[size];
    
    static class Insert <Key, Value>{    // static class so that instance of outer class does not have to be created
        Key k;                           // key data
        Value v;                         // value corresponding to the key
        Insert <Key, Value> next;        // pointer to the next bucket in correspondng index
        
        public Insert(Key k, Value v, Insert<Key, Value> next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
    @SuppressWarnings ("Unchecked")
    public CustomHashmap(){
        map = new Insert[size];          // default constructor to build instance
    }
    
    public void put(Key new_k, Value data){
        if(new_k == null)                // cannot store null values 
            return;
        else{
        int hash = hash (new_k);         // generate specific hashcode
        Insert <Key, Value> insert = new Insert <Key, Value> (new_k, data, null);
        if(map[hash] == null)
            map[hash] = insert;
        else{
            Insert <Key, Value> previous = null;
            Insert <Key, Value> current = map[hash];
            
            while(current != null){
                if(current.k.equals(new_k)){
                   if(previous == null){
                       insert.next = current.next;
                       map[hash] = insert;
                       return;
                   }
                   else{
                       insert.next = current.next;
                       previous.next = insert;
                       return;
                   }
                }
                previous = current;
                current = current.next;
            }
            previous.next = insert;
        }
    }
    }
    
    public Value get(Key k){
        int hash = hash(k);
        if(map[hash] == null)
            return null;
        else{
            Insert <Key, Value> temp = map[hash];
            while(temp != null){
                if(temp.k.equals(k)){
                    return temp.v;
                    
                }
                temp = temp.next;
            }
            return null;
        }
    }
    
    public void display(){
        for (int i = 0; i < size; i++){
            if(map[i] != null){
                Insert <Key, Value> insert = map[i];
                while(insert != null){
                    System.out.println("{"+insert.k+" = "+insert.v+"}");
                    insert = insert.next;
                }
            }
        }
    }
    
    private int hash(Key k){
        return (Math.abs(k.hashCode()) % size);
    }
}
