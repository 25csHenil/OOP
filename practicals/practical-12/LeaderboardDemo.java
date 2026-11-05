import java.util.*;

public class LeaderboardDemo {
    record Player(String name, int score) {}

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>(List.of(
            new Player("Henil", 950),
            new Player("Riya", 980),
            new Player("Aarav", 850),
            new Player("Kabir", 950)
        ));

        // Sort by score desc, then name asc
        players.sort(Comparator.comparingInt(Player::score).reversed().thenComparing(Player::name));

        System.out.println("=== Leaderboard Standings ===");
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.printf("  Rank %d: %-10s (%d pts)\n", (i+1), p.name(), p.score());
        }
    }
}
