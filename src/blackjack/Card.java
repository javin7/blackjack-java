package blackjack;

public class Card {

	private final int rank;
	private final int suit;
	private static String[] ranks = { "Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
	private static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };

	Card(int suit, int values) {
	    this.rank = values;
	    this.suit = suit;
	}

	public String toString() {
	    return ranks[rank] + " of " + suits[suit];
	}

	public int getRank() {
	    return rank;
	}

	public int getSuit() {
	    return suit;
	}

	public int getValue() {
	    int value = 0;
	    if (rank > 10) {
	        value = 10;
	    } else if (rank == 1) {
	        value = 11;
	    } else {
	        value = rank;
	    }
	    return value;
	}
	}