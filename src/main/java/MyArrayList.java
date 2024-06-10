import java.util.NoSuchElementException;

public class MyArrayList <E> {
    private E a[]; // 리스트의 항목을 저장할 배열
    private int size; // 리스트의 항목 수
    public MyArrayList() { // 생성자
        a = (E[]) new Object[1]; // 최초로 크기가 1인 배열 생성
        size = 0; // 항목 수를 0으로 초기화
    }


    // ArrayList에 접근하는 peek 메서드
    public E peek(int k) { // k번째 항목 반환, 단순히 읽기만 하는 용도
        if (size == 0) {
            throw new NoSuchElementException(); // underflow 경우에 프로그램 정지
        }
        return a[k];
    }
    
    // ArrayList의 크기를 확대/축소 시키는 메서드
    private void resize(int newSize) {  // 배열 크기 조절
        Object[] t = new Object[newSize];   // newSize 크기의 새로운 배열 t 생성
        for (int i = 0; i < size; i++) {    
            t[i] = a[i];                    // 배열 a를 배열 t로 복사
        }
        a = (E[]) t;                        // 배열 t를 배열 a로
        
    }

    /*
    *   왜 resize는 private으로 구현했을까?
    *   내부 배열의 크기를 조정하는 것은 클래스의 내부적인 동작이므로,
    *   외부에서 직접 접근할 필요가 없기 때문에
    */
    

    // ArrayList에 삽입하는 메서드
    // 배열의 마지막 항목 다음에 삽입
    public void insertLast(E newItem) { // 가장 뒤에 새 항목을 삽입
        if (size == a.length) {         // 배열에 빈 공간이 없으면
            resize(2 * a.length);       // 배열의 크기를 2배로 확장
        }
        a[size++] = newItem;
    }

    // 배열의 k번째 항목이 되도록 삽입하는 메소드
    public void insert(E newItem, int k) {  // 새 항목을 k-1번째 항목 다음에 삽입
        if (size == a.length ) {            // 배열에 빈 공간이 없으면
            resize(2 * a.length);           // 배열의 크기를 2배로 확장
        }
        for(int i=size-1; i>=k; i--) { // 한 칸씩 뒤로 이동
            a[i+1] = a[i];
        }
        a[k] = newItem;
        size++;
    }

    // 배열의 k번째 항목 삭제 메소드
    public E delete(int k) {    // k번째 항목 삭제
        if (isEmpty()) {
            throw new NoSuchElementException(); // underflow 시 프로그램 정지
        }
        E item = a[k];
        for (int i = k; i < size; i++) {    // 1칸씩 앞으로 이동
            a[i] = a[i+1];
        }
        size--;
        if ( size > 0 && size == a.length/4) {  // 배열에 항목들이 1/4만 차지하는 경우
            resize(a.length/2);         // 배열을 1/2 크기로 축소
        }
        return item;
    }
    
    // 배열이 비어있는지 확인하는 메서드
    private boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return a.length;
    }
}
