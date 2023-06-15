package blackjack;

public class Money {
	private static double cash;
	private static double bet;

	Money() {
	}


	public static void getCashback(int level) {
		if (level >= 200 && level < 400) {
			cash += cash * 0.01;
			System.out.print("You get 1% cashback!");
		} else if (level >= 400 && level < 800) {
			cash += cash * 0.02;
			System.out.print("You get 2% cashback!");
		} else if (level >= 800 && level < 1200) {
			cash += cash * 0.03;
			System.out.print("You get 3% cashback!");
		} else if (level >= 1200 && level < 2000) {
			cash += cash * 0.04;
			System.out.print("You get 4% cashback!");
		}
	}

	//Accessor methods
	public static double getCash() {
		return cash;
	}

	public static double getBet() {
		return bet;
	}	


	//Set methods
	public static void setCash(double money) {

		cash = money;
	}

	static void setBet(double money) {
	    bet = money;
	}

	
	//Money management
	public static void insurance() {
		cash -= bet/2;
	}

	public static void doubleDown() {
	    cash -= bet;
	    bet=  2 * bet;
	}

	public static void win() {
	    cash=cash+2*bet;
	    
	}
	
	public static void push() {
	    cash += bet;
	}
	
	public static void blackJack() {
	    cash += (3 * bet);
	    
	}
}
