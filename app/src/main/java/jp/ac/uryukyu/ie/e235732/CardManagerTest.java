package jp.ac.uryukyu.ie.e235732;


import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class CardManagerTest {

    @Test
    public void testSelectCard() throws IOException {
        // CardManager インスタンスを作成
        CardManager cardManager = new CardManager(false);

        // プレイヤーのためのサンプル手札を作成
        cardManager.getPlayerHand().add(3);
        cardManager.getPlayerHand().add(5);
        cardManager.getPlayerHand().add(8);

        // カードを選択するテスト
        try {
            cardManager.selectCard(cardManager.getPlayerHand(), 0, "Player");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testCpuSelectCard() throws IOException {
        // CardManager インスタンスを作成
        CardManager cardManager = new CardManager(false);

        // CPU のためのサンプル手札を作成
        cardManager.cpuHand.add(2);
        cardManager.cpuHand.add(6);
        cardManager.cpuHand.add(9);

        // CPU がカードを選択するテスト
        try {
            cardManager.cpuSelectCard();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testDetermineWinner() throws IOException {
        // CardManager インスタンスを作成
        CardManager cardManager = new CardManager(false);

        // ゲームの勝者を判定するテスト
        try {
            cardManager.determineWinner("Player");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    private void fail(String string) {
        // TODO #2 #1 Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fail'");
    }
}
