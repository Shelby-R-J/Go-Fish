public class Card {
    private int suit;
    private int num;

    //Spades -> 1
    //Clubs -> 2
    //Diamonds -> 3
    //Hearts -> 4

    //King -> 13
    //Queen -> 12
    //Jack -> 11

    public Card(int suit, int num) {
        this.suit = suit;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getSuit() {
        return suit;
    }

    public String toString() {
        String suitName = "";
        String cardNum = "";

        if (suit == 1) {
            suitName = "Spades";
        } else if (suit == 2) {
            suitName = "Clubs";
        } else if (suit == 3) {
            suitName = "Diamonds";
        } else {
            suitName = "Hearts";
        }

        if (num == 13) {
            cardNum = "King";
        } else if (num == 12) {
            cardNum = "Queen";
        } else if (num == 11) {
            cardNum = "Jack";
        } else {
            cardNum = Integer.toString(num);
        }

        return String.format("%s of %s", cardNum, suitName);
    }
}
