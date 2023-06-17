package blackjack;

/*
Card
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Cards which contain a rank and a value
*/

public class Card {
	//Vars
	private final int rankValue;
	private final int suitValue;
	private static final String[] RANKS = { "Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
	private static final String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };

	Card(int suit, int values) {
	    this.rankValue = values;
	    this.suitValue = suit;
	}

	//Return the rank of the card
	public int getRank() {
	    return rankValue;
	}

	//returns the card's rank and suit as a string
	public String toString() {
		return RANKS[rankValue] + " of " + SUITS[suitValue];
	}

	//Return the value of the current card
	public int getValue() {
	    int value;
	    if (rankValue >  10) {
	        value = 10;
	    } else if (rankValue == 1) {
	        value = 11;
	    } else {
	        value = rankValue;
	    }
	    return value;
	}
	}