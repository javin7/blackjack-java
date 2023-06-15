package blackjack;

import java.util.*;

abstract class Player {

	ArrayList<Hand> hand;

	Player() {
	    hand = new ArrayList<>();
	}

	abstract void takeTurn(Deck deck);
	
	//hasBusted
	public boolean hasBusted() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand); //Check if hand is over 21
		//System.out.print(Arrays.toString(tempHand));
	    return (tempHand[0].getHandValue() > 21);
	}
	
	public boolean hasBlackJack() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
	    return (tempHand[0].getHandSize() == 2 && tempHand[0].getHandValue() == 21);
	}

	public Hand getHand() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
		//System.out.println(this.getHand().getCard().getValue());
		//hand.get or whatever
	    return tempHand[0];
	}

	//Create a new hand
	public void dealHand(Deck deck) {
	    hand.clear();
	    Hand myHand = new Hand(deck);
	    hand.add(myHand);
	}
	}
