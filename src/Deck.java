import java.util.ArrayList;

public class Deck {
    // constructor
    private ArrayList<Card> cards;
    // creating the constructor
    public Deck() {
        this.cards = new ArrayList<Card>();
    }
    public void createFullDeck() {
        // generate cards
        // for each suit create all 13 values
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                // add a new card to the mix
                this.cards.add(new Card(cardValue, cardSuit));
            }
        }
    }
    public String toString() {
        // build a string
        String cardListOutput = "";
        int i = 0;
        for (Card aCard : this.cards) {
            cardListOutput += "\n" + i + "-" + aCard.toString();
            i++;
        }
        return cardListOutput;
    }
    // shuffle the deck
    public void shuffle() {
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        // randomize the deck
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            // generate random index
            randomCardIndex = (int) (Math.random() * (this.cards.size()));
            // put it in the new deck
            tmpDeck.add(this.cards.get(randomCardIndex));
            // remove from the old deck
            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDeck;
    }
    public void removeCard(int i) {
        this.cards.remove(i);
    }
    public Card getCard(int i) {
        return this.cards.get(i);
    }
    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }
    // to know the deck size and not give a card 2 times
    public int deckSize() {
        return this.cards.size();
    }
    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();
        // put cards in moveTo deck
        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }
        // empty the deck
        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }
    // draw a top card
    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }
    // Return total value of cards in deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;
        // for each card
        for (Card aCard : this.cards) {
            // switch values
            switch (aCard.getValue()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case ACE:
                    aces += 1;
                    break;
            }
        }
        // aces
        for (int i = 0; i < aces; i++) {
            // if ace would put me over 21, count it as 1
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}