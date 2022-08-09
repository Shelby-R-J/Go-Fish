import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GoFish {
    private ArrayList<Card> availableCards = new ArrayList<>();
    private ArrayList<Card> myHand = new ArrayList<>();
    private ArrayList<Card> opponentHand = new ArrayList<>();
    private boolean noCards = false;

    public GoFish() {
        int cardNum = 1; //card number
        int suit = 1; //card suit

        int index = 0; //index of available cards

        for (int j = 0; j < 4; j++) { //loop through suits
            for (int i = 0; i < 13; i++) { //loop through 13 card numbers

                if (cardNum > 13) { //when reach 14, set back to 1
                    cardNum = 1;
                }

                Card currentCard = new Card(suit, cardNum); //new card
                availableCards.add(currentCard);
                index++;

                cardNum++;
            }
            suit++;
        }
    }

    public Card fishForCard(ArrayList<Card> hand) {
        Random generator = new Random();
        int randomIndex = 0;
        if (!availableCards.isEmpty()) {
            randomIndex = generator.nextInt(availableCards.size());
        } else {
            noCards = true;
            return null;
        }

        int numRun = 0;

        while(availableCards.get(randomIndex) == null && numRun < 52) {
            randomIndex = generator.nextInt(availableCards.size());
            numRun++;
        }

        Card newCard = availableCards.get(randomIndex);

        availableCards.remove(availableCards.get(randomIndex));
        hand.add(newCard);
        return newCard;
    }

    public static void main(String[] args) {
        GoFish game = new GoFish();
//        for (int i = 0; i < game.availableCards.size(); i++) {
//            System.out.println(game.availableCards.get(i).toString());
//        }

        for (int i = 0; i < 52; i++) {
            game.fishForCard(game.myHand);
        }

        game.fishForCard(game.myHand);

        System.out.println(game.noCards);

        if (!game.noCards) {
            game.fishForCard(game.myHand);
        } else {
            System.out.println("There are no more cards!");
        }

//        for (int i = 0; i < game.myHand.size(); i++) {
//            System.out.printf(game.myHand.get(i).toString());
//            System.out.println();
//        }

    }
}
