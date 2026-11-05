import java.util.Objects;

public class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public static void main(String[] args) {
        Card[] deck = new Card[10];
        int count = 0;
        Card[] incoming = {
            new Card("King", "Diamonds"),
            new Card("Ace", "Spades"),
            new Card("10", "Hearts"),
            new Card("Ace", "Spades") // duplicate
        };

        for (Card c : incoming) {
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (deck[i].equals(c)) {
                    System.out.println("Duplicate found: " + c);
                    found = true;
                    break;
                }
            }
            if (!found) {
                deck[count++] = c;
                System.out.println("Added: " + c);
            }
        }
    }
}
