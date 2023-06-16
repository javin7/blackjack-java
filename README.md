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

### Player
* Has 2 hands
* CreatePlayer(): creates new player with a hand
* Checks if player has busted(handValue > 21)
* Checks if player has blackjack(handValue == 21)
* getHand() : returns hand of either user or dealer
* dealHand() : Create new hand for user or dealer

### User extends Player
* Has 2 Properties: username and level
* getUsername(): returns username
* Checks if player has five card trick

### Dealer extends Player
* Dealer is different from user as it is played by an AI instead of the player
* Show one of the two cards in dealers hand
* Checks if dealer wants to hit
* Make dealer either hit or stand

### Money
* Has 2 properties: cash availible and bet amount
* Calculate money after winning
* Calculate money after losing
* Calculate money after push (draw)
* Calculate money after hitting blackjack
* Using double down and insurance

### Database
* Stores player profiles using a username system
* Keeps track of the player index in the file in order to store player data without messing up others.
* Stores: 
  - username
  - level
  - wins
  - loses
  - biggestWin
  - biggestLoss
  - profit
  - loss
* createPlayerProfile(String): Creates a new player profile with all stats set to 0
* deletePlayerProfile(): Deletes current profile that is logged in
* checkStats(): updates all stats into the game
* updateStats(): takes all stats in game and writes them to the appropriate part of the file
* findPlayerIndex(): find the file index of the current player logged in
