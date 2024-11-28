import java.util.Scanner;

public class Game {
    private static final Scanner sc = new Scanner(System.in);
    private final Deck deck;
    private final Hand hand;

    public Game() {
        deck = new Deck();
        hand = new Hand();
    }

    public void run() {
        deck.shuffle();
        dealCards();
        playRound();
    }

    private void playRound(){
        hand.showHand();
        if(areCardsChanged()){
            hand.showHand();
        }
        evaluateHand();
    }

    private boolean areCardsChanged(){
        System.out.println("""
                
                Введите номера карт, которые вы хотите заменить через пробел:
                (Введите "Нет", если не хотите ничего заменять)""");

        int[] cardsToSwap = getValidCardsNumber();

        if(cardsToSwap.length == 0){
            System.out.println("Карты не были заменены");
            return false;
        }

        swapCards(cardsToSwap);
        return true;
    }

    private int[] getValidCardsNumber(){
        while (true) {
            String input = sc.nextLine().strip();
            if (input.equalsIgnoreCase("нет")) {
                return new int[0];
            }

            String[] indexesStr = input.split(" ");

            if (indexesStr.length > 5) {
                System.out.println("Максимально можно ввести только 5 цифр. Попробуйте снова.");
                continue;
            }

            boolean allNumbersValid = true;
            int[] indexes = new int[indexesStr.length];

            for (int i = 0; i < indexesStr.length; i++) {
                String str = indexesStr[i];
                if (!str.matches("[1-5]")) {
                    allNumbersValid = false;
                    break;
                }

                int num = Integer.parseInt(str);
                indexes[i] = num;
            }

            for (int i = 0; i < indexes.length; i++) {
                for (int j = i + 1; j < indexes.length; j++) {
                    if (indexes[i] == indexes[j]) {
                        allNumbersValid = false;
                        break;
                    }
                }
                if (!allNumbersValid) {
                    break;
                }
            }

            if (allNumbersValid) {
                return indexes;
            } else {
                System.out.println("Некорректный ввод. Убедитесь, что цифры уникальны и в пределах от 1 до 5.");
            }
        }
    }

    private void swapCards(int[] cardsIndexes){
        for (int index : cardsIndexes){
            deck.returnCard(hand.switchCard(index, deck.takeCard()));
        }
    }


    private void dealCards() {
        System.out.println("Ваши карты: ");
        for (int i = 0; i < hand.getCards().length; i++) {
            hand.addOneCard(deck.takeCard());
        }
    }

    private void evaluateHand() {
        Combinations combination = new Combinations(hand.getCards());
        System.out.println("Ваша лучшая комбинация: " + combination.findBestCombination());
    }
}
