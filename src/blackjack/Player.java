package blackjack;

import java.util.*;

/*
Player
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Abstract class for the creation of a player
*/

abstract class Player {

	ArrayList<Hand> hand;

	Player() {
	    hand = new ArrayList<>();
	}

	//Takes a turn
	abstract void takeTurn(Deck deck);

	//Returns if hand value is over 21
	public boolean hasBusted() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
		//System.out.print(Arrays.toString(tempHand));
	    return (tempHand[0].getHandValue() > 21);
	}

	//Returns if hand value is 21
	public boolean hasBlackJack() {
	    Hand[] tempHand = new Hand[] {};
	    tempHand = hand.toArray(tempHand);
	    return (tempHand[0].getHandSize() == 2 && tempHand[0].getHandValue() == 21);
	}

	//Return the hand
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
