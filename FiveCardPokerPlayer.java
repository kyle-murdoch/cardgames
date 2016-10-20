/*
Created by kmurdoch @ 1:23pm 10/19/16

The FiveCardPokerPlayer class is a subclass of the 
Player class. It adds the attributes to a player needed to
play a game of Five-Card poker

contents:
FiveCardPokerPlayer
-winnings: int // the caculated winnings based on the hand
-pairCard: int // the value of the card for one pair (used to determine jacks or better)
-----------------------------------
+constructors
+accessors
+setWinnings(value: int, hand: FiveCardPokerHand): void // used to determine how much has been won by the player
*/

package cardgames;
import java.util.*;

public class FiveCardPokerPlayer extends Player { // created by kmurdoch @ 1:38pm 10/19/16
    protected int winnings;
    
    // constructors
    public FiveCardPokerPlayer() { // created by kmurdoch @ 7:23pm 10/19/16
        super();
        this.winnings = 0;
    }
    public FiveCardPokerPlayer(int credits) { // created by kmurdoch @ 7:24pm 10/19/16
        super(credits);
        this.winnings = 0;
    }
    
    // accessors
    public int getWinnings() { // created by kmurdoch @ 7:25pm 10/19/16
        return this.winnings;
    }
    
    // other functions
    public void setWinnings(int value, FiveCardPokerHand hand) { // created by kmurdoch @7:38pm 10/19/16
        int[] numArray = new int[14];
        switch (hand.getValueOfHand()) {
            case 10:
                if (bet == 5) {
                    winnings = 4000;
                }
                else {
                    winnings = bet * 250;
                }
                break;
            case 9:
                winnings = bet * 50;
                break;
            case 8:
                winnings = bet * 25;
                break;
            case 7:
                winnings = bet * 9;
                break;
            case 6:
                winnings = bet * 6;
                break;
            case 5:
                winnings = bet * 4;
                break;
            case 4:
                winnings = bet * 3;
                break;
            case 3:
                winnings = bet * 2;
                break;
            case 2:
                int pairCard = 0;
                for (int i = 0; i < 14; i++) {
                    numArray[(hand.getHand())[i].getNum()]++;                
                }
                for (int i = 0; i < 14; i++) {
                    if (numArray[i] == 2) {
                        pairCard = i;
                    }
                }
                
                if (pairCard >= 11) {
                    winnings = bet;
                }
                else {
                    winnings = 0;
                }
                break;
            case 1:
                winnings = 0;
                break;
            default:
                winnings = 0;
                break;
        }
    }
}