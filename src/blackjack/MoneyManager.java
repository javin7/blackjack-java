package blackjack;

import java.util.*;

//Extending card manager :3
public class MoneyManager extends CardManager{
	
	public double scorekeeping[][] = new double[2][2];
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
	
	public void addProfitMoney(double money) {
		scorekeeping[1][1] += money;
	}
	
	public void addLossMoney(double money) {
		scorekeeping[1][2] -= money;
	}
	
	public void addWin() {
		scorekeeping[2][1]++;
	}
	
	public void addLoss() {
		scorekeeping[2][2]--;
	}
	
	//Money management
	public void insurance() {
		cash -= bet/2;
	}

	public void doubleDown() {
	    cash -= bet;
	    bet=  2 * bet;
	    scorekeeping[1][2] -= bet;
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
