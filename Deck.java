/*
Created by kmurdoch @ 2:00pm 10/10/16

The Deck class implements a stack using the
card data type. This is used as a deck for the cards.

Deck
-deck: card[52]
-ptr: int // points to the current top card
--------------------
+constructors
+accessors
+resetDeck(): void // set "stack pointer" back to beginning of array
+swapCards(int ind1, int ind2): void // swap the addresses of two cards
+shuffleDeck(): void // sets cards in deck to a random order
+dealCard(): Card // pops the "stack" and returns the next card
+printDeck(): void // for testing purposes
*/
package cardgames;
import java.util.*;

public class Deck { // created by kmurdoch @ 2:15pm 10/10/16
    protected Card[] deck = new Card[52];
    protected int ptr = 0;
    
    // constructors
    public Deck() { // created by kmurdoch @ 2:29pm 10/10/16
        int count = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                deck[count] = new Card(j, i);
                count++;
            }
        }
    }
    
    // accessors
    public Card getCard(int index) { // created by kmurdoch @ 2:33pm 10/10/16
        return deck[index];
    }
    
    // overloaded functions for resetDeck
    public void resetDeck() { // created by kmurdoch @ 2:40pm 10/10/16
        ptr = 0;
    }
    public void resetDeck(int ptr) { // resets the pointer to a specified position
        this.ptr = ptr;
    }
    
    public void swapCards(int ind1, int ind2) { // created by kmurdoch @ 2:45pm 10/10/16
        Card tmp = deck[ind1];
        deck[ind1] = deck[ind2];
        deck[ind2] = tmp;
    }
    
    public void shuffleDeck() { // created by kmurdoch @ 2:56pm 10/10/16
        Random random1 = new Random();
        int rand1 = random1.nextInt(10) + 1;
        
        for (int j = 0; j < rand1; j++) {
            for (int i = 0; i < 52; i++) {
                Random random2 = new Random();
                int rand2 = random2.nextInt(52);

                swapCards(i, rand2);
            }
        }
    }
    
    public Card dealCard() { // created by kmurdoch @ 2:58pm 10/10/16
        ptr++;
        return deck[ptr - 1];
    }
    
    public void printDeck() { // created by kmurdoch @ 9:53pm 10/11/16
        for (int i = 0; i < 52; i++) {
            System.out.println(deck[i].getName());
        }
    }
    public void printRestOfDeck() { // created by kmurdoch @ 2:59pm 10/10/16
        for (int i = ptr; i < 52; i++) {
            System.out.println(deck[i].getName());
        }
    }
}