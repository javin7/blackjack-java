package blackjack;

import java.util.ArrayList;

class User extends Player {
    private String username;
    public void setUsername(String name) {
        this.username = name;
    }
    public String getUsername() {
        return this.username;
    }
    public boolean hasFiveCardTrick(int handNum) {
        Hand[] tempHand = new Hand[]{};
        tempHand = hand.toArray(tempHand);
        Hand myHand = tempHand[handNum-1];
        return(myHand.getHandSize()==5 && myHand.getHandValue()<21);
    }
}
