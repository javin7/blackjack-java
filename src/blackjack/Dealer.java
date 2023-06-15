package blackjack;
import java.util.*;
class Dealer extends Player {

public void showFirstCard(Dealer dealer) {
    System.out.print("The dealer is showing:");
    Hand hand = dealer.getHand();
    System.out.println(hand.getCard());
}
public boolean wantsToHit() {
    Hand[] aHand = new Hand[]{};
    aHand = hand.toArray(aHand);
    return aHand[0].getHandValue() < 17;
}

public void showHand() {
    System.out.println(hand);
}

public void takeTurn(Deck deck) {
    Hand[] aHand = new Hand[]{};
    aHand = hand.toArray(aHand);
    while (wantsToHit()) {
        System.out.println("The dealer hits.");
        aHand[0].Hit(deck);
        if (hasBusted()) {
            break;
        }
    }
    System.out.println("The dealer stands.");
}

    public boolean hasFiveCardTrick(int handNum) {
        Hand[] tempHand = new Hand[]{};
        tempHand = hand.toArray(tempHand);
        Hand myHand = tempHand[handNum-1];
        return(myHand.getHandSize()==5 && myHand.getHandValue()<21);
    }
}