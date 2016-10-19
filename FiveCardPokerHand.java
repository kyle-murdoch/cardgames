/*
Created by kmurdoch @ 9:59pm 10/11/16

The FiveCardPokerHand class is a subclass
of the abstract Hand class that contains data attributes and 
functions needed to implement a Video Poker game
in the packages' main function.

Contents:
FiveCardPokerHand
-valueOfHand: int // numerical value representing the poker value of the hand
-valueName: String // string representing the value of a hand (i.e. Three of a kind, two pair)
-numArray: int[] // count cards with like number values
-suitArray: int[] // count cards with like suit values
----------------------
constructors
accessors
+setNumArray(): void // stores number of numerical values represented in the hand
+setSuitArray(): void // stores number of suit values represented in the hand
+setValueOfHand(): void // determines the rank of the hand and stores it as a numerical value
+setValueName(): void // converts valueOfHand to its string equivalent (i.e. One pair, three of a kind, etc.)
+parseArray(int numRow, int[] array):  boolean // returns true if there are a specified number of card numbers or suits in a row (helpful for determining straights)
+parseArraySpecified(int numRow, int start, int[] array): boolean // same as parseArray() but only searches a specified portion of the array
+checkFlush(): boolean: // returns true if all cards in hand are of same suit
+checkPairs(int numInPair): boolean // returns true if there are 'numInPair' of the same card in the hand
+checkTwoPairs(int numInPair): boolean // returns true if there are two pairs in the hand
+printArray(int[] array): void // prints out a specified integer array (for testing purposes)
*/

package cardgames;
import java.util.*;

public class FiveCardPokerHand extends Hand { // created by kmurdoch @ 10:02pm 10/11/16
    protected int valueOfHand;
    protected String valueName;
    protected int[] numArray = new int[14];
    protected int[] suitArray = new int[5];
    
    // constructors
    public FiveCardPokerHand() { // created by kmurdoch @ 10:05pm 10/11/16
        super(5);
        valueOfHand = 0;
    }
    public FiveCardPokerHand(Deck deck) { // created by kmurdoch @ 10:06pm 10/11/16
        super(5, deck);
        setValueOfHand();
    }
    
    // accessors
    public int getValueOfHand() { // created by kmurdoch @ 10:08pm 10/11/16
        setValueOfHand();
        return valueOfHand;
    }
    public String getValueName() {
        setValueOfHand();
        return valueName;
    }
    
    // other functions
    public void setNumArray() { // created by kmurdoch @ 10:41pm 10/11/16
        for (int i = 0; i < 14; i++) {
            numArray[i] = 0;
        }
        for (int i = 0; i < 5; i++) {
            numArray[hand[i].getNum()]++;
        }
    }
    public void setSuitArray() { // created by kmurdoch @ 10:50pm 10/11/16
        for (int i = 0; i < 5; i++) {
            suitArray[i] = 0;
        }        
        for (int i = 0; i < 5; i++) {
            suitArray[hand[i].getSuit()]++;
        }
    }
    
    public void setValueOfHand() { // created by kmurdoch @ 9:22am 10/12/16
        setNumArray();
        setSuitArray();
        if (parseArraySpecified(numCards, 9, numArray) && checkFlush()) { // royal flush
            valueOfHand = 10;
        }
        else if (parseArray(numCards, numArray) && checkFlush()) { // straight flush
            valueOfHand = 9;
        }
        else if (checkPairs(4)) { // four of a kind
            valueOfHand = 8;
        }
        else if (checkPairs(2) && checkPairs(3)) {
            valueOfHand = 7;
        }
        else if (checkFlush()) {
            valueOfHand = 6;
        }
        else if (parseArray(numCards, numArray)) {
            valueOfHand = 5;
        }
        else if (checkPairs(3)) {
            valueOfHand = 4;
        }
        else if (checkTwoPairs(2)) {
            valueOfHand = 3;
        }
        else if (checkPairs(2)) {
            valueOfHand = 2;
        }
        else {
            valueOfHand = 1;
        }
        
        setValueName();
    }
    
    public void setValueName() { // created by kmurdoch @ 12:17pm 10/12/16
        switch (valueOfHand) {
            case 0:
                valueName = "NULL";
                break;
            case 1:
                valueName = "High Card";
                break;
            case 2:
                valueName = "One Pair";
                break;
            case 3:
                valueName = "Two Pair";
                break;
            case 4:
                valueName = "Three of a Kind";
                break;
            case 5:
                valueName = "Straight";
                break;
            case 6:
                valueName = "Flush";
                break;
            case 7:
                valueName = "Full House";
                break;
            case 8:
                valueName = "Four of a Kind";
                break;
            case 9:
                valueName = "Straight Flush";
                break;
            case 10:
                valueName = "Royal Flush";
                break;
        }
    }
    
    public static String getValueNameSpecified(int value) { // created by kmurdoch @ 4:17pm 10/12/16
        String name = null;
        switch (value) {
            case 0:
                name = "NULL";
                break;
            case 1:
                name = "High Card";
                break;
            case 2:
                name = "One Pair";
                break;
            case 3:
                name = "Two Pair";
                break;
            case 4:
                name = "Three of a Kind";
                break;
            case 5:
                name = "Straight";
                break;
            case 6:
                name = "Flush";
                break;
            case 7:
                name = "Full House";
                break;
            case 8:
                name = "Four of a Kind";
                break;
            case 9:
                name = "Straight Flush";
                break;
            case 10:
                name = "Royal Flush";
                break;
        }
        
        return name;
    }
    public boolean parseArray(int numRow, int[] array) { // created by kmurdoch @ 9:03am 10/12/16
        boolean check = false;
        for (int i = 1; i <= 10; i++) {
            for (int j = i; j < i + numRow; j++) {
                int count = j;
                if (count == 14) {
                    count = 1;
                }
                
                if (array[count] != 0) {
                    check = true;
                }
                else {
                    check = false;
                    break;
                }
            }
            if (check) {
                break;
            }
        }
        
        return check;
    }
    
    public boolean parseArraySpecified(int numRow, int start, int[] array) { // created by kmurdoch @ 9:17am 10/12/16
        boolean check = false;
        for (int i = 10; i <= 14; i++) {
            int count = i;
            if (count == 14) {
                count = 1;
            }
            
            if (array[count] != 0) {
                check = true;
            }
            else {
                check = false;
                break;
            }
        }
        return check;
    }
    
    public boolean checkFlush() { // created by kmurdoch @ 9:45am 10/12/16
        boolean check = false;
        for (int i = 1; i <= 4; i++) {
            if (suitArray[i] == numCards) {
                check = true;
                break;
            }
        }
        return check;
    }
    
    public boolean checkPairs(int numInPair) { // created by kmurdoch @ 10:55am 10/12/16
        boolean check = false;
        for (int i = 1; i <= 13; i++) {
            if (numArray[i] == numInPair) {
                check = true;
                break;
            }
        }
        return check;
    }
    
    public boolean checkTwoPairs(int numInPair) { // created by kmurdoch @ 11:07am 10/12/16
        boolean check = false;
        for (int i = 1; i <= 13; i++) {
            if (numArray[i] == numInPair) {
                for (int j = i + 1; j <= 13; j++) {
                    if (numArray[j] == numInPair) {
                        check = true;
                    }
                }
            }
        }
        return check;
    }
    
    // for testing purposes
    public void printArray(int[] array) { // created by kmurdoch @ 10:02am 10/12/16
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
        }
    }
}