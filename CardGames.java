/*
Created by Kyle Murdoch (10/07/16 @ 11:53pm)

Main class file for CardGames
Goal:
This package will contain the necessary elements to build
and execute multiple well known card games.


Things to add:
- log file (logs everything that happens)
*/
package cardgames;
import java.util.*;

public class CardGames { // created 10/07/16 @ 11:56pm by kmurdoch
    public static void main(String args[]) {
        //playVideoPoker();
        //singleHandTest();
        //checkRandomizer();
    }
    
    public static int playVideoPoker() { // created by kmurdoch @ 1:23pm 10/17/16
        Scanner input = new Scanner(System.in);
        Deck deck = new Deck();
        FiveCardPokerPlayer player = new FiveCardPokerPlayer();
        boolean exit = false;
        
        while (!exit) {
            String action = "null";
            boolean playing = false;
            boolean valid = false;
            while (!valid) {
                System.out.println("Which would you like to do: BET, MAX BET, ADD CREDITS?");
                action = input.nextLine();
                action = action.toUpperCase();
                if (action.compareTo("BET") == 0 || action.compareTo("MAX BET") == 0 
                        || action.compareTo("ADD CREDITS") == 0 || action.compareTo("EXIT") == 0) {
                    valid = true;
                }
            }

            if (action.compareTo("ADD CREDITS") == 0) {
                System.out.println("How many credits would you like to add?");
                System.out.print("==> ");
                int numCredits = input.nextInt();
                player.addCredits(numCredits);
            }
            else if (action.compareTo("MAX BET") == 0) {
                if (player.getCredits() >= 5) {
                    player.setBet(5);
                    playing = true;
                }
                else if (player.getCredits() > 0) {
                    player.setBet(player.getCredits());
                    playing = true;
                }
                else {
                    System.out.println("Insufficient credits");
                }
            }
            else if (action.compareTo("BET") == 0) {
                boolean validBet = false;
                while (!validBet) {
                    System.out.print("Enter bet (MAX 5) ==> ");
                    int bet = input.nextInt();
                    if (bet > 0 && bet <= 5 && player.getCredits() >= bet) {
                        player.setBet(bet);
                        validBet = true;
                        playing = true;
                    }
                    else if (player.getCredits() < bet) {
                        System.out.println("Insufficient credits");
                        break;
                    }
                    else {
                        System.out.println("Invalid bet");
                        break;
                    }
                }
            }
            else if (action.compareTo("EXIT") == 0){
                exit = true;
            }
            else {
                return 1;
            }

            if (playing) {
                deck.shuffleDeck();
                FiveCardPokerHand hand = new FiveCardPokerHand(deck);
                hand.setValueOfHand();
                System.out.println("How many cards would you like to hold?");
                hand.printHand();
                System.out.println();
                System.out.println(hand.getValueName());
                
                int numHold = input.nextInt();
                System.out.println("Enter which cards to hold");
                System.out.println("ex. '1 3 4' will hold cards in position 1, 3, and 4");
                int[] hold = new int[6];
                for (int i = 0; i < numHold; i++) {
                    hold[input.nextInt()]++;
                }
                for (int i = 0; i < 5; i++) {
                    if (hold[i + 1] == 0) {
                        hand.setNewCard(i, deck);
                    }
                }
                
                hand.printHand();
                System.out.println();
                System.out.println(hand.getValueName());
            }
        }
        
        return 0;
    } 
    
    public static void checkRandomizer() {
        int[] array = new int[11];
        for (int i = 0; i < 1000000; i++) {
            Deck deck = new Deck();
            deck.shuffleDeck();
            FiveCardPokerHand hand = new FiveCardPokerHand(deck);

            hand.setValueOfHand();
            
            array[hand.getValueOfHand()]++;
        }
        for (int i = 1; i < 11; i++) {
            double percentage = (double) array[i] / 10000;
            System.out.println(array[i] + " " + percentage + "% ==> " + FiveCardPokerHand.getValueNameSpecified(i));
        }
    }
    
    public static void singleHandTest() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        FiveCardPokerHand hand = new FiveCardPokerHand(deck);
        hand.setValueOfHand();
        
        hand.printHand();
        System.out.println();
        System.out.println(hand.getValueName());
    }
}