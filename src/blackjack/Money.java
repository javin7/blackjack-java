package blackjack;

public class Money {
	private double cash;
	private double bet;

	Money() {
	}

	//Get methods	
	public double getCash() {
		return cash;
	}

	public double getBet() {
		return bet;
	}	


	//Set methods
	public void setCash(double money) {

		cash = money;
	}

	public void setBet(double money) {
	    bet = money;
	}

	
	//Money management
	public void insurance() {
		cash -= bet/2;
	}

	public void doubleDown() {
	    cash -= bet;
	    bet=  2 * bet;
	}

	public void win() {
	    cash=cash+2*bet;
	    
	}
	
	public void push() {
	    cash += bet;
	}
	
	public void blackJack() {
	    cash += (3 * bet);
	    
	}
}
