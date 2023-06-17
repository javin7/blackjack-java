package blackjack;

import java.util.*;

/*
Hand
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Hand which stores cards for both dealer and user
*/

class Hand {

	//Create an arraylist of cards
	private ArrayList<Card> hand;
	private int handValue;
	private int aceCounter;
	
	Hand(Deck deck) {
	    hand = new ArrayList<>();
	    aceCounter = 0;
	    handValue = 0;

		//Draw 2 cards when creating a new deck
	    for (int i = 0; i < 2; i++) {
	        hand.add(deck.drawCard());
	    }

		//Get value of deck
	    Card[] tempHand = new Card[]{};
	    tempHand = hand.toArray(tempHand);
		for (Card card : tempHand) {
			handValue += card.getValue();
			if (card.getValue() == 11) {
				aceCounter++;
			}
			while (aceCounter > 0 && handValue > 21) {
				handValue -= 10;
				aceCounter--;
			}
		}
	}
	
	//Draws a card from the deck and places it into hand
	public void Hit(Deck deck) {
		    hand.add(deck.drawCard());
		
		for (Card card : hand) {
		    this.handValue += card.getValue();
		    if (card.getValue() == 11) {
				this.aceCounter++;
		    }
		    while (this.aceCounter > 0 && this.handValue > 21) {
				this.handValue -= 10;
				this.aceCounter--;
		    }
		}
	}
	
	//Return size of current hand
	public int getHandSize() {
	    return hand.size();
	}

	//Return value of current hand
	public int getHandValue() {
	    return handValue;
	}

	//Return the current card
	public Card getCard() {
		return hand.get(0);
	}

}