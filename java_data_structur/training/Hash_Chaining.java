public class Hash_Chaining {

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

    public Hash_Chaining(Integer size) {
        this.hashTable = new Slot[size];
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean SaveData(String key, String value) {
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
                prevSlot.next = new Slot(key, value);
            }
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
        Hash_Chaining mainObject = new Hash_Chaining(20);
        mainObject.SaveData("kimTony", "01011112222");
        mainObject.SaveData("kimSoo", "01022223333");

        System.out.println(mainObject.getData("kimTony"));
        System.out.println(mainObject.getData("kimSoo"));
    }
    
}
