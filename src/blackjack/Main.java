package blackjack;

import java.util.Scanner;

class Main {
		
	String cash;

	public static void main(String[] args) {
		//Scanner
		Scanner n = new Scanner(System.in);

		//Classes
		Stats stats = new Stats();
		Input input = new Input();
		CardManager cardManager = new CardManager();
		Dealer dealer = new Dealer();
		MoneyManager moneyManager = new MoneyManager();
		
		stats.checkStats();

		//Get user name
		System.out.println("Hi! What is your name?");
		String name = n.nextLine();
		System.out.printf("Hello %s, let's play some blackjack!%n%n", name);
		
		
		if (stats.getWins() != 0 && stats.getLoses() != 0) {
			System.out.println("-------------STATISTICS-------------");
			System.out.printf("Your W/L %% is %.2f%%", stats.winPercentage());
			if (stats.getProfit() != 0) {
				System.out.printf("%nYour total profit is $%.2f%n", stats.getProfit());
			}
			
			if (stats.getLoss() != 0) {
				System.out.printf("Your total loss is $%.2f%n", stats.getLoss());
			}
			
			if (stats.getBiggestWin() != 0) {
				System.out.printf("Your biggest win is $%.2f%n", stats.getBiggestWin());
			}
			
			if (stats.getBiggestLoss() != 0) {
				System.out.printf("Your biggest loss is $%.2f%n", stats.getBiggestLoss());
			}
			System.out.println("------------------------------------");
		}
		
		//How much cash want to put in?
		System.out.print("How much cash do you want to start with? $");
		String cash = n.nextLine();
		while (!input.isNumber(cash)) {
			System.out.print("Please enter a number! $");
			cash = n.nextLine();
		}
		moneyManager.setCash(Double.parseDouble(cash));

		//Run game if cash is over 0
		while (moneyManager.getCash() > 0) {

			//Create deck and shuffle it
			Deck deck = new Deck();
			deck.shuffle();

			//Get new hand for dealer and player
			cardManager.dealHand(deck);
			dealer.dealHand(deck);

			System.out.printf("Cash: $%.2f%n", moneyManager.getCash());

			//Get bet amount from user and subtract from balance
			System.out.print("How much do you wish to bet? $");
			moneyManager.setBet(input.getBet(moneyManager.getCash()));
			moneyManager.setCash(moneyManager.getCash() - moneyManager.getBet());

			System.out.printf("%nCash: $%.2f%n", moneyManager.getCash());

			System.out.printf("Money on the table: $%.2f%n", moneyManager.getBet());

			//Show user hand
			System.out.printf("\nHere is your hand: %s%n%n", cardManager.getHand(1));

			dealer.showFirstCard(dealer);

			//Check if dealer has ace
			if (dealer.getHand(1).getCard(1).getRank() == 1) {
				System.out.println("\nThe dealer is showing an ace! Would you like insurance? (Yes/No)");   

				//Asks if user wants insurance
				if (input.choiceIsYes()) {
					moneyManager.insurance();
					//If dealer does have blackjack
					if (dealer.hasBlackJack(1)) {
						System.out.println("\nThe dealer does have Blackjack, you have won your insurance.");
						moneyManager.win();
						System.out.printf("%nCash: $%.2f%n", moneyManager.getCash());
						stats.increaseProfit(moneyManager.getBet());
					} else { //If dealer does not have blackjack
						System.out.println("\nThe dealer does not have Blackjack, you have lost your insurance");
						System.out.printf("%nCash: $%.2f%n", moneyManager.getCash());
						stats.increaseLoss(moneyManager.getBet());
					}

				}
			}

			//If both get blackjack
			if (cardManager.hasBlackJack(1) && dealer.hasBlackJack(1)) {
				System.out.println("It's a push!");
				System.out.println("You get your money back.");
				moneyManager.push();
				stats.updateStats();

				//If user has blackjack
			} else if (cardManager.hasBlackJack(1)) {
				System.out.println("You have BlackJack!");
				System.out.println("You win 3x your money back!");
				moneyManager.blackJack();
				stats.addWin(moneyManager.getBet());
				stats.increaseProfit(moneyManager.getBet());

				//If dealer has blackjack
			} else if (dealer.hasBlackJack(1)) {
				System.out.println("The dealer has Blackjack!");
				System.out.println("You lose your money!");
				stats.addLoss(moneyManager.getBet());

			} else {
				//Check if user is able to double down
				if (2 * moneyManager.getBet() < moneyManager.getCash()) {
					System.out.println("\nWould you like to double down?");
					//Ask if user wants to double down
					if (input.choiceIsYes()) {
						moneyManager.doubleDown();
						System.out.printf("Cash: $%.2f%n", moneyManager.getCash());
						System.out.printf("Money on the table: $%.2f%n", moneyManager.getBet());
					}
				}

				//Hit or stand
				System.out.println("Would you like to hit or stand?");
				while (input.choiceIsHit()) {
					cardManager.getHand(1).Hit(deck);

					//Show user hand
					System.out.println("Here is your hand: ");
					System.out.println(cardManager.getHand(1));

					//Check if user busted
					if (cardManager.hasBusted()) {
						System.out.println("You busted!");
						stats.addLoss(moneyManager.getBet());
						stats.updateStats();
						break;
					}

					//Check if user has a 5 card trick
					if (cardManager.hasFiveCardTrick(1)) {
						System.out.println("You have a five card trick! You have won!");
						stats.addWin(moneyManager.getBet());
						moneyManager.win();
						stats.updateStats();
						break;
					}
				}


				if(!cardManager.hasBusted()) {
					//The dealer hits
					dealer.takeTurn(deck);
					//Show the dealers deck
					System.out.println("Here is the dealer's hand:");
					System.out.println(dealer.getHand(1));

					//Check if dealer busted
					if (dealer.hasBusted()) {
						System.out.println("The dealer busted! You have won!");
						moneyManager.win();
						stats.addLoss(moneyManager.getBet());
					} else {
						//If you deck value is more than the dealers
						if ((21 - cardManager.getHand(1).getHandValue()) < (21 - dealer.getHand(1).getHandValue())) {
							System.out.println("Congratulations, you win!");
							System.out.println("You win 2x your money back!");
							moneyManager.win();
							stats.addWin(moneyManager.getBet());
						}
						//If you deck value is equal to the dealers
						if ((21 - cardManager.getHand(1).getHandValue()) == (21 - dealer.getHand(1).getHandValue())) {
							System.out.println("It's a push!");
							System.out.println("You get your money back.");
							moneyManager.push();
						}
						//If you deck value is less than the dealers
						if ((21 - cardManager.getHand(1).getHandValue()) > (21 - dealer.getHand(1).getHandValue())) {
							System.out.println("You lose!");
							stats.addLoss(moneyManager.getBet());
						}
					}
				}
			}

			System.out.printf("Cash: $%.2f%n", moneyManager.getCash());
			System.out.println("Would you like to play again?");

			if (!input.choiceIsYes()) {
				stats.updateStats();
				break;
			}

		}
		if (moneyManager.getCash() == 0) {
			System.out.println("You ran out of cash!");
		}
		stats.updateStats();
		System.out.printf("Your cash total is: %.2f", moneyManager.getCash());
		System.out.println("\nEnjoy your winnings!");
	}

}
