package blackjack;

/*
User
Javin Liu
07/06/23
A.Y. Jackson Secondary School
User that you play as
*/

class User extends Player {
    private String username;
    private int level;
    public void setUsername(String name) {
        this.username = name;
    }
    public String getUsername() {
        return this.username;
    }

    //Takes a turn by hitting
    public void takeTurn(Deck deck) {
        Hand[] aHand = new Hand[]{};
        aHand = hand.toArray(aHand);
        System.out.println("You hit.");
        aHand[0].Hit(deck);
    }

    //Returns if player has 5 cards in his hand
    public boolean hasFiveCardTrick(int handNum) {
        Hand[] tempHand = new Hand[]{};
        tempHand = hand.toArray(tempHand);
        Hand myHand = tempHand[handNum-1];
        return(myHand.getHandSize()==5 && myHand.getHandValue()<21);
    }
}
