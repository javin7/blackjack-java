package blackjack;

import java.util.*;

class Input {
	Input() {
	}
	
	//Get bet from user
	public static double getBet(double cash) {
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

	public static void login() {
		Scanner n = new Scanner(System.in);
		System.out.println("Options:");
		System.out.println("    1. Login");
		System.out.println("    2. Delete Current Profile");
		String temp = n.nextLine();

		while (!isNumber(temp)) {
			System.out.print("Please enter a number! $");
			temp = n.nextLine();
		}
		if (Integer.parseInt(temp) == 2) {
			Database.deleteProfile();
		}
	}

	//Check if answer is yes or no
	public static boolean choiceIsYes() {
		Scanner n = new Scanner(System.in);
		String yes = n.nextLine();
		while (!isYesOrNo(yes)) {
			System.out.println("Please answer yes or no.");
			yes = n.nextLine();
		}
		return yes.equalsIgnoreCase("yes");
	}

	//Check if answer is hit or stand
	public static boolean choiceIsHit() {
		Scanner n = new Scanner(System.in);
		String hit = n.nextLine();
		while(!isHitOrStand(hit)) {
			System.out.println("Please answer hit or stand.");
			hit = n.nextLine();
		}
		return hit.equalsIgnoreCase("hit");
	}

	//Check if answer is valid
	public static boolean isYesOrNo(String answer) {
		return (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"));
	}

	//Check if answer is valid
	public static boolean isHitOrStand(String hit) {
		return (hit.equalsIgnoreCase("hit") || hit.equalsIgnoreCase("stand"));
	}
	
	//Check if number is numeric
	public static boolean isNumber(String str) {
		try {  
			Double.parseDouble(str);  
			return true;
		} catch (NumberFormatException e) {  
			return false;
		}  
	}

}
