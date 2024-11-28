import enums.CardRanks;
import enums.CardSuits;

public class Combinations {
    private final Card[] hand;

    public Combinations(Card[] cards) {
        this.hand = cards;
    }

    public String findBestCombination(){
        if (isRoyalFlush(hand)) return "Роял Флэш";
        if (isStraightFlush(hand)) return "Стрит Флэш";
        if (isFourOfAKind(hand)) return "Каре";
        if (isFullHouse(hand)) return "Фулл Хаус";
        if (isFlush(hand)) return "Флэш";
        if (isStraight(hand)) return "Стрит";
        if (isThreeOfAKind(hand)) return "Тройка";
        if (isTwoPair(hand)) return "Две пары";
        if (isPair(hand)) return "Одна пара";

        return "Старшая карта: " + getHighestCard(hand);
    }

    private Card getHighestCard(Card[] hand) {
        Card highestCard = hand[0];
        for(Card card  : hand) {
            if(card.rank.ordinal() < highestCard.rank.ordinal()) {
                highestCard = card;
            }
        }
        return highestCard;
    }

    private boolean hasRank(Card[] hand, CardRanks rank) {
        for(Card card : hand) {
            if(card.rank == rank) {
                return true;
            }
        }
        return false;
    }

    private int[] getRanksCount(Card[] hand) {
        int[] rankCounts = new int[CardRanks.values().length];
        for (Card card : hand) {
            rankCounts[card.rank.ordinal()]++;
        }
        return rankCounts;
    }

    private boolean countSameRanks(Card[] hand, int targetCount) {
        int[] rankCounts = getRanksCount(hand);
        for(int count : rankCounts) {
            if(count == targetCount) {
                return true;
            }
        }
        return false;
    }

    private boolean isPair(Card[] hand) {
        return countSameRanks(hand, 2);
    }

    private boolean isTwoPair(Card[] hand) {
        int pairCount = 0;
        int[] ranksCounts = getRanksCount(hand);
        for(int count : ranksCounts) {
            if(count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    private boolean isThreeOfAKind(Card[] hand) {
        return countSameRanks(hand, 3);
    }

    private int countConsecutive(boolean[] seen, int startIndex) {
        int count = 0;
        for (int i = startIndex; i < Math.min(startIndex + 5, seen.length); i++) {
            if (!seen[i]) break;
            count++;
        }
        return count;
    }

    private boolean isStraight(Card[] hand) {
        boolean[] seen = new boolean[CardRanks.values().length];
        for (Card card : hand) {
            seen[card.rank.ordinal()] = true;
        }

        for (int i = 0; i <= CardRanks.values().length - 5; i++) {
            if (countConsecutive(seen, i) == 5) return true;
        }


        return false;
    }

    private boolean isFlush(Card[] hand) {
        CardSuits suit = hand[0].suit;
        for(Card card : hand) {
            if(card.suit != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse(Card[] hand) {
        return countSameRanks(hand, 3) && countSameRanks(hand, 2);
    }

    private boolean isFourOfAKind(Card[] hand) {
        return countSameRanks(hand, 4);
    }

    private boolean isStraightFlush(Card[] hand) {
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isRoyalFlush(Card[] hand) {
        return isFlush(hand) && isStraight(hand) && hasRank(hand, CardRanks.TEN);
    }
}
