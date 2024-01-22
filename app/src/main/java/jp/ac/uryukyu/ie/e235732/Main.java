
package jp.ac.uryukyu.ie.e235732;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("大富豪ゲームを開始しますか？ Yes or no");
        String userResponse = scanner.nextLine();

        if (userResponse.equalsIgnoreCase("yes")) {
            try {
                System.out.println("ゲームを開始します！");
                new CardManager(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userResponse.equalsIgnoreCase("no")) {
            System.out.println("プログラムを終了します。");
        }

        scanner.close();
    }
}

