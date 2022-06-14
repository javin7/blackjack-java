package blackjack;

class Dealer extends CardManager {

Dealer() {
}

public void showFirstCard(Dealer dealer) {
    System.out.print("The dealer is showing:");
    Hand hand = dealer.getHand(1);
    System.out.println(hand.getCard(1));
}
public boolean wantsToHit() {
    Hand[] aHand = new Hand[]{};
    aHand = hand.toArray(aHand);
    return aHand[0].getHandValue()<17;
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
}