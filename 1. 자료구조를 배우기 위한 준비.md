# 수행시간 빅오 그래프 표기 및 이름
![image](https://github.com/user-attachments/assets/23dae375-faa2-47f9-bace-98e29f8005dc)

- O(1) : 상수 시간
- O(logn) : 로그 시간
- O(n) : 선형 시간
- O(nlogn) : 로그 선형 시간 → tree
- O(n²) : 이차 시간 → for문 x for문

# 자바 언어에 대한 기본 지식

## Comparable

Comparable은 java.lang에 선언된 인터페이스로서 객체 하나의 멤버만을 기준으로 객체 비교할 때 사용되며, compareTo 메소드만을 가진다.

```java
public interface Comparable {
	public int compareTo(T other);
}
```

x.compareTo(y)는

- x < y : 음수
- x = y : 0
- x > y : 양수

Comparabale 인터페이스는 같은 클래스의 객체들을 비교할 때 사용되며, 일반적으로 compareTo 메소드를 재정의 하여 두 개의 객체를 비교한다.

String, Date, Integer, Character, Double 등의 Wrapper 클래스에는 이미 Comparabale 인터페이스가 구현되어 있으므로 compareTo 재정의할 필요 없다.

## Comparator

Comparator는 java.util에 선언된 인터페이스로서 한 프로그램 내에서 동일한 타입의 객체들을 여러 개의 멤버를 기준으로 비교 할 수 있으며, compare 메소드를 가진다.

```java
public class Comparator {
	public int compare(T first, T second);
}
```

Comparator 인터페이스는 일반적으로 비교에 기준이 되는 멤버를 위한 별도의 클래스를 선언하여 사용한다.

```java
public class 클래스명 implements Comparator<클래스명> {
	...
	public int compare(클래스명 first, 클래스명 second) {
	...
	}
}
```

## 순환

순환이란 메소드 실행 과정 중 메소드 자신을 호출하는 것이다.

순환 구조를 사용할 때 무한 호출을 방지해야 한다. StackOverFlowError(사용가능한 메모리가 없음)가 발생하게 된다.

순환으로 구현된 메소드는 두 부분으로 구성된다.

1. 기본(Base) case : 스스로를 더 이상 호출하지 않는 부분
2. 순환 case : 스스로를 호출하는 부분

따라서  무한 호출을 방지하기 위해 선언한 변수 또는 수식의 값이 호출이 일어날 때마다 순환 case에서 감소되어 최종적 If문의 조건식에서 기본 case를 실행하도록 제어해야 한다.

```java
public class Factorial {
	public static int factorial(int n) {
		if (n<=1)
			return 1;
		else
			return n * factorial(n-1);
}

public static void main(String[] args) {
	int result;
	resut = factorial(4);
	System.out.println(result);
}
```

팩토리얼은 반복문을 사용하면 순환을 사용한 경우보다 훨씬 빠르게 계산할 수 있다.

반복문을 이용한 계산은 메소드 호출로인한 시스템 스택을 사용하지 않으므로 메모리도 적게 사용한다.

```java
public class Factorial {
	public static void main(String[] args) {
		int n = 4;
		int resut = 1;
		for (int i = 1; i <= n; i++)
			resut = result * i;
		System.out.println(result);
	}
}
```

### 예제 1

**코드 : 섬나라 관광**

```java
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

```

### 예제2 : 쉬어가기_동전의 앞면 수를 동일하게 맞추기

```java
package Recursion;

import java.util.Random;

public class Coin {
    public static void main(String[] args) {
        // 동전 배열 초기화
        int coinCount = 20;
        boolean[] coins = new boolean[coinCount];
        int frontCount = 12;
        Random random = new Random();

        // 앞면 12개, 뒷면 8개 초기화
        for (int i = 0; i < frontCount; i++) {
            coins[i] = true;  // true는 앞면, false는 뒷면
        }
        for (int i = frontCount; i < coinCount; i++) {
            coins[i] = false;
        }

        // 동전을 섞음
        for (int i = 0; i < coinCount; i++) {
            int randomIndex = random.nextInt(coinCount);
            boolean temp = coins[i];
            coins[i] = coins[randomIndex];
            coins[randomIndex] = temp;
        }

        // 동전 배열 초기 상태 출력
        System.out.println("초기 동전 상태 :");
        printCoins(coins);

        // 그룹 나누기
        boolean[] group1 = new boolean[frontCount];
        boolean[] group2 = new boolean[coinCount - frontCount];

        for (int i = 0; i < frontCount; i++) {
            group1[i] = coins[i];
        }
        for (int i = 0; i < (coinCount - frontCount); i++) {
            group2[i] = coins[frontCount + i];
        }

        // 그룹1 뒤집기
        for (int i = 0; i < frontCount; i++) {
            group1[i] = !group1[i];
        }

        // 결과 출력
        System.out.println("\n그룹1 (뒤집기 후) 상태:");
        printCoins(group1);
        System.out.println("그룹2 상태:");
        printCoins(group2);

        // 각 그룹의 앞면 동전 개수 세기
        int group1FrontCount = countFront(group1);
        int group2FrontCount = countFront(group2);

        System.out.println("\n그룹1 앞면 동전 개수: " + group1FrontCount);
        System.out.println("그룹2 앞면 동전 개수: " + group2FrontCount);
    }

    // 동전 배열 출력
    public static void printCoins(boolean[] coins) {
        for (boolean coin : coins) {
            System.out.print(coin ? "앞 " : "뒤 ");
        }
        System.out.println();
    }

    // 앞면 동전 개수 세기
    public static int countFront(boolean[] coins) {
        int count = 0;
        for (boolean coin : coins) {
            if (coin) {
                count++;
            }
        }
        return count;
    }
}

```

일반적으로 동전이 n개 있고, h개가 앞면이 위로 향하고 있다면, 동전을 h개와 n-h 두 그룹으로 나눈 뒤, h개가 있는 그룹의 동전들을 모두 뒤집으면, 각 그룹에 있는 앞면이 위로 향한 동전 수 가 같다.

## 요약

- 자료구조는 일련의 동일한 타입의 데이터를 정돈하여 저장한 구성체
- 추상 데이터 타입은 데이터와 그 데이터에 관련된 추상적인 연산들로서 구성됨
  추상적이란 연산을 구체적으로 어떻게 구현하여야 한다는 상세를 포함하고 있지 않다는 뜻
  자료구조는 추상 데이터 타입을 구체적(실제 프로그램)으로 구현한 것
- 수행 시간은 알고리즘이 수행하는 기본적인 연산 횟수를 입력 크기에 대한 함수로 표현
- 알고리즘의 수행 시간 분석 방법 : 최악의 경우, 평균 경우, 최선 경우 분석, 상각 분석
- 점근 표기법 : 입력 크기가 증가함에 따른 수행시간의 간단한 표기법
    - O : 점근적 상한
- 자바 언어는 객체 지향 프로그래밍 언어로서 클래스를 선언하여 데이터를 객체에 저장하고 메소드를 선언하여 객체들에 대한 연산을 구현
- Comparable 인터페이스는 같은 타입의 객체를 비교하는 데 사용되며,
  compareTo 메소드를 재정의 하여 두 개의 객체를 비교
- Comparator는 프로그램 내에서 동일한 타입의 객체들을 여러 개의 멤버를 기준으로 비교하는 데 사용되며,
  비교 기준이 되는 각 멤버에 대한 새로운 클래스를 선언해야 함
- 순환(Recursion)은 메소드가 스스로를 호출하는 것
- 순환으로 구현된 메소드는 두 부분으로 구성됨
    - 기본 case : 순환 호출하지 않는 부분
    - 순환 case : 스스로를 호출하는 부분
- 무한 호출을 방지하기 위해 선언한 변수 또는 수식의 값이 호출이 일어날 때마다 순환 case에서 감소하여 최종적으로 if문의 조건식에서 기본 case를 실행하도록 제어함
- 꼬리 순환(Tail Recursion) : 메소드의 마지막 부분에서 순환하는 것으로서 호출이 끝난 뒤 메소드에서 수행할 것이 남아있지 않는 경우의 순환 호출을 뜻함.
  꼬리 순환은 최적화되면 반복문으로 변환되어 수행되므로 메모리 사용 측면에서 효율적
- 일반적으로 순환은 프로그램(알고리즘)의 가독성을 높일 수 있다는 장점이 있지만, 시스템 스택을 사용하기 때문에 메모리 사용 측면에서는 비효율적
