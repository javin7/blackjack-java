package blackjack;

import java.util.Scanner;

/*
Blackjack
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Organizes game into one
*/
class Blackjack {


    public static void runGame() {
        //Scanner
        Scanner n = new Scanner(System.in);

        //Classes
        User user = new User();
        Dealer dealer = new Dealer();

        //Get name
        System.out.println("Hi! What is your username? (case sensitive)");
        String name = n.nextLine();
        user.setUsername(name);
        //Database.setPlayerIndex(name);
        if (Database.setPlayerIndex(name)) {
            Input.login();
            Database.setPlayerIndex(name);
        }
        Database.sortFileByLevel();
        Database.checkStats();
        System.out.printf("Hello %s, let's play some blackjack!%n%n", name);

        Database.displayStats();

        //How much cash want to put in?
        System.out.print("How much cash do you want to start with? $");
        String cash = n.nextLine();
        while (!Input.isNumber(cash)) {
            System.out.print("Please enter a number! $");
            cash = n.nextLine();
        }
        Money.setCash(Double.parseDouble(cash));
        Database.addLevel(Double.parseDouble(cash)/3);
        //Run game if cash is over 0
        while (Money.getCash() > 0) {

            //Create deck and shuffle it
            Deck deck = new Deck();
            deck.shuffle();

            //Get new hand for dealer and player
            user.dealHand(deck);
            dealer.dealHand(deck);

            System.out.printf("Cash: $%.2f%n", Money.getCash());

            //Get bet amount from user and subtract from balance
            System.out.print("How much do you wish to bet? $");
            Money.setBet(Input.getBet(Money.getCash()));
            Money.setCash(Money.getCash() - Money.getBet());

            System.out.printf("%nCash: $%.2f%n", Money.getCash());

            System.out.printf("Money on the table: $%.2f%n", Money.getBet());

            //Show user hand
            System.out.printf("\nHere is your hand: %s%n%n", user.getHand());
            //System.out.println(user.getHand().getHandValue());

            dealer.showFirstCard(dealer);
            //dealer.showHand();

            //System.out.println(dealer.getHand().getHandValue());

            //Check if dealer has ace
            if (dealer.getHand().getCard().getRank() == 1) {
                System.out.println("\nThe dealer is showing an ace! Would you like insurance? (Yes/No)");

                //Asks if user wants insurance
                if (Input.choiceIsYes()) {
                    Money.insurance();
                    //If dealer does have blackjack
                    if (dealer.hasBlackJack()) {
                        System.out.println("\nThe dealer does have Blackjack, you have won your insurance.");
                        Money.win();
                        System.out.printf("%nCash: $%.2f%n", Money.getCash());
                        Database.increaseProfit(Money.getBet());
                    } else { //If dealer does not have blackjack
                        System.out.println("\nThe dealer does not have Blackjack, you have lost your insurance");
                        System.out.printf("%nCash: $%.2f%n", Money.getCash());
                        Database.increaseLoss(Money.getBet());
                    }

                }
            }

            //If both get blackjack
            if (user.hasBlackJack() && dealer.hasBlackJack()) {
                System.out.println("It's a push!");
                System.out.println("You get your money back.");
                Money.push();
                Database.updateStats();

                //If user has blackjack
            } else if (user.hasBlackJack()) {
                System.out.println("You have BlackJack!");
                System.out.println("You win 3x your money back!");
                Money.blackJack();
                Database.addWin(Money.getBet());
                Database.increaseProfit(Money.getBet());

                //If dealer has blackjack
            } else if (dealer.hasBlackJack()) {
                System.out.println("The dealer has Blackjack!");
                System.out.println("You lose your money!");
                Database.addLoss(Money.getBet());

            } else {
                //Check if user is able to double down
                if (2 * Money.getBet() < Money.getCash()) {
                    System.out.println("\nWould you like to double down?");
                    //Ask if user wants to double down
                    if (Input.choiceIsYes()) {
                        Money.doubleDown();
                        System.out.printf("Cash: $%.2f%n", Money.getCash());
                        System.out.printf("Money on the table: $%.2f%n", Money.getBet());
                    }
                }

                //Hit or stand
                System.out.println("Would you like to hit or stand?");
                while (Input.choiceIsHit()) {
                    user.takeTurn(deck);

                    //Show user hand
                    System.out.println("Here is your hand: ");
                    System.out.println(user.getHand());


                    //Check if user busted
                    if (user.hasBusted()) {
                        System.out.println("You busted!");
                        Database.addLoss(Money.getBet());
                        Database.updateStats();
                        Money.getCashback(Database.getLevel());
                        break;
                    }

                    //Check if user has a 5 card trick
                    if (user.hasFiveCardTrick(1)) {
                        System.out.println("You have a five card trick! You have won!");
                        Database.addWin(Money.getBet());
                        Money.win();
                        Database.updateStats();
                        break;
                    }
                }


                if(!user.hasBusted()) {
                    //The dealer hits
                    dealer.takeTurn(deck);

                    //Show user hand
                    System.out.println("Here is your hand:");
                    System.out.println(user.getHand());
                    //System.out.print(user.getHand().getHandValue());

                    //Show the dealers deck
                    System.out.println("Here is the dealer's hand:");
                    System.out.println(dealer.getHand());
                    //System.out.println(dealer.getHand().getHandValue());

                    //Check if dealer busted
                    if (dealer.hasBusted()) {
                        System.out.println("The dealer busted! You have won!");
                        Money.win();
                        Database.addLoss(Money.getBet());
                    } else {
                        //If you deck value is more than the dealers
                        if ((21 - user.getHand().getHandValue()) < (21 - dealer.getHand().getHandValue())) {
                            System.out.println("Congratulations, you win!");
                            System.out.println("You win 2x your money back!");
                            Money.win();
                            Database.addWin(Money.getBet());
                            Database.updateStats();
                        }
                        //If you deck value is equal to the dealers
                        if ((21 - user.getHand().getHandValue()) == (21 - dealer.getHand().getHandValue())) {
                            System.out.println("It's a push!");
                            System.out.println("You get your money back.");
                            Money.push();
                        }
                        //If you deck value is less than the dealers
                        if ((21 - user.getHand().getHandValue()) > (21 - dealer.getHand().getHandValue())) {
                            System.out.println("You lose!");
                            Database.addLoss(Money.getBet());
                            Database.updateStats();
                            Money.getCashback(Database.getLevel());
                        }
                    }
                }
            }

            System.out.printf("Cash: $%.2f%n", Money.getCash());
            System.out.println("Would you like to play again?");

            if (!Input.choiceIsYes()) {
                Database.updateStats();
                break;
            }

        }
        if (Money.getCash() == 0) {
            System.out.println("You ran out of cash!");
        }
        Database.updateStats();
        System.out.printf("Your cash total is: %.2f", Money.getCash());
        System.out.println("\nEnjoy your winnings!");
        Database.displayByLevel();
    }

}
