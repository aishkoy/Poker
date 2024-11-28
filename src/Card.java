import enums.CardRanks;
import enums.CardSuits;

public class Card {
    protected CardRanks rank;
    protected CardSuits suit;

    public Card(CardRanks rank, CardSuits suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return rank + "" + suit;
    }
}
