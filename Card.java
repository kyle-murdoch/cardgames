/*
Created by kmurdoch @ 11:58pm 10/07/16

Contents:
Class file for data on cards
Card
- num: int
- suit: int
-----------------
+constructors
+accessors
+mutators
+createName(): void // sets the name for a specified card
*/
package cardgames;

import java.util.*;

public class Card {
    protected int num; // numbered 1 - 13 (Ace - King)
    protected int suit; // numbered 1 - 4 (Spades, Hearts, Diamonds, Clubs)
    protected String name; // string representing the name of the card
    // if both values are zero (0) card is valued NULL
    
    // constructors
    public Card() { // created by kmurdoch @ 12:06am 10/08/16
        // create NULL card
        this.num = 0;
        this.suit = 0;
        this.name = "NULL";
    }
    public Card(int num, int suit) { // created by kmurdoch @ 12:06am 10/08/16
        if (num <= 13 && num >= 0) {
            this.num = num;    
        }
        else {
            System.out.println("ERROR: not a valid value for number: int");
        }
        
        
        if (suit >= 0 && suit <= 4) {
            this.suit = suit;
        }
        else {
            System.out.println("ERROR: not a valid value for Suit: int");
        }

        this.createName();
    }
    
    // accessors
    public int getNum() { // created by kmurdoch @ 12:11am 10/08/16
        return this.num;
    }
    public int getSuit() { // created by kmurdoch @ 12:11am 10/08/16
        return this.suit;
    }
    public String getName() { // created by kmurdoch @ 7:21pm 10/08/16
        return this.name;
    }
    
    // mutators
    public void setNum(int num) { // created by kmurdoch @ 12:12am 10/08/16
        if (num <= 13 && num >= 1) { // validate card number
            this.num = num;
            this.createName();
        }
        else {
            System.out.println("ERROR: card number set to invalid value");
            System.exit(1);
        }
    }
    public void setSuit(int suit) { // created by kmurdoch @ 12:18am 10/08/16
        if (suit <= 4 && suit >= 1) { // validate suit number
            this.suit = suit;
            this.createName();
        }
        else {
            System.out.println("ERROR: card suit set to invalid value");
            System.exit(1);
        }
    }
    
    // other functions
    public void createName() { // created by kmurdoch @ 7:23pm 10/08/16
        // returns a string representing the physical name of the card
        switch (this.num) {
            case 1:
                this.name = "Ace of ";
                break;
            case 11:
                this.name = "Jack of ";
                break;
            case 12:
                this.name = "Queen of ";
                break;
            case 13:
                this.name = "King of ";
                break;
            case 0:
                this.name = "NULL";
                break;
            default:
                this.name = this.num + " of ";
                break;
        }

        switch (this.suit) {
            case 1:
                this.name = this.name.concat("Spades");
                break;
            case 2:
                this.name = this.name.concat("Hearts");
                break;
            case 3:
                this.name = this.name.concat("Diamonds");
                break;
            case 4: 
                this.name = this.name.concat("Clubs");
                break;
            default:
                break;
        }
    }
}
