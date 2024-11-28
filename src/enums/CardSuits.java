package enums;

public enum CardSuits {
    HEARTS ("\u2665"),
    TILES ("\u2666"),
    CLOVES ("\u2663"),
    PIKES ("\u2660");

    private final String name;
    CardSuits(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
