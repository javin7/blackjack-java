package blackjack;

public class Card {
	private final int rankValue;
	private final int suitValue;
	private static final String[] RANKS = { "Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
	private static final String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades" };

	Card(int suit, int values) {
	    this.rankValue = values;
	    this.suitValue = suit;
		//System.out.println("rankValue: " + this.getValue() + " suit" + this.getRank());
	}

	public int getRank() {
	    return rankValue;
	}

	public String toString() {
		return RANKS[rankValue] + " of " + SUITS[suitValue];
	}

	public int getValue() {
	    int value;
	    if (rankValue > 10) {
	        value = 10;
	    } else if (rankValue == 1) {
	        value = 11;
	    } else {
	        value = rankValue;
	    }
	    return value;
	}
	}