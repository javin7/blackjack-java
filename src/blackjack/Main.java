package blackjack;

import java.util.Scanner;

class Main {
		
	//String cash;

	public static void main(String[] args) {
		//Scanner
		Scanner n = new Scanner(System.in);

		//Classes
		Stats stats = new Stats();
		Input input = new Input();
		User user = new User();
		Dealer dealer = new Dealer();
		Money money = new Money();
		


		//Get name
		System.out.println("Hi! What is your username?");
		String name = n.nextLine();
		user.setUsername(name);
		stats.setPlayerIndex(name);
		stats.checkStats();
		System.out.printf("Hello %s, let's play some blackjack!%n%n", name);
		
		
		if (stats.getWins() != 0 ||  stats.getLoses() != 0) {
			System.out.println("-------------STATISTICS-------------");
			System.out.printf("Your W/L %% is %.2f%%%n", stats.winPercentage());
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
			System.out.println("------------------------------------\n");
		}
		
		//How much cash want to put in?
		System.out.print("How much cash do you want to start with? $");
		String cash = n.nextLine();
		while (!input.isNumber(cash)) {
			System.out.print("Please enter a number! $");
			cash = n.nextLine();
		}
		money.setCash(Double.parseDouble(cash));

		//Run game if cash is over 0
		while (money.getCash() > 0) {

			//Create deck and shuffle it
			Deck deck = new Deck();
			deck.shuffle();

			//Get new hand for dealer and player
			user.dealHand(deck);
			dealer.dealHand(deck);

			System.out.printf("Cash: $%.2f%n", money.getCash());

			//Get bet amount from user and subtract from balance
			System.out.print("How much do you wish to bet? $");
			money.setBet(input.getBet(money.getCash()));
			money.setCash(money.getCash() - money.getBet());

			System.out.printf("%nCash: $%.2f%n", money.getCash());

			System.out.printf("Money on the table: $%.2f%n", money.getBet());

			//Show user hand
			System.out.printf("\nHere is your hand: %s%n%n", user.getHand());
			//System.out.println(user.getHand().getHandValue());

			dealer.showFirstCard(dealer);
			//dealer.showHand();
			//System.out.println(dealer.getHand().getHandValue());

			//Check if dealer has ace
			if (dealer.getHand().getCard(1).getRank() == 1) {
				System.out.println("\nThe dealer is showing an ace! Would you like insurance? (Yes/No)");   

				//Asks if user wants insurance
				if (input.choiceIsYes()) {
					money.insurance();
					//If dealer does have blackjack
					if (dealer.hasBlackJack()) {
						System.out.println("\nThe dealer does have Blackjack, you have won your insurance.");
						money.win();
						System.out.printf("%nCash: $%.2f%n", money.getCash());
						stats.increaseProfit(money.getBet());
					} else { //If dealer does not have blackjack
						System.out.println("\nThe dealer does not have Blackjack, you have lost your insurance");
						System.out.printf("%nCash: $%.2f%n", money.getCash());
						stats.increaseLoss(money.getBet());
					}

				}
			}

			//If both get blackjack
			if (user.hasBlackJack() && dealer.hasBlackJack()) {
				System.out.println("It's a push!");
				System.out.println("You get your money back.");
				money.push();
				stats.updateStats();

				//If user has blackjack
			} else if (user.hasBlackJack()) {
				System.out.println("You have BlackJack!");
				System.out.println("You win 3x your money back!");
				money.blackJack();
				stats.addWin(money.getBet());
				stats.increaseProfit(money.getBet());

				//If dealer has blackjack
			} else if (dealer.hasBlackJack()) {
				System.out.println("The dealer has Blackjack!");
				System.out.println("You lose your money!");
				stats.addLoss(money.getBet());

			} else {
				//Check if user is able to double down
				if (2 * money.getBet() < money.getCash()) {
					System.out.println("\nWould you like to double down?");
					//Ask if user wants to double down
					if (input.choiceIsYes()) {
						money.doubleDown();
						System.out.printf("Cash: $%.2f%n", money.getCash());
						System.out.printf("Money on the table: $%.2f%n", money.getBet());
					}
				}

				//Hit or stand
				System.out.println("Would you like to hit or stand?");
				while (input.choiceIsHit()) {
					user.getHand().Hit(deck);

					//Show user hand
					System.out.println("Here is your hand: ");
					System.out.println(user.getHand());


					//Check if user busted
					if (user.hasBusted()) {
						System.out.println("You busted!");
						stats.addLoss(money.getBet());
						stats.updateStats();
						break;
					}

					//Check if user has a 5 card trick
					if (user.hasFiveCardTrick(1)) {
						System.out.println("You have a five card trick! You have won!");
						stats.addWin(money.getBet());
						money.win();
						stats.updateStats();
						break;
					}
				}


				if(!user.hasBusted()) {
					//The dealer hits
					dealer.takeTurn(deck);

					//Show user hand
					System.out.println("Here is your hand:");
					System.out.println(user.getHand());

					//Show the dealers deck
					System.out.println("Here is the dealer's hand:");
					System.out.println(dealer.getHand());

					//Check if dealer busted
					if (dealer.hasBusted()) {
						System.out.println("The dealer busted! You have won!");
						money.win();
						stats.addLoss(money.getBet());
					} else {
						//If you deck value is more than the dealers
						if ((21 - user.getHand().getHandValue()) < (21 - dealer.getHand().getHandValue())) {
							System.out.println("Congratulations, you win!");
							System.out.println("You win 2x your money back!");
							money.win();
							stats.addWin(money.getBet());
							stats.updateStats();
						}
						//If you deck value is equal to the dealers
						if ((21 - user.getHand().getHandValue()) == (21 - dealer.getHand().getHandValue())) {
							System.out.println("It's a push!");
							System.out.println("You get your money back.");
							money.push();
						}
						//If you deck value is less than the dealers
						if ((21 - user.getHand().getHandValue()) > (21 - dealer.getHand().getHandValue())) {
							System.out.println("You lose!");
							stats.addLoss(money.getBet());
							stats.updateStats();
						}
					}
				}
			}

			System.out.printf("Cash: $%.2f%n", money.getCash());
			System.out.println("Would you like to play again?");

			if (!input.choiceIsYes()) {
				stats.updateStats();
				break;
			}

		}
		if (money.getCash() == 0) {
			System.out.println("You ran out of cash!");
		}
		stats.updateStats();
		System.out.printf("Your cash total is: %.2f", money.getCash());
		System.out.println("\nEnjoy your winnings!");
	}

}
