import java.util.ArrayList;

public class stack<T> {
    private ArrayList<T> stack = new ArrayList<T>();

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if(stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() -1);
    }

    public T peek() {
        if(stack.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() -1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        stack<Integer> ms = new stack<Integer>();
        ms.push(1);
        ms.push(2);
        System.out.println(ms.pop());
        System.out.println(ms.peek());
        ms.push(3);
        System.out.println(ms.pop());
        System.out.println(ms.pop());
    }

}

/*
스택 메소드
https://hbase.tistory.com/122

*/