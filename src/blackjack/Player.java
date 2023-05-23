package blackjack;

import java.util.*;

class Player {

	ArrayList<Hand> hand;

	Player() {
	    hand = new ArrayList<>();
	}
	
	//hasBusted
	public boolean hasBusted() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand); //Check if hand is over 21
	    return (tempHand[0].getHandValue() > 21);
	}
	
	public boolean hasBlackJack(int handNum) {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
	    return (tempHand[handNum-1].getHandSize() == 2 && tempHand[handNum-1].getHandValue() == 21);
	}

	public Hand getHand(int handNum) {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand); //Get 
	    return tempHand[handNum-1];
	}
		
	//If the user has a 5 card trick
	public boolean hasFiveCardTrick(int handNum) {
	    Hand[] tempHand = new Hand[]{};
	    tempHand = hand.toArray(tempHand);
	    Hand myHand = tempHand[handNum-1];
	    return(myHand.getHandSize()==5 && myHand.getHandValue()<21);
	}

	//Create a new hand
	public void dealHand(Deck deck) {
	    hand.clear();
	    Hand myHand = new Hand(deck);
	    hand.add(myHand);
	}
	}
