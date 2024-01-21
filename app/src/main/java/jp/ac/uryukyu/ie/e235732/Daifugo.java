package jp.ac.uryukyu.ie.e235732;

import java.util.Scanner;

public class Daifugo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("大富豪ゲームを開始しますか？ Yes or no");
        String userResponse = scanner.nextLine();

        if (userResponse.equalsIgnoreCase("yes")) {
            System.out.println("ゲームを開始します！");
            // ここにゲームの実際の処理を追加する部分を記述します。
        } else if (userResponse.equalsIgnoreCase("no")) {
            System.out.println("プログラムを終了します。");
            // ここにプログラムを終了するためのコードを追加します。
        } else {
            System.out.println("プログラムを終了します。");
        }

        scanner.close();
    }
}

