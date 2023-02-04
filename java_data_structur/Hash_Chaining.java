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

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        System.out.println("key: "+ key);
        System.out.println("address: "+ address);
        if (this.hashTable[address] != null) { //여기로 들오오는건 두번째 부터
            /*
            Slot findSlot = this.hashTable[address]; //DaveLee findSlot은 루프 탐색을 위해
            Slot prevSlot = this.hashTable[address]; //DaveLee prevSlot은 next가없는 마지막 커서를 지정하기 위해
            while (findSlot != null ) { //DaveLee David (빈 슬롯 까지 이동시키는 부분)
                if (findSlot.key == key) {
                    findSlot.value = value; //update data
                    return true;
                } else {
                    prevSlot = findSlot; //DaveLee David
                    findSlot = findSlot.next; //null 
                }
            }
            // findSlot.next와 같다
            prevSlot.next = new Slot(key, value); //David
            */
            Slot findSlot = this.hashTable[address];
            while (findSlot != null ) { //DaveLee David (빈 슬롯 까지 이동시키는 부분)
                if (findSlot.key == key) {
                    findSlot.value = value; //update data
                    return true;
                } else {
                    if (findSlot.next == null) {
                        findSlot.next = new Slot(key, value); //David
                        return true;
                    } else {
                        findSlot = findSlot.next; //null 
                    }
                }
            }
        } else { //첫번째
            this.hashTable[address] = new Slot(key, value); //DaveLee
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address];
            while (findSlot != null) {
                if(findSlot.key == key) {
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
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        // mainObject.saveData("fun-coding", "01033334444");
        
        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("David"));
        System.out.println(mainObject.getData("Dave"));
    }
}
