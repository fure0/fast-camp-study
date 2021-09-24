public class Hash_Chaining2 {
 
    public class Slot {
        String key;
        String value;
        Slot next;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public Slot[] hashTable;

    public Hash_Chaining2(Integer size) {
        this.hashTable = new Slot[size];
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
             Slot findSlot = this.hashTable[address];
             Slot prevSlot = this.hashTable[address];
             while (findSlot != null) {
                 if (findSlot.key == key) {
                     findSlot.value = value;
                 } else {
                     prevSlot = findSlot;
                     findSlot = findSlot.next;
                 }
             }
             prevSlot.next = new Slot(key, value);
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address];
            while (findSlot != null) {
                if (findSlot.key == key) {
                    return findSlot.value;
                } else {
                    findSlot = findSlot.next;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Hash_Chaining2 mainObject = new Hash_Chaining2(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        
        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("David"));
        System.out.println(mainObject.getData("Dave"));
    }

}
