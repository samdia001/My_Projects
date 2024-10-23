package controller;

import model.CardObserver;
import model.Game;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements CardObserver {
  private Game game;
  private View view;

  /**
   * Runs the play use case.

   * @param game The game state.
   * @param view The view to use.
   * @return True as long as the game should continue.
   */
  public boolean play(Game game, View view) {
    this.game = game;
    this.view = view;
    //game.getDealer().addObs(this); 
    //game.getPlayer().addObs(this);
    game.addObserver(this);

    view.displayWelcomeMessage();

    displayHands();

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }
    
    view.getChoice();

    if (view.startGameAction()) {
      game.newGame();
    } else if (view.hitAction()) {
      game.hit();
    } else if (view.standAction()) {
      game.stand();
    } else if (view.quitAction()) {
      return false;
    }

    return true;

  }


  @Override
  public void update() {
    view.showDelay();
    displayHands();
  }

  public void displayHands() {
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
  }


  
}