import java.util.Scanner;

// main class
public class Blackjack {
    public static void main(String[] args) {
        // start game
        System.out.println("Welcome to Blackjack!");
        // create a deck of cards
        Deck deck = new Deck();
        deck.createFullDeck();
        deck.shuffle();
        // create a deck for the player
        Deck playerDeck = new Deck();
        // dealer deck
        Deck dealerDeck = new Deck();
        double playerMoney = 200.0;
        // create a scanner
        Scanner userInput = new Scanner(System.in);
        // play the game
        while(playerMoney > 0){
            // take bet

            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You cannot bet more than you have. Game over.");
                break;
            }
            boolean endRound = false;
            // start dealing and giving cards
            playerDeck.draw(deck);
            playerDeck.draw(deck);
            // dealer
            dealerDeck.draw(deck);
            dealerDeck.draw(deck);

            while(true){
                // telling the player their cards
                System.out.println("Your hand: " + playerDeck.toString());
                System.out.println("Your hand is currently valued at: " + playerDeck.cardsValue());

                // telling the dealer their cards
                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [hidden]");

                // player options
                System.out.println("Would you like to -1 Hit or -2 Stand");
                // get input
                int response = userInput.nextInt();
                // if they hit
                if(response == 1){
                    playerDeck.draw(deck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    // bust if they go over 21
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        break;
                    }
                }
                // stand
                if(response == 2){
                    break;
                }
            }
            // reveal dealer cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            // see if dealer has more points than player
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer beats you " + dealerDeck.cardsValue() + " to " + playerDeck.cardsValue());
                playerMoney -= playerBet;
                endRound = true;
            }
            // dealer hits at 16 stands at 17
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(deck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            // display value of dealer
            System.out.println("Dealers hand value: " + dealerDeck.cardsValue());
            // determine if dealer busted
            if((dealerDeck.cardsValue() > 21) && endRound == false){
                System.out.println("Dealer busts. You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            // determine if push
            if((dealerDeck.cardsValue() == playerDeck.cardsValue()) && endRound == false){
                System.out.println("Push.");
                endRound = true;
            }
            // determine if player wins
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win the hand.");
                playerMoney += playerBet;
                endRound = true;
            }
            // empty deck
            playerDeck.moveAllToDeck(deck);
            dealerDeck.moveAllToDeck(deck);
            System.out.println("End of hand.");
        }
        System.out.println("Game over! You lost all your money.");
    }
}