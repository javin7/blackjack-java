package blackjack;

import java.util.*;

/*
Deck
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Stores cards in an arraylist which can be drawn from, acting as a deck
*/

class Deck {

	private ArrayList<Card> deck;

	//Create deck of cards as an ArrayList
	Deck() {
	    deck = new ArrayList<Card>();
	    for (int i = 0; i < 4; i++) {
	        for (int j = 1; j <= 13; j++) {
	            deck.add(new Card(i, j));
	        }
	    }
	}

	//Randomly shuffles the deck 200 times
	public void shuffle() {
	    Random random = new Random();
	    Card temp;
	    for (int i = 0; i < 200; i++) {
	        int index1 = random.nextInt(deck.size() - 1);
	        int index2 = random.nextInt(deck.size() - 1);
	        temp = deck.get(index2);
	        deck.set(index2, deck.get(index1));
	        deck.set(index1, temp);
	    }
	}

	//Returns a card from the deck and removes it form the deck
	public Card drawCard() {
	    return deck.remove(0);
	}	
}