import enums.CardRanks;
import enums.CardSuits;

import java.util.Random;

public class Deck {
    private Card[] deck;
    public Deck() {
        deck = new Card[52];
        deck = createDeck();
    }

    private Card[] createDeck(){
        int index = 0;
        for(CardSuits suit : CardSuits.values()){
            for(CardRanks rank : CardRanks.values()){
                deck[index] = new Card(rank, suit);
                index++;
            }
        }
        return deck;
    }

    public void shuffle(){
        Random rand = new Random();
        for(int i = 0; i < deck.length; i++){
            int randomIndex = rand.nextInt(deck.length);
            Card tempCard = deck[randomIndex];
            deck[randomIndex] = deck[i];
            deck[i] = tempCard;
        }
    }

    public Card takeCard(){
        Card card = deck[0];
        deck[0] = null;
        for(int i = 1; i < deck.length; i++){
            deck[i-1] = deck[i];
        }
        deck[deck.length-1] = null;
        return card;
    }

    public void returnCard(Card card){
        for (int i = 0; i < deck.length; i++) {
            if(deck[i] == card){
                System.out.println("Эта карта уже есть в колоде!");
                return;
            }

            if (deck[i] == null) {
                deck[i] = card;
                return;
            }
        }
    }

    public void describe(){
        for(Card card : deck){
            System.out.print(card + " ");
        }
        System.out.println();
    }

}
