package blackjack;

import java.util.*;

class Hand {

	private ArrayList<Card> hand;
	private int handValue;
	private int aceCounter;
	
	Hand(Deck deck) {
	    hand = new ArrayList<>();
	    aceCounter = 0;
	    handValue = 0;
	    
	    for (int i = 0; i < 2; i++) {
	        hand.add(deck.drawCard());
	    }
	    
	    Card[] tempHand = new Card[]{};
	    tempHand = hand.toArray(tempHand);
		for (Card card : tempHand) {
			//Get value of deck
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
	
/*	public boolean hasBlackJack(int handnum) {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
	    return (tempHand[handnum-1].getHandSize() == 2 && tempHand[handnum-1].getHandValue() == 21);
	}
	
	//hasBusted
	public boolean hasBusted() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand); //Check if hand is over 21
	    return (tempHand[0].getHandValue() > 21);
	}	*/
	
	//Hit method
	public void Hit(Deck deck) {
		    hand.add(deck.drawCard());
		
		for (Card card : hand) {
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
	
	//Getters
	public int getHandSize() {
	    return hand.size();
	}

	public int getHandValue() {
	    return handValue;
	}

	public Card getCard(int cardnum) {
		return hand.get(cardnum - 1);
	}

	public String toString(){
	    String hands ="";
	    Card[] tempHand = new Card[]{};
	    tempHand = hand.toArray(tempHand);
	    for(int i=0; i<tempHand.length-1; i++) {
	        hands = hands + tempHand[i].toString() + ", ";
	    }
	    hands = hands + tempHand[tempHand.length-1].toString();
	    return hands;
	}   
}