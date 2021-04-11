import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DiceGame {
    private class Player {
        boolean computer;
        int score;
        String name;

        Player(boolean computer, String name) {
            this.computer = computer;
            this.name = name;
            score = 0;
        }

        int drop(int count) {
            if (!computer) {
                System.out.println("Press Enter key to drop dice");
                try {
                    System.in.read();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            int diceDropResult = dice.drop(count);
            System.out.println("Player " + name + " drop " + diceDropResult);
            return diceDropResult;
        }
    }

    private class Dice {
        Random random = new Random();

        int drop() {
            return random.nextInt(6);
        }

        int drop(int count) {
            int result = 0;
            for (int i = 0; i < count; i++) {
                result += drop();
            }
            return result;
        }
    }

    Dice dice;
    Player[] players;
    int realPlayersCount;
    int computerPlayersCount;
    int diceCount;
    int winsCount;
    int lastWinner;

    DiceGame(int realPlayersCount, int computerPlayersCount, int diceCount) {
        dice = new Dice();
        this.realPlayersCount = realPlayersCount;
        this.computerPlayersCount = computerPlayersCount;
        this.diceCount = diceCount;
        winsCount = 7;
        players = new Player[realPlayersCount + computerPlayersCount];
        for (int i = 0; i < realPlayersCount; i++) {
            players[i] = (new Player(false, Integer.toString(i)));
        }
        for (int i = 0; i < computerPlayersCount; i++) {
            players[realPlayersCount + i] = (new Player(true, Integer.toString(realPlayersCount + i)));
        }
        lastWinner = -1;
    }

    int playRound() {
        int[] currentScore = new int[realPlayersCount + computerPlayersCount];
        int maxScore = 0;
        int maxPlayer = -1;

        if (lastWinner != -1) {
            currentScore[lastWinner] = players[lastWinner].drop(diceCount);
        }

        for (int i = 0; i < currentScore.length; i++) {
            if (currentScore[i] == 0) {
                currentScore[i] = players[i].drop(diceCount);
            }
            if (currentScore[i] == maxScore) {
                maxPlayer = -1;
            } else if (currentScore[i] > maxScore) {
                maxScore = currentScore[i];
                maxPlayer = i;
            }
        }
        return maxPlayer;
    }

    int playGame() {
        int maxScore = 0;
        int maxPlayer = -1;
        do {
            int resultOfRound = playRound();
            if (resultOfRound != -1) {
                lastWinner = resultOfRound;
                players[lastWinner].score++;
            }

            for (int i = 0; i < players.length; i++) {
                if (players[i].score > maxScore) {
                    maxScore = players[i].score;
                    maxPlayer = i;
                }
            }
            showScoreboard(maxPlayer, maxScore);
        } while(maxScore < winsCount);

        System.out.println("Winner: " + maxPlayer);
        return maxPlayer;
    }

    void showScoreboard(int maxPlayer, int maxScore) {
        System.out.println("Scoreboard:");
        for(int i = 0; i < players.length; i++) {
            System.out.println("Player " + players[i].name + ": " + players[i].score);
        }
        System.out.println("Max player: " + maxPlayer + "\nHigh score: " + maxScore);
    }
}
