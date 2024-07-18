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
