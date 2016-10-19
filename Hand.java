/*
Created by kmurdoch @ 2:15pm 10/11/16

The Hand class implements data variables
and functions for use with a generic hand consisting of 
num cards.

Hand
-hand: Card[]
-numCards: int
--------------------
+constructors
+accessors
+mutators
+setNewHand(): void // discard current hand and retrieve new cards
+discardHand(): void // set all cards in hand to null
+discardCard(index: int): void // set a single card to null
+setNewCard(index: int): void // set card to next card in deck
+printHand(): void // call recursive print for testing purposes
+recursivePrint(): void // recursively prints the hand
*/


package cardgames;
import java.util.*;

public abstract class Hand { // created by kmurdoch @ 2:23pm 10/11/16
    protected Card[] hand;
    protected int numCards; // should not need a mutator; in order to modify, make new hand
    
    // constructors
    public Hand() { // created by kmurdoch @ 2:26pm 10/11/16
        numCards = 0;
    }
    public Hand(int numCards) { // created by kmurdoch @ 2:27pm 10/11/16
        this.numCards = numCards;
        hand = new Card[numCards];
    }
    public Hand(int numCards, Deck deck) { // created by kmurdoch @ 2:31pm 10/11/16
        this.numCards = numCards;
        hand = new Card[this.numCards];
        for (int i = 0; i < this.numCards; i++) {
            hand[i] = deck.dealCard();
        }
    }
    
    // accessors
    public Card[] getHand() { // created by kmurdoch @ 2:34pm 10/11/16
        return hand;
    }
    public Card getHandCard(int index) { // created by kmurdoch @ 2:35pm 10/11/16
        return hand[index];
    }
    public int getHandSize() { // created by kmurdoch @ 2:36pm 10/11/16
        return numCards;
    }
    
    // mutators
    public void discardCard(int index) { // created by kmurdoch @ 3:56pm 10/11/16
        // set card to null
        hand[index].setNum(0);
        hand[index].setSuit(0);
    }
    public void setCard(int index, Card card) {
        hand[index] = card;
    }
    
    // other functions
    public void discardHand() { // created by kmurdoch @ 5:26pm 10/11/16
        // set entire hand to null
        for (int i = 0; i < numCards; i++) {
            hand[i].setNum(0);
            hand[i].setNum(0);
        }
    }
    public void setNewCard(int index, Deck deck) { // created by kmurdoch @ 5:29pm 10/11/16
        hand[index] = deck.dealCard();
    }
    public void setNewHand(Deck deck) { // created by kmurdoch @ 5:33pm 10/11/16
        for (int i = 0; i < numCards; i++) {
            hand[i] = deck.dealCard();
        }
    }
    
    public void printHand() { // created by kmurdoch @ 5:35pm 10/11/16
        recursivePrint(numCards);
    }
    public void recursivePrint(int num) {
        if (num > 0) {
            recursivePrint(num - 1);
            System.out.print(hand[num - 1].getName() + " | ");
        }
    }
}