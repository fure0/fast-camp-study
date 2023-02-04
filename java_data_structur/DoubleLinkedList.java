public class DoubleLinkedList<T> {
    
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
            node.next = new Node<T>(data); // 가장 마지막 노드 다음에 추가
            node.next.prev = node; // 새로운 마지막 노드랑 이전 노드랑 연결
            this.tail = node.next; // tail을 새로운 마지막 노드로 갱신
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
    
    public boolean insertToFront(T existedData, T addData) {
        if (this.head == null) {
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        } else if (this.head.data == existedData) { // 헤드앞에 넣었을때 기존 헤드를 밀어내고 새로운 헤드를 입력
            Node<T> newHead = new Node<T>(addData);
            newHead.next = this.head;
            this.head = newHead;
            return true;
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == existedData) { //기존에 1, 2가 있고 2 앞에 새로운 숫자를 넣는다고 하면
                    // 코드로 이해가 잘 안되면 그림으로 생각해서 1, 2, 3을 서로 연결한다고 생각하면 된다.
                    Node<T> nodePrev = node.prev; //2의 앞은 1
                    
                    nodePrev.next = new Node<T>(addData); //기존 1 다음에 새로운 데이터 입력 2  1->새로운2 연결
                    nodePrev.next.next = node; // 기존 2->3 연결
                    // 여기까지는 1->new2->3 연결
                    nodePrev.next.prev = nodePrev; // 1<-새로운2 연결 
                    node.prev = nodePrev.next; //2<-3 연결
                    // 여기까지는 1<-new2<-3 연결
                    return true;
                } else {
                    node = node.next;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> MyLinkedList = new DoubleLinkedList<Integer>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(3, 2);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(6, 2);
        MyLinkedList.insertToFront(1, 0);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.addNode(6);
        MyLinkedList.printAll();
    }
}