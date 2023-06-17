package blackjack;

/*
Dealer
Javin Liu
07/06/23
A.Y. Jackson Secondary School
Dealer player that the user plays against
*/


class Dealer extends Player {

    //Show only one of the dealers cards
public void showFirstCard(Dealer dealer) {
    System.out.print("The dealer is showing:");
    Hand hand = dealer.getHand();
    System.out.println(hand.getCard());
}

//Returns if dealer would want to hit
public boolean wantsToHit() {
    Hand[] aHand = new Hand[]{};
    aHand = hand.toArray(aHand);
    return aHand[0].getHandValue() < 17;
}

//Show dealer hand
public void showHand() {
    System.out.println(hand);
}

//Calculate weather or not the dealer wants to hit or stand, then makes a decision
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