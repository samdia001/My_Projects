package model.rules;

import model.Dealer;
import model.Deck;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Deck deck, Dealer dealer, Player player) {

    //player get first card
    dealer.giveCard(player, deck.getCard(), true);

    //dealer get first card
    dealer.giveCard(dealer, deck.getCard(), true);

    //player get second card
    dealer.giveCard(player, deck.getCard(), true);

    return true;
  }
}