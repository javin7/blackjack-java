# Blackjack 

This is a Blackjack game created in Java

## Features
* Login to or create a username profile
* Deposit money
* Draw a card
* Double down
* Stand
* Insurance
* Store profile stats and level

## Classes

###  Blackjack
 * Create user and dealer object
 * Create input, money, and fileio classes

### Card
* Has 2 properties: Rank and Suit
* Rank will be used to determine the value of the card
* Suit will only be used for show and won't have any function
* getValue(): return the value of the current card based on
* toString() to print out Rank and Suit

### Deck
* Arraylist of Cards
* Fill Arraylist with 52 Cards - 13 Ranks with 4 Suit each
* Shuffle deck function
* Draw a card from the deck function

### Hand
* Arraylist of cards
* Has 2 properties: handValue and handSize
* Hit function: Draws a card from the deck and put them into the hand
* getHandSize(): return amount of cards in the hand
* getHandValue(): return total value of all cards in the hand, will be used to determine blackjack, busted, etc...
