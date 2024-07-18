package Recursion;

//            출발 H
//          F           S
//      U       E   Z       K
//  N       A                   Y
//              T


public class Travel {
    private Node start;
    public Travel() { start = null; } // 여행 생성자
    public class Node {
        private char name;  // 섬 이름
        private Node left, right;   // 섬 사이의 다리 연결
        public Node( char newIsland, Node lt, Node rt) {    // 섬 생성자
            name = newIsland;
            left = lt;
            right = rt;
        }
    }

    // 지도 만들기
    public Node map() {
        Node n1 = new Node('H', null, null);
        Node n2 = new Node('F', null, null);
        Node n3 = new Node('S', null, null);
        Node n4 = new Node('U', null, null);
        Node n5 = new Node('E', null, null);
        Node n6 = new Node('Z', null, null);
        Node n7 = new Node('K', null, null);
        Node n8 = new Node('N', null, null);
        Node n9 = new Node('A', null, null);
        Node n10 = new Node('Y', null, null);
        Node n11 = new Node('T', null, null);

        n1.left = n2;   n1.right = n3;
        n2.left = n4;   n2.right = n5;
        n3.left = n6;   n3.right = n7;
        n4.left = n8;   n4.right = n9;
        n7.left = n10;  n7.right = n11;

        return n1;  // 시작 섬 반환
    }

    //  A코스 : 섬에 도착하면 항상 도착한 섬을 먼저 관광하고, 그 다음 왼쪽 섬으로 관광 진행.
    //  왼쪽 방향의 모든 섬 방문 후 오른쪽 섬으로 관광 진행
    /*
        내가 생각한
        관광 순서 : H -> F -> U -> N -> E -> A -> T -> S -> Z -> K -> Y
     */
    /*
        실제(정답)
        관광 순서 : H -> F -> U -> N -> E -> A -> T -> S -> Z -> K -> Y
     */
    public void A_Course(Node n) {
        if(n != null) {
            System.out.print(n.name + " -> ");
            A_Course(n.left);
            A_Course(n.right);
        }
    }

    // B코드 : 섬에 도착하면 도착한 섬의 관광을 미루고, 먼저 왼쪽 섬 관광 진행, 왼쪽 섬 모두 관광 후 오른쪽 섬으로 관광 진행
    // 모든 섬 관광 후 미루었던 섬을 관광
    /*
        내가 생각한
        관광 순서 : F -> U -> N -> E -> A -> T -> S -> Z -> K -> Y -> H
     */
    /*
        실제(정답)
        관광 순서 : N -> U -> F -> A -> T -> E -> H -> Z -> S -> K -> Y
     */
    public void B_Course(Node n) {
        if (n != null) {
            B_Course(n.left);
            System.out.print(n.name + " -> ");
            B_Course(n.right);
        }
    }

    // C코스 : 섬에 도착하면 도착한 섬의 관광을 미루고, 먼저 왼쪽 섬으로 관광 진행 후 모두 관광후 오른쪽 섬 관광 진행
    // 모든 섬 관광한 후 돌아와서 관광 미룬 섬 관광 진행
    /*
        내가 생각한
        관광 순서 : N -> U -> F -> H -> A -> E -> T -> Z -> S -> Y -> K
     */
    /*
        실제(정답)
        관광 순서 : N -> U -> T -> A -> E -> F -> Z -> Y -> K -> S -> H
     */
    public void C_Course(Node n) {
        if(n != null) {
            C_Course(n.left);
            C_Course(n.right);
            System.out.print(n.name + " -> ");
        }
    }

    public static void main(String[] args) {
        Travel t = new Travel(); // 여행 객체 t 생성
        t.start = t.map(); // t의 시작 섬을 n1로
        System.out.print("A-코스: "); t.A_Course(t.start);
        System.out.println();
        System.out.print("B-코스: "); t.B_Course(t.start);
        System.out.println();
        System.out.print("C-코스: "); t.C_Course(t.start);
    }
}
