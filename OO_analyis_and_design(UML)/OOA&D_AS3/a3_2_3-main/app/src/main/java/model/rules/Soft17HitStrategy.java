package model.rules;

import model.Card;
import model.Player;

/**
 * Soft17 = Assume that ace are worth 11. If the current 
 * score reachs 17, subtract 10 points for this ace we have. 
 * This means that Aces value have reduced to 1.
 */

public class Soft17HitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  @Override
  public boolean doHit(Player dealer) {
    
    int tempScore = dealer.calcScore();

    if (tempScore < hitLimit) {
      return true;
    } else if (tempScore == hitLimit) {
      for (Card c : dealer.getHand()) {
        if (c.getValue() == Card.Value.Ace) {
          tempScore = tempScore - 10;
        }
      }
      return true;
    }
    return false;
  }
}
