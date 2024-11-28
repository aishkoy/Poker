public class Hand {
    private final Card[] cards;

    public Hand() {
        cards = new Card[5];
    }

    public void addOneCard(Card card) {
        for(int i = 0; i < cards.length; i++) {
            if(cards[i] == null) {
                cards[i] = card;
                return;
            }
        }
    }

    public Card switchCard(int index, Card card) {
        Card someCard = cards[index-1];
        cards[index-1] = card;
        return someCard;
    }

    public void showHand(){
        println("╔═══════╦═══════╦═══════╦═══════╦═══════╗");
        println("║   1   ║   2   ║   3   ║   4   ║   5   ║");
        println("╠═══════╬═══════╬═══════╬═══════╬═══════╣");
        System.out.printf
                ("║  %-3s  ║  %-3s  ║  %-3s  ║  %-3s  ║  %-3s  ║\n",
                cards[0], cards[1], cards[2], cards[3], cards[4]);
        println("╚═══════╩═══════╩═══════╩═══════╩═══════╝");
    }

    public Card[] getCards(){
        return cards;
    }

    private void println(String s){
        System.out.println(s);
    }
}
