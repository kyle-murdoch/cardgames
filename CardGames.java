/*
Created by Kyle Murdoch (10/07/16 @ 11:53pm)

Main class file for CardGames
Goal:
This package will contain the necessary elements to build
and execute multiple well known card games.
*/
package cardgames;
import java.util.*;

public class CardGames { // created 10/07/16 @ 11:56pm by kmurdoch
    public static void main(String args[]) {
        playVideoPoker();
        //singleHandTest();
        //checkRandomizer();
    }
    
    public static int playVideoPoker() { // created by kmurdoch @ 1:23pm 10/17/16
        Scanner input = new Scanner(System.in);
        FiveCardPokerPlayer player = new FiveCardPokerPlayer();
        boolean exit = false;
        
        while (!exit) {
            Deck deck = new Deck(); // create deck
            String action = "null";
            boolean playing = false;
            boolean valid = false;
            while (!valid) { // validate input
                System.out.println("What would you like to do: BET, MAX BET, ADD CREDITS?");
                System.out.print("(Credits: " + player.getCredits() + ") ==> ");
                
                action = input.nextLine();
                action = action.toUpperCase(); // change all user input to upper case
                if (action.compareTo("BET") == 0 || action.compareTo("MAX BET") == 0 
                        || action.compareTo("ADD CREDITS") == 0 || action.compareTo("EXIT") == 0) {
                    valid = true;
                }
                else {
                    System.out.println("Try again");
                    System.out.println(); // space
                }
            }

            if (action.compareTo("ADD CREDITS") == 0) {
                System.out.println(); // space
                System.out.println("How many credits would you like to add?");
                System.out.print("(Credits: " + player.getCredits() + ") ==> ");
                if (input.hasNextInt()) { // validate input
                    int numCredits = input.nextInt();
                    if (numCredits > 0) {
                        player.addCredits(numCredits); // set credits
                        String trash = input.nextLine(); // clear line
                        System.out.println(); // space
                    }
                    else {
                        System.out.println("Not a valid number of credits.");
                        String trash = input.nextLine(); // clear line
                        System.out.println(); // space
                    }
                }
                else {
                    System.out.println("INVALID INPUT");
                    String trash = input.nextLine(); // clear line
                    System.out.println(); // space
                }
            }
            else if (action.compareTo("MAX BET") == 0) { // start game of poker with maximum bet of 5
                if (player.getCredits() >= 5) {
                    player.setBet(5);
                    playing = true;
                    System.out.println(); // space
                }
                else if (player.getCredits() > 0) { // if player doesn't have at least 5 credits, set bet to number of credits
                    player.setBet(player.getCredits());
                    playing = true;
                    System.out.println(); // space
                }
                else { // print error if user has no credits
                    playing = false;
                    System.out.println("Insufficient credits");
                    System.out.println(); // space
                }
            }
            else if (action.compareTo("BET") == 0) {
                boolean validBet = false;
                while (!validBet) { // validate input
                    System.out.print("Enter bet (MAX 5) ==> ");
                    if (input.hasNextInt()) {
                        int bet = input.nextInt();
                        if (bet > 0 && bet <= 5 && player.getCredits() >= bet) {
                            player.setBet(bet);
                            validBet = true;
                            playing = true;
                            System.out.println(); // space 
                        }
                        else if (player.getCredits() < bet) {
                            System.out.println("Insufficient credits");
                            String trash = input.nextLine();
                            System.out.println(); // space
                            break;
                        }
                        else {
                            System.out.println("Invalid bet");
                            String trash = input.nextLine();
                            System.out.println(); // space
                            break;
                        }
                    }
                    else {
                        System.out.println("INVALID INPUT");
                        String trash = input.nextLine();
                        System.out.println(); // space
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

            /*
            Once a player starts a game of Video Poker the game must
            be finished. Therefore, there will be no way to exit the 
            game even if an error is thrown.
            */
            if (playing) { 
                deck.shuffleDeck();
                FiveCardPokerHand hand = new FiveCardPokerHand(deck);
                hand.setValueOfHand();
                System.out.println("How many cards would you like to hold?");
                
                hand.printHand();
                System.out.println();
                
                player.setWinnings(hand.getValueOfHand(), hand);
                
                int winnings = player.getWinnings();
                if (winnings != 0) {
                    System.out.println(hand.getValueName());
                }
                else {
                    System.out.println("High Card");
                }
                int numHold = 0;
                System.out.print("==> ");
                
                boolean validIn = false;
                while (!validIn) {
                    try { // try to get a integer from the user
                        numHold = input.nextInt();
                        if (numHold < 0 || numHold > 5) {
                            System.out.println("Enter a value from 0 to 5");
                            System.out.print("==> ");
                            validIn = false;
                            System.out.println(); // space
                        }
                        else {
                            validIn = true;
                            System.out.println(); // space
                        }
                    } // catch exception if next int is not present
                    catch (Exception e) {
                        System.out.println("INVALID INPUT");
                        System.out.println(); // space
                        System.out.print("==> ");
                        String trash = input.nextLine(); // discard harmful data
                        validIn = false;
                    }
                }
                
                if (numHold > 0) {
                    hand.printHand();
                    System.out.println(); // space
                    System.out.println("Enter the cards you want to hold");
                    System.out.println("ex. '1 3 4' will hold cards in position 1, 3, and 4");
                    System.out.print("==> ");
                }
                
                int[] hold = new int[6];
                validIn = false;
                while (!validIn) {
                    try { // used in order to ensure valid integer value
                        for (int i = 0; i < numHold; i++) {
                            int num = input.nextInt();
                            if (num > 0 && num <= 5) {
                                hold[num]++; // determine which cards are being held
                                if (hold[num] > 1) {
                                    throw new Exception(); // throw an exception for any repeat cards
                                }
                                else {
                                    validIn = true;
                                }
                            }
                            else {
                                throw new Exception(); // throw an exception if conditions are not met
                            }
                        }
                        System.out.println(); // space
                    }
                    catch (Exception e) {
                        validIn = false;
                        for (int i = 0; i < 6; i++) { // reset data
                            hold[i] = 0;
                        }
                        String trash = input.nextLine(); // discard harmful data
                        
                        System.out.println("Invalid input");
                        System.out.println(); // space
                        System.out.println("Enter the cards you would like to hold");
                        System.out.print("==> ");
                    }
                }
                String trash = input.nextLine();
                
                for (int i = 0; i < 5; i++) { // replace cards being traded in hand
                    if (hold[i + 1] == 0) {
                        hand.setNewCard(i, deck);
                    }
                }
                
                hand.printHand();
                
                player.setWinnings(hand.getValueOfHand(), hand);
                winnings = player.getWinnings();
                player.addCredits(winnings);
                
                System.out.println();
                
                if (winnings != 0) {
                    System.out.println(hand.getValueName() + " | Winnings: " + winnings);
                    System.out.println(); // space
                }
                else {
                    System.out.println("High Card | Winnings: 0");
                    System.out.println(); // space
                }
            }
        }
        
        return 0;
    } 
    
    public static void checkRandomizer() { // runs 1 Million hands of poker and prints the total values and percentages
        int[] array = new int[11];         // used to test the odds for the randomizer
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
    
    public static void singleHandTest() { // prints a single randomized hand along with it's value
        Deck deck = new Deck();
        deck.shuffleDeck();
        FiveCardPokerHand hand = new FiveCardPokerHand(deck);
        hand.setValueOfHand();
        
        hand.printHand();
        System.out.println();
        System.out.println(hand.getValueName());
    }
}
