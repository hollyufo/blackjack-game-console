public class Card {
    private Value value;
    private Suit suit;
    // constructor
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    // getter
    public Value getValue() {
        return value;
    }
    // getter
    public Suit getSuit() {
        return suit;
    }
    // method to get the value of the card
    public String toString() {
        return this.suit.toString() + " " + this.value.toString();
    }
}
