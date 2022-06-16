package blackjack;

import java.util.*;

class Input {
	Input() {
	}
	
	//Get bet from user
	public double getBet(double cash) {
		Scanner n = new Scanner(System.in);
		String temp = n.nextLine();

		while (!isNumber(temp)) {
			System.out.print("Please enter a number! $");
			temp = n.nextLine();
		}

		double bet = Double.parseDouble(temp);
		while (bet > cash) {
			System.out.println("You cannot bet more money than you have!");
			System.out.println("How much do you wish to bet?");
			bet = n.nextInt();
		}
		return bet;
	}

	//Check if answer is yes or no
	public boolean choiceIsYes() {
		Scanner n = new Scanner(System.in);
		String yes = n.nextLine();
		while (!isYesOrNo(yes)) {
			System.out.println("Please answer yes or no.");
			yes = n.nextLine();
		}
		return yes.equals("yes");
	}

	//Check if answer is hit or stand
	public boolean choiceIsHit() {
		Scanner n = new Scanner(System.in);
		String hit = n.nextLine();
		while(!isHitOrStand(hit)) {
			System.out.println("Please answer hit or stand.");
			hit = n.nextLine();
		}
		return hit.equals("hit");
	}

	//Check if answer is valid
	public boolean isYesOrNo(String answer) {
		return (answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no"));
	}

	//Check if answer is valid
	public boolean isHitOrStand(String hit) {
		return (hit.toLowerCase().equals("hit") || hit.toLowerCase().equals("stand"));
	}
	
	//Check if number is numeric
	public boolean isNumber(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch (NumberFormatException e) {  
			return false;
		}  
	}

}
