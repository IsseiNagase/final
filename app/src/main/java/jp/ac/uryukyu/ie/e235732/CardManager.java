package jp.ac.uryukyu.ie.e235732;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 大富豪ゲームのカード管理クラス。
 */
class CardManager {
    private static final int CARD_COUNT = 5;
    private static final int CARD_NUMBER_LIMIT = 9;
    private final ArrayList<Integer> playerHand = new ArrayList<>();
    final ArrayList<Integer> cpuHand = new ArrayList<>();
    private int lastPlayerMove = 0;
    private int lastCpuMove = 0;

    /**
     * カード管理クラスのコンストラクタ。
     */
    public CardManager(boolean startGame) throws IOException {
        if (startGame) {
            startGame();
        } else {
            System.out.println("プログラムを終了します。");
            System.exit(0);
        }
    }

    /**
     * CPUの最終出しを取得するメソッド。
     *
     * @return CPUの最終出し
     */
    public int getLastCpuMove() {
        return lastCpuMove;
    }

    /**
     * CPUの最終出しを設定するメソッド。
     *
     * @param lastCpuMove 設定する最終出し
     * @return 設定された最終出し
     */
    public int setLastCpuMove(int lastCpuMove) {
        this.lastCpuMove = lastCpuMove;
        return lastCpuMove;
    }

    /**
     * プレイヤーの手札を取得するメソッド。
     *
     * @return プレイヤーの手札
     */
    public ArrayList<Integer> getPlayerHand() {
        return playerHand;
    }

    private void displayHand(ArrayList<Integer> hand, String message) {
        System.out.println(message);
        System.out.println(hand);
    }

    /**
     * カードを選択するメソッド。
     *
     * @param hand 手札
     * @param lastMove 最終出し
     * @param player プレイヤーまたはCPUの識別子
     * @throws IOException 入出力例外
     */
    void selectCard(ArrayList<Integer> hand, int lastMove, String player) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        displayHand(getPlayerHand(), "Displaying your hand.");
        System.out.println("\nEnter the number of the card to play (from the left, 1, 2,...). Enter 'P' to pass.");

        while (true) {
            String inputString = br.readLine();

            if (inputString.equalsIgnoreCase("P")) {
                System.out.println("Passed.");
                lastPlayerMove = 0;
                break;
            }

            int selection = Integer.parseInt(inputString);
            if (selection < 1 || selection > hand.size() || hand.get(selection - 1) <= lastMove) {
                System.out.println((selection < 1) ? "Please enter a number greater than 1." : "Cannot play this card.");
            } else {
                lastPlayerMove = hand.remove(selection - 1);
                System.out.println("\nPlayed " + lastPlayerMove + ".");
                break;
            }
        }
        determineWinner(player);
    }

    /**
     * CPUがカードを選択するメソッド。
     */
    void cpuSelectCard() {
        System.out.println("\nCPU's turn.\nSelecting a card......");
        boolean pass = true;

        for (Integer card : cpuHand) {
            if (lastPlayerMove < card) {
                System.out.println("\nCPU played " + setLastCpuMove(card) + ".");
                cpuHand.remove(card);
                pass = false;
                break;
            }
        }

        if (pass) {
            System.out.println("CPU passed.");
            setLastCpuMove(0);
        }

        determineWinner("CPU");
    }

    void determineWinner(String player) {
        if (getPlayerHand().isEmpty() || cpuHand.isEmpty()) {
            System.out.println("Game over!");
            System.out.println(player + " wins.");
            System.exit(0);
        }
    }

    /**
     * ゲームのメインメソッド。
     *
     * @param args コマンドライン引数
     * @throws IOException 入出力例外
     */
    private void startGame() throws IOException {
        Random rd = new Random();
        System.out.println("大富豪ゲームを開始します。");

        // 手札の初期化
        for (int i = 0; i < CARD_COUNT; i++) {
            getPlayerHand().add(rd.nextInt(CARD_NUMBER_LIMIT) + 1);
            cpuHand.add(rd.nextInt(CARD_NUMBER_LIMIT) + 1);
        }

        for (int i = 0; i < 10; i++) {
            selectCard(getPlayerHand(), getLastCpuMove(), "Player");
            cpuSelectCard();
        }
    }

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