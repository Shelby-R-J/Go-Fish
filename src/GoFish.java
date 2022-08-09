import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GoFish {
    private ArrayList<Card> availableCards = new ArrayList<>();
    private ArrayList<Card> myHand = new ArrayList<>();
    private ArrayList<Card> opponentHand = new ArrayList<>();
    private ArrayList<Card> myMatches = new ArrayList<>();
    private ArrayList<Card> opponentMatches = new ArrayList<>();
    private boolean noCards = false;

    public GoFish() {

        // INITIALIZING CARD SET

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

        // CREATING HANDS

        for (int i = 0; i < 7; i++) {
            fishForCard(myHand);
            fishForCard(opponentHand);
        }

    }

    public String displayHand(ArrayList<Card> hand) {

        // BUBBLE SORT - puts cards in order

        for (int i = 0; i < hand.size() - 1; i++) {

            for (int j = 1; j < hand.size() - i; j++) {
                if (hand.get(j - 1).getNum() > hand.get(j).getNum()) {
                    Card temp = hand.get(j - 1);
                    hand.set(j - 1, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }

        // HAND DISPLAY

        String currentCards = "";
        int cardCount = 0;

        for (int i = 0; i < hand.size(); i++) {
            if (cardCount == 6) {
                System.out.println();
                cardCount = 0;
            }

            currentCards += (hand.get(i).toString() + ", ");
            cardCount++;
        }

        currentCards = currentCards.substring(0, currentCards.length() - 2);
        return currentCards;
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

    public void checkMatches(ArrayList<Card> hand) {
        int matchCount = 0;

        for (int i = 0; i < myHand.size(); i++) {
            if (matchCount == 4);
            //if ()
        }
    }

    public static void main(String[] args) {
        GoFish game = new GoFish();

        Scanner scanner = new Scanner(System.in);
        String response = "";
        int replyNum = 0;

        int dumbCounter = 0;

        System.out.println("Welcome to Go Fish!");

        do {
            System.out.println("Start game? (y/n)");
            response = scanner.nextLine();

            if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")) {
                break;
            } else if (response.toLowerCase().equals("n") || response.toLowerCase().equals("no")) {
                System.out.println("Okay, take your time. I guess.\n\n\n");
            } else {

                if (dumbCounter >= 3) {
                    System.out.println("That's it. You're too dumb to play this game. No Go Fish for you.");
                    return;
                }

                System.out.println("Invalid input, I said (y/n), didn't I?\n");
                dumbCounter++;
            }
        } while (true);

        System.out.println("\nOkay cool, I'll start the game for you now.......");

        do {

            if (game.myHand.isEmpty()) {
                System.out.println("You have no cards!");
            } else {
                System.out.println("This is your hand:");
                System.out.printf(game.displayHand(game.myHand));
            }
            break;



        } while (true);



    }
}
