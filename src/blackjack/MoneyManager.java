package blackjack;

//Extending card manager :3
public class MoneyManager extends CardManager{
	private double cash;
	private double bet;
	private String name;

	MoneyManager() {
	}

	//Get methods	
	public double getCash() {
	    return cash;
	}

	public double getBet() {
	    return bet;
	}	

	public String getName() {
	    return name;
	}

	//Set methods
	public void setCash(double money) {
	    cash = money;
	}

	public void setBet(double money) {
	    bet = money;
	}

	public void setName(String username) {
	    name = username;
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
