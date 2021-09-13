public class DoubleLinkedList<T> {

    public static void main(String[] args) {
        DoubleLinkedList<Integer> MyLinkedLIst = new DoubleLinkedList<Integer>();

        MyLinkedLIst.addNode(1);
        MyLinkedLIst.addNode(2);
        MyLinkedLIst.addNode(3);
        MyLinkedLIst.addNode(4);
        MyLinkedLIst.addNode(5);

        MyLinkedLIst.printAll();

        System.out.println("----------------------------");
        System.out.println(MyLinkedLIst.searchFromHead(3));
        System.out.println(MyLinkedLIst.searchFromTail(1));
        System.out.println(MyLinkedLIst.searchFromTail(6));

    }

    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }
    
    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<T>(data);
            this.tail = this.head;
        } else {
            Node<T> node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<T>(data);
            node.next.prev = node;
            this.tail = node.next;
        }
    }

    public void printAll() {
        if (this.head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);
            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    public T searchFromHead(T isData) {
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == isData) {
                    return node.data;
                } else {
                    node = node.next;
                }
            }
            return null;
        }
    }

    public T searchFromTail(T isData) {
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.tail;
            while (node != null) {
                if (node.data == isData) {
                    return node.data;
                } else {
                    node = node.prev;
                }
            }
            return null;
        }
    }
}
