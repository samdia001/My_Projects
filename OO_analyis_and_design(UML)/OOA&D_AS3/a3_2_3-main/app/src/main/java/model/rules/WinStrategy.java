package model.rules;

/**
 * This variation could, for example, change who wins on an 
 * equal score (in one implementation the Dealer wins, in 
 * the other the Player).
 */
public interface WinStrategy {

  boolean isTheWinner();
}
