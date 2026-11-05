import java.util.Random;

public class RPSLS {
    public enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    public static int winner(Move a, Move b) {
        if (a == b) return 0;
        return switch (a) {
            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case PAPER -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case ROCK -> (b == Move.LIZARD || b == Move.SCISSORS) ? 1 : -1;
            case LIZARD -> (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;
            case SPOCK -> (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Rock-Paper-Scissors-Lizard-Spock (5 Rounds) ===");
        Move[] playerMoves = {Move.ROCK, Move.SPOCK, Move.SCISSORS, Move.PAPER, Move.LIZARD};
        Move[] computerMoves = {Move.SCISSORS, Move.ROCK, Move.PAPER, Move.LIZARD, Move.SPOCK};
        int playerScore = 0, compScore = 0;

        for (int i = 0; i < 5; i++) {
            Move player = playerMoves[i];
            Move comp = computerMoves[i];
            int result = winner(player, comp);
            String roundRes;
            if (result == 1) {
                playerScore++;
                roundRes = "Player wins round!";
            } else if (result == -1) {
                compScore++;
                roundRes = "Computer wins round!";
            } else {
                roundRes = "Tie round!";
            }
            System.out.printf("Round %d: Player: %-8s | Computer: %-8s -> %s\n", (i+1), player, comp, roundRes);
        }
        System.out.printf("Summary: You win %d-%d\n", playerScore, compScore);
    }
}
