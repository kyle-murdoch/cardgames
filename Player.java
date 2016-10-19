/*
Created by kmurdoch @ 1:14pm 10/12/16

The Player class defines an instance of a player of one of the
games on the machine. 

contents:
Player
-credits: int // number of credits for bet
-bet: int // current bet for game
-----------------------------------------
+constructors
+accessors
+mutators
*/

package cardgames;
import java.util.*;

public abstract class Player { // created by kmurdoch @ 7:08pm 10/12/16
    protected int credits, bet;
    
    // constructors
    public Player() { // created by kmurdoch @ 7:20pm 10/12/16
        credits = 0;
        bet = 0;
    }
    public Player(int credits) { // created by kmurdoch @ 7:29pm 10/12/16
        this.credits = credits;
        bet = 0;
    }
    
    // accessors 
    public int getCredits() { // created by kmurdoch @ 8:33am 10/17/16
        return credits;
    }
    public int getBet() { // created by kmurdoch @ 8:34am 10/17/16
        return bet;
    }
    
    // mutators
    public void addCredits(int numCredits) { // created by kmurdoch @ 8:39am 10/17/16
        credits += numCredits;
    }
    public void setBet(int bet) { // created by kmurdoch @ 8:41am 10/17/16
        this.bet = bet;
        this.credits -= bet;
    }
}