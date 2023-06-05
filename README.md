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
* Fill Arraylist<Card> with 52 Cards, 13 Ranks with 4 Suit each
* Shuffle deck function
* Draw a card from the deck function
