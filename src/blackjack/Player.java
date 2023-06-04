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
	
	public boolean hasBlackJack() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
	    return (tempHand[0].getHandSize() == 2 && tempHand[0].getHandValue() == 21);
	}

	public Hand getHand() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand); //Get
	    return tempHand[0];
	}

	//If the user has a 5 card trick

	//Create a new hand
	public void dealHand(Deck deck) {
	    hand.clear();
	    Hand myHand = new Hand(deck);
	    hand.add(myHand);
	}
	}
