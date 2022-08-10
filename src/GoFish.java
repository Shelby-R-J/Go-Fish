import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GoFish {
    private ArrayList<Card> availableCards = new ArrayList<>();
    private ArrayList<Card> myHand = new ArrayList<>();
    private ArrayList<Card> opponentHand = new ArrayList<>();
    private ArrayList<Integer> myMatches = new ArrayList<>();
    private ArrayList<Integer> opponentMatches = new ArrayList<>();
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

    }

    public void sortHand(ArrayList<Card> hand) {
        // BUBBLE SORT - puts cards in order

        for (int i = 0; i < hand.size() - 1; i++) {

            for (int j = 1; j < hand.size() - i; j++) {
                if (hand.get(j - 1).getNum() > hand.get(j).getNum()) { // swapping
                    Card temp = hand.get(j - 1);
                    hand.set(j - 1, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }

    }

    public String displayHand(ArrayList<Card> hand) {
        // HAND DISPLAY

        String currentCards = "";
        int cardCount = 0;

        for (int i = 0; i < hand.size(); i++) { // printing each card
            if (cardCount == 6) { // line limit
                currentCards += "\n";
                cardCount = 0;
            }

            currentCards += (hand.get(i).toString() + ", ");
            cardCount++;
        }

        currentCards = currentCards.substring(0, currentCards.length() - 2); // removing end comma and space
        return currentCards;
    }

    public String displayMatches(ArrayList<Integer> matches) {
        // HAND DISPLAY

        String currentCards = "\nThere are four of these cards:\n";
        int cardCount = 0;

        for (int i = 0; i < matches.size(); i++) { // printing each card
            if (cardCount == 10) { // line limit
                System.out.println();
                cardCount = 0;
            }

            String match = "";
            if (matches.get(i) == 11) {
                match = "Jack";
            } else if (matches.get(i) == 12) {
                match = "Queen";
            } else if (matches.get(i) == 13) {
                match = "King";
            } else {
                match = matches.get(i).toString();
            }

            currentCards += (matches.get(i) + ", ");
            cardCount++;
        }

        if (cardCount != 0) {
            currentCards = currentCards.substring(0, currentCards.length() - 2); // removing end comma and space
            currentCards += "\n";
            return currentCards;
        } else {
            return ("No matches found.");
        }

    }

    public Card fishForCard(ArrayList<Card> hand) {
        Random generator = new Random();
        int randomIndex = 0;

        if (!availableCards.isEmpty()) {
            randomIndex = generator.nextInt(availableCards.size());
        } else { // is empty, no more cards in deck
            noCards = true;
            return null;
        }

        Card newCard = availableCards.get(randomIndex);

        availableCards.remove(availableCards.get(randomIndex));
        hand.add(newCard);
        return newCard;
    }

    public boolean checkMatches(ArrayList<Card> hand, ArrayList<Integer> matches) {
        boolean anyMatches = false;
        int matchCount = 1;

        for (int i = 0; i < hand.size(); i++) { // each number in hand
            if (hand.size() - i > 3) {
                for (int j = 1; j < 4; j++) { // compares to every number after
                    if (hand.get(i).getNum() == hand.get(i + j).getNum()) {
                        matchCount++;
                    } else {
                        break;
                    }

                    if (matchCount == 4) {
                        matches.add(hand.get(i).getNum());
                        for (int k = 0; k < 4; k++) {
                            hand.remove(i);
                        }
                        i -= 1;
                        anyMatches = true;
                    }
                }

                matchCount = 1;
            }
        }

        return anyMatches;
    }

    public static void main(String[] args) {
        GoFish game = new GoFish();

        Scanner scanner = new Scanner(System.in);
        String response = "";
        int replyNum = 0;

        int dumbCounter = 0;

        System.out.println("Welcome to Go Fish!");

        // GAME START MENU

        do {
            System.out.println("Start game? (y/n)");
            response = scanner.nextLine();

            if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")) { // starting game
                break;

            } else if (response.toLowerCase().equals("n") || response.toLowerCase().equals("no")) { // redo loop
                System.out.println("Okay, take your time. I guess.\n\n\n");

            } else { // invalid input
                if (dumbCounter >= 3) {
                    System.out.println("That's it. You're too dumb to play this game. No Go Fish for you.");
                    return;
                }

                System.out.println("Invalid input, I said (y/n), didn't I?\n");
                dumbCounter++;
            }
        } while (true);

        System.out.println("\nOkay cool, I'll start the game for you now.......\n");

        // CREATING HANDS

        for (int i = 0; i < 26; i++) {
            game.fishForCard(game.myHand);
            game.fishForCard(game.opponentHand);
        }

        // TURN LOOP

        do {
            if (game.myMatches.size() + game.opponentMatches.size() == 13) {
                break;
            }

            String choice = "";

            System.out.println("1) View hand   2) Ask for card   3) View my matches   4) View opponent's matches");
            choice = scanner.nextLine();

            if (choice.equals("1")) {               // VIEW HAND
                if (game.myHand.isEmpty()) {
                    System.out.println("You have no cards.");

                } else {
                    System.out.println("\nThis is your hand:"); // printing hand
                    game.sortHand(game.myHand);
                    System.out.printf(game.displayHand(game.myHand));

                    System.out.println(); // new line

                    boolean anyMatches = game.checkMatches(game.myHand, game.myMatches); // any matches? if so print
                    System.out.println();
                    if (anyMatches) {
                        System.out.println("You have match(es)!\n");
                    } else {
                        System.out.println("no current matches.\n");
                    }

                }
            } else if (choice.equals("2")) {        // ASK FOR CARD
                game.fishForCard(game.myHand);

            } else if (choice.equals("3")) {        // VIEW MY MATCHES
                System.out.println(game.displayMatches(game.myMatches));

            } else if (choice.equals("4")) {        // VIEW OPPONENT MATCHES
                game.sortHand(game.opponentHand);
                game.checkMatches(game.opponentHand, game.opponentMatches); // any matches? if so pring
                System.out.println();
                System.out.println(game.displayMatches(game.opponentMatches));
                System.out.println();

            } else {
                System.out.println("Invalid input.");
            }


        } while (true);

        System.out.println("--------------GAME OVER--------------\n");

        if (game.myMatches.size() > game.opponentMatches.size()) {
            System.out.println("Congratulations, you won!!!");
        } else {
            System.out.println("Sorry, you lost...");
        }



    }
}
