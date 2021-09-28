import java.util.ArrayList;
import java.util.Collections;

public class Heap {
    public ArrayList<Integer> heapArray = null;

    public Heap (Integer data) {
        heapArray = new ArrayList<Integer>();

        heapArray.add(null); //힙은 1부터 새는게 편하기 때문에 0번째를 Null로
        heapArray.add(data);
    }

    // idx tower example
    //    1
    //   2 3
    // 4 5 6 7
    public boolean move_up(Integer inserted_idx) { //inserted_idx 는 맨 마지막 삽입된 인덱스 this.heapArray.size()-1
        if (inserted_idx <=1) { // 더이상 올라갈 곳이 없는 경우
            return false;
        }
        Integer parent_idx = inserted_idx / 2; // JAVA 에서는 / 연산자로 몫 을 구할 수 있음
        if (this.heapArray.get(inserted_idx) > this.heapArray.get(parent_idx)) { //삽입된 값이 부모값 보다 크면 스왑해야한다
            return true;
        } else {
            return false;
        }
    }

    public boolean insert(Integer data) {
        Integer inserted_idx, parent_idx;

        if (heapArray == null) {
            heapArray = new ArrayList<Integer>();

            heapArray.add(null);
            heapArray.add(data);
            return true;
        }

        this.heapArray.add(data);
        inserted_idx = this.heapArray.size()-1;

        while (this.move_up(inserted_idx)) { //최후의 삽입된 값이 부모노드 값 보다 크면 스왑
            parent_idx = inserted_idx/2;
            Collections.swap(this.heapArray, inserted_idx, parent_idx);
            inserted_idx = parent_idx;
        }
        return true;
    }

    public boolean move_down(Integer popped_idx) {
        Integer left_child_popped_idx, right_child_popped_idx;

        left_child_popped_idx = popped_idx * 2;
        right_child_popped_idx = popped_idx * 2+1;

        // CASE1: 왼쪽 자식 노드도 없을 때
        if (left_child_popped_idx >= this.heapArray.size()) {
            return false;
            // CASE2: 오른쪽 자식 노드만 없을 때
        } else if (right_child_popped_idx >= this.heapArray.size()) {
            if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                return true;
            } else {
                return false;
            }
            // CASE3: 왼쪽, 오른쪽 자식 노드 모두 있을 때
        } else {
            if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public Integer pop() {
        Integer returned_data, popped_idx, left_child_popped_idx, right_child_popped_idx;

        if (this.heapArray.size() <= 1) { // null을 넣어놨기 떄문에 size가 1보다 적거나 같다면 null 밖에 없거나 데이터가 없는경우
            return null;
        }

        returned_data = this.heapArray.get(1); // 맨 위에 값 = 최대값
        this.heapArray.set(1, this.heapArray.get(this.heapArray.size() - 1));
        this.heapArray.remove(this.heapArray.size() - 1);
        popped_idx = 1;

        while (this.move_down(popped_idx)) {
            left_child_popped_idx = popped_idx * 2;
            right_child_popped_idx = popped_idx * 2 + 1;

            // CASE2: 오른쪽 자식 노드만 없을 때
            if (right_child_popped_idx >= this.heapArray.size()) {
                if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) { // 왼쪽 자식 노드가 부모 노드보다 클 때
                    Collections.swap(this.heapArray, popped_idx, left_child_popped_idx); //왼쪽 자식 노드를 위로 (부모가 자식 보다 커야되서)
                    popped_idx = left_child_popped_idx;
                }
            // CASE3: 왼쪽/오른쪽 자식 노드가 모두 있을 때 
            } else {
                if (this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)) { //왼쪽 자식노드가 오른쪽 자식노드 보다 클 때
                    if (this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)) { //왼쪽 자식 노드가 부모 노드보다 클 때
                        Collections.swap(this.heapArray, popped_idx, left_child_popped_idx); //왼쪽 자식 노드를 위로 (부모가 자식 보다 커야되서)
                        popped_idx = left_child_popped_idx;
                    }
                } else { //오른쪽 자식노드가 왼쪽 자식노드 보다 클 때
                    if (this.heapArray.get(popped_idx) < this.heapArray.get(right_child_popped_idx)) {  //오른쪽 자식 노드가 부모 노드보다 클 때
                        Collections.swap(this.heapArray, popped_idx, right_child_popped_idx); //오른쪽 자식 노드를 위로 (부모가 자식 보다 커야되서)
                        popped_idx = right_child_popped_idx;
                    }
                }
            }
        }
        return returned_data;
    }

    public static void main(String[] args) {
        Heap heapTest = new Heap(15);
        heapTest.insert(10);
        heapTest.insert(8);
        heapTest.insert(5);
        heapTest.insert(4);
        heapTest.insert(20);

        System.out.println(heapTest.heapArray);

        heapTest.pop();
        System.out.println(heapTest.heapArray);
    }

}
