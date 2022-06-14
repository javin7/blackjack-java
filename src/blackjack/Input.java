package blackjack;

import java.util.*;

class Input {
	Input() {
	}

	public double getBet(double cash) {
	    Scanner n = new Scanner(System.in);
	    int bet = n.nextInt();
	        while (bet > cash) {
	        	System.out.println("You cannot bet more money than you have!");
	        	System.out.println("How much do you wish to bet?");
	            bet = n.nextInt();
	        }
	    return bet;
	}
	
	public boolean choiceIsYes() {
	    Scanner n = new Scanner(System.in);
	    String yes = n.nextLine();
	    while (!isYesOrNo(yes)) {
	    	System.out.println("Please answer yes or no.");
	    	yes = n.nextLine();
	        yes = yes.toLowerCase();
	    }
	    return yes.equals("yes");
	}

	public boolean choiceIsHit() {
	    Scanner n = new Scanner(System.in);
	    String hit = n.nextLine();
	    while(!isHitOrStand(hit)) {
	    	System.out.println("Please answer yes or no.");
	        hit = n.nextLine();
	    }
	    return hit.equals("hit");
	}

	//Check if answer is valid
	public boolean isYesOrNo(String answer) {
	    return (answer.equals("yes") || answer.equals("no"));
	}
	
	public boolean isHitOrStand(String hit) {
	    return (hit.equals("hit") || hit.equals("stand"));
	}

}
